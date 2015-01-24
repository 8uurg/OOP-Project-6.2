package voetbalmanager.controller;

import javafx.beans.property.SimpleStringProperty;

public class SchemaTabel {

	
	private final SimpleStringProperty team;
	private final SimpleStringProperty tegenteam;
	
	private SchemaTabel(String te, String tt){
		this.team = new SimpleStringProperty(te);
		this.tegenteam = new SimpleStringProperty(tt);
	}
	
	public String getTteam(){
		return team.get();
	}
	
	public void setTteam(String te){
		team.set(te);
	}
	
	public String getTegenteam(){
		return tegenteam.get();
	}
	
	public void setTegenteam(String tt){
		tegenteam.set(tt);
	}
}
