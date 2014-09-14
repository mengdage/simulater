package gwu.csci.arc;

public abstract class ISA {

	public CPU isaCpu;
	public ISA(CPU cpu) {
		// TODO Auto-generated constructor stub
		isaCpu = cpu;
	}
	final int start() {
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
