package security;
import java.io.IOException;
public class App {
	
	public static void main(String[] args) throws IOException {	
		Printer myPrinter = new Printer();
		CBC myCBC =  new CBC(myPrinter.readDictionary());
		
		if(args.length<10 || args.length>12){
			throw new IOException("Error args number dont match");
		}
		

		Attack attack = new Attack();
		byte[] firstFileRead;


		String a="";
		String c="";
		String t="";
		String k="";
		String v="";
		String o="";
		String kc="";
		String kp="";
		
  		for(int i=0;i<args.length-1;i++){
  			if(args[i].equals("-a"))
  				a=args[i+1];
  			else if(args[i].equals("-c"))
  				c=args[i+1];
  			else if(args[i].equals("-t"))
  				t=args[i+1];
  			else if(args[i].equals("-k"))
  				k=args[i+1];
  			else if(args[i].equals("-v"))
  				v=args[i+1];
  			else if(args[i].equals("-o"))
  				o=args[i+1];
  			else if(args[i].equals("-kc"))
  				kc=args[i+1];
  			else if(args[i].equals("-kp"))
  				kp=args[i+1];
  		}

		firstFileRead=myPrinter.fileToBytes(t);
 		String iv=myPrinter.fileToString(v);

		if(args.length==10){
			String key=attack.chipherTextAttack(firstFileRead,iv);
			String outKey="";
			outKey = myPrinter.printKey(key, outKey);
		    myPrinter.writeToFile(o, outKey);
		}
		else if(args.length==12){
	        String key=myPrinter.readKey(k);
	        if(c.equals("encryption"))
	        	myPrinter.writeToFile(o, myCBC.encrypt(firstFileRead, iv, key));  
	        else
	         	myPrinter.writeToFile(o, myCBC.decrypt(firstFileRead, iv, key));
	          
		}
		else if(args.length==14){
			String key;
			byte[] knownText=myPrinter.fileToBytes(kp);
			byte[] knowwnCipher=myPrinter.fileToBytes(kc);
			key=attack.knownTextAttack(knownText, knowwnCipher, iv);
			String outKey="";
			outKey = myPrinter.printKey1(key, outKey);
		    myPrinter.writeToFile(o, outKey);
		}
	}

}
