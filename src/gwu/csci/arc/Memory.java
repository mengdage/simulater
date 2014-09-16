package gwu.csci.arc;

import gwu.csci.arc.utility.Converter;

//need revise
public class Memory {

	private static final int LENGTH = 1024;
	//Singleton
	private static Memory memory;
	
	private char[] content = new char[LENGTH];
	private Memory() {
		// TODO Auto-generated constructor stub
		System.out.println("Hey, I am the Memory! I am starting up!");
		//initialization
		int i;
		for(i = 0; i < LENGTH; i++) {
			content[i] = '0';
		}
	}
	public static int getLength() {
		return LENGTH;
	}
	
	//Singleton: the only way to get a Memory object
	public static Memory getInstance() {
		if(memory == null){
			//the Memory class has not been instantiated yet
			//new an object and return the object
			memory = new Memory();
			return memory;
		}
		else {
			//return the object which has already been instantiated
			return memory;
		}
	}

	/**
	 * write c with length len into memory starting form addr
	 * !******need revise******! 
	 * @param c the content to be written
	 * @param len the length of the content
	 * @param addr the address where to start writing
	 * @return 0
	 */
	public int write(char[] c,int len, char[] addr ) {
		int intaddr;
		intaddr = Converter.addrConveterS2I(addr, IntegratedCircuit.getLenAddr());
		System.out.println("Memory: Writing content into memory at " + intaddr);
		for(int i = 0; i < len; i++) {
			content[intaddr+i] = c[i];
		}
		System.out.println("Memory: Succeed!");
		return 0;
	}

	/**
	 * read content with length len from memory starting form addr into c
	 * !******need revise******! 
	 * @param c store the content read
	 * @param len the length of the content
	 * @param binAddr the address where to start reading
	 * @return 0
	 */
	public int read(char[] c, int len, char[] addr) {
		int intaddr;
		intaddr = Converter.addrConveterS2I(addr, IntegratedCircuit.getLenAddr());
		//!!!!!!!!!!!!need revise here!!!!!!!!!!!!
//		System.out.print("Memory: the address to read is: ");
//		System.out.println(addr);
//		System.out.println("Memory: change the address to 128");
//		int fakeaddr = 128; 
		System.out.println("Memory: Reading content from memory at: " + intaddr);
		for(int i = 0; i < len; i++) {
			c[i] = content[intaddr + i];
		}
		System.out.println("Memory: Succeed!");
		return 0;
	}
}
