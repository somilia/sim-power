package model;

import patterns.Observable;
import patterns.InformationObserver;
import patterns.HomeObserver;

import java.util.ArrayList;
import java.util.Random;

/**
 * Cette classe est la classe principale du Model.
 * Elle est composée d'Objets de type Box qui sont eux même composés d'objets de type EnergySources ou Home.
 * Cette classe contient toutes les méthodes qui permettent de calculer et mettre à jour les données du jeu.
 * On implémente l'interface Observable qui permet de transmettre et de notifier la vue à chaque mise à jour des données du jeu
 * On implémente l'interface Runnable pour pouvoir mettre à jour les données periodiquement dans un thread indépendant
 */
public class Map extends Observable implements Runnable{

    public static final int NB_BOX_X = 32;//< comme les Box sont des carrés on choisit ce nombre de Box pour un format 16:9
    public static final int NB_BOX_Y = 18;
    public static final int BOUNDS_X= NB_BOX_X /4;//< Limite de coordonnées pour le départ de la rivière
    public static final int BOUNDS_Y= NB_BOX_X /4;

    public static final long UPDATE_BOX_DATA_FREQUENCY = 5*1000;//< fréquence à laquelle les attribut (sun, wind, water) sont mis à jour
    public static final long UPDATE_GLOBAL_DATA = 5*1000;//< fréquence de mise à jour des données globales (population, energyProduced, pollutionRate ...)
    public static final long UPDATE_POPULATION_AVAILABLE_FREQUENCY = 30*1000;//< fréquence de mise à jour de la population disponible

    public static final int STARTING_AMOUNT = 10000000;//< montant d'argent de départ pour le joueur
    public static final int STARTING_POPULATION_AVAILABLE = 5;//< population disponible de départ


    private int population;//< population de la Map : nombre d'habitants
    private int populationAvailable;//< nombre d'habitants disponibles s'il y a assez de place dans les habitations, augmente + energyPrice et + pollutionRate sont faibles
    private int populationMax;//< sommes des capacités maximales de chaque habitations construite
    private double energyProduced;//< somme totale de l'énergie produite à un instant t
    private double energyPrice;//< prix de l'énergie : ratio entre energyProduced et population
    private double pollutionRate;///< le taux de pollution correspond à la proportion d'énergie venant energies fossiles
    private int userMoney;//< argent du joueur

    private double nbOfFossilEnergy;//< nombre de bâtiments héritant de FossilEnergy
    private double nbOfRenewableEnergy;//< nombre de bâtiments héritant de RenewableEnergy

    private Box[][] boxList;//< liste des Box qui composent la Map
    private int[][] buildingMoneyAmount;//< Tableaux qui stock le montant généré par chaque habitation tant qu'il n'a pas encore été récolté

    private volatile boolean isRunning;//< volatile pour que la valeur soit toujours à jour, au cas ou un autre thread voudrait interrompre celui-ci

    public Map() {

        population = 0;
        populationAvailable = STARTING_POPULATION_AVAILABLE;
        populationMax=0;
        energyProduced = 0.0;
        energyPrice = 0.0;
        pollutionRate = 0.0;
        userMoney = STARTING_AMOUNT;
        nbOfFossilEnergy=0.0;
        nbOfRenewableEnergy = 0.0;

        buildingMoneyAmount = new int[NB_BOX_X][NB_BOX_Y];

        for(int i=0;i<NB_BOX_X;i++){
            for(int j=0; j<NB_BOX_Y;j++){
                buildingMoneyAmount[i][j]=0;
            }
        }


        boxList = new Box[NB_BOX_X][NB_BOX_Y];
        Random xr_gas = new Random();
        int x_gas = xr_gas.nextInt(9 - 2 + 1) + 2;   //r.nextInt((max - min) + 1) + min;
        Random yr_gas = new Random();
        int y_gas = yr_gas.nextInt(16- 2 + 1) + 2;

        Random xr_coal = new Random();
        int x_coal = xr_coal.nextInt(19 - 10 + 1) + 10;
        Random yr_coal = new Random();
        int y_coal = yr_coal.nextInt(16- 2 + 1) + 2;

        Random xr_uranium = new Random();
        int x_uranium = xr_uranium.nextInt(30 - 20 + 1) + 20;
        Random yr_uranium = new Random();
        int y_uranium = yr_uranium.nextInt(16- 2 + 1) + 2;


        for(int i=0;i<NB_BOX_X;i++){
            for(int j=0;j<NB_BOX_Y;j++){
                boxList[i][j] = new Box(NB_BOX_Y-j, 1+j, 0, randomFossilGen(i,j,x_gas,y_gas), randomFossilGen(i,j,x_coal,y_coal), randomFossilGen(i,j,x_uranium,y_uranium)); //public Box(double wind, double sun, double water, boolean gas, boolean coal, boolean uranium){}
            }
        }

        randomStartRiverGeneration();

        isRunning=true;

        this.informationObserverList = new ArrayList<>();
        this.homeObserverList = new ArrayList<>();

    }

