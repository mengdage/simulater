package gwu.csci.arc.isa;

import gwu.csci.arc.CPU;

public class JMP extends ISA{

	public JMP(CPU cpu) {
		super(cpu);
		// TODO Auto-generated constructor stub
	}

	@Override
	int execute() {
		// TODO Auto-generated method stub
		isaCpu.cpu_jmp();
		return 0;
	}
	

}
