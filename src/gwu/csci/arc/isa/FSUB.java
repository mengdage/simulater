package gwu.csci.arc.isa;

import gwu.csci.arc.CPU;

public class FSUB extends ISA {
	
	public FSUB(CPU cpu)
	{
		super(cpu);
	}
	
	int execute()
	{
		isaCpu.cpu_fsub();
		isaCpu.readPC(isaPC, isaPC.length);
		isaCpu.addition(isaPC, oneInstranceLengthInSByte, isaCpu.getNewPC());
		
		return 0;
	}
}
