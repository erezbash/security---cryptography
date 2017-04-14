package security;


public class Encrypt {
	
	private final int BLOCKSIZE=10;
	private char[] initVector;
	private char[] plaintText;
	private String Key;
	private char[] cipherText;
	private char[] decryptText;
	private table myTable;
	
	public Encrypt(String plaintText,String initVector,String Key){
		this.plaintText = plaintText.toCharArray();
		this.initVector = initVector.toCharArray();
		this.Key = Key;
		padding();
		myTable = new table(this.Key);
		encrypt();
	}
	private void padding(){
		if(plaintText.length%10!=0)
			cipherText = new char[(plaintText.length/10)*10+10];
		else
			cipherText = new char[plaintText.length];
	}
	

	public void encrypt(){
		int startIndex=0;
		char[] chiperTextBlock;
		char[] plaintTextBlocks =  new char[10];
		System.arraycopy(plaintText,startIndex,plaintTextBlocks,0,BLOCKSIZE);
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
		
	}
	public String decrypt(){
		decryptText = new char[cipherText.length];
		int startIndex=0;
		char[] toDecrypt=cipherText;
		char[] chiperTextBlock0 = new char[10];
		char[] chiperTextBlock1 = new char[10];
		char[] plaintTextBlocks =  new char[10];
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
		return new String(decryptText);
	}
	private char[] helperEync(char[] first,char[] second){		
		return myTable.replaceAll(xor(first,second));
	}
	private char[] helperDyc(char[] first,char[] second){
		first = myTable.replaceAll(first);
		return xor(first,second);
	}
	private char[] xor(char[] first,char[] second){
		char[] result = new char[BLOCKSIZE];
		for(int i=0;i<result.length;i++){
			result[i] = (char) (first[i] ^ second[i]);
		}
		return result;
	}
	public String getChiper() {
		// TODO Auto-generated method stub
		return new String(cipherText);
	}
}
