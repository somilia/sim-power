package model;

import model.EnergySources;

public class RenewableEnergy extends EnergySources
{
	public void genElectricity(){}
	
	public RenewableEnergy(Box baseBox){
		super(baseBox);
		this.type = BuildingType.RENEWABLE;
	}
}
