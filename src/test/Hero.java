package test;

import monde.Coordonnees;

public class Hero {

	Coordonnees c;
	String nom;
	
	public Hero(String nom){
		this.nom = nom;
	}
	
	public void setCoordonnees(int x, int y){
		c = new Coordonnees(x, y);
	}
	
	public void setCoordonnees(Coordonnees c){
		this.c = c;
	}
	
	public Coordonnees getCoordonnees(){
		return c;
	}
	
}
