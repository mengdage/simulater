package gwu.csci.arc.isa;

import gwu.csci.arc.CPU;

public class AMR extends ISA{
	public AMR(CPU cpu){
		super(cpu);
	}

	@Override
	int execute() {
		// TODO Auto-generated method stub
		isaCpu.cpu_amr();
		isaCpu.readPC(isaPC, isaPC.length);
		isaCpu.addition(isaPC, oneInstranceLength, isaCpu.getNewPC());
		return 0;
	}
	
	

}
