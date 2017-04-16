package security;
import java.util.Arrays;
public class Encrypt {
	
	private final int BLOCKSIZE=10;
	private int[] initVector;
	private int[] plaintText;
	private String Key;
	private int[] cipherText;
	private int[] decryptText;
	private table myTable;
	
	public Encrypt(String plaintText,String initVector,String Key){
		this.plaintText = new int[plaintText.length()];
		for (int i = 0; i < plaintText.length(); i++){
			this.plaintText[i] =(int)plaintText.charAt(i);
	    }
		this.initVector = new int[initVector.length()];
		for (int i = 0; i < initVector.length(); i++){
			this.initVector[i] =(int)initVector.charAt(i);
	    }
		this.Key = Key;
		padding();
		myTable = new table(this.Key);
		encrypt();
	}
	private void padding(){
		if(plaintText.length%10!=0)
			cipherText = new int[(plaintText.length/10)*10+10];
		else
			cipherText = new int[plaintText.length];
	}
	public void same(int[] a,int[] b){
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
	public void encrypt(){
		int startIndex=0;
		int[] chiperTextBlock;
		int[] plaintTextBlocks =  new int[10];
		System.arraycopy(plaintText,startIndex,plaintTextBlocks,0,BLOCKSIZE);
		//System.out.print("textblock:"+Arrays.toString(plaintTextBlocks)+",init:"+Arrays.toString(initVector));
		chiperTextBlock = helperEync(plaintTextBlocks,initVector);
		//System.out.println(",chiperblock:"+Arrays.toString(chiperTextBlock));
		System.arraycopy(chiperTextBlock,0,cipherText,startIndex,BLOCKSIZE);
		startIndex=startIndex+10;
		while((startIndex+10)<=plaintText.length){
			System.arraycopy(plaintText,startIndex,plaintTextBlocks,0,BLOCKSIZE);
			//System.out.print("textblock:"+Arrays.toString(plaintTextBlocks)+",oldchiper:"+Arrays.toString(chiperTextBlock));
			chiperTextBlock = helperEync(plaintTextBlocks,chiperTextBlock);
			//System.out.println(",chiperblock:"+Arrays.toString(chiperTextBlock));
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
		
	}
	public void decrypt(){
		decryptText = new int[cipherText.length];
		int startIndex=0;
		int[] toDecrypt=cipherText;
		int[] chiperTextBlock0 = new int[10];
		int[] chiperTextBlock1 = new int[10];
		int[] plaintTextBlocks =  new int[10];
		System.arraycopy(toDecrypt,startIndex,chiperTextBlock0,0,BLOCKSIZE);
		//System.out.print("textblock:"+Arrays.toString(chiperTextBlock0)+",init:"+Arrays.toString(initVector));
		plaintTextBlocks = helperDyc(chiperTextBlock0,initVector);
		//System.out.println(",chiperblock:"+Arrays.toString(plaintTextBlocks));
		//System.out.println(func(plaintTextBlocks));
		System.arraycopy(plaintTextBlocks,0,decryptText,startIndex,BLOCKSIZE);
		startIndex=startIndex+10;
		while((startIndex+10)<=toDecrypt.length){
			System.arraycopy(chiperTextBlock0,0,chiperTextBlock1,0,BLOCKSIZE);
			System.arraycopy(toDecrypt,startIndex,chiperTextBlock0,0,BLOCKSIZE);
			//System.out.print("textblock:"+Arrays.toString(chiperTextBlock0)+",init:"+Arrays.toString(chiperTextBlock1));
			plaintTextBlocks = helperDyc(chiperTextBlock0,chiperTextBlock1);
			//System.out.println(",chiperblock:"+Arrays.toString(plaintTextBlocks));
			//System.out.println(func(plaintTextBlocks));
			System.arraycopy(plaintTextBlocks,0,decryptText,startIndex,BLOCKSIZE);
			startIndex=startIndex+10;
		}

	}
	private int[] helperEync(int[] first,int[] second){		
		return myTable.replaceAll(xor(first,second));
	}
	private int[] helperDyc(int[] first,int[] second){
		return xor(myTable.replaceRevAll(first),second);
	}
	public int[] xor(int[] first,int[] second){
		int[] result = new int[first.length];
		for(int i=0;i<first.length;i++){
			result[i] = first[i] ^ second[i];
		}
		return result;
	}
	public String getChiper() {
		// TODO Auto-generated method stub
		return intToChar(cipherText);
	}
	public String getDycText() {
		// TODO Auto-generated method stub
		return intToChar(decryptText);
	}
	public String intToChar(int[] text){
		char[] chiperToReturn = new char[text.length];
		for(int i=0;i<text.length;i++)
			chiperToReturn[i]=(char)text[i];
		return new String(chiperToReturn);
	}
}
