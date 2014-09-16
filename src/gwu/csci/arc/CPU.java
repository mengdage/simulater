package gwu.csci.arc;

import gwu.csci.arc.utility.OPERATORS;

public class CPU {

	//singleton
	private static CPU cpu;
	
	//the index of the register file, R0, R1, R2, R3, 18 bits each, General Purpose Register
	private RegisterFile rf = new RegisterFile();
	//the condition code, 4 bits, overflow, underflow, division by zero, equal or not
	private ConditionCode cc = new ConditionCode();
	//the index register, X1, X2, X3, 12 bits each, for index addressing
	private IndexRegister xr = new IndexRegister();
	//the program counter, 12 bit, storing the address of the next instruction
	private ProgramCounter pc = new ProgramCounter();
	//the instruction register, 18 bit, storing the content of the next instruction 
	private InstructionRegister ir = new InstructionRegister();
	
	//the algorithm logic unit
	private ALU alu = new ALU();
	//the integrated circuit
	private IntegratedCircuit ic;
	
	//result from the ALU
	private char[] RES = new char[IntegratedCircuit.getLenWord()];
	//first operand
	private char[] OP1 = new char[IntegratedCircuit.getLenWord()];
	//second operand
	private char[] OP2 = new char[IntegratedCircuit.getLenWord()];
	//
	private char[] newPC = new char[IntegratedCircuit.getLenAddr()];
	private CPU() {
		// TODO Auto-generated constructor stub
		System.out.println("I am the CPU. I am starting up!!");
		alu = new ALU();
		ic = IntegratedCircuit.getInstance(this);
	}
	//singleton: the only way to get a cpu object
	public static CPU getInstance() {
		//the CPU class has not been instantiated yet
		//new an object and return the object
		if(cpu == null) {
			cpu = new CPU();
			return cpu;
		}
		else {
			//return the object which has already been instantiated
			return cpu;
		}
	}
	
	//Getter and Setter
	public char[] getRES() {
		return RES;
	}
	public void setRES(char[] rES) {
		RES = rES;
	}
	public char[] getEA() {
		return ic.getEA();
	}
	public char[] getRfi() {
		return ic.getRfi();
	}
	
	public char[] getNewPC() {
		return newPC;
	}
	public void setNewPC(char[] newPC) {
		this.newPC = newPC;
	}
	public char[] getMAR() {
		return ic.getMAR();
	}
	public char[] getMBR() {
		return ic.getMBR();
	}
	/**
	 * write an instruction into memory
	 * @param ins the instruction
	 * @param len the length of the instruction
	 * @param ins_addr the address where the instruction is
	 * @return 0
	 */
	public int writeIns(char[] ins, int len, char[] ins_addr) {
		ic.writeIns(ins, len, ins_addr);
		return 0;
	}
	/**
	 * write c of length len into id-th register
	 * @param c the content to be written
	 * @param id the ID of the register used
	 * @param len the length of the content
	 * @return
	 */
	public int writeGPR(char[] c, char[] id, int len) {
		if(id[1] == '0') {
			if (id[0] == '0' ){ // 00(2) = 0(10) register
				if(rf.setR0(c, len) == 0)
					System.out.println("CPU: writing register 0 succeed");
			}else {//10(2) = 2(10) register
				if(rf.setR2(c, len) == 0)
					System.out.println("CPU: writing register 2 succeed");
			}
		}
		else {
			if (id[0]=='0') {//01(2) = 1(10) register
				if(rf.setR1(c, len) == 0)
					System.out.println("CPU: writing register 1 succeed");
			}
			else { //11(2) = 3(10) register
				if(rf.setR3(c, len) == 0)
					System.out.println("CPU: writing register 3 succeed");
			}
		}
		
		return 0;
	}
	
	/**
	 * read the content of length len from id-th register into c
	 * @param c store the content read
	 * @param id the ID of the register used
	 * @param len the length of the content
	 * @return
	 */
	public int readGPR(char[] c, char[] id, int len) {
		if(id[1] == '0') {
			if (id[0] == '0' ){ // 00(2) = 0(10) register
				if(rf.getR0(c, len) == 0)
					System.out.println("CPU: reading register 0 succeed");
			}else {//10(2) = 2(10) register
				if(rf.getR2(c, len) == 0)
					System.out.println("CPU: reading register 2 succeed");
			}
		}
		else {
			if (id[0]=='0') {//01(2) = 1(10) register
				if(rf.getR1(c, len) == 0)
					System.out.println("CPU: reading register 1 succeed");
			}
			else { //11(2) = 3(10) register
				if(rf.getR3(c, len) == 0)
					System.out.println("CPU: reading register 3 succeed");
			}
		}
		return 0;
	}

	/**
	 * actually invoke the ic's readMem metohd
	 * @param c: store the content
	 * @param len: the length of content
	 * @param addr: the address of the content
	 * @return 0
	 */
	public int readMem(char[] c, int len, char[] addr) {
		
		ic.readMem(c, len, addr);
		
		return 0;
	}
	
	/**
	 * actually invoke the ic's writeMem metohd
	 * @param c then content to be written
	 * @param len the length of the content
	 * @param addr the address of the memory to start writing
	 * @return
	 */
	public int writeMem(char[] c, int len, char[] addr) {
		ic.writeMem(c, len, addr);
		
		return 0;
	}
	/**
	 * write c of length len into id-th index register
	 * @param c the content to be written
	 * @param id the ID of the register used
	 * @param len the length of the content
	 * @return
	 */
	public int writeXR(char[] c, char[] id, int len){
		if(id[1] == '0') {
			if (id[0] == '0' ){ // 00(2) = 0(10) illegal id
				
			}else {//10(2) = 2(10) register
				if(xr.setX2(c, len) == 0)
					System.out.println("CPU: writing index register 2 succeed");
			}
		}
		else {
			if (id[0]=='0') {//01(2) = 1(10) register
				if(xr.setX1(c, len) == 0)
					System.out.println("CPU: writing index register 1 succeed");
			}
			else { //11(2) = 3(10) register
				if(xr.setX3(c, len) == 0)
					System.out.println("CPU: writing index register 3 succeed");
			}
		}
		return 0;
	}
	
