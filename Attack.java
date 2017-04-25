package security;

import java.io.IOException;
import java.security.Key;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.stream.Stream;

import javax.swing.plaf.synth.SynthSpinnerUI;

public class Attack {
	class Key{
		public String key;
		public int cur;
		public Key(String k,int c){
			key=k;
			cur=c;
		}
	}

	List<String> keys;
	CBC myCBC;
	String bestKey="";
	int best;
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
		//myCBC = new CBC(10);
		 best=0;
		
        int n = 8;
        String alphabet = "abcdefgh";
        String elements = alphabet.substring(0, n);
        perm1(elements);
        ForkJoinPool myPool = new ForkJoinPool(3);
        try {
			myPool.submit(() ->
			keys.parallelStream().forEach(s->{
				int c=0;
				try {
					c = (new CBC(10)).cipherTextAttack(cipher, iv,s);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				checkForMe(new Key(s,c));
			})).get();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//		for(String s: keys){
//			if(System.currentTimeMillis()-startTime>=59*1000)
//				break;
//			cur=myCBC.cipherTextAttack(cipher, iv,s);
//			if(cur>best){
//				best=cur;
//				bestKey=s;
//			}
//		}
		return bestKey;
	}
	private void checkForMe(Key key) {
		if(key.cur>best){
			best=key.cur;
			bestKey=key.key;
		}
	}
	
}

