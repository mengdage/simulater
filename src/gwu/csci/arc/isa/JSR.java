package gwu.csci.arc.isa;

import gwu.csci.arc.CPU;

public class JSR extends ISA{

	public JSR(CPU cpu) {
		super(cpu);
		// TODO Auto-generated constructor stub
	}

	@Override
	int execute() {
		// TODO Auto-generated method stub
		isaCpu.cpu_jsr();
		return 0;
	}
	

}
