package test;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Polygon;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import monde.Coordonnees;

@SuppressWarnings("serial")
public class FenetreTest extends JPanel implements KeyListener{
	private int cpt = 1;
	private int vitesseDeDeplacement = 3;
	private int lastEvent = 0;
	private boolean collision = false;
	private Coordonnees c; 
	private Hero hero;
	private int imgActuelle = 2;
	private int scale = 3;
	private boolean up_down;
	private boolean down_down;
	private boolean right_down;
	private boolean left_down;
	
	private int keyDisabled;
	
	private BufferedImage herbe;
	private BufferedImage rock;
	
	private BufferedImage left_fixe;
	private BufferedImage left_move1;
	private BufferedImage left_move2;
	
	private BufferedImage right_fixe;
	private BufferedImage right_move1;
	private BufferedImage right_move2;
	
	private BufferedImage up_fixe;
	private BufferedImage up_move1;
	private BufferedImage up_move2;
	
	private BufferedImage down_fixe;
	private BufferedImage down_move1;
	private BufferedImage down_move2;
	

	Rectangle r1 = new Rectangle(125, 125, 50, 50);
	Rectangle r2 = new Rectangle(225, 225, 50, 50);
	
	Rectangle personnage = null;
	
	/**
	 * 
	 */
	public FenetreTest(){
		c = new Coordonnees(0,0);
		hero = new Hero("Paul");
		
		File f = null;
		try {
			f = new File("img/herbe.png");
			herbe = ImageIO.read(f); 
			f = new File("img/rock.png");
			rock = ImageIO.read(f); 
			
			f = new File("img/Tera/left_fixe.png");
			left_fixe = ImageIO.read(f);
			f = new File("img/Tera/left_move1.png");
			left_move1 = ImageIO.read(f);
			f = new File("img/Tera/left_move2.png");
			left_move2 = ImageIO.read(f);
			
			f = new File("img/Tera/right_fixe.png");
			right_fixe = ImageIO.read(f);
			f = new File("img/Tera/right_move1.png");
			right_move1 = ImageIO.read(f);
			f = new File("img/Tera/right_move2.png");
			right_move2 = ImageIO.read(f);
			
			f = new File("img/Tera/up_fixe.png");
			up_fixe = ImageIO.read(f);
			f = new File("img/Tera/up_move1.png");
			up_move1 = ImageIO.read(f);
			f = new File("img/Tera/up_move2.png");
			up_move2 = ImageIO.read(f);
			
			f = new File("img/Tera/down_fixe.png");
			down_fixe = ImageIO.read(f);
			f = new File("img/Tera/down_move1.png");
			down_move1 = ImageIO.read(f);
			f = new File("img/Tera/down_move2.png");
			down_move2 = ImageIO.read(f);
		} catch (IOException e) {
			e.printStackTrace();
		}
		

		addKeyListener(this);
	}
	
	public void addNotify() {
		super.addNotify();
		requestFocus();
	}
	
