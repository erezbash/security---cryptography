package security;
import java.nio.charset.Charset;
public class CBC {
	
	private final int BLOCKSIZE=10;
	private byte[] initVector;
	private byte[] plaintText;
	private byte[] Key;
	private byte[] cipherText;
	private byte[] decryptText;
	private table myTable;
	
	public CBC(){}
	
	public String encrypt(byte[] text,String iv,String k){
		encryptInit(text,iv,k);
		myTable = new table(Key);
		int startIndex=0;
		byte[] chiperTextBlock;
		byte[] plaintTextBlocks =  new byte[10];
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
		return  new String(cipherText);
	}

	private void encryptInit(byte[] a,String b,String c){
		plaintText = a;
		initVector = b.getBytes(Charset.forName("UTF-8"));
		Key = c.getBytes(Charset.forName("UTF-8"));;
		if(plaintText.length%10!=0)
			cipherText = new byte[(plaintText.length/10)*10+10];
		else
			cipherText = new byte[plaintText.length];
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
	
	public String decrypt(byte[] chiper,String iv,String k){
		decryptText = new byte[chiper.length];
		initVector = iv.getBytes(Charset.forName("UTF-8"));
		Key=k.getBytes(Charset.forName("UTF-8"));;
		myTable = new table(Key);
		int startIndex=0;
		byte[] toDecrypt=chiper;
		byte[] chiperTextBlock0 = new byte[10];
		byte[] chiperTextBlock1 = new byte[10];
		byte[] plaintTextBlocks =  new byte[10];
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
		return  new String(decryptText);
	}
	private byte[] helperEync(byte[] first,byte[] second){		
		return myTable.replaceAll(xor(first,second));
	}
	private byte[] helperDyc(byte[] first,byte[] second){
		return xor(myTable.replaceRevAll(first),second);
	}
	private byte[] xor(byte[] first,byte[] second){
		byte[] result = new byte[first.length];
		for(int i=0;i<first.length;i++){
			result[i]=(byte) (first[i] ^ second[i]);
		}
		return result;
	}

	public int[] charToInt(String a){
		int[] toReturn = new int[a.length()];
		for (int i = 0; i < a.length(); i++){
			toReturn[i] =(int)a.charAt(i);
	    }
		return toReturn;
	}
}