package monde;

import java.util.Random;

public class Case {
	
	String typeCase;
	
	public Case(int rand){
		Random r = new Random();
		int alea = r.nextInt(100)+1;
		if(alea < rand){
				typeCase = "terre";
		}else{
				typeCase = "eau";
		}
	}
	
	public String getType(){
		return typeCase;
	}
	
	public void setType(String type){
		this.typeCase = type;
	}
}
