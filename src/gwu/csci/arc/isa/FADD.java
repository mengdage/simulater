package gwu.csci.arc.isa;

import gwu.csci.arc.CPU;

public class FADD extends ISA {
	
	public FADD(CPU cpu)
	{
		super(cpu);
	}
	
	int execute()
	{
		isaCpu.cpu_fadd();
		isaCpu.readPC(isaPC, isaPC.length);
		isaCpu.addition(isaPC, oneInstranceLengthInSByte, isaCpu.getNewPC());
		
		return 0;
	}
}
