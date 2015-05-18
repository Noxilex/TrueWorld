package monde;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.Random;

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
	Case[][] monde;
	JPanel map;
	
	public Monde(int hauteur,  int largeur){
		map = new JPanel();
		monde = new Case[hauteur][largeur];
		initUI(monde, hauteur, largeur, false);
		newMonde = new Case[hauteur][largeur];
		initUI(newMonde, hauteur, largeur, true);
		erruption();
		miseAJour();
		miseAJour();
		miseAJour();
		miseAJour();
		miseAJour();
		setPreferredSize(new Dimension(taille*hauteur*2, taille*largeur));
	}
	

	public void initUI(Case[][] tab, int hauteur , int largeur, boolean eau){
		if(eau){
			for(int h = 0; h < hauteur; h++){
				for(int l = 0; l < largeur; l++){
					tab[h][l] = new Case(0);
				}
			}
		}else{
			for(int h = 0; h < hauteur; h++){
				for(int l = 0; l < largeur; l++){
					tab[h][l] = new Case(3);
				}
			}
		}
	}
	
	public void erruption(){
		for (int l = 0; l < monde.length; l++) {
			for (int h = 0; h < monde[0].length; h++) {
				if(monde[h][l].getType() == "terre"){
					try{
					//Créer des carrés de terre autour, sauf si on dépasse le tableau
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
		Monde m = new Monde(600,600);
		JFrame f = new JFrame("Test");
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.getContentPane().add(m);
		f.pack();
		f.setVisible(true);
	}
}
