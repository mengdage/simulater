package gwu.csci.arc.test;

import gwu.csci.arc.CPU;
import gwu.csci.arc.IntegratedCircuit;
import gwu.csci.arc.Memory;

public class Initialization {

	CPU cpu;
	Memory memory;
	IntegratedCircuit ic;
	
	//000000000001
	char[] test_xr1 = {'0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '1'};
	//111000010101
	char[] test_xr2 = {'1', '1', '1', '0', '0', '0', '0', '1', '0', '1', '0', '1'};
	//101000010101
	char[] test_xr3 = {'1', '0', '1', '0', '0', '0', '0', '1', '0', '1', '0', '1'};
	//000001001010101010
	char[] test_content = {'0','0','0','0','0','1', '0', '0', '1', '0', '1', '0', '1', '0', '1', '0', '1', '0'};
	//000000000001
	char[] test_xr = {'0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '1'};
	//101010 10 10 1 0101011
	char[] test_ins = {'1', '0', '1', '0','1', '0', '1', '0','1', '0', '1', '0','1', '0', '1', '0','1','1'};
	//101010 10 00 1 0101011
	char[] test_ins2 = {'1', '0', '1', '0','1', '0', '1', '0','0', '0', '1', '0','1', '0', '1', '0','1','1'};
	//101010 10 10 0 0101011
	char[] test_ins3 = {'1', '0', '1', '0','1', '0', '1', '0','1', '0', '0', '0','1', '0', '1', '0','1','1'};
	//101010 10 00 0 0101011
	char[] test_ins4 = {'1', '0', '1', '0','1', '0', '1', '0','0', '0', '0', '0','1', '0', '1', '0','1','1'};
	//000001 10 10 1 0101101
	char[]  test_ins_LDR = {'0', '0', '0', '0','0', '1', '1', '0','1', '0', '1', '0','1', '0', '1', '0','1','0'};
	//000010 10 10 1 0101101
	char[]  test_ins_STR = {'0', '0', '0', '0','1', '0', '1', '0','1', '0', '1', '0','1', '0', '1', '0','1','0'};
	//000011 10 00 0 0101101
	char[]  test_ins_LDA = {'0', '0', '0', '0','1', '1', '1', '0','0', '0', '0', '0','1', '0', '1', '0','1','0'};
	//000011 10 10 1 0101101
	char[]  test_ins_LDA2 = {'0', '0', '0', '0','1', '1', '1', '0','1', '0', '1', '0','1', '0', '1', '0','1','0'};
	//000011 10 00 0 0101101
	char[]  test_ins_LDX = {'0', '0', '0', '0','1', '1', '1', '0','0', '0', '0', '0','1', '0', '1', '0','1','0'};
	//000011 10 10 1 0101101
	char[]  test_ins_LDX2 = {'0', '0', '0', '0','1', '1', '1', '0','1', '0', '1', '0','1', '0', '1', '0','1','0'};
	//100(10)
	char[] ad1 = {'0','0','0','0','0','1', '1', '0','0', '1', '0', '0'};
	//43(10)
	char[] ad2 = {'0', '0','0', '0', '0', '0','1', '0', '1', '0','1','1'};
	
	char[] id = {'1', '0'};
	char[] id_0 = {'0','0'};
	char[] id_1 = {'0','1'};
	char[] id_2 = {'1','0'};
	char[] id_3 = {'1','1'};
	public Initialization() {
		// TODO Auto-generated constructor stub
		cpu = CPU.getInstance();
		ic = IntegratedCircuit.getInstance(cpu);
	}
	public void toRun() {
		cpu.writeXR(test_xr1, id_1, test_xr1.length);
		cpu.writeXR(test_xr2, id_2, test_xr2.length);
		cpu.writeXR(test_xr3, id_3, test_xr3.length);
		
		ic.writeMem(test_ins_LDR, test_ins_LDR.length, ad1);
		ic.writeMem(test_content, test_content.length, ad2);
		cpu.writeXR(test_xr, id, test_xr.length);
		cpu.writePC(ad1, ad1.length);
		
		ic.setMAR(ad1);
		ic.setMBR(test_content);
	}
	
	
	
}
