package gwu.csci.arc.isa;

import gwu.csci.arc.CPU;

public class STIX extends ISA{

	public STIX(CPU cpu) {
		super(cpu);
		// TODO Auto-generated constructor stub
	}
	
	int execute() {
		// TODO Auto-generated method stub
		isaCpu.cpu_stix();
		isaCpu.readPC(isaPC, isaPC.length);
		isaCpu.addition(isaPC, oneInstranceLengthInSByte, isaCpu.getNewPC());
		return 0;
	}

	@Override
	boolean ifNeedCalcEA() {
		// TODO Auto-generated method stub
		return false;
	}

}
