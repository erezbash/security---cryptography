package security;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
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
	Printer p;
	public Attack(){
		p=new Printer();
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
    
	public String chipherTextAttack(byte[] cipher,String iv) throws IOException{
		 best=0;
        int n = 8;
        Set<String> dictionary=p.readDictionary();
        String alphabet = "abcdefgh";
        String elements = alphabet.substring(0, n);
        perm1(elements);
		keys.parallelStream().forEach(s->{
			try {
				checkForMe(new Key(s,(new CBC(dictionary)).cipherTextAttack(cipher, iv,s)));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			});
		return bestKey;
	}
	public String knownTextAttack(byte[] chiper,byte[] knownText,byte[] knowwnCipher,String iv) throws IOException{
		myCBC= new CBC(null);
		return new String(myCBC.knownTextAttack(chiper,knowwnCipher,knownText,iv.getBytes(Charset.forName("UTF-8"))));
	}
	private void checkForMe(Key key) {
		if(key.cur>best){
			best=key.cur;
			bestKey=key.key;
		}
	}
	
}

