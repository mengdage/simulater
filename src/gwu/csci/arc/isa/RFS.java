package gwu.csci.arc.isa;

import gwu.csci.arc.CPU;

public class RFS extends ISA{

	public RFS(CPU cpu) {
		super(cpu);
		// TODO Auto-generated constructor stub
	}

	@Override
	int execute() {
		// TODO Auto-generated method stub
		isaCpu.cpu_rfs();
		return 0;
	}

	@Override
	boolean ifNeedCalcEA() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	boolean ifNeedALUcalc() {
		// TODO Auto-generated method stub
		return false;
	}
	
	

}
