package gwu.csci.arc;
enum REG_TYPE {
	GPR,
	XR,
	CC,
	PC,
	IR
}

public class IntegratedCircuit {
	private static IntegratedCircuit ic; //singleton
	private static final int LEN_WORD = 18; // the length of the word
	private static final int LEN_ADDR = 12; // the length of the address
	private static final int LEN_INSTRUCTION = 18; //the length of an instruction
	private static final int LEN_OPCODE = 6; //the length of the OPCODE in an instruct
	private static final int LEN_RFI = 2; //the length of the RFI in an instruction
	private static final int LEN_XFI = 2; //the length of the XFI in an instruction
	private static final int LEN_I = 1; //the length of the I in an instruction
	private static final int LEN_ADDR_INCODE= 7; //the length of the address in an instruction


	/*used for communicating with the memory*/
	private char[] MAR = new char[LEN_ADDR];
	private char[] MBR = new char[LEN_WORD];
	/*-------------------------------------------*/
	
	/*used for decompose a instruction; 18 bits*/
	private char[] opcode = new char[LEN_OPCODE];
	private char[] rfi = new char[LEN_RFI];
	private char[] xfi = new char[LEN_XFI];
	private char[] I = new char[LEN_I];
	private char[] addr = new char[LEN_ADDR_INCODE];
	/*------------------------------------------*/
	
	//the effective address
	private char[] EA = new char[LEN_ADDR];
	
	/*used for ALU calculation*/
	//result from the ALU
	private char[] RES = new char[LEN_WORD];
	//first operand
	private char[] OP1 = new char[LEN_WORD];
	//second operand
	private char[] OP2 = new char[LEN_WORD];
	/*------------------------*/
	//the new PC
	private char[] newPC = new char[LEN_ADDR];
	
	//the value read from the register file
	private char[] valR = new char[LEN_WORD];
//	private char[] valM = new char[LEN_WORD];
	//the memory
	private Memory memory;
	
	//the CPU
	private CPU cpu;
	
	/**
	 * constructor
	 * @param cpu
	 */
	private IntegratedCircuit(CPU cpu) {
		// TODO Auto-generated constructor stub
		memory = Memory.getInstance();
		this.cpu = cpu;
		
		//initiliaztion
		for (int i = 0; i < LEN_ADDR; i++) {
			EA[i] = '0';
			MAR[i] = '0';
		}
		for (int i = 0; i < LEN_WORD; i++) {
			MBR[i] = '0';
			RES[i] = '0';
			OP1[i] = '0';
			OP2[i] = '0';
			
		}
	}
	
	/**
	 * Singleton: the only way to get an IntegratedCircuit instance
	 * @return an instance
	 */
	public static IntegratedCircuit getInstance(CPU cpu) {
		//the IntegratedCircuit class has not been instantiated yet
		//new an object and return the object
		if(ic == null) {
			ic = new IntegratedCircuit(cpu);
			return ic;
		}
		else {
			//return the object which has already been instantiated
			return ic;
		}
	}
	/**
	 * get the length of a word
	 * @return length
	 */
	public static int getLenWord() {
		return LEN_WORD;
	}

	/**
	 * get the length of the address
	 * @return length
	 */
	public static int getLenAddr() {
		return LEN_ADDR;
	}

	/**
	 * get the length of an instruction
	 * @return length
	 */
	public static int getLenInstruction() {
		return LEN_INSTRUCTION;
	}
	
	
	/**
	 * get the length of the OPCODE in an instruction 
	 * @return
	 */
	public static int getLenAddrIncode() {
		return LEN_ADDR_INCODE;
	}

	public char[] getEA() {
		return EA;
	}
	public char[] getRfi() {
		return rfi;
	}

	public void setRfi(char[] rfi) {
		this.rfi = rfi;
	}

	public char[] getXfi() {
		return xfi;
	}

	public void setXfi(char[] xfi) {
		this.xfi = xfi;
	}

	public char[] getAddr() {
		return addr;
	}

	public void setAddr(char[] addr) {
		this.addr = addr;
	}

	public char[] getMAR() {
		return MAR;
	}

	public void setMAR(char[] mAR) {
		MAR = mAR;
	}
	

	public char[] getMBR() {
		return MBR;
	}

