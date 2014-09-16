package gwu.csci.arc.isa;

import gwu.csci.arc.CPU;
import gwu.csci.arc.IntegratedCircuit;
import gwu.csco.arc.utility.Converter;

public abstract class ISA {

	
	public static final char[] oneInstranceLength= new char[IntegratedCircuit.getLenAddr()];
	public char[] isaPC = new char[12];
	public CPU isaCpu;
	public ISA(CPU cpu) {
		// TODO Auto-generated constructor stub
		isaCpu = cpu;
		Converter.addrConverterI2S(IntegratedCircuit.getLenInstruction(), oneInstranceLength);
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
	
	abstract int pcUpdate(); 
	
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
	
}
