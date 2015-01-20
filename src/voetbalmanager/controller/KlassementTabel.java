package voetbalmanager.controller;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 * @author Mayke
 * Klasse voor het klassements tabel
 */
public class KlassementTabel {
	
	//Rangnummer declaren?
	private final SimpleStringProperty naam;
	private final SimpleIntegerProperty puntentotaal;
	private final SimpleIntegerProperty gewonnen;
	private final SimpleIntegerProperty gelijk;
	private final SimpleIntegerProperty verloren;
	private final SimpleIntegerProperty doelsaldo;
	private final SimpleIntegerProperty tegendoel;
	
	private KlassementTabel(String na, int pt, int win, int gel, int ver, int doel, int tegen){
		this.naam = new SimpleStringProperty(na);
		this.puntentotaal = new SimpleIntegerProperty(pt);
		this.gewonnen = new SimpleIntegerProperty(win);
		this.gelijk = new SimpleIntegerProperty(gel);
		this.verloren = new SimpleIntegerProperty(ver);
		this.doelsaldo = new SimpleIntegerProperty(doel);
		this.tegendoel = new SimpleIntegerProperty(tegen);
	}
	
	public String getNaam(){
		return naam.get();
	}
	
	public void setNaam(String na){
		naam.set(na);
	}
	
	public int getTotaal(){
		return puntentotaal.get();
	}
	
	public void setTotaal(int pt){
		puntentotaal.set(pt);
	}
	
	public int getWin(){
		return gewonnen.get();
	}
	
	public void setWin(int win){
		gewonnen.set(win);
	}
	
	public int getGel(){
		return gelijk.get();
	}
	
	public void setGel(int gel){
		gelijk.set(gel);
	}
	
	public int getVer(){
		return verloren.get();
	}
	
	public void setVer(int ver){
		verloren.set(ver);
	}
	
	public int getSaldo(){
		return doelsaldo.get();
	}
	
	public void setSaldo(int doel){
		doelsaldo.set(doel);
	}
	
	public int getTegen(){
		return tegendoel.get();
	}
	
	public void setTegen(int tegen){
		tegendoel.set(tegen);
	}
}
