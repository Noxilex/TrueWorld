package personnages;

import main.Constante;

/**
 * 
 * @author noxilex
 *
 */
public abstract class Personnage{
	String nom;
	String prenom;
	int age;
	int poids;
	int taille;
	Metier metier;
	
	public Personnage(){
		
	}
	
	public Personnage(String nom, String prenom, int age, int poids, int taille){
		this.nom = nom;
		this.prenom = prenom;
		this.age = age;
		this.poids = poids;
		this.taille = taille;
	}
	@Override
	public String toString() {
		return "Personnage [nom=" + nom + ", prenom=" + prenom + ", age=" + age
				+ ", poids=" + poids + ", taille=" + taille + "]";
	}
	
	

	
}
