package monde;

import java.awt.Rectangle;
import java.util.Random;

import personnages.Personnage;

/**
 * 
 * @author Noxilex
 *
 */
public class Case extends Rectangle{
	
	/**
	 * Personnage contenu dans la case
	 */
	Personnage perso;
	/**
	 * Retourne le type de bloc de la case
	 */
	String typeCase;
	
	/**
	 * Génère un bloc de terre ou d'eau avec une probabilité rand/10000
	 * @param rand
	 */
	public Case(int rand){
		Random r = new Random();
		int alea = r.nextInt(10000);
		if(alea < rand){
				typeCase = "terre";
		}else{
				typeCase = "eau";
		}
	}
	
	/**
	 * 
	 * @return
	 */
	public Personnage getPerso() {
		return perso;
	}

	/**
	 * 
	 * @param perso
	 */
	public void setPerso(Personnage perso) {
		this.perso = perso;
	}
	
	/**
	 * 
	 * @return
	 */
	public String getType(){
		return typeCase;
	}
	
	/**
	 * 
	 * @param type
	 */
	public void setType(String type){
		this.typeCase = type;
	}
	
	/**
	 * 
	 * @return
	 */
	public boolean isTerre(){
		if(typeCase == "terre")
			return true;
		return false;
	}
	
	/**
	 * 
	 * @return
	 */
	public boolean isEau(){
		if(typeCase == "eau")
			return true;
		return false;		
	}
	
	/**
	 * 
	 * @return
	 */
	public boolean isPersonnage(){
		if(perso != null)
			return true;
		return false;
	}
	
	/**
	 * 
	 * @return
	 */
	public boolean isVide(){
		if(isEau() || isTerre() || isPersonnage())
			return false;
		return true;
	}
}
