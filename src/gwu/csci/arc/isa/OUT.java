package gwu.csci.arc.isa;

import gwu.csci.arc.CPU;

public class OUT extends ISA{

	public OUT(CPU cpu) {
		super(cpu);
		// TODO Auto-generated constructor stub
	}

	@Override
	int execute() {
		// TODO Auto-generated method stub
		isaCpu.cpu_out();
		isaCpu.readPC(isaPC, isaPC.length);
		isaCpu.addition(isaPC, oneInstranceLengthInSByte, isaCpu.getNewPC());
		return 0;
	}
	boolean ifNeedCalcEA() {
		// TODO Auto-generated method stub
		return false;
	}

}
