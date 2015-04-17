package personnages;

/**
 * 
 * @author noxilex
 *
 */
public abstract class Personnage implements Metier{
	String nom;
	String prenom;
	int age;
	int poids;
	int taille;
	
	@Override
	public String toString() {
		return "Personnage [nom=" + nom + ", prenom=" + prenom + ", age=" + age
				+ ", poids=" + poids + ", taille=" + taille + "]";
	}

	
}
