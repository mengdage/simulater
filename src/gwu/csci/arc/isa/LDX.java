package gwu.csci.arc.isa;

import gwu.csci.arc.CPU;
import gwu.csci.arc.IntegratedCircuit;
import gwu.csco.arc.utility.Converter;

public class LDX extends ISA{

	public LDX(CPU cpu) {
		super(cpu);
		// TODO Auto-generated constructor stub
	}

	@Override
	int execute() {
		// TODO Auto-generated method stub
		isaCpu.A2X();
		//increase the PC by 18 which is the length of an Instruction
		int insLen = IntegratedCircuit.getLenInstruction();
		char[] plusOne = Converter.addrConverterI2S(insLen, 12);
		char[] PC = new char[12];
		isaCpu.readPC(PC, PC.length);
		isaCpu.addition(PC, plusOne, isaCpu.getNewPC());
		return 0;
	}

	@Override
	int pcUpdate() {
		// TODO Auto-generated method stub
		isaCpu.pcUpdate();
		return 0;
	}
}