	public void setMBR(char[] mBR) {
		MBR = mBR;
	}

	/**
	 * 
	 * @param c: store the content
	 * @param len: the length of the content
	 * @param rt: the type of the register
	 * @return 0
	 */
	private int readReg(char[] c, int len, REG_TYPE rt) {
		switch (rt) {
		case GPR:
			cpu.readGPR(c, rfi, len);break;
		case XR:
			cpu.readXR(c, xfi, len);break;
		case CC:
			//cpu.readCC(, len)break;
		case PC:
			cpu.readPC(c, len);break;
		case IR:
			cpu.readIR(c, len);break;

		default:
			break;
		}
		return 0;
	}
	
	/**
	 * 
	 * @param c: the content to be written
	 * @param len: the length of the content
	 * @param rt: the type of the register
	 * @return 0
	 */
	private int writeReg(char[] c, int len, REG_TYPE rt) {
		switch (rt) {
		case GPR:
			cpu.writeGPR(c, rfi, len);
			break;
			
		case XR:
			break;
			
		case CC:
			//cpu.readCC(, len)
			break;
			
		case PC:
			
			break;
		case IR:
			cpu.writeIR(c, len);
			
			break;

		default:
			break;
		}
		return 0;
		
	}
	/**
	 * 
	 * @param c: store the content
	 * @param len: the length of content
	 * @param addr: the address of the content
	 * @return 0
	 */
	public int readMem(char[] c, int len, char[] addr) {
		
		memory.read(c, len, addr);
		
		return 0;
	}
	/**
	 * 
	 * @param c then content to be written
	 * @param len the length of the content
	 * @param addr the address of the memory to start writing
	 * @return
	 */
	public int writeMem(char[] c, int len, char[] addr) {
		memory.write(c, len, addr);
		
		return 0;
	}
	/**
	 * Load a word from Memory to general purpouse register
	 * the Memory address is EA
	 * the register id is rfi
	 * @return 0
	 */
	public int M2R() {
		readMem(MBR, LEN_WORD, EA);
		writeReg(MBR, LEN_WORD, REG_TYPE.GPR);
		return 0;
	}
	/**
	 * store a word from general purpouse register to Memory
	 * the Memory address is EA
	 * the register id is rfi
	 * @return 0
	 */
	public int R2M() {
		readReg(valR, LEN_WORD, REG_TYPE.GPR);
		writeMem(valR, LEN_WORD, EA);
		return 0;
		
	}
	/**
	 * load the register with addr or M(addr)
	 * the register id is rfi
	 * @return
	 */
	public int A2R() {
		if (I[0] == '0') {
			writeReg(EA, EA.length, REG_TYPE.GPR);
		}
		else {
			readMem(MBR, LEN_WORD, EA);
			writeReg(MBR, LEN_WORD, REG_TYPE.GPR);
		}
		
		return 0;
	}
	
	/**
	 * load the register with addr or M(addr)
	 * the register id is rfi
	 * @return
	 */
	public int A2X() {
		if (I[0] == '0') {
			writeReg(EA, EA.length, REG_TYPE.XR);
		}
		else {
			readMem(MBR, LEN_WORD, EA);
			writeReg(MBR, LEN_WORD, REG_TYPE.XR);
		}
		
		return 0;
	}
	
