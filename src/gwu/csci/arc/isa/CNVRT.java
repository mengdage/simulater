package gwu.csci.arc.isa;

import gwu.csci.arc.CPU;

public class CNVRT extends ISA {
	
	public CNVRT(CPU cpu)
	{
		super(cpu);
	}
	
	int execute()
	{
		isaCpu.cpu_cnvrt();
		isaCpu.readPC(isaPC, isaPC.length);
		isaCpu.addition(isaPC, oneInstranceLengthInSByte, isaCpu.getNewPC());
		
		return 0;
	}
}
