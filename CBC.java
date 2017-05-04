package security;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;
public class CBC {
	
	private int BLOCKSIZE=10;
	private byte[] initVector;
	private byte[] plaintText;
	private byte[] Key;
	private byte[] cipherText;
	private byte[] decryptText;
	private Table myTable;
	public String kkk;
	int blockCrackSize;
	Printer p ;
	Set<String> dictionary; 
	public CBC(Set<String> dic) throws IOException{
		 p  = new Printer();
		 dictionary=dic;
	}
	
	public String encrypt(byte[] text,String iv,String k){
		if(k.length()>8)
			BLOCKSIZE=8128;
		encryptInit(text,iv,k);
		myTable = new Table(Key);
		int startIndex=0;
		byte[] chiperTextBlock;
		byte[] plaintTextBlocks =  new byte[BLOCKSIZE];
		System.arraycopy(this.plaintText,startIndex,plaintTextBlocks,0,BLOCKSIZE);
		chiperTextBlock = helperEync(plaintTextBlocks,initVector);
		System.arraycopy(chiperTextBlock,0,cipherText,startIndex,BLOCKSIZE);
		startIndex=startIndex+BLOCKSIZE;
		while((startIndex+BLOCKSIZE)<=plaintText.length){
			System.arraycopy(plaintText,startIndex,plaintTextBlocks,0,BLOCKSIZE);
			chiperTextBlock = helperEync(plaintTextBlocks,chiperTextBlock);
			System.arraycopy(chiperTextBlock,0,cipherText,startIndex,BLOCKSIZE);
			startIndex=startIndex+BLOCKSIZE;
		}
		return  new String(cipherText);
	}

