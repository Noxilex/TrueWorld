package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import monde.Case;
import personnages.PNJ;

/**
 * Cr�e un Panel qui remplit l'ensemble de la frame et affiche le monde ainsi que son interface
 * @author Noxilex
 *
 */
public class Monde extends JPanel implements MouseMotionListener, ActionListener{

	double multiplicateur = 1;
	final double vitesseDeBase = 1000;
	
	int mx, my;
	
	int taille = 5;
	int hauteur = 600/taille;
	int largeur = 600/taille;
	List<PNJ> listePNJ;
	List<Rectangle> hover;

	Case[][] newMonde;
	//mondeTmp qui sert � la fonction continent pour g�n�rer les nouveaux volcans autour des premiers
	Case[][] mondeTmp;
	Case[][] monde;
	JPanel map;
	String type;
	JButton riseVitesse;
	JButton lowerVitesse;
	
	/**
	 * G�n�re un monde de type continent ou swamp en fonction du type pass� en param�tre 
	 * @param type
	 */
	public Monde(String type){
		this.setLayout(null);
		addMouseMotionListener(this);
		this.type = type;
		listePNJ = new ArrayList<PNJ>();
		System.out.println(listePNJ);
		map = new JPanel();

		lowerVitesse = new JButton("-");
		riseVitesse = new JButton("+");
		
		lowerVitesse.setBounds(largeur*taille+10, 320, 50, 30);
		riseVitesse.setBounds(largeur*taille+70, 320, 50, 30);
		
		lowerVitesse.addActionListener(this);
		riseVitesse.addActionListener(this);
		
		this.add(lowerVitesse);
		this.add(riseVitesse);
		
		System.out.println("Création d'une terre recouverte d'eau...");
		//G�n�ration d'un monde plein d'eau (vide)
		newMonde = new Case[hauteur][largeur];
			swamp(newMonde, hauteur, largeur, true);	
		
		System.out.println("Erruption des volcans...");
		//G�n�ration des volcans
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
		System.out.println("Adoucissement du paysage...");
		miseAJour(4);
		
		//Mise en place des arbres
		System.out.println("Plantage des arbres...");
		forrestation(newMonde);
		
		//Ensablement de la plage
		System.out.println("Ensablement de la plage...");
		desertification(newMonde);
		
		setPreferredSize(new Dimension(taille*hauteur+150, taille*largeur));
	}
	
