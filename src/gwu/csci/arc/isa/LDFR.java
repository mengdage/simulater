package gwu.csci.arc.isa;

import gwu.csci.arc.CPU;

public class LDFR extends ISA {
	
	public LDFR(CPU cpu)
	{
		super(cpu);
	}
	
	int execute()
	{
		isaCpu.cpu_ldfr();
		isaCpu.readPC(isaPC, isaPC.length);
		isaCpu.addition(isaPC, oneInstranceLengthInSByte, isaCpu.getNewPC());
		
		return 0;
	}
}
