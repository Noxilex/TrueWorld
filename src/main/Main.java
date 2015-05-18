package main;

public class Main {
	static int duree = 100;
	static boolean bissextile;
	
	public static void main(String[] args){
		for(int annee = 1; annee < duree; annee++){
			if(annee%4 == 0){
				if(annee%100 == 0){
					if(annee%400 == 0){
						bissextile = true;
					}
					else{
						bissextile = false;
					}
				}
				else{
					bissextile = true;
				}
			}
			else{
				bissextile = false;
			}
			for(int mois = 1; mois <= 12; mois++){
				int jourMois = 0;
				if(mois == 2){
					jourMois = 28;
				}
				else if(mois%2 == 1){
					jourMois = 30;
				}
				else{
					jourMois = 31;
				}
				for(int jour = 1; jour <= jourMois; jour++){
					try{
						Thread.sleep(100);
						System.out.println(jour + "/" + mois + "/" + annee);
					}catch(Exception e){
						System.out.println(e.getMessage());
					}
				}
			}
		}
	}
}