	public void paintComponent(Graphics g){
		//Créer un rectangle de dimension de l'obstable dès qu'un obstacle est créé
		//Le rectangle de l'object en mouvement sera modifié avec un event
		int largeur = this.getWidth();
		int hauteur = this.getHeight();
		
		r1 = new Rectangle(125, 125, 50, 50);
		r2 = new Rectangle(225, 225, 50, 50);
		//Polygon p = new Polygon(new int[]{300,310,320,320,310,300,300}, new int[]{100,90,100,110,120,110,100}, 6);
		
		
		
		personnage = new Rectangle(c.getX()+15+scale, c.getY()+(24*scale)+10, (15+scale)*2, 14);
		
		g.setColor(Color.WHITE);
		g.drawImage(herbe, 0, 0, largeur, hauteur, null, null);
		
		//g.setColor(Color.BLACK);
		//g.drawPolygon(p);
		
		g.setColor(Color.BLACK);
		g.drawRect(125, 125, 50, 50);
		g.drawImage(rock, 100, 100, 100, 100, null, null);
		g.drawRect(225, 225, 50, 50);
		g.drawImage(rock, 200, 200, 100, 100, null, null);
		g.drawRect(c.getX()+15+scale, c.getY()+(24*scale)+10, (15+scale)*2, 14);
		
		if(lastEvent == KeyEvent.VK_DOWN){
			if(imgActuelle == 2 || imgActuelle == 4)
				g.drawImage(down_fixe, c.getX()+15, c.getY()+24, down_fixe.getWidth()*scale, down_fixe.getHeight()*scale, null, null);
			if(imgActuelle == 1)
				g.drawImage(down_move1, c.getX()+15, c.getY()+24, down_move1.getWidth()*scale, down_move1.getHeight()*scale, null, null);
			if(imgActuelle == 3)
				g.drawImage(down_move2, c.getX()+15, c.getY()+24, down_move2.getWidth()*scale, down_move2.getHeight()*scale, null, null);
		}
		else if(lastEvent == KeyEvent.VK_UP){
			if(imgActuelle == 2 || imgActuelle == 4)
				g.drawImage(up_fixe, c.getX()+15, c.getY()+24, up_fixe.getWidth()*scale, up_fixe.getHeight()*scale, null, null);
			if(imgActuelle == 1)
				g.drawImage(up_move1, c.getX()+15, c.getY()+24, up_move1.getWidth()*scale, up_move1.getHeight()*scale, null, null);
			if(imgActuelle == 3)
				g.drawImage(up_move2, c.getX()+15, c.getY()+24, up_move2.getWidth()*scale, up_move2.getHeight()*scale, null, null);
		}
		else if(lastEvent == KeyEvent.VK_RIGHT){
			if(imgActuelle == 2 || imgActuelle == 4)
				g.drawImage(right_fixe, c.getX()+15, c.getY()+24, right_fixe.getWidth()*scale, right_fixe.getHeight()*scale, null, null);
			if(imgActuelle == 1)
				g.drawImage(right_move1, c.getX()+15, c.getY()+24, right_move1.getWidth()*scale, right_move1.getHeight()*scale, null, null);
			if(imgActuelle == 3)
				g.drawImage(right_move2, c.getX()+15, c.getY()+24, right_move2.getWidth()*scale, right_move2.getHeight()*scale, null, null);
		}
		else if(lastEvent == KeyEvent.VK_LEFT){
			if(imgActuelle == 2 || imgActuelle == 4)
				g.drawImage(left_fixe, c.getX()+15, c.getY()+24, left_fixe.getWidth()*scale, left_fixe.getHeight()*scale, null, null);
			if(imgActuelle == 1)
				g.drawImage(left_move1, c.getX()+15, c.getY()+24, left_move1.getWidth()*scale, left_move1.getHeight()*scale, null, null);
			if(imgActuelle == 3)
				g.drawImage(left_move2, c.getX()+15, c.getY()+24, left_move2.getWidth()*scale, left_move2.getHeight()*scale, null, null);
		} else{
			//1 = Gauche
			if(lastEvent == 1)
				g.drawImage(left_fixe, c.getX()+15, c.getY()+24, down_fixe.getWidth()*scale, down_fixe.getHeight()*scale, null, null);
			//2 = haut
			else if(lastEvent == 2)
				g.drawImage(up_fixe, c.getX()+15, c.getY()+24, down_fixe.getWidth()*scale, down_fixe.getHeight()*scale, null, null);
			//3 = droite
			else if(lastEvent == 3)
				g.drawImage(right_fixe, c.getX()+15, c.getY()+24, down_fixe.getWidth()*scale, down_fixe.getHeight()*scale, null, null);
			//4 = bas
			else if(lastEvent == 4)
				g.drawImage(down_fixe, c.getX()+15, c.getY()+24, down_fixe.getWidth()*scale, down_fixe.getHeight()*scale, null, null);
			else
				g.drawImage(down_fixe, c.getX()+15, c.getY()+24, down_fixe.getWidth()*scale, down_fixe.getHeight()*scale, null, null);
		}
		
			
	}

