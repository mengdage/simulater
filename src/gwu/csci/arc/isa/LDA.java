package gwu.csci.arc.isa;

import gwu.csci.arc.CPU;

public class LDA extends ISA{

	public LDA(CPU cpu) {
		super(cpu);
		// TODO Auto-generated constructor stub
	}

	@Override
	int execute() {
		// TODO Auto-generated method stub
		isaCpu.A2R();
		//increase the PC by 18 which is the length of an Instruction
		
		isaCpu.readPC(isaPC, isaPC.length);
		isaCpu.addition(isaPC, oneInstranceLengthInSByte, isaCpu.getNewPC());
		return 0;
	}

	
}
