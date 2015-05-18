package monde;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.Random;
import java.lang.*;

import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * 
 * @author noxilex
 *
 */
public class Monde extends JPanel{
	
	int taille = 1;

	Case[][] newMonde;
	//monde qui sert à la fonction continent pour générer les nouveaux volcans autour des premiers
	Case[][] mondeTmp;
	Case[][] monde;
	JPanel map;
	String type;
	
	public Monde(int hauteur,  int largeur, String type){
		this.type = type;
		map = new JPanel();
		

		//Génération d'un monde plein d'eau (vide)
		newMonde = new Case[hauteur][largeur];
			swamp(newMonde, hauteur, largeur, true);	
		
		//Génération des volcans
		monde = new Case[hauteur][largeur];
		if(this.type == "swamp"){
			swamp(monde, hauteur, largeur, false);
			//Fusion des volcans
			erruption(monde, newMonde);
		}
			
		if(this.type == "continent"){
			mondeTmp = new Case[hauteur][largeur];
			swamp(mondeTmp, hauteur, largeur, true);
			continent(monde, hauteur, largeur);
			//Fusion des volcans
			erruption(mondeTmp, newMonde);
		}		

		
		//Adoucissement du paysage
		miseAJour();
		miseAJour();
		miseAJour();
		miseAJour();
		miseAJour();
		
		setPreferredSize(new Dimension(taille*hauteur*2, taille*largeur));
	}
	
	/**
	 * Transforme le monde passé en paramètre en un continent
	 * @param tab
	 * @param hauteur
	 * @param largeur
	 */
	private void continent(Case[][] tab, int hauteur, int largeur) {
		for(int h = 0; h < hauteur; h++){
			for(int l = 0; l < largeur; l++){
				tab[h][l] = new Case(20);
			}
		}
		for(int h = 0; h < tab.length; h++){
			for(int l = 0; l < tab[0].length; l++){
				for(int caseY = -10; caseY < 10; caseY++){
					for(int caseX = -10; caseX < 10; caseX++){
						try{
							if(tab[h+caseY][l+caseX].getType() == "terre"){
								int res = Math.abs(caseY)+Math.abs(caseX);
								mondeTmp[h][l] = new Case((10-res)*1000);
							}
						}catch(Exception e){
							
						}
					}
				}
			}
		}
	}


	public void swamp(Case[][] tab, int hauteur , int largeur, boolean eau){
		if(eau){
			for(int h = 0; h < hauteur; h++){
				for(int l = 0; l < largeur; l++){
					tab[h][l] = new Case(0);
				}
			}
		}else{
			for(int h = 0; h < hauteur; h++){
				for(int l = 0; l < largeur; l++){
					tab[h][l] = new Case(200);
				}
			}
		}
	}
	/**
	 * Transforme le monde d'origine en un nouveau monde où les volcan sont déjà rentrés en erruption
	 * @param monde
	 * @param newMonde
	 */
	public void erruption(Case[][] monde, Case[][] newMonde){
		for (int l = 0; l < monde.length; l++) {
			for (int h = 0; h < monde[0].length; h++) {
				if(monde[h][l].getType() == "terre"){
					try{
					//CrÃ©er des carrÃ©s de terre autour, sauf si on dÃ©passe le tableau
					//Layer 1
					newMonde[h-4][l].setType("terre");
					
					//Layer 2
					newMonde[h-3][l-1].setType("terre");
					newMonde[h-3][l].setType("terre");
					newMonde[h-3][l+1].setType("terre");
					
					//Layer 3
					newMonde[h-2][l-2].setType("terre");
					newMonde[h-2][l-1].setType("terre");
					newMonde[h-2][l].setType("terre");
					newMonde[h-2][l+1].setType("terre");
					newMonde[h-2][l+2].setType("terre");
					
					//Layer 4
					newMonde[h-1][l-3].setType("terre");
					newMonde[h-1][l-2].setType("terre");
					newMonde[h-1][l-1].setType("terre");
					newMonde[h-1][l].setType("terre");
					newMonde[h-1][l+1].setType("terre");
					newMonde[h-1][l+2].setType("terre");
					newMonde[h-1][l+3].setType("terre");
					
					//Layer 5
					newMonde[h][l-4].setType("terre");
					newMonde[h][l-3].setType("terre");
					newMonde[h][l-2].setType("terre");
					newMonde[h][l-1].setType("terre");
					newMonde[h][l].setType("terre");
					newMonde[h][l+1].setType("terre");
					newMonde[h][l+2].setType("terre");
					newMonde[h][l+3].setType("terre");
					newMonde[h][l+4].setType("terre");
					
					//Layer 6
					newMonde[h+1][l-3].setType("terre");
					newMonde[h+1][l-2].setType("terre");
					newMonde[h+1][l-1].setType("terre");
					newMonde[h+1][l].setType("terre");
					newMonde[h+1][l+1].setType("terre");
					newMonde[h+1][l+2].setType("terre");
					newMonde[h+1][l+3].setType("terre");
					
					//Layer 7
					newMonde[h+2][l-2].setType("terre");
					newMonde[h+2][l-1].setType("terre");
					newMonde[h+2][l].setType("terre");
					newMonde[h+2][l+1].setType("terre");
					newMonde[h+2][l+2].setType("terre");
					
					//Layer 8
					newMonde[h+3][l-1].setType("terre");
					newMonde[h+3][l].setType("terre");
					newMonde[h+3][l+1].setType("terre");
					
					//Layer 9
					newMonde[h+4][l].setType("terre");
					
					}catch(Exception e){
					}

				}
			}
		}
		//newMonde[abscisse][ordonnee]
		//Dessin de bite
		newMonde[10][1].setType("terre");
		newMonde[11][1].setType("terre");
		newMonde[12][1].setType("terre");
		newMonde[13][1].setType("terre");
		newMonde[13][0].setType("terre");
		newMonde[13][2].setType("terre");
	}
	