	@Override
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		
		if(KeyEvent.VK_RIGHT == key){
			right_down = true;
		}
		else if(KeyEvent.VK_LEFT == key){
			left_down = true;
		}
		else if(KeyEvent.VK_UP == key){
			up_down = true;
		}
		else if(KeyEvent.VK_DOWN == key){
			down_down = true;
		}
		
		//Tant que le personnage ne touche pas d'objet, il bouge. 
		if(!(r1.intersects(personnage) || r2.intersects(personnage))){
			
			if(down_down){
				c.setY(c.getY()+vitesseDeDeplacement);
			}
			if(up_down){
				c.setY(c.getY()-vitesseDeDeplacement);
			}
			if(right_down){
				c.setX(c.getX()+vitesseDeDeplacement);
			}
			if(left_down){
				c.setX(c.getX()-vitesseDeDeplacement);
			}
			
			if(KeyEvent.VK_RIGHT == key){
				c.setX(c.getX()+vitesseDeDeplacement);
			}
			else if(KeyEvent.VK_LEFT == key){
				c.setX(c.getX()-vitesseDeDeplacement);
			}
			else if(KeyEvent.VK_UP == key){
				c.setY(c.getY()-vitesseDeDeplacement);
			}
			else if(KeyEvent.VK_DOWN == key){
				c.setY(c.getY()+vitesseDeDeplacement);
			}

			lastEvent = key;
			keyDisabled = key;
		}
		
		//Autrement on désactive la touche qui a causé la collision et on autorise seulement l'utilisation des autres
		//Probleme: Si l'on rentre en collision avec plusieurs objets, on peut passer au travers des objets suivants.
		//On aura donc tout de meme besoin de faire revenir le personnage en arrière
		else {

			if(KeyEvent.VK_RIGHT == key && KeyEvent.VK_RIGHT != keyDisabled){
				c.setX(c.getX()+vitesseDeDeplacement);
			}
			else if(KeyEvent.VK_LEFT == key && KeyEvent.VK_LEFT != keyDisabled){
				c.setX(c.getX()-vitesseDeDeplacement);
			}
			else if(KeyEvent.VK_UP == key && KeyEvent.VK_UP != keyDisabled){
				c.setY(c.getY()-vitesseDeDeplacement);
			}
			else if(KeyEvent.VK_DOWN == key && KeyEvent.VK_DOWN != keyDisabled){
				c.setY(c.getY()+vitesseDeDeplacement);
			}
			lastEvent = key;
		}

		//Permet de faire bouger l'animation des jambes seulement tous les 3 mouvements
		if(cpt == 5){
			//2-3-4-1 (2=4)
			if(imgActuelle < 4)
				imgActuelle++;
			else
				imgActuelle = 1;
			
		}
		System.out.println(imgActuelle);
		cpt++;
		cpt = cpt%5+1;
		this.repaint();
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		int key = e.getKeyCode();
		
		if(KeyEvent.VK_RIGHT == key){
			right_down = false;
		}
		else if(KeyEvent.VK_LEFT == key){
			left_down = false;
		}
		else if(KeyEvent.VK_UP == key){
			up_down = false;
		}
		else if(KeyEvent.VK_DOWN == key){
			down_down = false;
		}
		
		if(KeyEvent.VK_LEFT == key){
			lastEvent = 1;
		}
		else if(KeyEvent.VK_UP == key){
			lastEvent = 2;
		}
		else if(KeyEvent.VK_RIGHT == key){
			lastEvent = 3;
		}
		else if(KeyEvent.VK_DOWN == key){
			lastEvent = 4;
		}

		this.repaint();
	}

	@Override
	public void keyTyped(KeyEvent e) {
		this.repaint();
	}
}
