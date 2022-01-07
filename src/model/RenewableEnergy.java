package model;

import model.EnergySources;

public abstract class RenewableEnergy extends EnergySources
{

	
	public RenewableEnergy(Box baseBox){
		super(baseBox);
		this.type = BuildingType.RENEWABLE;
	}

	/**
	 * simule la production d'énergie en fonction des caractéristiques dynamiques (sun, wind, water) de baseBox
	 */
	public abstract void genElectricity();
}
