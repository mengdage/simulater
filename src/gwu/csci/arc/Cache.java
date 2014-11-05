package gwu.csci.arc;

import gwu.csci.arc.utility.Converter;
import gwu.csci.arc.utility.IOConnector;

public class Cache {
	private boolean debug = false;
	
	private static final int LEN_VALID=1;
	private static final int LEN_DIRTY=1;
	private static final int LEN_TAG=4;
	private static final int LEN_BLOCK=16*6;
	private static final int LEN_LIN= LEN_VALID + LEN_DIRTY + LEN_TAG + LEN_BLOCK;
	//the number of sets, NUM_SET = 2^LEN_SET_BIT
	private static final int NUM_SET = 16;
	private static final int LEN_SET_BIT = 4;
	
	//Signleton
	private static Cache cache;
	//the cache memory
	private char[][] content = new char[NUM_SET][LEN_LIN];
	
	private Memory memory;
	
	private IOConnector io;
	
	
	//the set id
	private int setId;
	//the block offset
	private int offset;
	
	//the address used when write block back to memory
	char[] blocktoMemAddr = new char[IntegratedCircuit.getLenAddr()];
	//the address used when read block from memory into cache
	char[] memtoBlockAddr = new char[IntegratedCircuit.getLenAddr()];
	
	private Cache() {
		System.out.println("Hey, I am the Cache! I am starting up!");
		setId = 0;
		offset=0;
		io = IOConnector.getInstance();
		
		memory = Memory.getInstance();
		
		int i,j;
		
		for(i = 0; i< NUM_SET; i++) {
			for(j = 0; j < LEN_LIN; j++) {
				content[i][j] = '0';
			}
		}
	}

	public static int getLengthLine() {
		return LEN_LIN;
	}
	
	public static int getNumSet() {
		return NUM_SET;
	}
	