	private void encryptInit(byte[] a,String b,String c){
		plaintText =a;
		initVector = b.getBytes(Charset.forName("UTF-8"));
		Key = c.getBytes(Charset.forName("UTF-8"));;
		if(plaintText.length%BLOCKSIZE!=0)
			cipherText = new byte[(plaintText.length/BLOCKSIZE)*BLOCKSIZE+BLOCKSIZE];
		else
			cipherText = new byte[plaintText.length];
		plaintText = padding(BLOCKSIZE,a);
	}
	private byte[] padding(int blockSize,byte[] chars){
		int bufferSize;
		if(chars.length%BLOCKSIZE!=0)
			bufferSize=(chars.length/blockSize)*blockSize+blockSize;
		else
			bufferSize=chars.length;
		byte[] result = new byte[bufferSize];
		System.arraycopy(chars,0,result,0,chars.length);
		for(int i=chars.length;i<bufferSize;i++)
			result[i]=0;
		return result;
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
		if(k.length()>8)
			BLOCKSIZE=8128;
		decryptText = new byte[chiper.length];
		initVector = iv.getBytes(Charset.forName("UTF-8"));
		Key=k.getBytes(Charset.forName("UTF-8"));;
		myTable = new Table(Key);
		int startIndex=0;
		byte[] toDecrypt=chiper;
		byte[] chiperTextBlock0 = new byte[BLOCKSIZE];
		byte[] chiperTextBlock1 = new byte[BLOCKSIZE];
		byte[] plaintTextBlocks =  new byte[BLOCKSIZE];
		System.arraycopy(toDecrypt,startIndex,chiperTextBlock0,0,BLOCKSIZE);
		plaintTextBlocks = helperDyc(chiperTextBlock0,initVector);
		System.arraycopy(plaintTextBlocks,0,decryptText,startIndex,BLOCKSIZE);
		startIndex=startIndex+BLOCKSIZE;
		while((startIndex+BLOCKSIZE)<=toDecrypt.length){
			System.arraycopy(chiperTextBlock0,0,chiperTextBlock1,0,BLOCKSIZE);
			System.arraycopy(toDecrypt,startIndex,chiperTextBlock0,0,BLOCKSIZE);
			plaintTextBlocks = helperDyc(chiperTextBlock0,chiperTextBlock1);
			System.arraycopy(plaintTextBlocks,0,decryptText,startIndex,BLOCKSIZE);
			startIndex=startIndex+BLOCKSIZE;
		}
		return  new String(decryptText);
	}
	private byte[] helperEync(byte[] first,byte[] second){		
		return myTable.replaceAll(xor(first,second));
	}
	public byte[] helperDyc(byte[] first,byte[] second){
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
	 public int cipherTextAttack(byte[] chiper,String iv,String k){

		blockCrackSize=35000;
		decryptText = new byte[chiper.length];
		initVector = iv.getBytes(Charset.forName("UTF-8"));
		Key=k.getBytes(Charset.forName("UTF-8"));
		myTable = new Table(Key);
		int startIndex=0;
		byte[] toDecrypt=chiper;
		byte[] chiperTextBlock0 = new byte[BLOCKSIZE];
		byte[] chiperTextBlock1 = new byte[BLOCKSIZE];
		byte[] plaintTextBlocks =  new byte[BLOCKSIZE];
		System.arraycopy(toDecrypt,startIndex,chiperTextBlock0,0,BLOCKSIZE);
		plaintTextBlocks = helperDyc(chiperTextBlock0,initVector);
		System.arraycopy(plaintTextBlocks,0,decryptText,startIndex,BLOCKSIZE);
		startIndex=startIndex+BLOCKSIZE;
		while((startIndex+BLOCKSIZE)<=toDecrypt.length){
			System.arraycopy(chiperTextBlock0,0,chiperTextBlock1,0,BLOCKSIZE);
			System.arraycopy(toDecrypt,startIndex,chiperTextBlock0,0,BLOCKSIZE);
			plaintTextBlocks = helperDyc(chiperTextBlock0,chiperTextBlock1);
			if(startIndex>=blockCrackSize){

				return cehckIfNotCracked(decryptText);
			}
			System.arraycopy(plaintTextBlocks,0,decryptText,startIndex,BLOCKSIZE);
			startIndex=startIndex+BLOCKSIZE;	
		}

		return cehckIfNotCracked(decryptText);
	}
	private int cehckIfNotCracked(byte[] partOfDecryptText) {
		String text;
		if(partOfDecryptText.length<blockCrackSize)
			text = new String(partOfDecryptText);
		else{
			byte[] temp= new byte[blockCrackSize];
			System.arraycopy(partOfDecryptText,0,temp,0,blockCrackSize);
			text=new String(temp);
		}
		int goods=0;
		String[] textWords=text.split("\\s+");
		for(int i=0;i<textWords.length;i++){
			if(dictionary.contains(textWords[i]))
				goods++;
		}

		return goods;
	}
	public byte[] knownTextAttack(byte[] knownCipher,byte[] knownText,byte[] iv){
		BLOCKSIZE=8128;
		byte[] Key = new byte[52];
		byte[] afterXor = new byte[BLOCKSIZE];
		afterXor = xor(iv,padding(BLOCKSIZE,knownText));
		Key=extractKey(afterXor,knownCipher,Key);
		return Key;
	}
	private byte[] extractKey(byte[] afterXor, byte[] chiperBlocks, byte[] key) {
		char[] temp ={'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z','A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z'};
		shuffleArray(temp);shuffleArray(temp);shuffleArray(temp);shuffleArray(temp);shuffleArray(temp);shuffleArray(temp);
		for(int i=0;i<key.length;i++)
			key[i]=(byte)temp[i];
		for(int i=0;i<afterXor.length;i++){
			int c=(int)afterXor[i];
			if(c>96 && c<123){
				key[c-97]=chiperBlocks[i];
			}
			else if(c>64 && c<91){
				key[c-65+26]=chiperBlocks[i];
			}
		}
		return key;
	}
	private void shuffleArray(char[] ar)
	  {
	    // If running on Java 6 or older, use `new Random()` on RHS here
	    Random rnd = ThreadLocalRandom.current();
	    for (int i = ar.length - 1; i > 0; i--)
	    {
	      int index = rnd.nextInt(i + 1);
	      // Simple swap
	      char a = ar[index];
	      ar[index] = ar[i];
	      ar[i] = a;
	    }
	  }
}
