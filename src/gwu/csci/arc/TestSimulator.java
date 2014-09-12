package gwu.csci.arc;

public class TestSimulator {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//test for github

		IntegratedCircuit ic = new IntegratedCircuit();
		CPU cpu = CPU.getInstance();
		Memory memory = Memory.getInstance();
		//001010101010
		char[] test_pc = {'0', '0', '1', '0', '1', '0', '1', '0', '1', '0', '1', '0'};
		//000010000000
		char[] test_xr = {'0', '0', '0', '0', '1', '0', '0', '0', '0', '0', '0', '0'};
		//101010 10 10 1 0101011
		char[] test_ir = {'1', '0', '1', '0','1', '0', '1', '0','1', '0', '1', '0','1', '0', '1', '0','1','1'};
		//101010 10 00 1 0101011
		char[] test_ir2 = {'1', '0', '1', '0','1', '0', '1', '0','0', '0', '1', '0','1', '0', '1', '0','1','1'};
		//101010 10 10 0 0101011
		char[] test_ir3 = {'1', '0', '1', '0','1', '0', '1', '0','1', '0', '0', '0','1', '0', '1', '0','1','1'};
		//101010 10 00 0 0101011
		char[] test_ir4 = {'1', '0', '1', '0','1', '0', '1', '0','0', '0', '0', '0','1', '0', '1', '0','1','1'};
//		cpu.writePC(test_pc, Mainboard.getLenAddr());
//		memory.write(test_ir, Mainboard.getLenInstruction(), 128);
//		mb.decode();
		
		
		char[] id = {'1', '0'};
		cpu.writeXR(test_xr, id, 12);
		cpu.writePC(test_pc, IntegratedCircuit.getLenAddr());
		memory.write(test_ir2, IntegratedCircuit.getLenInstruction(), 128);
		ic.decode();
		ic.calcEA();
	}

}
