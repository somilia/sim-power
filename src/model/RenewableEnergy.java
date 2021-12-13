package model;

import model.EnergySources;

public abstract class RenewableEnergy extends EnergySources
{
	public abstract double genElectricity();
	
	public RenewableEnergy(Box baseBox){
		super(baseBox);
		this.type = BuildingType.RENEWABLE;
	}
}
