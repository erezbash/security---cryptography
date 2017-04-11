package security;


public class table {
	private char key[] = {'a','b','c','d','e','f','g','h'};
	
	public void setKey(String key){
		if(key.length()>7){
			for(int i=0;i<8;i++)
				this.key[i]=key.charAt(i);
		}
	}
	char[] replaceAll(char[] text){
		for(int i=0;i<text.length;i++)
			text[i]=replace(text[i]);
		return text;
	}
	char[] replaceRevAll(char[] text){
		for(int i=0;i<text.length;i++)
			text[i]=replaceRev(text[i]);
		return text;
	}
	char replace(char c){
		 switch (c) {
	     case 'a': return key[0] ;
	     case 'b': return key[1] ;
	     case 'c': return key[2] ;
	     case 'd': return key[3] ;
	     case 'e': return key[4] ;
	     case 'f': return key[5] ;
	     case 'g': return key[6] ;
	     case 'h': return key[7] ;
	     default: return c;         
		 }
	 }
	char replaceRev(char c){
	     if(c==key[0]) return 'a';
	     else if(c==key[1]) return 'b';
	     else if(c==key[2]) return 'c';
	     else if(c==key[3]) return 'd';
	     else if(c==key[4]) return 'e';
	     else if(c==key[5]) return 'f';
	     else if(c==key[6]) return 'g';
	     else if(c==key[7]) return 'h';
	     else
	    	 return c;
	 }
}