	//Singleton: the only way to get a Memory object
	public static Cache getInstance() {
		if(cache == null){
			//the Memory class has not been instantiated yet
			//new an object and return the object
			cache = new Cache();
			return cache;
		}
		else {
			//return the object which has already been instantiated
			return cache;
		}
	}
	/**
	 * read from cache
	 * @param c store the content read from cache
	 * @param startPos the position where to start 
	 * @param len the length of the content
	 * @param addr the address of the memory
	 * @return 0: success
	 */
	public int read(char[] c, int startPos, int len, char[] addr) {
		prepare(addr);
		int offsetPos = LEN_VALID+LEN_DIRTY+LEN_TAG+offset*IntegratedCircuit.getLenSByte();
		if(debug){
		System.out.println("Cache: read from set "+setId+" blockoffset " + offset);
		}
		//return the requested content
		for (int i = 0; i < len; i++) { 
			c[startPos+i] = content[setId][offsetPos+i];
			
		}
		
		
		
		
		return 0;
	}
	/**
	 * read from cache
	 * @param c store the content read from cache
	 * @param startPos the position where to start 
	 * @param len the length of the content
	 * @param addr the address of the memory
	 * @return 0: success
	 */
	public int write(char[] c, int startPos, int len, char[] addr) {
		
		prepare(addr);
		int offsetPos = LEN_VALID+LEN_DIRTY+LEN_TAG+offset*IntegratedCircuit.getLenSByte();
		if(debug){
		System.out.println("Cache: write to set "+setId+" blockoffset " + offset);
		}
		//return the requested content
		for (int i = 0; i < len; i++) { 
			content[setId][offsetPos+i] = c[startPos+i]; 
			
		}
		content[setId][1]='1';
		return 0;
	}
	/**
	 * Check if the target content is in the cache. If not, load 
	 * content into cache from memory
	 * @param addr
	 * @return
	 */
	private int prepare(char[] addr) {
		addrDecode(addr);
		int tagPos = LEN_VALID+LEN_DIRTY;
		int blockPos = LEN_VALID+LEN_DIRTY+LEN_TAG;
		if (content[setId][0] == '1') { // the valid bit is set
			if(debug){
			System.out.println("Cache: valid set");
			}
			
			//compare tags
			if(!compareChars(content[setId], tagPos, addr, 0, LEN_TAG)) { 
				if(debug){
				System.out.println("Cache: cache miss");
				}
				//cache miss	
				//if dirty bit is set, write the block back to memory
				if(content[setId][1] == '1') {
					//calculate the address to write block back to memory
					for(int i = 0; i< IntegratedCircuit.getLenAddr(); i++) {
						if(i < LEN_TAG) {
							blocktoMemAddr[i] = content[setId][tagPos+i];
						}else if(i<LEN_TAG+LEN_SET_BIT){
							blocktoMemAddr[i] = addr[i];
						} else {
							blocktoMemAddr[i] = '0';
						}
						
					}
					if(debug){
					System.out.println("Cache: write block back to memory "+ new String(blocktoMemAddr));
					}
					//ic.writeMem(content[setId], blockPos, LEN_BLOCK, blocktoMemAddr);
					memory.write(content[setId], blockPos, LEN_BLOCK, blocktoMemAddr);
				}
				
				//calculate the address to read block from memory into cache
				for (int i = 0; i < IntegratedCircuit.getLenAddr(); i++) {
					if (i<LEN_TAG+LEN_SET_BIT) {
						memtoBlockAddr[i] = addr[i];
					} else {
						memtoBlockAddr[i] = '0';
					}
				}
				if(debug){
				System.out.println("Cache: load memory("+ new String(memtoBlockAddr)+") to cache block at line: "+setId);
				}
				//load the content from Memory to Cache
				memory.read(content[setId], blockPos, LEN_BLOCK, memtoBlockAddr);
				//reset Tag
				copyChars(content[setId], tagPos, addr, 0, LEN_TAG);
			} else{
				if(debug){
				System.out.println("Cache: cache hit");
				}
			}
		} else { //the valid bit is not set
			if(debug){
			System.out.println("Cache: valid unset");
			}
			//calculate the address to read block from memory into cache
			for (int i = 0; i < IntegratedCircuit.getLenAddr(); i++) {
				if (i<LEN_TAG+LEN_SET_BIT) {
					memtoBlockAddr[i] = addr[i];
				} else {
					memtoBlockAddr[i] = '0';
				}
			}
			if(debug){
			System.out.println("Cache: load memory("+ new String(memtoBlockAddr)+") to cache block at line: "+setId);
			}
			//load the content from Memory to Cache
			memory.read(content[setId], blockPos, LEN_BLOCK, memtoBlockAddr);
			//set Tag
			copyChars(content[setId], tagPos, addr, 0, LEN_TAG);
			//set valid bit
			content[setId][0] = '1';
			
		}
		return 0;
	}
	/**
	 * calculate the set id and block offset
	 * @param addr
	 * @return 0
	 */
	private int addrDecode(char[] addr) {
		if(addr.length != 12) {
			System.err.println("Cache: the add is not 12 bits");
			return 1;
		}
		setId = Converter.conveterS2I(addr, 4, 4);
		offset = Converter.conveterS2I(addr, 8, 4);
		return 0;
	}
	/**
	 * 
	 * @param c1 
	 * @param startPos1 the start position of c1
	 * @param c2
	 * @param startPos2 the start position of c2
	 * @param len the length to be compared
	 * @return true if equal or false
	 */
	private boolean compareChars(char[] c1, int startPos1, char[] c2, int startPos2, int len) {
		boolean flag = true;
		for (int i = 0; i < len; i++) {
			if(c1[startPos1+i] != c2[startPos2+i]) {
				flag = false;
			}
		}
		return flag;
	}
	
	/**
	 * 
	 * @param c1 to
	 * @param startPos1 the start position of c1
	 * @param c2 from 
	 * @param startPos2 the start position of c2
	 * @param len the length to be compared
	 * @return true if equal or false
	 */
	private int copyChars(char[] c1, int startPos1, char[] c2, int startPos2, int len) {

		for (int i = 0; i < len; i++) {
			c1[startPos1+i] = c2[startPos2+i];
		}
		return 0;
	}
	
	
}
