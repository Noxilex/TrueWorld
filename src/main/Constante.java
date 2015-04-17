package main;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
/**
 * 
 * @author noxilex
 *
 */
public class Constante {
	public static int puissanceTotalleVoy;
	public static int puissanceTotalleCon;
	
	public static char[][] tabConsonnes = {{'n', 'r', 's', 't', 'l'},{'d','m', 'g'},{'b', 'c', 'p'}, {'f', 'h', 'v'}, {'j', 'q'}, {'k', 'w', 'x', 'z'}}; //20, {5,3,3,3,2,4}
	public static char[][] tabVoyelles = {{'a', 'e', 'i', 'o', 'u'}, {'y'}}; //6, {5,1}

	public static Map<Integer, char[]> consonnes = new HashMap<Integer, char[]>();
	public static Map<Integer, char[]> voyelles = new HashMap<Integer, char[]>();
	
	/**
	 * 
	 */
	public Constante(){
		//Initialisation de la Map, avec la puissance des lettres
		consonnes.put(10, tabConsonnes[0]);
		consonnes.put(9, tabConsonnes[1]);
		consonnes.put(8, tabConsonnes[2]);
		consonnes.put(7, tabConsonnes[3]);
		consonnes.put(3, tabConsonnes[4]);
		consonnes.put(1, tabConsonnes[5]);
		
		voyelles.put(10, tabVoyelles[0]);
		voyelles.put(1, tabVoyelles[1]);
		
		puissanceTotalleCon = puissanceTotale(1);
		puissanceTotalleVoy = puissanceTotale(0);
	}
	
	/**
	 * 
	 * @param numTab
	 * @return
	 */
	public static char[] getVoyelles(int numTab) {
		return voyelles.get(numTab);
	}
	
	/**
	 * 
	 * @param numTab
	 * @return
	 */
	public static char[] getConsonnes(int numTab) {
		return consonnes.get(numTab);
	}
	
	/**
	 * 
	 * @param numDic
	 * @return
	 */
	public static int puissanceTotale(int numDic){
		int power = 0;
		if(numDic == 0){
			Set<Entry<Integer, char[]>> v_Set = voyelles.entrySet();
			for(Entry<Integer, char[]> e : v_Set){
				power += e.getKey()*e.getValue().length;
			}
		}
		else{
			Set<Entry<Integer, char[]>> c_Set = consonnes.entrySet();
			for(Entry<Integer, char[]> e : c_Set){
				power += e.getKey()*e.getValue().length;
			}
		}
		return power;
	}
	
	/**
	 * 
	 * @param numDic
	 * @param puissance
	 * @return
	 */
	public static int puissance(int numDic, int puissance){
		int power = 0;
		if(numDic == 0){
			power += puissance*voyelles.get(puissance).length;
		}
		else{
			power += puissance*consonnes.get(puissance).length;
		}
		return power;
	}
	
	/**
	 * 
	 * @param groupe
	 * @param lettre
	 * @return
	 */
	public static boolean mapContient(Map<Integer, char[]> groupe, char lettre){
		Set<Character> ensemble = new HashSet<Character>();
		for(Entry<Integer, char[]> a : groupe.entrySet()){
			for(char b : a.getValue()){
				ensemble.add(b);
			}
		}
		return ensemble.contains(lettre);
	}

	public static void main(String[] args){
		new Constante();
		System.out.println(puissanceTotalleCon);
		System.out.println(puissanceTotalleVoy);
		System.out.println("----------------");
		System.out.println(puissance(0,1)); 
	}
}
