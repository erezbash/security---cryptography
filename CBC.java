package security;
import java.util.Arrays;
public class CBC {
	
	private final int BLOCKSIZE=10;
	private int[] initVector;
	private int[] plaintText;
	private String Key;
	private int[] cipherText;
	private int[] decryptText;
	private table myTable;
	
	public CBC(){}
	
	public String encrypt(String text,String iv,String k){
		encryptInit(text,iv,k);
		myTable = new table(Key);
		int startIndex=0;
		int[] chiperTextBlock;
		int[] plaintTextBlocks =  new int[10];
		System.arraycopy(this.plaintText,startIndex,plaintTextBlocks,0,BLOCKSIZE);
		chiperTextBlock = helperEync(plaintTextBlocks,initVector);
		System.arraycopy(chiperTextBlock,0,cipherText,startIndex,BLOCKSIZE);
		startIndex=startIndex+10;
		while((startIndex+10)<=plaintText.length){
			System.arraycopy(plaintText,startIndex,plaintTextBlocks,0,BLOCKSIZE);
			chiperTextBlock = helperEync(plaintTextBlocks,chiperTextBlock);
			System.arraycopy(chiperTextBlock,0,cipherText,startIndex,BLOCKSIZE);
			startIndex=startIndex+10;
		}
		if(startIndex<plaintText.length){
			System.arraycopy(plaintText,startIndex,plaintTextBlocks,0,plaintText.length-startIndex);
			for(int i=plaintText.length-startIndex;i<BLOCKSIZE;i++){
				plaintTextBlocks[i]=0;
			}
			chiperTextBlock = helperEync(plaintTextBlocks,chiperTextBlock);
			System.arraycopy(chiperTextBlock,0,cipherText,startIndex,BLOCKSIZE);
		}
		return intToChar(cipherText);
	}

	private void encryptInit(String a,String b,String c){
		plaintText = charToInt(a);
		initVector = charToInt(b);
		Key = c;
		if(plaintText.length%10!=0)
			cipherText = new int[(plaintText.length/10)*10+10];
		else
			cipherText = new int[plaintText.length];
	}
	
	public void isSame(int[] a,int[] b){
		int k=a.length;
		for(int i=0;i<k;i=i+1){
			if(a[i]==b[i])
				//System.out.println((int)(b.charAt(i))+" :ok"+" i="+i);
				continue;
			else{
				System.out.println("XXXXXXXXXX");
			}
		}
	}
	
	public String decrypt(String chiper,String iv,String k){
		decryptText = new int[chiper.length()];
		initVector = charToInt(iv);
		Key=k;
		myTable = new table(Key);
		int startIndex=0;
		int[] toDecrypt=charToInt(chiper);
		int[] chiperTextBlock0 = new int[10];
		int[] chiperTextBlock1 = new int[10];
		int[] plaintTextBlocks =  new int[10];
		System.arraycopy(toDecrypt,startIndex,chiperTextBlock0,0,BLOCKSIZE);
		plaintTextBlocks = helperDyc(chiperTextBlock0,initVector);
		System.arraycopy(plaintTextBlocks,0,decryptText,startIndex,BLOCKSIZE);
		startIndex=startIndex+10;
		while((startIndex+10)<=toDecrypt.length){
			System.arraycopy(chiperTextBlock0,0,chiperTextBlock1,0,BLOCKSIZE);
			System.arraycopy(toDecrypt,startIndex,chiperTextBlock0,0,BLOCKSIZE);
			plaintTextBlocks = helperDyc(chiperTextBlock0,chiperTextBlock1);
			System.arraycopy(plaintTextBlocks,0,decryptText,startIndex,BLOCKSIZE);
			startIndex=startIndex+10;
		}
		return intToChar(decryptText);
	}
	private int[] helperEync(int[] first,int[] second){		
		return myTable.replaceAll(xor(first,second));
	}
	private int[] helperDyc(int[] first,int[] second){
		return xor(myTable.replaceRevAll(first),second);
	}
	private int[] xor(int[] first,int[] second){
		int[] result = new int[first.length];
		for(int i=0;i<first.length;i++){
			result[i] = first[i] ^ second[i];
		}
		return result;
	}
	private String intToChar(int[] text){
		char[] chiperToReturn = new char[text.length];
		for(int i=0;i<text.length;i++)
			chiperToReturn[i]=(char)text[i];
		return new String(chiperToReturn);
	}
	public int[] charToInt(String a){
		int[] toReturn = new int[a.length()];
		for (int i = 0; i < a.length(); i++){
			toReturn[i] =(int)a.charAt(i);
	    }
		return toReturn;
	}
}
