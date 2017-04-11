package security;


public class Encrypt {
	
	private final int BLOCKSIZE=10;
	private char[] initVector;
	private char[] plaintText;
	private String Key;
	private char[] cipherText;
	private table myTable;
	private String chiper;
	
	public Encrypt(String plaintText,String initVector,String Key){
		this.plaintText = plaintText.toCharArray();
		this.initVector = initVector.toCharArray();
		this.Key = Key;
		myTable = new table();
		myTable.setKey(this.Key);
		run();
	}
	public void run(){
		chiper = new String(helper(plaintText,initVector));
	}
	public String helper(char[] first,char[] second){
		char[] temp = new char[BLOCKSIZE];
		for(int i=0;i<temp.length;i++){
			temp[i] = (char) (first[i] ^ second[i]);
		}
		return new String(myTable.replaceAll(temp));
	}
	public Object getChiper() {
		// TODO Auto-generated method stub
		return chiper;
	}
	
	
	
}
