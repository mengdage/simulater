package gwu.csci.arc;
import gwu.csci.arc.isa.ISA;
import gwu.csci.arc.utility.Converter;
import gwu.csci.arc.utility.IOConnector;

enum REG_TYPE {
	GPR,
	XR,
	FR,
	CC,
	PC,
	IR
}

public class IntegratedCircuit {
	private static IntegratedCircuit ic; //singleton
	private static final int LEN_WORD = 18; // the length of the word
	private static final int LEN_FLOATING = 18; // the length of the floating point
	private static final int LEN_ADDR = 12; // the length of the address
	private static final int LEN_INSTRUCTION = 18; //the length of an instruction
	private static final int LEN_OPCODE = 6; //the length of the OPCODE in an instruct
	private static final int LEN_RFI = 2; //the length of the RFI in an instruction
	private static final int LEN_XFI = 2; //the length of the XFI in an instruction
	private static final int LEN_FFI = 1; //the length of the FFI in an instruction
	private static final int LEN_I = 1; //the length of the I in an instruction
	private static final int LEN_ADDR_INCODE= 7; //the length of the address in an instruction
	private static final int LEN_SBYTE = 6; //one sByte = six sBit
	//for floating point arithmetic usages
	private static final int LEN_SIGN = 1;
	private static final int LEN_EXP = 7;
	private static final int LEN_MTS = 10;
	//6 in binary
	private static final char[] ONE_12 = {'0','0','0','0','0','0','0','0', '0', '0', '0','1'}; //one sByte = six sBit


	/*used for communicating with the memory*/
	private char[] MAR = new char[LEN_ADDR];
	private char[] MBR = new char[LEN_WORD];
	/*$$$$$$$$$$$$$$-*/
	
	/*used for decompose a instruction; 18 bits*/
	private char[] opcode = new char[LEN_OPCODE];
	private char[] rfi = new char[LEN_RFI];
	private char[] xfi = new char[LEN_XFI];
	private char[] ffi = new char[LEN_FFI];
	private char[] I = new char[LEN_I];
	private char[] addr = new char[LEN_ADDR_INCODE];
	/*$$$$$$$$$$$$$$*/
	
	//the effective address
	private char[] EA = new char[LEN_ADDR];
	//the two operand address used to calculate EA
	private char[] ad = new char[LEN_ADDR];
	private char[] ad2 = new char[LEN_ADDR];
	
	/*used for ALU calculation*/
	//result from the ALU
	private char[] RES = new char[LEN_WORD];
	//first operand
	private char[] OP1 = new char[LEN_WORD];
	//second operand
	private char[] OP2 = new char[LEN_WORD];
	/*$$$$$$$$*/
	
	//the value read from the register file
	private char[] valR = new char[LEN_WORD];
	//the value read from the index file
	private char[] valX = new char[LEN_ADDR];
	//the value read from the instruction register
	private char[] valIR = new char[18];
	//the value read from the floating point register
	private char[] valF = new char[18];
	
	//the value read from the cc
	private char[] valC = new char[1];
//	private char[] valM = new char[LEN_WORD];
	//the pointer to the Memory address when writing instructions
	private char[] ins_pointer = new char[LEN_ADDR];
	
	// for floating point arithmetic usages
	private char[] FP1 = new char[18];
	private char[] FP2 = new char[18];
	private char[] fp_sign_x = new char[LEN_SIGN];
	private char[] fp_sign_y = new char[LEN_SIGN];
	private char[] exp_x = new char[LEN_EXP];
	private char[] exp_y = new char[LEN_EXP];
	private char[] mts_x = new char[LEN_MTS];
	private char[] mts_y = new char[LEN_MTS];
	
	
	private int[] p1In = new int[21];
	private int p1In_id = 0;
	public void printP1In1() {
		String sin = "";
		for (int i = 0; i < p1In.length -1; i++) {
			sin = sin+p1In[i]+ " ";
		}
		io.printToConsole(sin);
		
	}
	
	
	//the memory
	private Memory memory;
	
