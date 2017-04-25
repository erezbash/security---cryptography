package security;
public class Table {
	private byte[] key;
	public Table(byte[] key){
		this.key=key;
	}
	byte[] replaceAll(byte[] text){
		byte[] temp= new byte[text.length];
		for(int i=0;i<text.length;i++)
				temp[i]=replace(text[i]);
		return temp;
	}
	byte[] replaceRevAll(byte[] text){
		byte[] temp= new byte[text.length];
		for(int i=0;i<text.length;i++)
			temp[i]=replaceRev(text[i]);
		return temp;
	}
	byte replace(byte c){
		int cur = (int)c;
		if(!(cur>=65&&cur<=90 || cur>=97&&cur<=122))
			return c;
		//a-z = 97-122
		//A-Z = 65-90
		if(key.length==8){//8 chars
			return (cur>104 || cur <97 ) ? c : key[cur-97];
		}
		else{ // 52 chars
			if(cur>=97)
				return key[cur-97];
			return key[cur-65+26];
		}
	}
	byte replaceRev(byte c){
		int cur = (int)c;
		if(!(cur>=65&&cur<=90 || cur>=97&&cur<=122))
			return c;
		if(key.length==8){
			for(int i=0;i<8;i++){
				if(c==key[i])
					return (cur>104 || cur <97 ) ? c : (byte)(i+97);
			}
		}
		else{
			for(int i=0;i<52;i++){
				if(c==key[i]){
					if(i<=25)
						return (byte)(i+97);
					else 
						return (byte)(i-26+65);
				}
			}
		}
		return c;
	}
}
