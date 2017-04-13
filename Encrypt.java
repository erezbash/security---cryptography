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
		int x;
		if(this.plaintText.length%10!=0)
			x=(this.plaintText.length/10)*10+10;
		else
			x=this.plaintText.length;
		cipherText = new char[x];
		this.initVector = initVector.toCharArray();
		this.Key = Key;
		myTable = new table();
		myTable.setKey(this.Key);
		run();
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
	public void run(){
		
		int startIndex=0;
		char[] chiperTextBlock;
		char[] plaintTextBlocks =  new char[10];
		System.arraycopy(plaintText,startIndex,plaintTextBlocks,0,BLOCKSIZE);
		chiperTextBlock = helper(plaintTextBlocks,initVector);
		System.arraycopy(chiperTextBlock,0,cipherText,startIndex,BLOCKSIZE);
		startIndex=startIndex+10;
		while((startIndex+10)<=plaintText.length){
			System.arraycopy(plaintText,startIndex,plaintTextBlocks,0,BLOCKSIZE);
			chiperTextBlock = helper(plaintTextBlocks,chiperTextBlock);
			System.arraycopy(chiperTextBlock,0,cipherText,startIndex,BLOCKSIZE);
			startIndex=startIndex+10;
		}
		if(startIndex<plaintText.length){
			System.arraycopy(plaintText,startIndex,plaintTextBlocks,0,plaintText.length-startIndex);

			for(int i=plaintText.length-startIndex;i<BLOCKSIZE;i++){
				plaintTextBlocks[i]=0;
			}
			chiperTextBlock = helper(plaintTextBlocks,chiperTextBlock);
			System.arraycopy(chiperTextBlock,0,cipherText,startIndex,BLOCKSIZE);
		}
		
	}
	public char[] helper(char[] first,char[] second){
		
		char[] chiperTextBlock = new char[BLOCKSIZE];
		for(int i=0;i<chiperTextBlock.length;i++){
//			if((int)first[i]==10){
//				System.out.println("first: "+i);
//				System.out.println("second: "+(int)second[i]);
//				System.out.println((int) (first[i] ^ second[i]));
//			}
			chiperTextBlock[i] = (char) (first[i] ^ second[i]);
		}
//		System.out.println("---------------");
//		System.out.println(new String(first));
//		System.out.println(new String(second));
//		System.out.println(new String(myTable.replaceAll(chiperTextBlock)));
		
		return myTable.replaceAll(chiperTextBlock);
		
	}
	public char[] helperDyc(char[] first,char[] second){
		first = myTable.replaceAll(first);
		char[] plainTextBlock = new char[BLOCKSIZE];
		for(int i=0;i<plainTextBlock.length;i++){
			plainTextBlock[i] = (char) (first[i] ^ second[i]);
		}
		return plainTextBlock;
		
	}
	public String getChiper() {
		// TODO Auto-generated method stub
		return new String(cipherText);
	}
	
	
	
}
