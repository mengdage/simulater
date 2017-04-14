package gwu.csci.arc.test;

import gwu.csci.arc.CPU;
import gwu.csci.arc.isa.*;
import gwu.csci.arc.utility.Converter;
import gwu.csci.arc.utility.IOConnector;

public class Runner {
	public static void main(String[] args) {
		CPU cpu = CPU.getInstance();
		SOB sob = new SOB(cpu);
		AIR air = new AIR(cpu);
		IN in = new IN(cpu);
		OUT out = new OUT(cpu);
		IOConnector io = IOConnector.getInstance();
		
		
		
		
		
		
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
		char[] r_3={'0', '0', '0','0', '0', '0','0', '0', '0','0', '0', '0','0', '0', '0','0', '1', '1'};
		char[] id_3 = {'1','1'};

		
		
		cpu.writeMem(ins_air, ins_air.length, ad_100);
		cpu.writeMem(ins_sob, ins_sob.length, ad_103);
		cpu.writePC(ad_100, ad_100.length);
		cpu.writeGPR(r_3, id_3, r_3.length);
		
		char[] pc = new char[12];
		char[] ir = new char[18];
		cpu.readPC(pc, pc.length);
		cpu.readMem(ir, ir.length, pc);
		
		int ins;
		
		ins= Converter.conveterS2I(ir, 6);
		io.printString("----------------Start----------------");
		while(ins != 0) {
			switch (ins) {
			case 6:
				air.start();
				break;
			case 14:
				sob.start();
				break;
			case 49:
				in.start();
				break;
			case 50:
				out.start();
				break;
	
			default:
				io.printString("Runner: Invalid instruction!");
				break;
			}
			cpu.readPC(pc, pc.length);
			cpu.readMem(ir, ir.length, pc);
			ins= Converter.conveterS2I(ir, 6);
		}
		if(ins == 0) {
			io.printString("Runner: Complete!");
		}
	}
	

}
