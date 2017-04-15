package security;


public class table {
	private int[] key;
	public table(String key){
		this.key = new int[8];
		setKey(key);
	}
	public void setKey(String key){
		if(key.length()>7){
			for(int i=0;i<8;i++)
				this.key[i]=(int)key.charAt(i);
		}
	}
	int[] replaceAll(int[] text){
		int[] temp= new int[text.length];
		for(int i=0;i<text.length;i++)
			temp[i]=replace(text[i]);
		return temp;
	}
	int[] replaceRevAll(int[] text){
		int[] temp= new int[text.length];
		for(int i=0;i<text.length;i++)
			temp[i]=replaceRev(text[i]);
		return temp;
	}
	int replace(int c){
		 switch ((char)c) {
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
	int replaceRev(int c){
	          if(c==key[0]) return (int)'a';
	     else if(c==key[1]) return (int)'b';
	     else if(c==key[2]) return (int)'c';
	     else if(c==key[3]) return (int)'d';
	     else if(c==key[4]) return (int)'e';
	     else if(c==key[5]) return (int)'f';
	     else if(c==key[6]) return (int)'g';
	     else if(c==key[7]) return (int)'h';
	     else
	    	 return c;
	 }
}
