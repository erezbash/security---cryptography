package security;


public class App {
	

	public static void main(String[] args) {
		if(args.length!=12){
			return;
		}
		System.out.println(args[0]); //-a   algo
		System.out.println(args[1]);
		System.out.println(args[2]); //-c   action
		System.out.println(args[3]);
		System.out.println(args[4]); //-t
		System.out.println(args[5]);
		System.out.println(args[6]); //-k
		System.out.println(args[7]);
		System.out.println(args[8]); //-v
		System.out.println(args[9]);
		System.out.println(args[10]); //-o
		System.out.println(args[11]);
		
		
	
	}
}
