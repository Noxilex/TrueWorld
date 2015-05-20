package main;

import javax.swing.JFrame;

import personnages.PNJ;

public class Main {
	static double vitesse = 1000;
	static int duree = 100;
	static boolean bissextile;
	static String date = "";
	
	public static void main(String[] args){
		Monde m = new Monde("continent");
		JFrame f = new JFrame("Monde");
		
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.getContentPane().add(m);
		f.pack();
		f.setLocationRelativeTo(null);
		f.setVisible(true);

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
						Thread.sleep((int)vitesse);
						switch(mois){
						case(1):
							date = jour + " " + "Janvier"+ " " + annee;
						break;
						case(2):
							date = jour + " " + "Février"+ " " + annee;
						break;
						case(3):
							date = jour + " " + "Mars"+ " " + annee;
						break;
						case(4):
							date = jour + " " + "Avril"+ " " + annee;
						break;
						case(5):
							date = jour + " " + "Mai"+ " " + annee;
						break;
						case(6):
							date = jour + " " + "Juin"+ " " + annee;
						break;
						case(7):
							date = jour + " " + "Juillet"+ " " + annee;
						break;
						case(8):
							date = jour + " " + "Août"+ " " + annee;
						break;
						case(9):
							date = jour + " " + "Septembre"+ " " + annee;
						break;
						case(10):
							date = jour + " " + "Octobre"+ " " + annee;
						break;
						case(11):
							date = jour + " " + "Novembre"+ " " + annee;
						break;
						case(12):
							date = jour + " " + "Décembre"+ " " + annee;
						break;
						}
						
						m.spawnPNJ(new PNJ());
						f.repaint();
					}catch(Exception e){
						System.out.println(e.getMessage());
					}
				}
			}
		}
	}
}
