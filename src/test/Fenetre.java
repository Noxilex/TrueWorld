package test;

import java.awt.Dimension;

import javax.swing.JFrame;

@SuppressWarnings("serial")
public class Fenetre extends JFrame{

	public Fenetre(){
		this.setTitle("Test");
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setPreferredSize(new Dimension(500, 500));
		this.setLocationRelativeTo(null);
		this.getContentPane().add(new FenetreTest());
		this.pack();
		this.setVisible(true);
	}
	
	public static void main(String[] args) {
		new Fenetre();
	}
	
}
