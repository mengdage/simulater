package gwu.csci.arc;

import gwu.csci.arc.utility.Converter;
import gwu.csci.arc.utility.IOConnector;

//need revise
public class Memory {

	//1 sByte = 6 sBits
	//the total memory is 4096 sBytes
	private static final int LENGTH = 4096*6;
	//Singleton
	private static Memory memory;
	
	private IOConnector io = IOConnector.getInstance();
	
	private char[] content = new char[LENGTH];
	private Memory() {
		// TODO Auto-generated constructor stub
		io.printString("Hey, I am the Memory! I am starting up!");
		
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
		return write(c, 0, len, addr);
	}
	
	/**
	 * write c with length len into memory starting form addr
	 * @param c the content to be written
	 * @param startPos the start position of c
	 * @param len the length of the content
	 * @param addr the address where to start writing
	 * @return 0
	 */
	public int write(char[] c,int startPos, int len, char[] addr ) {
		int intaddr;
		intaddr = 6*Converter.conveterS2I(addr, IntegratedCircuit.getLenAddr());
		io.printString("Memory: Writing content into memory at " + intaddr/6 +" ("+intaddr+")");
		for(int i = 0; i < len; i++) {
			content[intaddr+i] = c[startPos+i];
		}
		return 0;
	}

	/**
	 * read content with length len from memory starting form addr into c
	 * @param c store the content read
	 * @param len the length of the content
	 * @param binAddr the address where to start reading
	 * @return 0
	 */
	public int read(char[] c, int len, char[] addr) {
		return read(c, 0, len, addr);
	}
	
	/**
	 * read content with length len from memory starting form addr into c
	 * @param c store the content read
	 * @param startPos the start position of c
	 * @param len the length of the content
	 * @param binAddr the address where to start reading
	 * @return 0
	 */
	public int read(char[] c,int startPos, int len, char[] addr) {
		int intaddr;
		intaddr = 6*Converter.conveterS2I(addr, IntegratedCircuit.getLenAddr());
		io.printString("Memory: Reading content from memory at: " + intaddr/6 +" ("+intaddr+")");
		for(int i = 0; i < len; i++) {
			c[startPos+i] = content[intaddr + i];
		}
		return 0;
	}
}
