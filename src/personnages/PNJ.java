package personnages;

import java.util.Random;

import main.Constante;

/**
 * 
 * @author Noxilex
 *
 */
public class PNJ extends Personnage{
		

	public PNJ(){
		new Constante();
		nom = generationNom();
	}
	
	/**
	 * G�n�re un nom al�atoire
	 * @return
	 */
	public String generationNom(){	
		Random alea = new Random();
		int tailleNom = alea.nextInt(6)+4;
		char[] nom = new char[tailleNom];
		
		/*
		 * G�n�re tailleNom nombre de lettres pour le nom
		 */
		for(int i = 0; i < tailleNom; i++){
			
			/*
			 * Si alea == 0, on choisit une voyelle, sinon une consonne
			 * 
			 * *Idée* Générer de nouvelles lettres en fonction des lettres précédentes.
			 * Si une consonnes avant: générer une voyelle 70%, consonnes 30%
			 * *FinIdee*  
			 */
			if(i == 0 || i == 1){
				nom[i] = generationLettre(1); //50%
			}
			else{
				if(Constante.mapContient(Constante.voyelles,nom[i-1]) || Constante.mapContient(Constante.voyelles,nom[i-2])){
					nom[i] = generationLettre(4); //80% consonnes
				}
				else if(Constante.mapContient(Constante.voyelles,nom[i-1]) && Constante.mapContient(Constante.voyelles,nom[i-2])){
					nom[i] = generationLettre(9); //90% consonnes
				}
				else{
					nom[i] = generationLettre(0);
				}
			}
		}
		nom[0] = (char)(nom[0] + ('A'-'a'));
		return tabToString(nom);
	}
		
	/**
	 * 
	 * @param interv
	 * @return
	 */
	public char generationLettre(int interv){ //1 = 50%, 0 = voyelle, 2,3,4 = consonnes (66%,75%,80%)
		Random alea = new Random();
		if(alea.nextInt(1+interv) == 0){
			//puissance: 10,1
			int alea_voy = alea.nextInt(Constante.puissanceTotalleVoy);
			if(alea_voy < Constante.puissance(0, 1)){
				return Constante.voyelles.get(1)[alea.nextInt(Constante.voyelles.get(1).length)];
			}		
			else{
				return Constante.voyelles.get(10)[alea.nextInt(Constante.voyelles.get(10).length)];
			}

		}else{//puissance: 10,9,8,7,3,1
			int alea_con = alea.nextInt(Constante.puissanceTotalleCon);
			int palier1 = Constante.puissance(1, 1);
			int palier2 = palier1 + Constante.puissance(1, 3);
			int palier3 = palier2+ Constante.puissance(1, 7);
			int palier4 = palier3+ Constante.puissance(1, 8);
			int palier5 = palier4+ Constante.puissance(1, 9);
			if(alea_con < palier1){
				return Constante.consonnes.get(1)[alea.nextInt(Constante.consonnes.get(1).length)];
			}		
			else if(alea_con < palier2){
				return Constante.consonnes.get(3)[alea.nextInt(Constante.consonnes.get(3).length)];
			}
			else if(alea_con <palier3){
				return Constante.consonnes.get(7)[alea.nextInt(Constante.consonnes.get(7).length)];
			}
			else if(alea_con < palier4){
				return Constante.consonnes.get(8)[alea.nextInt(Constante.consonnes.get(8).length)];
			}
			else if(alea_con < palier5){
				return Constante.consonnes.get(9)[alea.nextInt(Constante.consonnes.get(9).length)];
			}
			else{
				return Constante.consonnes.get(10)[alea.nextInt(Constante.consonnes.get(10).length)];
			}
		}	
	}	
	
	/**
	 * 
	 * @param tabString
	 * @return
	 */
	public String tabToString(char[] tabString){
		String res = "";
		for(char s: tabString){
			res += s;
		}
		return res;
	}
	
	public String toString(){
		return super.nom;
	}
}
