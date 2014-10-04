package gwu.csci.arc.isa;

import gwu.csci.arc.CPU;

public class JZ extends ISA{

	
	public JZ(CPU cpu) {
		super(cpu);
		// TODO Auto-generated constructor stub
	}

	@Override
	int execute() {
		// TODO Auto-generated method stub
		if(isaCpu.cpu_jz() != 0) {
			isaCpu.readPC(isaPC, isaPC.length);
			isaCpu.addition(isaPC, oneInstranceLengthInSByte, isaCpu.getNewPC());
		}
		
		return 0;
	}
	
}