    /**
     * Notifie et envoie les données de la Map comme population, userMoney, energyProduced ... à la Vue
     */
    @Override
    public void notifyInformationObservers() {
        for (InformationObserver obs: informationObserverList) {
            obs.update(population, populationAvailable, populationMax, energyProduced, energyPrice, pollutionRate, userMoney);
        }
    }

    /**
     * Notifie et envoie les mise à jour des habitations concernant la récolte d'argent à la Vue
     * @param x abscisse de la case du bâtiment concerné
     * @param y ordonnées de la case du bâtiment concerné
     */
    @Override
    public void notifyHomeObservers(int x, int y) {
        for(HomeObserver obs : homeObserverList){
            obs.update(x,y);
        }
    }

    /**
     * Ajoute ou non de la population disponible selon pollutionRate et energyPrice
     */
    private void updatePopulationAvailable(){
        if(pollutionRate<0.75 && energyPrice < 0.75 && energyPrice>0){
            double newPopulationAvailable = 1/(energyPrice*pollutionRate*4);
            if(newPopulationAvailable>5){
                populationAvailable+=5;
            }
            else{
                populationAvailable += (int) newPopulationAvailable;
            }
        }
    }

    /**
     * Permet de mettre a jour la population
     * Si il y a de la population disponible, on parcours les Home de la Map et si elles ne sont pas remplies, on les remplis jusqu'à ce qu'elles soient pleines ou qu'il n'y est plus de population disponible
     */
    public void updatePopulation(){

        for(int i=0; i<NB_BOX_X && population<populationMax && populationAvailable>0;i++){
            for(int j=0;population<populationMax && populationAvailable>0 && j<NB_BOX_Y; j++){

                if (boxList[i][j].getContainHome()){
                    int freeSpace = boxList[i][j].getHome().getMaxNbOfHabitants() - boxList[i][j].getHome().getNbOfHabitants();
                    if(freeSpace>0){
                        if(populationAvailable>freeSpace){
                            boxList[i][j].getHome().addHabitants(freeSpace);
                            populationAvailable-= freeSpace;
                            population+=freeSpace;
                        }else{
                            boxList[i][j].getHome().addHabitants(populationAvailable);
                            population+=populationAvailable;
                            populationAvailable=0;
                        }
                    }
                }
            }
        }
    }

    /**
     * Met à jour energyPrice en fonction du ration population/energyProduced
     */
    private void updateEnergyPrice(){
        if(energyProduced==0.0)
            this.energyPrice = 0.0;
        else
            this.energyPrice = (population/(energyProduced))%2;
    }

    /**
     * Met à jour pollutionRate en fonction de la proportion de nbOfFossilEnergy par rapport au nombre total d'EnergySources
     */
    private void updatePollutionRate(){
        if(nbOfFossilEnergy==0 && nbOfRenewableEnergy==0)
            this.pollutionRate = 0.0;
        else
            this.pollutionRate = nbOfFossilEnergy/(nbOfFossilEnergy+nbOfRenewableEnergy);
    }

    /**
     *On parcours la liste des Box et si une Box possède une habitation, on test si le délai de collect d'argent est dépassé (temps_actuel - dernière_mise_à_jour >= fréquence_de_collecte)  et si oui, on notifie la vue.
     */
    private void updateMoneyCollect(){
        int i=0, j=0;
        for (Box[] array :boxList) {
            for (Box box:array) {
                if (box.getContainHome()){
                    if(System.currentTimeMillis()-box.getHome().getLastMoneyCollect() >= box.getHome().getMoneyFrequency() && box.getHome().getNbOfHabitants()>0){
                        notifyHomeObservers(i,j);
                        box.getHome().setLastMoneyCollect(System.currentTimeMillis());
                        buildingMoneyAmount[i][j]+=box.getHome().genMoney();//On ajoute au montant total qu'il est possible de récolter avec ce bâtiment, la valeur de la génération d'argent
                    }
                }
                j++;
            }
            j=0;
            i++;
        }
    }

    /**
     * Permet d'ajouter au portefeuille du joueur le montant total généré par l'habtitation
     * @param x abscisse de l'habitation
     * @param y ordonnées de l'habitation
     */
    public void addMoney(int x, int y){
        this.userMoney += buildingMoneyAmount[x][y];
        buildingMoneyAmount[x][y]=0;
        notifyInformationObservers();

    }