	private void miseAJour() {
		for (int l = 0; l < monde.length; l++) {
			for (int h = 0; h < monde[0].length; h++) {
				int terre = 0;
				int eau = 0;
				for(int caseX = -2; caseX<2; caseX++){
					for(int caseY = -2; caseY<2; caseY++){
						try{
							if(newMonde[h-caseY][l-caseX].getType() == "terre"){
								terre++;
							}else{
								eau++;
							}
						}catch(Exception e){
							
						}
					}
				}
				Random r = new Random();
				if(terre>eau){
					newMonde[h][l].setType("terre");
					/*if(terre>18){
						newMonde[h][l].setType("terre");
					}else{
						if(r.nextFloat()>(float)(terre/(terre+eau)))
							newMonde[h][l].setType("terre");
						else
							newMonde[h][l].setType("eau");
					}*/
				}else{
					newMonde[h][l].setType("eau");
				}
			}
		}
	}
	
	public void paintComponent(Graphics g){
		super.paintComponents(g);
		for (int h = 0; h < monde.length; h++) {
			int cH = h*taille;
			for (int l = 0; l < monde[0].length; l++) {
				int cL = l*taille;
				if(monde[h][l].getType() == "terre"){
					g.setColor(Color.GREEN);
					g.fillRect(cH,cL,taille,taille);
				} else if(monde[h][l].getType() == "eau"){
					g.setColor(Color.BLUE);
					g.fillRect(cH,cL,taille,taille);
				}/*
				g.setColor(Color.BLACK);
				g.drawRect(cH, cL, taille, taille);
				*/
			}
		}
		for (int h = 0; h < newMonde.length; h++) {
			int cH = h*taille+newMonde.length*taille;
			for (int l = 0; l < newMonde[0].length; l++) {
				int cL = l*taille;
				if(newMonde[h][l].getType() == "terre"){
					g.setColor(Color.GREEN);
					g.fillRect(cH,cL,taille,taille);
				} else if(newMonde[h][l].getType() == "eau"){
					g.setColor(Color.BLUE);
					g.fillRect(cH,cL,taille,taille);
				}
				/*
				g.setColor(Color.BLACK);
				g.drawRect(cH, cL, taille, taille);
				*/
			}
		}

	}
	
	public static void main(String[] args){

		Monde m = new Monde(600,600, "continent");
		JFrame f = new JFrame("Monde");
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.getContentPane().add(m);
		f.pack();
		f.setVisible(true);
	}
}
