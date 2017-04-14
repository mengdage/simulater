package gwu.csci.arc.isa;

import gwu.csci.arc.CPU;

public class STFR extends ISA {
	
	public STFR(CPU cpu)
	{
		super(cpu);
	}
	
	int execute()
	{
		isaCpu.cpu_stfr();
		isaCpu.readPC(isaPC, isaPC.length);
		isaCpu.addition(isaPC, oneInstranceLengthInSByte, isaCpu.getNewPC());
		
		return 0;
	}
}
