package security;
import java.io.IOException;
public class App {
	
	public static void main(String[] args) throws IOException {
		if(args.length<10){
			throw new IOException("Boom!");
		}
		CBC myCBC;
		Printer myPrinter = new Printer();
		String key;
  		String iv;
  		String output;
  		Attack attack;
		//String algo;
		//String operation;
		
		byte[] text=myPrinter.fileToBytes(args[5]);
		  switch (args[3]) {
          case "encryption": {
        	  myCBC =  new CBC(myPrinter.readDictionary());
        	 key=myPrinter.readKey(args[7]);
      		 iv=myPrinter.fileToString(args[9]);
      		 output=args[11];
      		myPrinter.writeToFile(output, myCBC.encrypt(text, iv, key));
          }break;
          case "decryption": {
        	  myCBC =  new CBC(myPrinter.readDictionary());
        	 key=myPrinter.readKey(args[7]);
      		 iv=myPrinter.fileToString(args[9]);
      		 output=args[11];
      		myPrinter.writeToFile(output, myCBC.decrypt(text, iv, key));
          }break;
          case "attack": {
        	  myCBC =  new CBC(myPrinter.readDictionary());
      		 iv=myPrinter.fileToString(args[7]);
    		 output=args[9];	
    		long startTime = System.nanoTime();
    		 attack = new Attack();
    		 key=attack.crack(text,iv);
    		String outKey="";
    		outKey = myPrinter.printKey(key, outKey);
    	    myPrinter.writeToFile(output, outKey);
    		long endTime = System.nanoTime();
    		System.out.println("Took "+(endTime - startTime) + " ns");
          }break;
		  }
	}

}
