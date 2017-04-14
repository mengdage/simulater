package gwu.csci.arc.isa;

import gwu.csci.arc.CPU;

public class VSUB extends ISA {
	
	public VSUB(CPU cpu)
	{
		super(cpu);
	}
	
	int execute()
	{
		isaCpu.cpu_vsub();
		isaCpu.readPC(isaPC, isaPC.length);
		isaCpu.addition(isaPC, oneInstranceLengthInSByte, isaCpu.getNewPC());
		
		return 0;
	}
}