	/**
	 * Transforme le monde pass� en param�tre en un continent
	 * @param tab
	 * @param hauteur
	 * @param largeur
	 */
	public void continent(Case[][] tab, int hauteur, int largeur) {
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

	/**
	 * Transforme le monde pass� en param�tre en un environnement mar�cageux
	 * @param tab
	 * @param hauteur
	 * @param largeur
	 */
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
	 * Transforme le monde d'origine en un nouveau monde o� les volcan sont d�j� rentr�s en erruption
	 * @param monde
	 * @param newMonde
	 */
	public void erruption(Case[][] monde, Case[][] newMonde){
		for (int l = 0; l < monde.length; l++) {
			for (int h = 0; h < monde[0].length; h++) {
				if(monde[h][l].getType() == "terre"){
					
					
					
					for(int i = -4; i <= 4; i++){
						for(int j = -4; j <= 4; j++){
							try{
								newMonde[h+i][l+j].setType("terre");
							} catch(Exception e){
							}
						}
					}
					

				}
			}
		}
		//Debug
		//newMonde[abscisse][ordonnee]
		//Dessin de bite
		/*newMonde[10][1].setType("terre");
		newMonde[11][1].setType("terre");
		newMonde[12][1].setType("terre");
		newMonde[13][1].setType("terre");
		newMonde[13][0].setType("terre");
		newMonde[13][2].setType("terre");*/
	}
	
	public void desertification(Case[][] newMonde){
		Case[][] mondeTmp = newMonde;
		for (int l = 0; l < mondeTmp.length; l++) {
			for (int h = 0; h < mondeTmp[0].length; h++) {
				int terre = 0;
				int eau = 0;
				for(int caseX = -2; caseX<=2; caseX++){
					for(int caseY = -2; caseY<=2; caseY++){

						try{
							if(mondeTmp[h-caseY][l-caseX].getType() != "eau"){
								terre++;
							}else{
								eau++;
							}
						}catch(Exception e){
						
						}
					}
				}
				if(eau > 0 && mondeTmp[h][l].isTerre()){
					newMonde[h][l].setType("sable");
				}
			}
		}
	}
	
	public void forrestation(Case[][] newMonde){
		Case[][] mondeTmp = newMonde;
		for (int l = 0; l < mondeTmp.length; l++) {
			for (int h = 0; h < mondeTmp[0].length; h++) {
				int terre = 0;
				int eau = 0;
				for(int caseX = -5; caseX<=5; caseX++){
					for(int caseY = -5; caseY<=5; caseY++){

						try{
							if(mondeTmp[h-caseY][l-caseX].getType() != "eau"){
								terre++;
							}else{
								eau++;
							}
						}catch(Exception e){
						
						}
					}
				}
				if(eau == 0){
					newMonde[h][l].setType("foret");
				}
			}
		}
	}
	
	/**
	 * Permet de fluidifier les paysage un nombre (nb) de fois, 
	 * en adoucissant les bord et en fusionnant les �les c�te � c�te
	 * 
	 * @param nb
	 */
	private void miseAJour(int nb) {
		for(int i = 0; i < nb; i++){
			for (int l = 0; l < monde.length; l++) {
				for (int h = 0; h < monde[0].length; h++) {
					int terre = 0;
					int eau = 0;
					for(int caseX = -2; caseX<=2; caseX++){
						for(int caseY = -2; caseY<=2; caseY++){
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
					if(terre>eau){
						newMonde[h][l].setType("terre");
					}else{
						newMonde[h][l].setType("eau");
					}
				}
			}
		}
	}
	
	/**
	 * Parcourt la map � la recherche d'un bout de terre vide, puis spawn un PNJ dessus
	 * @param pnj
	 */
	public void spawnPNJ(PNJ pnj){
		
		/*
		 *	1.Parcourt la map
		 *	2.Trouve de la terre
		 *	3.Spawn un pnj
		 *
		 *	Probleme, on ne veut pas plusieurs PNJ
		 *
		 *	Sol1: Compter le nombre de cases vertes et
		 *	G�n�rer un nombre al�a qui compte le nombre de cases restantes avant le spawn
		 *
		 */
		int caseVerte = 0;
		for (int h = 0; h < newMonde.length; h++) {
			for (int l = 0; l < newMonde[0].length; l++) {
				if((newMonde[h][l].isTerre() || newMonde[h][l].isSable()) && !newMonde[h][l].isPersonnage()){
					caseVerte++;
				}
			}
		}
		
		Random r = new Random();
		int alea = r.nextInt(caseVerte);
		while(caseVerte > 0){
			for (int h = 0; h < newMonde.length; h++) {
				for (int l = 0; l < newMonde[0].length; l++) {
					if((newMonde[h][l].isTerre() || newMonde[h][l].isSable()) && !newMonde[h][l].isPersonnage()){
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
	
	/**
	 *	Dessine le monde et l'interface de droite
	 */
	public void paintComponent(Graphics g){
		super.paintComponents(g);
		Font f = new Font("Dialog", Font.CENTER_BASELINE, 12);
		g.setFont(f);
		
		//Interface de droite
		g.setColor(Color.WHITE);
		g.fillRect(largeur*taille, 0, this.getWidth()-largeur*taille, this.getHeight());
		g.setColor(Color.BLACK);
		g.drawRect(largeur*taille, 0, this.getWidth()-largeur*taille, this.getHeight());
		
		//Boite 1
		g.setColor(Color.BLACK);
		g.drawRect(largeur*taille, 0, this.getWidth()-largeur*taille, this.getHeight()/5);
		
		g.drawString("Date:",largeur*taille+10, 40);
		g.drawString(Main.date, largeur*taille+10, 80);
		
		//Boite 2
		g.setColor(Color.BLACK);
		g.drawRect(largeur*taille, this.getHeight()/5, this.getWidth()-largeur*taille, this.getHeight()/5);
		
		g.drawString("Nom du personnage:",largeur*taille+10, this.getHeight()/5+40);

		//Boite 3
		g.setColor(Color.BLACK);
		g.drawRect(largeur*taille, this.getHeight()/5*2, this.getWidth()-largeur*taille, this.getHeight()/5);
		
		g.drawString("Vitesse actuelle:",largeur*taille+10, this.getHeight()/5*2+40);
		g.drawString("x"+1/multiplicateur,largeur*taille+10, this.getHeight()/5*2+60);

		//Boite 4
		g.setColor(Color.BLACK);
		g.drawRect(largeur*taille, this.getHeight()/5*3, this.getWidth()-largeur*taille, this.getHeight()/5);
		
		Rectangle souris = new Rectangle(mx, my, 1, 1);
		for (int h = 0; h < newMonde.length; h++) {
			int cH = h*taille;
			for (int l = 0; l < newMonde[0].length; l++) {
				int cL = l*taille;
				Rectangle r = new Rectangle(cH, cL, taille, taille);
				if(newMonde[h][l].isPersonnage()){
					if(souris.intersects(r)){
	            		g.setColor(Color.BLACK);
	            		g.drawString(newMonde[h][l].getPerso().toString(),largeur*taille+10, this.getHeight()/5+80);
	    				g.setColor(Color.GRAY);
	                	g.fillRect(cH, cL, taille, taille);
					}else{
						g.setColor(Color.BLACK);
						g.fillRect(cH, cL, taille, taille);
					}
				}
				else{
					if(newMonde[h][l].isTerre()){
						g.setColor(new Color(163, 209, 25));
						g.fillRect(cH,cL,taille,taille);
					} else if(newMonde[h][l].isEau()){
						g.setColor(new Color(0, 102, 204));
						g.fillRect(cH,cL,taille,taille);
					} else if(newMonde[h][l].isForet()){
						g.setColor(new Color(0,128,0));
						g.fillRect(cH, cL, taille, taille);
					} else if(newMonde[h][l].isSable()){
						g.setColor(new Color(255,247,221));
						g.fillRect(cH, cL, taille, taille);
					}
				}
			}
		}
		repaint();

	}

	/**
	 * 
	 * @return
	 */
	public List getListePNJ() {
		return listePNJ;
	}
	
	/**
	 * 
	 * @param m
	 * @param x
	 * @param y
	 * @return
	 */
	public Case getCase(Case[][] m, int x, int y){
		return m[y][x];
	}

	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	/**
	 * Capte la position de la souris � son emplacement actuel et vide la m�moire de l'�v�nement
	 */
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		mx = e.getX();
		my = e.getY();
		
		e.consume();
	}

	/**
	 * Change la vitesse de d�roulement du jeu si l'on appuie sur les bouton + ou -
	 */
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getActionCommand().toString() == "+" && multiplicateur > 0.125){
			multiplicateur *= 0.5;
		}else if(e.getActionCommand().toString() == "-" && multiplicateur < 8){
			multiplicateur *= 2;
		}
		Main.vitesse = multiplicateur*vitesseDeBase;
	}
}
