package gwu.csci.arc.isa;

import gwu.csci.arc.CPU;

public class VADD extends ISA {
	
	public VADD(CPU cpu)
	{
		super(cpu);
	}
	
	int execute()
	{
		isaCpu.cpu_vadd();
		isaCpu.readPC(isaPC, isaPC.length);
		isaCpu.addition(isaPC, oneInstranceLengthInSByte, isaCpu.getNewPC());
		
		return 0;
	}
}
