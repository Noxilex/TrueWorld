package test;

import java.awt.Dimension;

import javax.swing.JFrame;

@SuppressWarnings("serial")
public class FenetreTest extends JFrame{
	
	public FenetreTest(){
		this.setTitle("Test");
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setSize(new Dimension(500, 500));
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}
	
	public static void main(String[] args){
		
	}
}
