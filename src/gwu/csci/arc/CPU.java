package gwu.csci.arc;

import gwu.csci.arc.isa.ISA;
import gwu.csci.arc.utility.IOConnector;
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
	// floating point registers, FR0, FR1, 18 bits each, for floating point arithmetic
	private FloatingRegister fr = new FloatingRegister();
	//the program counter, 12 bit, storing the address of the next instruction
	private ProgramCounter pc = new ProgramCounter();
	//the instruction register, 18 bit, storing the content of the next instruction 
	private InstructionRegister ir = new InstructionRegister();
	
	//the algorithm logic unit
	private ALU alu;
	//the integrated circuit
	private IntegratedCircuit ic;
	//the cache
	private Cache cache;
	
	//result from the ALU
	private char[] RES = new char[IntegratedCircuit.getLenWord()];
	//first operand
	private char[] OP1 = new char[IntegratedCircuit.getLenWord()];
	//second operand
	private char[] OP2 = new char[IntegratedCircuit.getLenWord()];
	//the value that will be write into PC
	private char[] newPC = new char[IntegratedCircuit.getLenAddr()];
	//the pointer to the Memory address when writing instructions
	private char[] ins_pointer = {'0','0','0','0','0','0', '0', '0','1', '1', '1', '1'};
	private char[] ins_pointer2 = {'0','0','0','0','0','0', '0', '0','0', '0', '1', '1'};
	
	private IOConnector io = IOConnector.getInstance();
	
	//the address used when read or write cache
	private char[] addressCache = new char[IntegratedCircuit.getLenAddr()];
	
	private CPU() {
		// TODO Auto-generated constructor stub
		io.printString("I am the CPU. I am starting up!!");
		alu = ALU.getInstance(this);
		ic = IntegratedCircuit.getInstance(this);
		cache = Cache.getInstance();
		
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
	public void setNewPC(char[] newPC, int len) {
		for (int i = 0; i < len; i++) {
			this.newPC[i] = newPC[i];
		}
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
	 * @param ins_addr the address where the instruction is in the memory
	 * @return 0
	 */
	public int writeIns(char[] ins, int len, char[] ins_addr) {
		//ic.writeIns(ins, len, ins_addr);
		writeMem(ins, ins.length, ins_pointer2);
		for (int i = 0; i < ins_addr.length; i++) {
			ins_addr[i] = ins_pointer2[i];
		}
		cpu.addition(ins_pointer2, ISA.oneInstranceLengthInSByte, ins_pointer2);
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
					io.printString("CPU: writing register 0 succeed");
			}else {//10(2) = 2(10) register
				if(rf.setR2(c, len) == 0)
					io.printString("CPU: writing register 2 succeed");
			}
		}
		else {
			if (id[0]=='0') {//01(2) = 1(10) register
				if(rf.setR1(c, len) == 0)
					io.printString("CPU: writing register 1 succeed");
			}
			else { //11(2) = 3(10) register
				if(rf.setR3(c, len) == 0)
					io.printString("CPU: writing register 3 succeed");
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
					io.printString("CPU: reading register 0 succeed");
			}else {//10(2) = 2(10) register
				if(rf.getR2(c, len) == 0)
					io.printString("CPU: reading register 2 succeed");
			}
		}
		else {
			if (id[0]=='0') {//01(2) = 1(10) register
				if(rf.getR1(c, len) == 0)
					io.printString("CPU: reading register 1 succeed");
			}
			else { //11(2) = 3(10) register
				if(rf.getR3(c, len) == 0)
					io.printString("CPU: reading register 3 succeed");
			}
		}
		return 0;
	}

	/**
	 * actually invoke the ic's readMem method
	 * @param c: store the content
	 * @param len: the length of content
	 * @param addr: the address of the content
	 * @return 0
	 */
	public int readMem(char[] c, int len, char[] addr) {
		return readMem(c, 0, len, addr);
	}
	
	public int readMem(char[] c, int startPos, int len, char[] addr) {
		for (int i = 0; i < addr.length; i++) {
			addressCache[i] = addr[i];
		}
		int numByte = len /IntegratedCircuit.getLenSByte();
		for(int i =0; i<numByte; i++){
			cache.read(c, startPos+i*IntegratedCircuit.getLenSByte(), IntegratedCircuit.getLenSByte(), addressCache);
			if(i+1 < numByte) {
				addition(IntegratedCircuit.getOne(), addressCache, addressCache);
			}
		}
		
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
		return writeMem(c, 0, len, addr);
	}
	public int writeMem(char[] c, int startPos, int len, char[] addr) {
		for (int i = 0; i < addr.length; i++) {
			addressCache[i] = addr[i];
		}
		int numByte = len /IntegratedCircuit.getLenSByte();
		for(int i =0; i<numByte; i++){
			cache.write(c, startPos+i*IntegratedCircuit.getLenSByte(), IntegratedCircuit.getLenSByte(), addressCache);
			if(i+1 < numByte) {
				addition(IntegratedCircuit.getOne(), addressCache, addressCache);
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
					io.printString("CPU: writing index register 2 succeed");
			}
		}
		else {
			if (id[0]=='0') {//01(2) = 1(10) register
				if(xr.setX1(c, len) == 0)
					io.printString("CPU: writing index register 1 succeed");
			}
			else { //11(2) = 3(10) register
				if(xr.setX3(c, len) == 0)
					io.printString("CPU: writing index register 3 succeed");
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
					io.printString("CPU: reading index register 2 succeed");
			}
		}
		else {
			if (id[0]=='0') {//01(2) = 1(10) register
				if(xr.getX1(c, len) == 0)
					io.printString("CPU: reading index register 1 succeed");
			}
			else { //11(2) = 3(10) register
				if(xr.getX3(c, len) == 0)
					io.printString("CPU: reading index register 3 succeed");
			}
		}
		return 0;
	}
	
	/**
	 * write c of length len into id-th floating point register
	 * @param c the content to be written
	 * @param id the ID of the register used
	 * @param len the length of the content
	 * @return 0
	 */
	public int writeFR(char[] c, char[] id, int len)
	{
		if (id[1] == '0')
		{
			if (fr.setFR0(c, len) == 0)
				System.out.println("CPU: writing floating point register 0 succeed");
		}
		else
		{
			if (fr.setFR1(c, len) == 0)
				System.out.println("CPU: writing floating point register 1 succeed");
		}
		
		return 0;
	}
	
	/**
	 * read the content of length len from id-th floating point register into c
	 * @param c store the content read
	 * @param id the ID of the register read
	 * @param len the length of the content
	 * @return 0
	 */
	public int readFR(char[] c, char[] id, int len)
	{
		if (id[1] == '0')
		{
			if (fr.getFR0(c, len) == 0)
				System.out.println("CPU: reading floating point register 0 succeed");
		}
		else
		{
			if (fr.getFR1(c, len) == 0)
				System.out.println("CPU: reading floating point register 1 succeed");
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
			io.printString("CPU: writing condition code succeed");
		return 0;
	}

	/**
	 * read the condition code register
	 * @param c store the content
	 * @param len the length of the content
	 * @return 0
	 */
	public int readCC(char[] c, int id) {
		if(cc.getCC(c, id) == 0) 
			io.printString("CPU: reading condition code succeed");
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
			io.printString("CPU: writing program counter register succeed");
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
			io.printString("CPU: reading program counter register succeed");
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
			io.printString("CPU: writing instruction register succeed");
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
			io.printString("CPU: reading instruction register succeed");
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
		return ic.R2M();
		
	}
	/**
	 * call the A2R() method in IntegratedCircuit
	 * @return
	 */
	public int A2R() {
		return ic.A2R();
		
	}
	/**
	 * call the A2X() method in IntegratedCircuit
	 * @return
	 */
	public int A2X() {
		return ic.A2X();
		
	}
	/**
	 * call the X2M() method in IntegratedCircuit
	 * @return
	 */
	public int X2M() {
		return ic.X2M();
		
	}
	/**
	 * call the ic_jz() method in IntegratedCircuit
	 * @return
	 */
	public int cpu_jz() {
		return ic.ic_jz();
		
	}
	/**
	 * call the ic_jne() method in IntegratedCircuit
	 * @return
	 */
	public int cpu_jne() {
		return ic.ic_jne();
	}
	
	/**
	 * call the ic_jcc() method in IntegratedCircuit
	 * @return
	 */
	public int cpu_jcc() {
		return ic.ic_jcc();
	}
	/**
	 * call the ic_jmp() method in IntegratedCircuit
	 * @return
	 */
	public int cpu_jmp() {
		return ic.ic_jmp();
	}
	
	/**
	 * call the ic_jsr() method in IntegratedCircuit
	 * @return
	 */
	public int cpu_jsr() {
		return ic.ic_jsr();
	}
	/**
	 * call the ic_rfs method in IntegratedCircuit
	 * @return
	 */
	public int cpu_rfs() {
		return ic.ic_rfs();
	}
	/**
	 * call the ic_sob method in IntegratedCircuit
	 * @return
	 */
	public int cpu_sob() {
		return ic.ic_sob();
	}
	/**
	 * call the ic_jge method in IntegratedCircuit
	 * @return
	 */
	public int cpu_jge() {
		return ic.ic_rfs();
	}
	/**
	 * call the ic_amr method in IntegratedCircuit
	 * @return
	 */
	public int cpu_amr() {
		return ic.ic_amr();
	}
	/**
	 * call the ic_smr method in IntegratedCircuit
	 * @return
	 */
	public int cpu_smr() {
		return ic.ic_smr();
	}
	/**
	 * call the ic_stir method in IntegratedCircuit
	 * @return
	 */
	public int cpu_stir() {
		return ic.ic_stir();
	}
	/**
	 * call the ic_air method in IntegratedCircuit
	 * @return
	 */
	public int cpu_air() {
		return ic.ic_air();
	}
	
	
	/**
	 * call the ic_aix method in IntegratedCircuit
	 * @return
	 */
	public int cpu_aix() {
		return ic.ic_aix();
	}
	
	/**
	 * call the ic_stir method in IntegratedCircuit
	 * @return
	 */
	public int cpu_stix() {
		return ic.ic_stix();
	}
	/**
	 * call the ic_sir method in IntegratedCircuit
	 * @return
	 */
	public int cpu_sir() {
		return ic.ic_sir();
	}
	
	/**
	 * call the ic_six method in IntegratedCircuit
	 * @return
	 */
	public int cpu_six() {
		return ic.ic_six();
	}
	public int cpu_in() {
		return ic.ic_in();
	}
	public int cpu_out() {
		return ic.ic_out();
	}
	public int cpu_fout() {
		return ic.ic_fout();
	}
	
	/**
	 * call floating point, vector arithmetics and operations in IC
	 */
	public int cpu_fadd()
	{
		return ic.ic_fadd();
	}
	public int cpu_fsub()
	{
		return ic.ic_fsub();
	}
	public int cpu_vadd()
	{
		return ic.ic_vadd();
	}
	public int cpu_vsub()
	{
		return ic.ic_vsub();
	}
	public int cpu_cnvrt()
	{
		return ic.ic_cnvrt();
	}
	public int cpu_ldfr()
	{
		return ic.ic_ldfr();
	}
	public int cpu_stfr()
	{
		return ic.ic_stfr();
	}
	
	/**
	 * do addition
	 * @param op1 
	 * @param op2
	 * @param result
	 * @return
	 */
	public int addition(char[] op1, char[] op2, char[] result) {
		alu.addition(op1, op2, result);
		return 0;
	}
	public int subtraction(char[] op1, char[] op2, char[] result) {
		//return alu.addition(44, 55);
		alu.subtraction(op1, op2, result);
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
			alu.addition(OP1, OP2, RES);
			break;
		case subtract:
			alu.subtraction(OP1, OP2, RES);
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
