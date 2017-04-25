package security;
import java.io.IOException;
public class App {
	
	public static void main(String[] args) throws IOException {
		if(args.length<10){
			throw new IOException("Boom!");
		}
		CBC myCBC;
		Printer myPrinter = new Printer();
		byte[] text=myPrinter.read(args[5]);
		  switch (args[3]) {
          case "encryption": {
        	  myCBC =  new CBC(10,myPrinter.readDic());
        	String key=myPrinter.readKey8(args[7]);
      		String iv=myPrinter.readFile(args[9]);
      		String output=args[11];
      		myPrinter.writeToFile(output, myCBC.encrypt(text, iv, key));
          }break;
          case "decryption": {
        	  myCBC =  new CBC(10,myPrinter.readDic());
        	String key=myPrinter.readKey8(args[7]);
      		String iv=myPrinter.readFile(args[9]);
      		String output=args[11];
      		myPrinter.writeToFile(output, myCBC.decrypt(text, iv, key));
          }break;
          case "attack": {
        	  myCBC =  new CBC(10,myPrinter.readDic());
      		String iv=myPrinter.readFile(args[7]);
    		String output=args[9];	
    		long startTime = System.nanoTime();
    		Attack a = new Attack();
    		String key=a.crack(text,iv);
    		String outKey="";
    		for(int i=0;i<key.length();i++){
    			if(i==key.length()-1)
    				outKey=outKey+(char)(i+97)+" "+key.charAt(i);
    			else
    			outKey=outKey+(char)(i+97)+" "+key.charAt(i)+"\n";
    		}
    	    myPrinter.writeToFile(output, outKey);
    		long endTime = System.nanoTime();
    		System.out.println("Took "+(endTime - startTime) + " ns");
          }break;
		  }
		
		
	}
}
