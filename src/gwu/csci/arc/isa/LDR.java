package gwu.csci.arc.isa;

import gwu.csci.arc.CPU;
import gwu.csco.arc.utility.OPERATORS;

public class LDR extends ISA{

	public LDR(CPU cpu) {
		super(cpu);
		// TODO Auto-generated constructor stub
	}

	@Override
	int ALUcalc() {
		// TODO Auto-generated method stub
		isaCpu.RICalc();
		isaCpu.ALUcalc(OPERATORS.add);
		return 0;
	}

	
	@Override
	int execute() {
		// TODO Auto-generated method stub
		
		//isaCpu.writeGPR(isaCpu.getEA(), isaCpu.getRfi(), isaCpu.getRES().length);
		isaCpu.M2R();
		char[] plusOne = {'0','0','0','0','0','0','0','0','0','0','0','1'};
		isaCpu.addition(isaCpu.getMAR(), plusOne, isaCpu.getNewPC());
		return 0;
	}

	@Override
	int pcUpdate() {
		// TODO Auto-generated method stub
		isaCpu.pcUpdate();
		return 0;
	}
	

}
