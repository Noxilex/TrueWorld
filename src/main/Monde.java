package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JPanel;

import monde.Case;
import personnages.PNJ;

/**
 * 
 * @author Noxilex
 *
 */
public class Monde extends JPanel implements MouseMotionListener{
	
	int mx, my;
	
	int taille = 5;
	int hauteur = 600/taille;
	int largeur = 600/taille;
	List<PNJ> listePNJ;
	List<Rectangle> hover;

	Case[][] newMonde;
	//mondeTmp qui sert à la fonction continent pour générer les nouveaux volcans autour des premiers
	Case[][] mondeTmp;
	Case[][] monde;
	JPanel map;
	String type;
	
	public Monde(String type){
		addMouseMotionListener(this);
		this.type = type;
		listePNJ = new ArrayList<PNJ>();
		System.out.println(listePNJ);
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
		
		setPreferredSize(new Dimension(taille*hauteur+150, taille*largeur));
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
				}else{
					newMonde[h][l].setType("eau");
				}
			}
		}
	}
	
	public void spawnPNJ(PNJ pnj){
		
		/*
		 *	1.Parcourt la map
		 *	2.Trouve de la terre
		 *	3.Spawn un pnj
		 *
		 *	Probleme, on ne veut pas plusieurs PNJ
		 *
		 *	Sol1: Compter le nombre de cases vertes et
		 *	Générer un nombre aléa qui compte le nombre de cases restantes avant le spawn
		 *
		 */
		int caseVerte = 0;
		for (int h = 0; h < newMonde.length; h++) {
			for (int l = 0; l < newMonde[0].length; l++) {
				if(newMonde[h][l].isTerre() && !newMonde[h][l].isPersonnage()){
					caseVerte++;
				}
			}
		}
		
		Random r = new Random();
		int alea = r.nextInt(caseVerte);
		while(caseVerte > 0){
			for (int h = 0; h < newMonde.length; h++) {
				for (int l = 0; l < newMonde[0].length; l++) {
					if(newMonde[h][l].isTerre() && !newMonde[h][l].isPersonnage()){
						if(alea == caseVerte){
							newMonde[h][l].setPerso(pnj);
							listePNJ.add(pnj);
						}
						caseVerte--;
					}
				}
			}
		}
		
	}
	
	public void paintComponent(Graphics g){
		super.paintComponents(g);
		
		//Interface de droite
		g.setColor(Color.WHITE);
		g.fillRect(largeur*taille, 0, this.getWidth()-largeur*taille, this.getHeight());
		g.setColor(Color.BLACK);
		g.drawString("Nom du personnage:",largeur*taille+10, 100);
		g.drawString("Date:",largeur*taille+10, 20);
		g.drawString(Main.date, largeur*taille+10, 40);
		
		Rectangle souris = new Rectangle(mx, my, 1, 1);
		for (int h = 0; h < newMonde.length; h++) {
			int cH = h*taille;
			for (int l = 0; l < newMonde[0].length; l++) {
				int cL = l*taille;
				Rectangle r = new Rectangle(cH, cL, taille, taille);
				if(newMonde[h][l].isPersonnage()){
					if(souris.intersects(r)){
	            		g.setColor(Color.BLACK);
	            		g.drawString("Nom du personnage:",largeur*taille+10, 100);
	            		g.drawString(newMonde[h][l].getPerso().toString(),largeur*taille+10, 120);
	    				g.setColor(Color.GRAY);
	                	g.fillRect(cH, cL, taille, taille);
					}else{
						g.setColor(Color.BLACK);
						g.fillRect(cH, cL, taille, taille);
					}
				}
				else{
					if(newMonde[h][l].isTerre()){
						g.setColor(Color.GREEN);
						g.fillRect(cH,cL,taille,taille);
					} else if(newMonde[h][l].isEau()){
						g.setColor(Color.BLUE);
						g.fillRect(cH,cL,taille,taille);
					}
				}
			}
		}
		repaint();

	}
	
	public static void main(String[] args){

		Monde m = new Monde("continent");
		JFrame f = new JFrame("Monde");
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.getContentPane().add(m);
		f.pack();
		f.setVisible(true);
		
		m.spawnPNJ(new PNJ());
		m.spawnPNJ(new PNJ());
		m.spawnPNJ(new PNJ());
		
		f.repaint();
		
		System.out.println(m.getListePNJ());
	}

	public List getListePNJ() {
		return listePNJ;
	}
	
	public Case getCase(Case[][] m, int x, int y){
		return m[y][x];
	}

	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		mx = e.getX();
		my = e.getY();
	}
}