    /**
     * Met à jour l'énergie totale produite à l'instant t
     * On parcours la liste de Box et on fait la somme des énergies produites
     */
    private void updateEnergyProduced(){

        double sumEnergyProduced=0;

        for (Box[] array :boxList) {
            for (Box box:array) {
                if(box.getContainEnergySource()){
                    box.getEnergySource().genElectricity();
                    sumEnergyProduced+=box.getEnergySource().getElectricityProduced();
                }
            }
        }
        this.energyProduced = sumEnergyProduced;
    }


    /**
     *Vrai si l'utilisateur à assez d'argent pour acheter le type de Building en paramètre , Faux sinon
     * @param type : type du bâtiment
     * @return résultat
     */
    public boolean hasEnoughMoney(BuildingType type){
        return type.price <= userMoney;
    }

    /**
     * Renvoie Vrai si la case est vide, Faux si la Box contient un bâtiment
     * @param posX abscisse de la Box
     * @param posY ordonnées de la Box
     * @return  résultat
     */
    public boolean boxIsEmpty(int posX, int posY){

        boolean isEmpty = false;
        try{
            isEmpty = !boxList[posX][posY].getContainHome() || !boxList[posX][posY].getContainEnergySource();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return isEmpty;
    }

    /**
     * Permet d'ajouter un bâtiment à la Map et de mettre à jour les données potentiellement modifiées à cause de ce nouveau bâtiment
     * @param posX abscisse de la Box sur laquelle sera le bâtiment
     * @param posY ordonnées de la Box sur laquelle sera le bâtiment
     * @param type : type du bâtiment construit
     */
    public void buildBuilding(int posX, int posY, BuildingType type) {
        boxList[posX][posY].addBuilding(type);
        userMoney-= type.price;

        if(type == BuildingType.HOUSE || type==BuildingType.APPARTEMENT){
            populationMax += boxList[posX][posY].getHome().getMaxNbOfHabitants();
            updatePopulation();
            updateEnergyPrice();
            notifyInformationObservers();
        }
        else if(type==BuildingType.COAL || type==BuildingType.GAS || type==BuildingType.NUCLEAR ){
            this.nbOfFossilEnergy++;
            updatePollutionRate();
            notifyInformationObservers();
        }
        else if(type== BuildingType.SOLAR || type==BuildingType.WIND || type==BuildingType.WATER){
            this.nbOfRenewableEnergy++;
            updatePollutionRate();
            notifyInformationObservers();
        }
    }

    /**
     * Appel les méthodes qui génère de nouvelles valeur pour les caractéristiques dynamiques de chaque case
     */
    public void updateBoxData() {
        for (Box[] array :boxList) {
            for (Box box:array) {
                box.randomWindGen();
                box.randomWaterGen();
                box.randomSunGen();
            }
        }
    }

    /**
     * Appel toutes les méthodes qui permettent de mettre à jour les données globales
     */
    private void updateGlobalData(){

        updatePopulation();
        updateEnergyProduced();
        updateEnergyPrice();
        updatePollutionRate();
    }


    /**
     * Thread qui permet de mettre à jour en continue les différentes données de la Map
     */
    @Override
    public void run() {

        long boxLastUpdate=System.currentTimeMillis();
        long populationAvailableLastUpdate=System.currentTimeMillis();
        long globalDataLastUpdate=System.currentTimeMillis();

        while(isRunning){

            if(System.currentTimeMillis()-boxLastUpdate >= UPDATE_BOX_DATA_FREQUENCY) {
                updateBoxData();
                notifyInformationObservers();
                boxLastUpdate=System.currentTimeMillis();
            }
            updateMoneyCollect();
            if(System.currentTimeMillis()-populationAvailableLastUpdate>= UPDATE_POPULATION_AVAILABLE_FREQUENCY) {
                updatePopulationAvailable();
                notifyInformationObservers();
                populationAvailableLastUpdate=System.currentTimeMillis();
            }
            if(System.currentTimeMillis()-globalDataLastUpdate >= UPDATE_GLOBAL_DATA) {
                updateGlobalData();
                notifyInformationObservers();
                globalDataLastUpdate=System.currentTimeMillis();
            }

            try {
                Thread.sleep(100);//Pour réduire l'utilisation du CPU :)
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Selon le type de bâtiment, retourne Vrai si on peut construire un bâtiment sur la Box, Faux sinon
     * @param positionX abscisse de la Box
     * @param positionY ordonnées de la Box
     * @param buildingType type du bâtiment
     * @return résultat
     */
    public boolean canBuildOnBox(int positionX, int positionY, BuildingType buildingType){

        return switch (buildingType) {
            case SOLAR, WIND,HOUSE,APPARTEMENT -> boxIsEmpty(positionX, positionY);
            case WATER -> boxIsEmpty(positionX,positionY) && boxList[positionX][positionY].getWater()>0;
            case COAL -> boxIsEmpty(positionX, positionY) && boxList[positionX][positionY].hasCoal();
            case GAS -> boxIsEmpty(positionX, positionY) && boxList[positionX][positionY].hasGas();
            case NUCLEAR -> boxIsEmpty(positionX, positionY) && boxList[positionX][positionY].hasUranium();
            default -> throw new IllegalArgumentException("BuildingType not found");
        };
    }

    public boolean randomFossilGen(int i, int j, int x, int y) {
        return ((i + 2 == x) || (i - 2 == x) || (i + 1 == x) || (i - 1 == x) || (i == x)) && ((j + 2 == y) || (j - 2 == y) || (j + 1 == y) || (j - 1 == y) || (j == y)) && !(((i + 2 == x) && (j + 2 == y) || (i - 2 == x) && (j - 2 == y) || (i + 2 == x) && (j - 2 == y) || (i - 2 == x) && (j + 2 == y)));
    }

    /**
     * Permet de générer le point de départ de notre rivière aléatoirement
     */
    private void randomStartRiverGeneration(){
        int startingPosX;
        int startingPosY;

        Random r = new Random();
        int a = r.nextInt(4);

        // les 4 cas différents correspondent au 4 frontières de la Map
        // Pour 2 cas on génère l'abscisse aléatoirement et l'ordonnées = soit 0 soit NB_BOX_Y
        // Pour les 2 autres cas c'est l'inverse, l'ordonnées et générée aléatoirement et l'abscisse vaut soir 0 soit NB_BOX_X
        // Ensuite on appelle la fonction randomRiverGeneration avec les bonnes probabilités selon le côté de départ
        if(a==0){
            Random s0 = new Random();
            startingPosX= s0.nextInt(NB_BOX_X-BOUNDS_X*2)+BOUNDS_X-1;
            for(startingPosY=0;startingPosY<5;startingPosY++){
                boxList[startingPosX][startingPosY].setWater(1.0);
            }

            randomRiverGeneration(0.5, 0.8, startingPosX, startingPosY);
        }
        else if(a==1){
            Random s1 = new Random();
            startingPosX= s1.nextInt(NB_BOX_X-BOUNDS_X*2)+BOUNDS_X-1;
            for(startingPosY=NB_BOX_Y-1;startingPosY>NB_BOX_Y-5;startingPosY--){
                boxList[startingPosX][startingPosY].setWater(1.0);
            }

            randomRiverGeneration(0.5, 0.2, startingPosX, startingPosY);
        }
        else if(a==2){
            Random s2 = new Random();
            startingPosY= s2.nextInt(NB_BOX_Y-BOUNDS_Y*2)+BOUNDS_Y-1;
            for(startingPosX=NB_BOX_X-1;startingPosX>NB_BOX_X-5;startingPosX--){
                boxList[startingPosX][startingPosY].setWater(1.0);
            }

            randomRiverGeneration(0.2, 0.5, startingPosX, startingPosY);
        }
        else {
            Random s3 = new Random();
            int b3 = s3.nextInt(NB_BOX_Y-BOUNDS_Y*2)+BOUNDS_Y-1;
            startingPosY=b3;
            for(startingPosX=0;startingPosX<5;startingPosX++){
                boxList[startingPosX][startingPosY].setWater(1.0);
            }

            randomRiverGeneration(0.8, 0.5, startingPosX, startingPosY);


        }
    }

    /**
     * Fonction récursive qui ajoute de l'eau à une Box et avance ou recule en X ou en Y aléatoirement, puis ajoute de l'eau à cette nouvelle Box, et ainsi de suite ....
     * @param probXPlus probabilité d'avancer selon l'axe des abscisses
     * @param probYPlus probabilité d'avancer selon l'axe des ordonnées
     * @param x précédente Box
     * @param y précédente Box
     */
    private void randomRiverGeneration(double probXPlus, double probYPlus, int x, int y){
        if(x>=0 && x<NB_BOX_X && y>=0 && y<NB_BOX_Y){
            boxList[x][y].setWater(1.0);

            Random rand0 = new Random();
            double a = rand0.nextDouble();
            if(a<0.5) {

                Random rand1 = new Random();
                if (rand1.nextDouble() < probXPlus)
                    x++;
                else
                    x--;
            }
            else{

                Random rand2 = new Random();
                if(rand2.nextDouble()<probYPlus)
                    y++;
                else
                    y--;
            }
            // permet de rajouter une branche à la rivière mais dans notre cas elle devient vraiment grande
            /*if(a<=(double)1/NB_BOX_X){
                randomRiverGeneration(probXPlus, probYPlus, ++x, ++y);
            }*/
            randomRiverGeneration(probXPlus, probYPlus, x, y);
        }
    }

    public Box[][] getBoxList() { return boxList; }


}