	/**
	 * read the content of length len from id-th index register into c 
	 * @param c store the content read
	 * @param id the ID of the register used
	 * @param len the length of the content
	 * @return 0
	 */
	public int readXR(char[] c, char[] id, int len) {
		if(id[1] == '0') {
			if (id[0] == '0' ){ // 00(2) = 0(10) illegal id
				
			}else {//10(2) = 2(10) register
				if(xr.getX2(c, len) == 0)
					System.out.println("CPU: reading index register 2 succeed");
			}
		}
		else {
			if (id[0]=='0') {//01(2) = 1(10) register
				if(xr.getX1(c, len) == 0)
					System.out.println("CPU: reading index register 1 succeed");
			}
			else { //11(2) = 3(10) register
				if(xr.getX3(c, len) == 0)
					System.out.println("CPU: reading index register 3 succeed");
			}
		}
		return 0;
	}
	
	/**
	 * write c of length len into condition code register 
	 * @param c the content to be written
	 * @param len the length of the content
	 * @return
	 */
	public int writeCC(char[] c, int len) {
		if(cc.setCC(c, len) == 0) 
			System.out.println("CPU: writing condition code succeed");
		return 0;
	}

	/**
	 * read the condition code register
	 * @param c store the content
	 * @param len the length of the content
	 * @return 0
	 */
	public int readCC(char[] c, int len) {
		if(cc.getCC(c, len) == 0) 
			System.out.println("CPU: reading condition code succeed");
		return 0;
	}
	
	/**
	 *write c of length len into program counter register 
	 * @param c the content to be written
	 * @param len the length of the content
	 * @return 0
	 */
	public int writePC(char[] c, int len) {
		if(pc.setPC(c, len) == 0) 
			System.out.println("CPU: writing program counter register succeed");
		return 0;
	}
	
	/**
	 * read program counter register
	 * @param c store the content
	 * @param len the length of the content
	 * @return 0
	 */
	public int readPC(char[] c, int len) {
		if(pc.getPC(c, len) == 0) 
			System.out.println("CPU: reading program counter register succeed");
		return 0;
	}

	/**
	 *write c of length len into instruction register register 
	 * @param c the content to be written
	 * @param len the length of the content
	 * @return
	 */
	public int writeIR(char[] c, int len) {
		if(ir.setIR(c, len) == 0) 
			System.out.println("CPU: writing instruction register succeed");
		return 0;
	}

	/**
	 *read Instruction Register 
	 * @param c: store the content
	 * @param len: the length of the content
	 * @return
	 */
	public int readIR(char[] c, int len) {
		if(ir.getIR(c, len) == 0) 
			System.out.println("CPU: reading instruction register succeed");
		return 0;
	}
	/**
	 * call the M2R() method in IntegratedCircuit
	 * @return
	 */
	public int M2R() {
		ic.M2R();
		return 0;
	}
	/**
	 * call the R2M() method in IntegratedCircuit
	 * @return
	 */
	public int R2M() {
		ic.R2M();
		return 0;
	}
	
	public int A2R() {
		ic.A2R();
		return 0;
	}
	
	public int A2X() {
		ic.A2X();
		return 0;
	}
	public int X2M() {
		ic.X2M();
		return 0;
	}
	//!******need revised******!
	public int addition(char[] op1, char[] op2, char[] result) {
		//return alu.addition(44, 55);
		alu.addition(op1, op2, op1.length, result);
		return 0;
	}
	
	/**
	 * decode process
	 * actually invoke the ic's decode method
	 * @return
	 */
	public int decode() {
		return ic.decode();
	}
	/**
	 * EA calculation process
	 * actually invoke the ic's calcEa method
	 * @return
	 */
	public int calcEA() {
		return ic.calcEA();
	}
	
	/**
	 * ALU calculation process
	 * @param p1 operand 1
	 * @param p2 operand 2
	 * @param opr operator
	 * @return 0
	 */
	public int ALUcalc(OPERATORS opr) {
		switch (opr) {
		case add:
			alu.addition(OP1, OP2, OP1.length, RES);
			break;
		case subtract:
			alu.subtraction(OP1, OP2, OP1.length, RES);
			break;

		default:
			break;
		}
		return 0;
	}
	
	/**
	 * put register in op1
	 * put Immediate in op2
	 * @return
	 */
	public int RIOperandPreparation() {
		readGPR(OP1, getRfi(), OP1.length);
		char[] addr = ic.getAddr();
		for (int i = 0; i < IntegratedCircuit.getLenWord(); i++) {
			if(i < (IntegratedCircuit.getLenWord()-IntegratedCircuit.getLenAddrIncode()) ) {
				OP2[i] = '0';
			}
			else {
				int j = i-(IntegratedCircuit.getLenWord()-IntegratedCircuit.getLenAddrIncode());
				OP2[i] = addr[j];
			}
			
		}
		return 0;
	}
	//!!!!!!!!!!need reverse!!!!!!!!!!!!!
	/**
	 * update the pc
	 * the new pc is stored in newPC
	 * @return
	 */
	public int pcUpdate() {
//		char[] plusOne = {'0','0','0','0','0','0','0','0','0','0','0','1'};
//		addition(ic.getMAR(), plusOne, newPC);
		writePC(newPC, IntegratedCircuit.getLenAddr());
		return 0;
	}
}
