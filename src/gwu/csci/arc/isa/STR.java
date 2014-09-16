package gwu.csci.arc.isa;

import gwu.csci.arc.CPU;
import gwu.csci.arc.IntegratedCircuit;
import gwu.csco.arc.utility.Converter;
import gwu.csco.arc.utility.OPERATORS;

public class STR extends ISA{

	
	public STR(CPU cpu) {
		super(cpu);
		// TODO Auto-generated constructor stub
	}

//	@Override
//	int ALUcalc() {
//		// TODO Auto-generated method stub
//		isaCpu.RIOperandPreparation();
//		isaCpu.ALUcalc(OPERATORS.add);
//		return 0;
//	}

	@Override
	int execute() {
		// TODO Auto-generated method stub
		isaCpu.R2M();
		
		//increase the PC by 18 which is the length of an Instruction
		isaCpu.readPC(isaPC, isaPC.length);
		isaCpu.addition(isaPC, oneInstranceLength, isaCpu.getNewPC());
		return 0;
	}

	@Override
	int pcUpdate() {
		// TODO Auto-generated method stub
		isaCpu.pcUpdate();
		return 0;
	}

}
