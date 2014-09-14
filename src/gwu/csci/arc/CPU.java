package gwu.csci.arc;

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
				if(xr.getX3(c, len) == 0)
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
			System.out.println("CPU: reading instruction register register succeed");
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
	
}