	/**
	 * Perform the decode stage
	 * MAR <- R(RFI)
	 * MBR <- M(MAR)
	 * IR <- R(MBR)
	 * OPCODE <- IR0-5
	 * RFI <- IR6-7
	 * XFI <- IR8-9
	 * I <- IR10
	 * ADDR <- IR11-17
	 * @return 0
	 */
	public int decode() {
		char[] ir = new char[18];
		int n;
		readReg(MAR, LEN_ADDR, REG_TYPE.PC); // MAR <- R(RFI)
		readMem(MBR, LEN_WORD, MAR); //MBR <- M(MAR)
		writeReg(MBR, LEN_WORD, REG_TYPE.IR); // IR <- R(MBR)
		readReg(ir, LEN_INSTRUCTION, REG_TYPE.IR);
		for(n = 0; n < LEN_INSTRUCTION; n++) {
			if(n<LEN_OPCODE) { //OPCODE <- IR0-5
				opcode[n] = ir[n];
			}
			else if(n<LEN_OPCODE+LEN_RFI) {
				//RFI <- IR6-7
				rfi[n-LEN_OPCODE] = ir[n];
			}
			else if(n<LEN_OPCODE + LEN_RFI + LEN_XFI) {
				//XFI <- IR8-9
				xfi[n - LEN_OPCODE - LEN_RFI] = ir[n];
			}
			else if(n<LEN_OPCODE + LEN_RFI + LEN_XFI + LEN_I) {
				//I <- IR10
				I[n - LEN_OPCODE - LEN_RFI- LEN_XFI] = ir[n];
			}
			else if(n<LEN_OPCODE + LEN_RFI + LEN_XFI + LEN_I + LEN_ADDR_INCODE) {
				//ADDR <- IR11-17
				addr[n - LEN_OPCODE - LEN_RFI- LEN_XFI - LEN_I] = ir[n];
			}
		}
		
		return 0;
		
	}
	/**
	 * 
	 * IF I=0
	 * 		IF XFI =0
	 * 			EA <- ADDR
	 * 		IF XFI = 1, 2, 3
	 * 			EA <- R(XFI) + ADDR
	 * IF I = 1
	 * 		IF XFI = 0
	 * 			EA <- M(ADDR)
	 * 		IF XFI = 1, 2, 3
	 * 			EA <- M(R(XFI) + ADDR)
	 * @return 0
	 */
	public int calcEA() {
		if (I[0] == '0') {                   //IF I=0
			if(xfi[0] == '0' && xfi[1] == '0') { //IF XFI =0
				//EA <- ADDR
				for (int i = 0; i < LEN_ADDR; i++) {
					if(i < LEN_ADDR - LEN_ADDR_INCODE) {
						EA[i] = '0';
					}
					else { 
						EA[i] =addr[i-(LEN_ADDR-LEN_ADDR_INCODE)]; 
					}
					
				}
			}
			else { //IF XFI = 1, 2, 3
				//EA <- R(XFI) + ADDR
				char[] ad = new char[LEN_ADDR];
				char[] ad2 = new char[LEN_ADDR];
				cpu.readXR(ad, xfi, LEN_ADDR);
				//expand the 7-bit addr to 12-bit in ad2 
				for (int i = 0; i < LEN_ADDR; i++) {
					if(i < LEN_ADDR - LEN_ADDR_INCODE) {
						ad2[i] = '0';
					}
					else { 
						ad2[i] =addr[i-(LEN_ADDR-LEN_ADDR_INCODE)]; 
					}
					
				}
				//add ad to addr
				cpu.addition(ad, ad2, EA);
				//!!!!!!need revise!!!!!!!!!!
//				for(int i = 0; i < LEN_ADDR; i++) {
//					EA[i] = ad[i];
//				}
			}
		}
		else { //IF I = 1
			if(xfi[0] == '0' && xfi[1] == '0') { //IF XFI = 0
				//EA <- M(ADDR)
				char[] ad = new char[LEN_ADDR];
						
				memory.read(ad, LEN_ADDR, addr);
				for(int i = 0; i < LEN_ADDR; i++) {
					EA[i] = ad[i];
				}
				
			}
			else { //IF XFI = 1, 2, 3
				//EA <- M(R(XFI) + ADDR)
				
				//the content from index register 
				char[] ad = new char[LEN_ADDR];
				//the address of the memory where the EA resides
				char[] ad2 = new char[LEN_ADDR];
				
				//ad <- R(XFI)
				cpu.readXR(ad, xfi, LEN_ADDR);
				
				//ad2 <- ADDR
				for (int i = 0; i < LEN_ADDR; i++) {
					if(i < LEN_ADDR - LEN_ADDR_INCODE) {
						ad2[i] = '0';
					}
					else { 
						ad2[i] =addr[i-(LEN_ADDR-LEN_ADDR_INCODE)]; 
					}
					
				}
				//add ad to ad2
				//!!!!!!need revise!!!!!!!!!!
				cpu.addition(ad, ad2, EA);
//				memory.read(EA, LEN_ADDR, ad2);
//				for(int i = 0; i < LEN_ADDR; i++) {
//					EA[i] = ad2[i];
//				}
				
			}
		}
		System.out.println(EA);
		return 0;
	}
	
	
	
}
