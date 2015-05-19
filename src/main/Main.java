package main;

import javax.swing.JFrame;

import monde.Monde;
import personnages.PNJ;

public class Main {
	static int duree = 100;
	static boolean bissextile;
	
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
						Thread.sleep(1000);
						System.out.println(jour + "/" + mois + "/" + annee);
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