	//the CPU
	private CPU cpu;
	
	//
	private IOConnector io;
	
	/**
	 * constructor
	 * @param cpu
	 */
	private IntegratedCircuit(CPU cpu) {
		// TODO Auto-generated constructor stub
		memory = Memory.getInstance();
		this.cpu = cpu;
		io =IOConnector.getInstance();
		
		//initialization
		for (int i = 0; i < LEN_ADDR; i++) {
			EA[i] = '0';
			MAR[i] = '0';
			ins_pointer[i] = '0';
		}
		for (int i = 0; i < LEN_WORD; i++) {
			MBR[i] = '0';
			RES[i] = '0';
			OP1[i] = '0';
			OP2[i] = '0';
			
		}
		
		//start memory address at 15
		ins_pointer[LEN_ADDR - 1 - 3] = '1';
		ins_pointer[LEN_ADDR - 1 - 2] = '1';
		ins_pointer[LEN_ADDR - 1 - 1] = '1';
		ins_pointer[LEN_ADDR - 1 - 0] = '1';
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
	
	public static int getLenSByte() {
		return LEN_SBYTE;
	}
	public static char[] getOne() {
		return ONE_12;
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
	
	public char[] getFfi()
	{
		return ffi;
	}
	
	public void setFfi(char[] ffi)
	{
		this.ffi = ffi;
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
		case FR:
			cpu.readFR(c, rfi, len);break;
		case CC:
			cpu.readCC(c, Converter.conveterS2I(rfi, rfi.length));
			break;
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
			cpu.writeXR(c, xfi, len);
			break;
			
		case FR:
			cpu.writeFR(c, rfi, len);
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
	 * read from the Memory without a start position
	 * @param c: store the content
	 * @param len: the length of content
	 * @param addr: the address of the content
	 * @return 0
	 */
	public int readMem(char[] c, int len, char[] addr) {
		
		return readMem(c, 0, len, addr);
	}
	
	/**
	 * read from the Memory with a start position
	 * @param c: store the content
	 * @param startPos the start position of c
	 * @param len: the length of content
	 * @param addr: the address of the content
	 * @return 0
	 */
	public int readMem(char[] c,int startPos, int len, char[] addr) {
		
		//memory.read(c, startPos,len, addr);
		cpu.readMem(c, startPos, len, addr);
		
		return 0;
	}
	/**
	 * write to the Memory without a start position
	 * @param c then content to be written
	 * @param len the length of the content
	 * @param addr the address of the memory to start writing
	 * @return
	 */
	public int writeMem(char[] c, int len, char[] addr) {
		return writeMem(c, 0, len, addr);
	}
	/**
	 * write to the Memory with a start position
	 * @param c then content to be written
	 * @param startPos the start position of c
	 * @param startPos the start position of c
	 * @param len the length of the content
	 * @param addr the address of the memory to start writing
	 * @return
	 */
	public int writeMem(char[] c, int startPos,int len, char[] addr) {
		//memory.write(c, startPos, len, addr);
		cpu.writeMem(c, startPos, len, addr);
		
		return 0;
	}
	
	/**
	 * write an instruction into memory
	 * @param ins the instruction
	 * @param len the length of the instruction
	 * @param ins_addr the address where the instruction is
	 * @return 0
	 */
	public int writeIns(char[] ins, int len, char[] ins_addr) {
		memory.write(ins, len, ins_pointer);
		for (int i = 0; i < ins_addr.length; i++) {
			ins_addr[i] = ins_pointer[i];
		}
//		Converter.addrConverterI2S(len, ins_addr);
		//increase the ins_pointer to the next place available to write instructions
		cpu.addition(ins_pointer, ISA.oneInstranceLengthInSByte, ins_pointer);
		return 0;
	}
	/**
	 * Load a word from Memory to general purpouse register
	 * the Memory address is EA
	 * or the Memory address is M(EA)
	 * the register id is rfi
	 * For LDR
	 * @return 0
	 */
	public int M2R() {
		if(I[0] == '0') {
			//read a word of memory at EA into MBR
			readMem(MBR, LEN_WORD, EA);
			//write MRB into GPR
			writeReg(MBR, LEN_WORD, REG_TYPE.GPR);
		}
		else {
			//read the actual address from memory at EA into MBR
			readMem(MAR, LEN_ADDR, EA);
			//read a word from memory at MAR into MBR
			readMem(MBR, LEN_WORD, MAR);
			writeReg(MBR, LEN_WORD, REG_TYPE.GPR);
			
		}
		return 0;
	}
	/**
	 * store a word from general purpose register to Memory
	 * the Memory address is EA
	 * or the Memory address is M(EA)
	 * the register id is rfi
	 * For STR
	 * @return 0
	 */
	public int R2M() {
		if(I[0] == '0') {
			//read a word of GPR into valR
			readReg(valR, LEN_WORD, REG_TYPE.GPR);
			//write a word into memory at EA
			io.printString("STR: store " + new String(valR) + "to memory at: " + new String(EA));
			writeMem(valR, LEN_WORD, EA);
		}
		else {
			//read a word of GPR into valR
			readReg(valR, LEN_WORD, REG_TYPE.GPR);
			//read the actual address from memory at EA into MAR
			readMem(MAR, LEN_ADDR, EA);
			//write the valR into memory at MAR
			writeMem(valR, LEN_WORD, MAR);
		}
		return 0;
		
	}
	/**
	 * load the register with addr or M(addr)
	 * the register id is rfi
	 * For LDA
	 * @return
	 */
	public int A2R() {
		if (I[0] == '0') {
			//write the EA into general purpose register
			writeReg(EA, EA.length, REG_TYPE.GPR);
		}
		else {
			//read the actual address from memory at EA into MAR
			readMem(MAR, LEN_ADDR, EA);
			//write the MAR into general purpose register
			writeReg(MAR, LEN_ADDR, REG_TYPE.GPR);
		}
		
		return 0;
	}
	
	/**
	 * load the register with addr or M(addr)
	 * the register id is rfi
	 * for LDX
	 * @return
	 */
	public int A2X() {
		if (I[0] == '0') {
			//write the EA into index register
			readMem(MAR, MAR.length, EA);
			writeReg(MAR, MAR.length, REG_TYPE.XR);
		}
		else {
			//read the actual address from memory at EA into MAR
			readMem(MAR, LEN_ADDR, EA);
			readMem(MAR, MAR.length, MAR);
			//write the MAR into index register
			writeReg(MAR, LEN_ADDR, REG_TYPE.XR);
		}
		
		return 0;
	}
	/**
	 * Store Index Register to Memory
	 * For STX
	 * @return
	 */
	public int X2M() {
		if(I[0] == '0') {
			//read the index address from index register into valR
			cpu.readXR(valX, xfi, valR.length);
			//write the content valR into memory at EA
			writeMem(valX, valR.length, EA);
		}
		else{
			//read the index address from index register into valR
			cpu.readXR(valX, xfi, valX.length);
			//read the actual address from memory at EA into MAR
			readMem(MAR, LEN_ADDR, EA);
//			readMem(MAR, MAR.length, MAR);
			//write the content valR into memory at EA
			writeMem(valX, valX.length, MAR);
		}
		return 0;
		
	}
	
	/**
	 * If gpr is 0
	 * If gpr is not 0
	 * For JZ
	 * @return
	 */
	public int ic_jz() {
		//test if c(r) is zero
		readReg(valR, valR.length, REG_TYPE.GPR);
		int flag = 0;
		for(int i = 0; i < valR.length; i++) {
			if(valR[i] != '0') {
				flag = 1;
			}
		}
		
		if(flag == 0) { //c(r) is zero, change the PC
			if(I[0] == '0') {
				cpu.writePC(EA, EA.length);
			} else {
				readMem(MAR, MAR.length, EA);
				cpu.setNewPC(MAR, MAR.length);
			}
			return 0;
		} else {
			return 1;
		}
	}
	
	/**
	 * If gpr is 0
	 * If gpr is not 0
	 * For JNE
	 * @return
	 */
	public int ic_jne() {
		//test if c(r) is zero
		readReg(valR, valR.length, REG_TYPE.GPR);
		int flag = 0;
		for(int i = 0; i < valR.length; i++) {
			if(valR[i] != '0') {
				flag = 1;
			}
		}
		
		if(flag == 1) { //c(r) is not zero, change the PC
			if(I[0] == '0') {
				cpu.writePC(EA, EA.length);
			} else {
				readMem(MAR, MAR.length, EA);
				cpu.setNewPC(MAR, MAR.length);
			}
			return 0;
		} else {
			return 1;
		}
	}
	/**
	 * If c is
	 * If c is not 0
	 * For JCC
	 * @return 0 if c is set and newPC is update; 1 otherwise
	 */
	public int ic_jcc() {
		readReg(valC, valC.length, REG_TYPE.CC);
		if(valC[0] == '1') {
			if (I[0] =='0') {
				io.printToLog("$JMP: jump to Memory "+Converter.conveterS2I(EA, EA.length));
				cpu.setNewPC(EA, EA.length);
				
			} else {
				
				//readMem(MAR, MAR.length, EA);
				io.printToLog("$JMP: jump to Memory "+Converter.conveterS2I(EA, MAR.length));
				cpu.setNewPC(EA, EA.length);
			}
			return 0;
		}
		return 1;
	}
	/**
	 * unconditional jump 
	 * for jmp
	 * @return
	 */
	public int ic_jmp() {
		if(I[0] == '0') {
			io.printToLog("$JMP: jump to Memory "+Converter.conveterS2I(EA,EA.length));
			cpu.setNewPC(EA, EA.length);
			
		} else {
			//readMem(MAR, MAR.length, EA);
			io.printToLog("$JMP: jump to Memory "+Converter.conveterS2I(EA,EA.length));
			cpu.setNewPC(EA, EA.length);
			
		}
		return 0;
	}
	/**
	 * Jump and Save Return Address
	 * @return
	 */
	public int ic_jsr() {
		cpu.readPC(valR, LEN_ADDR);
		cpu.addition(ISA.oneInstranceLengthInSByte,valR, cpu.getNewPC());
		rfi[1] = '1';
		rfi[0] = '1';
		writeReg(cpu.getNewPC(), LEN_ADDR, REG_TYPE.GPR);
		
		if(I[0] == '0') {
			cpu.writePC(EA, EA.length);
			
		} else {
			readMem(MAR, MAR.length, EA);
			cpu.setNewPC(MAR, MAR.length);
			
		}
		return 0;
	}
	/**
	 * 
	 * @return
	 */
	public int ic_rfs() {
		rfi[1] = '0';
		rfi[0] = '0';
		writeReg(addr, addr.length, REG_TYPE.GPR);
		rfi[1] = '1';
		rfi[0] = '1';
		readReg(valR, valR.length, REG_TYPE.GPR);
		cpu.setNewPC(valR, valR.length);
		return 0;
	}
	/**
	 * Subtract one and Branch
	 * @return 0 if c(r)>=0 and newPC is update; 1 otherwise
	 */
	public int ic_sob() {
		readReg(valR, valR.length, REG_TYPE.GPR);
		int r = Converter.conveterS2I(valR, valR.length);
		r = r-1;
		io.printToLog("$SOB: r-1 = " + r);
		Converter.converterI2S(r, valR);
		//c(r) <- c(r)-1
		writeReg(valR, valR.length, REG_TYPE.GPR);
		if(r > 0) {
			
			if(I[0] == '0') {
				io.printToLog("$SOB: r-1 = "+r+" > 0 and I =0");
				io.printToLog("$SOB: PC = " + Converter.conveterS2I(EA, EA.length));
				cpu.setNewPC(EA, EA.length);
			} else {
				//readMem(MAR, MAR.length, EA);
				io.printToLog("$SOB: r-1 = "+r+" > 0 and I =1");
				io.printToLog("$SOB: PC = " + Converter.conveterS2I(EA, EA.length));
				cpu.setNewPC(EA, EA.length);
			}
			return 0;
		} else {
			io.printString("r <= 0");
			return 1;
		}
	}
	/**
	 * 
	 * @return 0 if c(r)>=0 and newPC is update; 1 otherwise
	 */
	public int ic_jge(){
		//test if c(r) is zero
		readReg(valR, valR.length, REG_TYPE.GPR);
		
		if(valR[0] == '0') { //c(r) is greater than or equal to zero, change the PC
			if(I[0] == '0') {
				cpu.setNewPC(EA, EA.length);
			} else {
				readMem(MAR, MAR.length, EA);
				cpu.setNewPC(MAR, MAR.length);
			}
			return 0;
		} else {
			return 1;
		}
	}
	/**
	 * Add memory to register
	 * @return 0 succeed
	 */
	public int ic_amr() {
		readMem(MBR, MBR.length, EA);
		readReg(valR, valR.length, REG_TYPE.GPR);
		io.printToLog("$AMR: add "+Converter.conveterS2I(MBR,MBR.length) + " to GPR(" + Converter.conveterS2I(valR,valR.length)+")");
		cpu.addition(valR, MBR, valR);
		writeReg(valR, valR.length, REG_TYPE.GPR);
		return 0;
	}
	
	/**
	 * subtract memory from register 
	 * @return 0 succeed
	 */
	public int ic_smr() {
		readMem(MBR, MBR.length, EA);
		readReg(valR, valR.length, REG_TYPE.GPR);
		io.printToLog("$SMR: GPR " + Converter.conveterS2I(rfi, rfi.length) + " = "+ Converter.conveterS2I(valR,valR.length) + " - " + Converter.conveterS2I(MBR,MBR.length));
		cpu.subtraction(valR, MBR, valR);
		writeReg(valR, valR.length, REG_TYPE.GPR);
		return 0;
	}
	/**
	 * store immediate to general purpose register
	 * @return
	 */
	public int ic_stir() {
		io.printToLog("$STIR: GPR " + Converter.conveterS2I(rfi, rfi.length) + " = " + Converter.conveterS2I(addr, addr.length));
		
		writeReg(addr, addr.length, REG_TYPE.GPR);
		return 0;
	}
	/**
	 * store immediate to index register
	 * @return
	 */
	public int ic_stix() {
		io.printToLog("$STIX: XR " + Converter.conveterS2I(xfi, xfi.length) + " = " + Converter.conveterS2I(addr, addr.length));
		
		writeReg(addr, addr.length, REG_TYPE.XR);
		return 0;
	}
	/**
	 * add immediate to register
	 * @return
	 */
	public int ic_air() {
		readReg(valR, valR.length, REG_TYPE.GPR);
		io.printToLog("$AIR: GPR " + Converter.conveterS2I(rfi, rfi.length) + " = "+ Converter.conveterS2I(valR, valR.length) + " + " + Converter.conveterS2I(addr, addr.length));
		cpu.addition(valR, addr, valR);
		writeReg(valR, valR.length, REG_TYPE.GPR);
		return 0;
	}
	
	/**
	 * add immediate to index register
	 * @return
	 */
	public int ic_aix() {
		readReg(valX, valX.length, REG_TYPE.XR);
		io.printToLog("$AIX: XR " + Converter.conveterS2I(xfi,xfi.length) + " = "+ Converter.conveterS2I(valX, valX.length) + " + " + Converter.conveterS2I(addr, addr.length));
		cpu.addition(valX, addr, valX);
		writeReg(valX, valX.length, REG_TYPE.XR);
		return 0;
	}
	
	/**
	 * subtract immediate to register
	 * @return
	 */
	public int ic_sir() {
		readReg(valR, valR.length, REG_TYPE.GPR);
		io.printToLog("$SIR: GPR " + Converter.conveterS2I(rfi, rfi.length) + " = "+ Converter.conveterS2I(valR,valR.length) + " - " + Converter.conveterS2I(addr,addr.length));
		cpu.subtraction(valR, addr, valR);
		writeReg(valR, valR.length, REG_TYPE.GPR);
		return 0;
	}
	
	
	/**
	 * add immediate to index register
	 * @return
	 */
	public int ic_six() {
		readReg(valX, valX.length, REG_TYPE.XR);
		io.printToLog("$SIX: XR " + Converter.conveterS2I(xfi,xfi.length) + " = "+ Converter.conveterS2I(valX,valX.length) + " - " + Converter.conveterS2I(addr,addr.length));
		cpu.subtraction(valX, addr, valX);
		writeReg(valX, valX.length, REG_TYPE.XR);
		return 0;
	}
	public int ic_in(){
		int devid = Converter.conveterS2I(addr, addr.length);
		switch (devid) {
		case 0:
			int r = io.readInt();
			if (p1In_id == 21) {
				p1In_id=0;
			}
			p1In[p1In_id] = r;
			p1In_id++;
			Converter.converterI2S(r, valR);
			io.printToLog("$IN: write " + r + " into GPR" + Converter.conveterS2I(rfi, rfi.length));
			writeReg(valR, valR.length, REG_TYPE.GPR);
			break;

		default:
			break;
		}
		return 0;
	}
	
	public int ic_out(){
		int devid = Converter.conveterS2I(addr, addr.length);
		switch (devid) {
		case 1:
			readReg(valR, valR.length, REG_TYPE.GPR);
			int r = Converter.conveterS2I(valR, valR.length);
			//io.printString("$OUT: "+ r);
			io.printToConsole("$OUT: "+ r);
			break;

		default:
			break;
		}
		return 0;
	}
	
	public int ic_fout(){
		int devid = Converter.conveterS2I(addr, addr.length);
		switch (devid) {
		case 1:
			readReg(valR, valR.length, REG_TYPE.FR);
			double r = Converter.converterS2F(valR, valR.length);
			//io.printString("$OUT: "+ r);
			io.printToConsole("$OUT: "+ r);
			break;

		default:
			break;
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
	
	public int ic_fadd()
	{	
		// get floating point operands from register and addr
		readReg(FP1, FP1.length, REG_TYPE.FR);
		
		if (I[0] == '0')
		{
			readMem(FP2, LEN_FLOATING, EA);
		}
		else
		{
			readMem(MAR, LEN_ADDR, EA);
			readMem(FP2, LEN_FLOATING, MAR);
		}
		
		double dec_x = 0;
		double dec_y = 0;
		
		// convert 18 bits binary floating point numbers into decimal
		dec_x = Converter.converterS2F(FP1, FP1.length);
		dec_y = Converter.converterS2F(FP2, FP2.length);
		
		// floating point addition in decimal
		dec_x += dec_y;
		
		// convert sum of 2 floating point numbers back to 18 bits binary from decimal
		FP1 = Converter.converterF2S(dec_x);
		
		// write back
		writeReg(FP1, FP1.length,REG_TYPE.FR);
		
		return 0;
	}
	public int ic_fsub()
	{
		// get floating point operands from register and addr
		readReg(FP1, FP1.length, REG_TYPE.FR);
				
		if (I[0] == '0')
		{
			readMem(FP2, LEN_FLOATING, EA);
		}
		else
		{
			readMem(MAR, LEN_ADDR, EA);
			readMem(FP2, LEN_FLOATING, MAR);
		}
				
		double dec_x = 0;
		double dec_y = 0;
				
		// convert 18 bits binary floating point numbers into decimal
		dec_x = Converter.converterS2F(FP1, FP1.length);
		dec_y = Converter.converterS2F(FP2, FP2.length);
				
		// floating point subtraction in decimal
		dec_x -= dec_y;
				
		// convert sum of 2 floating point numbers back to 18 bits binary from decimal
		FP1 = Converter.converterF2S(dec_x);
				
		// write back
		writeReg(FP1, FP1.length,REG_TYPE.FR);
		
		return 0;
	}
	public int ic_vadd()
	{
		
		
		return 0;
	}
	public int ic_vsub()
	{
		
		
		return 0;
	}
	public int ic_cnvrt()
	{
		if (I[0] == '0')
		{
			readMem(FP1, LEN_FLOATING, EA);
			writeReg(FP1, LEN_FLOATING, REG_TYPE.GPR);
		}
		else
		{
			readMem(FP1, LEN_FLOATING, EA);
			writeReg(FP1,LEN_FLOATING, REG_TYPE.FR);
		}
		
		return 0;
	}
	
	/**
	 * load a floating point number from memory to floating point register
	 * @return
	 */
	public int ic_ldfr()
	{
		if (I[0] == '0')
		{
			//need editing
			//read a word of memory at EA into MBR
			readMem(MBR, LEN_FLOATING, EA);
			//write MRB into GPR
			writeReg(MBR, LEN_FLOATING, REG_TYPE.FR);
		}
		else
		{
			//need editing
			//read the actual address from memory at EA into MBR
			readMem(MAR, LEN_ADDR, EA);
			//read a word from memory at MAR into MBR
			readMem(MBR, LEN_FLOATING, MAR);
			writeReg(MBR, LEN_FLOATING, REG_TYPE.FR);
		}
		
		return 0;
	}
	
	/**
	 * store a floating point number from floating point register to memory
	 */
	public int ic_stfr()
	{
		if (I[0] == '0')
		{
			//read floating point register to valR
			readReg(valR, LEN_FLOATING, REG_TYPE.FR);
			//store valR to the destination in the Memory
			writeMem(valR, LEN_FLOATING, EA);
		}
		else
		{
			//read floating point register to valR
			readReg(valR, LEN_FLOATING, REG_TYPE.FR);
			//read the actual address from memory at EA into MBR
			readMem(MAR, LEN_ADDR, EA);
			//store valR to the destination in the Memory
			writeMem(valR, LEN_FLOATING, MAR);
		}
		
		return 0;
	}
	
	public int decode() {
		
		int n;
		readReg(MAR, LEN_ADDR, REG_TYPE.PC); // MAR <- R(RFI)
		readMem(MBR, LEN_WORD, MAR); //MBR <- M(MAR)
		writeReg(MBR, LEN_WORD, REG_TYPE.IR); // IR <- R(MBR)
		readReg(valIR, LEN_INSTRUCTION, REG_TYPE.IR);
		for(n = 0; n < LEN_INSTRUCTION; n++) {
			if(n<LEN_OPCODE) { //OPCODE <- IR0-5
				opcode[n] = valIR[n];
			}
			else if(n<LEN_OPCODE+LEN_RFI) {
				//RFI <- IR6-7
				rfi[n-LEN_OPCODE] = valIR[n];
			}
			else if(n<LEN_OPCODE + LEN_RFI + LEN_XFI) {
				//XFI <- IR8-9
				xfi[n - LEN_OPCODE - LEN_RFI] = valIR[n];
			}
			else if(n<LEN_OPCODE + LEN_RFI + LEN_XFI + LEN_I) {
				//I <- IR10
				I[n - LEN_OPCODE - LEN_RFI- LEN_XFI] = valIR[n];
			}
			else if(n<LEN_OPCODE + LEN_RFI + LEN_XFI + LEN_I + LEN_ADDR_INCODE) {
				//ADDR <- IR11-17
				addr[n - LEN_OPCODE - LEN_RFI- LEN_XFI - LEN_I] = valIR[n];
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
				memory.read(EA, LEN_ADDR, addr);
				readMem(EA, EA.length, EA);
				
			}
			else { //IF XFI = 1, 2, 3
				//EA <- M(R(XFI) + ADDR)
				
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
				cpu.readMem(EA, EA.length, EA);
				//readMem(EA, EA.length, EA);
//				memory.read(EA, LEN_ADDR, ad2);
//				for(int i = 0; i < LEN_ADDR; i++) {
//					EA[i] = ad2[i];
//				}
				
			}
		}
		io.printString("EA: " + new String(EA));
		return 0;
	}
	
	
	
}
