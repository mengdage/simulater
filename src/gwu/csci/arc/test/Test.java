package gwu.csci.arc.test;

import gwu.csci.arc.CPU;
import gwu.csci.arc.isa.AIR;
import gwu.csci.arc.isa.IN;
import gwu.csci.arc.isa.OUT;
import gwu.csci.arc.isa.SOB;

public class Test {
	public static void main(String[] args) {
		//100(10)
		char[] ad_100 = {'0','0','0','0','0','1', '1', '0','0', '1', '0', '0'};
		//103(10)
		char[] ad_103 = {'0','0','0','0','0','1', '1', '0','0', '1', '1', '1'};
		char[] imme_100 = {'1', '1', '0','0', '1', '0', '0'};
		//100(10)
		char[] ad = {'1','0','0','0','0','1', '1', '0','0', '1', '0', '0'};
		//101010 10 10 1 0101011
		char[] test_ins = {'1', '0', '1', '0','1', '0', '1', '0','1', '0', '1', '0','1', '0', '1', '0','1','1'};
		//000001 10 10 1 0101010
		char[]  test_ins_LDR = {'0', '0', '0', '0','0', '1', '1', '0','1', '0', '1', '0','1', '0', '1', '0','1','0'};
		//
		char[] ins_air = {'0', '0', '0','1', '1', '0','1', '0', '0','0', '0', '0','0', '0', '0','0', '0', '1'};
		//
		char[] ins_sob = {'0', '0', '1','1', '1', '0','1', '1', '0','0', '0', '1', '1', '0','0', '1', '0', '0'};
		
		char[] ins_in={'1', '1', '0','0', '1', '1','1', '0', '0','0', '0', '0','0', '0', '0','0', '0', '0'};
		char[] ins_out={'1', '1', '0','1', '0', '1','1', '0', '0','0', '0', '0','0', '0', '0','0', '0', '1'};
		char[] r_3={'0', '0', '0','0', '0', '0','0', '0', '0','0', '0', '0','0', '0', '0','0', '1', '1'};
		char[] id_3 = {'1','1'};
		CPU cpu = CPU.getInstance();
		
		
		System.out.println("-------start-------");
		cpu.writeMem(test_ins, test_ins.length, ad_100);
		System.out.println("-------------------");
		cpu.writeMem(test_ins_LDR, test_ins_LDR.length, ad);
		char[] container = new char[18];
		System.out.println("-------------------");
		cpu.readMem(container, container.length, ad_100);
		System.out.println("Test: " + new String(container));
		System.out.println("-------------------");
		cpu.readMem(container, container.length, ad_100);
		System.out.println("Test: " + new String(container));
		
//		cpu.writeMem(ins_in, ins_in.length, ad_100);
//		cpu.writeMem(ins_out, ins_in.length, ad_103);
//		//cpu.writeMem(ins_sob, ins_sob.length, ad_103);
//		cpu.writePC(ad_100, ad_100.length);
////		cpu.writeGPR(r_3, id_3, r_3.length);
////		AIR air = new AIR(cpu);
////		SOB sob = new SOB(cpu);
////		air.start();
////		sob.start();
////		air.start();
////		sob.start();
////		air.start();
////		sob.start();
//		
//		IN in = new IN(cpu);
//		OUT out = new OUT(cpu);
//		in.start();
//		
//		out.start();
	}
}
