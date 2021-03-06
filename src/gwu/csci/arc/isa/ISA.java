package gwu.csci.arc.isa;

import gwu.csci.arc.CPU;
import gwu.csci.arc.IntegratedCircuit;
import gwu.csci.arc.utility.Converter;

public abstract class ISA {

	
	//public static final char[] oneInstranceLength= new char[IntegratedCircuit.getLenAddr()];
	public static final char[] oneInstranceLengthInSBite= {'0','0','0','0','0','0','0','1', '0', '0', '1','0'};
	public static final char[] oneInstranceLengthInSByte = {'0','0','0','0','0','0','0','0', '0', '0', '1','1'};
	public char[] isaPC = new char[12];
	public CPU isaCpu;
	public ISA(CPU cpu) {
		// TODO Auto-generated constructor stub
		isaCpu = cpu;
		//Converter.addrConverterI2S(IntegratedCircuit.getLenInstruction(), oneInstranceLength);
	}
	
	public final int start() {
		if (ifNeedDecode()) {
			decode();
		}
		if (ifNeedCalcEA()) {
			calcEA();
		}
		if(ifNeedALUcalc()){
			ALUcalc();
		}
		if(ifNeedExecute()) {
			execute();
		}
		if(ifNeedPcUpdate()) {
			pcUpdate();
		}
		return 0;
	}
	
	public CPU getIsaCpu() {
		return isaCpu;
	}
	public void setIsaCpu(CPU isaCpu) {
		this.isaCpu = isaCpu;
	}
	int decode() {
		isaCpu.decode();
		return 0;
	}
	
	int calcEA() {
		isaCpu.calcEA();
		return 0;
	}
	
	int ALUcalc() {
		
		return 0;
	}
	abstract int execute();
	
	int pcUpdate() {
		isaCpu.pcUpdate();
		return 0;
	}
	
	//the condition method for each each stage
	/**
	 * 
	 * @return
	 */
	boolean ifNeedDecode() {
		return true;
	}
	boolean ifNeedCalcEA() {
		return true;
	}
	boolean ifNeedALUcalc() {
		return true;
	}
	boolean ifNeedExecute() {
		return true;
	}
	boolean ifNeedPcUpdate() {
		return true;
	}
	void printString(String s) {
		System.out.println(s);
	}
	
}
