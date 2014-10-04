package gwu.csci.arc.isa;

import gwu.csci.arc.CPU;

public class SOB extends ISA{

	public SOB(CPU cpu) {
		super(cpu);
		// TODO Auto-generated constructor stub
	}

	@Override
	int execute() {
		// TODO Auto-generated method stub
		if(isaCpu.cpu_sob() != 0) {
			isaCpu.readPC(isaPC, isaPC.length);
			isaCpu.addition(isaPC, oneInstranceLength, isaCpu.getNewPC());
		}
		return 0;
	}
}
