package com;

public class Updates {
	private String aid,occured;
	private int smokeLevel,co2Level,isActive,isWorking;
	
	public Updates() {
		super();
	}

	public Updates(String aid, String occured, int smokeLevel, int co2Level, int isActive, int isWorking) {
		super();
		this.aid = aid;
		this.occured = occured;
		this.smokeLevel = smokeLevel;
		this.co2Level = co2Level;
		this.isActive = isActive;
		this.isWorking = isWorking;
	}

	public String getAid() {
		return aid;
	}

	public void setAid(String aid) {
		this.aid = aid;
	}

	public String getOccured() {
		return occured;
	}

	public void setOccured(String occured) {
		this.occured = occured;
	}

	public int getSmokeLevel() {
		return smokeLevel;
	}

	public void setSmokeLevel(int smokeLevel) {
		this.smokeLevel = smokeLevel;
	}

	public int getCo2Level() {
		return co2Level;
	}

	public void setCo2Level(int co2Level) {
		this.co2Level = co2Level;
	}

	public int getIsActive() {
		return isActive;
	}

	public void setIsActive(int isActive) {
		this.isActive = isActive;
	}

	public int getIsWorking() {
		return isWorking;
	}

	public void setIsWorking(int isWorking) {
		this.isWorking = isWorking;
	}

	@Override
	public String toString() {
		return "Updates [aid=" + aid + ", occured=" + occured + ", smokeLevel=" + smokeLevel + ", co2Level=" + co2Level
				+ ", isActive=" + isActive + ", isWorking=" + isWorking + "]";
	}
	
}
