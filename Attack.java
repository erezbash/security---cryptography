package security;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Attack {
	List<String> keys;
	CBC myCBC;
	public Attack(){
		keys = new ArrayList<String>();
		
	}
    public   void perm1(String s) { perm1("", s); }
    private  void perm1(String prefix, String s) {
        int n = s.length();
        if (n == 0) {
        	keys.add(prefix);
        }
        else {
            for (int i = 0; i < n; i++)
               perm1(prefix + s.charAt(i), s.substring(0, i) + s.substring(i+1, n));
        }
    }
    
	public String crack(byte[] cipher,String iv) throws IOException{
		long startTime = System.currentTimeMillis();
		myCBC = new CBC();
		int best=0;
		int cur=0;
		String bestKey="abcdefgh";
        int n = 8;
        String alphabet = "abcdefgh";
        String elements = alphabet.substring(0, n);
        perm1(elements);
		for(String s: keys){
			if(System.currentTimeMillis()-startTime>=59*1000)
				break;
			cur=myCBC.cipherTextAttack(cipher, iv,s);
			if(cur>best){
				best=cur;
				bestKey=s;
			}
		}
		return bestKey;
	}
}
