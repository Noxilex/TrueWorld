package personnages;

import java.awt.Dimension;
import java.awt.Graphics;
import java.util.Random;

import javax.swing.JPanel;

import monde.Coordonnees;

/**
 * 
 * @author Noxilex
 *
 */
public abstract class Personnage{
	String nom;
	String prenom;
	int age;
	int poids;
	int taille;
	Metier metier;
	Coordonnees coord;
	
	public Personnage(){
	}
	
	public Personnage(String nom, String prenom, int age, int poids, int taille){
		this();
		this.nom = nom;
		this.prenom = prenom;
		this.age = age;
		this.poids = poids;
		this.taille = taille;
	}
	
	public void randomCoord(int tailleMondeX, int tailleMondeY){
		Random r = new Random();
		int x = r.nextInt(tailleMondeX);
		int y = r.nextInt(tailleMondeY);
		coord = new Coordonnees(x, y);
	}

	public String toString() {
		return "Personnage [nom=" + nom + ", prenom=" + prenom + ", age=" + age
				+ ", poids=" + poids + ", taille=" + taille + "]";
	}
	
}
