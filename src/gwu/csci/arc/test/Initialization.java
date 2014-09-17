package gwu.csci.arc.test;

import gwu.csci.arc.CPU;
import gwu.csci.arc.IntegratedCircuit;
import gwu.csci.arc.Memory;
import gwu.csci.arc.isa.LDA;

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
	//001001001010101010
	char[] test_content = {'0','0','1','0','0','1', '0', '0', '1', '0', '1', '0', '1', '0', '1', '0', '1', '0'};
	//000001101010101010
	char[] test_content2 = {'0','0','1','0','1','1', '0', '0', '1', '0', '1', '0', '1', '0', '1', '0', '1', '0'};
	//001000000000
	char[] test_xr = {'0', '0', '1', '0', '0', '0', '0', '0', '0', '0', '0', '0'};
	//101010 10 10 1 0101011
	char[] test_ins = {'1', '0', '1', '0','1', '0', '1', '0','1', '0', '1', '0','1', '0', '1', '0','1','1'};
	//101010 10 00 1 0101011
	char[] test_ins2 = {'1', '0', '1', '0','1', '0', '1', '0','0', '0', '1', '0','1', '0', '1', '0','1','1'};
	//101010 10 10 0 0101011
	char[] test_ins3 = {'1', '0', '1', '0','1', '0', '1', '0','1', '0', '0', '0','1', '0', '1', '0','1','1'};
	//101010 10 00 0 0101011
	char[] test_ins4 = {'1', '0', '1', '0','1', '0', '1', '0','0', '0', '0', '0','1', '0', '1', '0','1','1'};
	//000001 10 10 1 0101010
	char[]  test_ins_LDR = {'0', '0', '0', '0','0', '1', '1', '0','1', '0', '1', '0','1', '0', '1', '0','1','0'};
	//000001 10 10 0 0101010
	char[]  test_ins_LDR1 = {'0', '0', '0', '0','0', '1', '1', '0','1', '0', '0', '0','1', '0', '1', '0','1','0'};
	//000010 10 10 1 0101010
	char[]  test_ins_STR = {'0', '0', '0', '0','1', '0', '1', '0','1', '0', '1', '0','1', '0', '1', '0','1','0'};
	//000010 10 10 0 0101010
	char[]  test_ins_STR1 = {'0', '0', '0', '0','1', '0', '1', '0','1', '0', '0', '0','1', '0', '1', '0','1','0'};
	//000011 10 10 0 0101010
	char[]  test_ins_LDA = {'0', '0', '0', '0','1', '1', '1', '0','1', '0', '0', '0','1', '0', '1', '0','1','0'};
	//000011 10 10 1 0101010
	char[]  test_ins_LDA2 = {'0', '0', '0', '0','1', '1', '1', '0','1', '0', '1', '0','1', '0', '1', '0','1','0'};
	//000011 10 10 0 0101010
	char[]  test_ins_LDX = {'0', '0', '0', '0','1', '1', '1', '0','1', '0', '0', '0','1', '0', '1', '0','1','0'};
	//000011 10 10 1 0101010
	char[]  test_ins_LDX2 = {'0', '0', '0', '0','1', '1', '1', '0','1', '0', '1', '0','1', '0', '1', '0','1','0'};
	//000011 10 10 0 0101010
	char[]  test_ins_STX = {'0', '0', '0', '0','1', '1', '1', '0','1', '0', '0', '0','1', '0', '1', '0','1','0'};
	//000011 10 10 1 0101010
	char[]  test_ins_STX2 = {'0', '0', '0', '0','1', '1', '1', '0','1', '0', '1', '0','1', '0', '1', '0','1','0'};
	//100(10)
	char[] ad_1124 = {'0','0','1','0','0','1', '1', '0','0', '1', '0', '0'};
	//43(10)
	char[] ad_1066 = {'0', '0','1', '0', '0', '0','1', '0', '1', '0','1','0'};
	//74(10)
	char[] ad_1098 = {'0','0','1','0','0','1','0','0','1','0','1','0'};
	//42(10)
	char[] ad_1026 = {'0', '0','1', '0', '0', '0','1', '0', '1', '0','1','0'};
	//000011001010 202
	char[] ad_1226 = {'0','0','1','0','1','1','0','0','1','0','1','0'};
	char[] id = {'1', '0'};
	char[] id_0 = {'0','0'};
	char[] id_1 = {'0','1'};
	char[] id_2 = {'1','0'};
	char[] id_3 = {'1','1'};
	public Initialization() {
		// TODO Auto-generated constructor stub
		cpu = CPU.getInstance();
		ic = IntegratedCircuit.getInstance(cpu);
		Memory memory = Memory.getInstance();
	}
	public void preRun_LDR() {
		//prepare the environment 
		//write 001001001010(1098) 101010 into address: 1066
		//if I=0 this(001001001010101010) would be the content to be read into GPR
		ic.writeMem(test_content, test_content.length, ad_1066);
		//write 001011001010(1226) 101010  into address: 1098
		ic.writeMem(test_content2, test_content.length, ad_1098);
		//write 001011001010(202) 101010 into address: 1226
		//if I=1 this(001011001010101010) would be the content to be read into GPR
		ic.writeMem(test_content2, test_content.length, ad_1226);
		//write 001000000000 into X2
		cpu.writeXR(test_xr, id, test_xr.length);
		
		//output
		char[] pc = new char[12];
		cpu.readPC(pc, 12);
		System.out.print("PC: ");
		System.out.println(pc);
		char[] gpr = new char[18];
		cpu.readGPR(gpr, id, 18);
		System.out.print("GPR: ");
		System.out.println(gpr);
	}
	public void preRun_STR() {

		//prepare the environment
		//write 001001001010101010 into address: 43
		//If I = 0, this(001001001010) would be memory address
		ic.writeMem(test_content, test_content.length, ad_1066);
		//write 001011001010101010 into address: 1098
		//If I = 1, this(001011001010) would be memory address
		ic.writeMem(test_content2, test_content2.length, ad_1098);
		//101010 10 10 1 0101011
		//the content to be read into memory
		cpu.writeGPR(test_ins, id, test_ins.length);

		//write 001000000000 into X2
		cpu.writeXR(test_xr, id, test_xr.length);
		
		//output
		char[] pc = new char[12];
		cpu.readPC(pc, 12);
		System.out.print("PC: ");
		System.out.println(pc);
		char[] mem = new char[18];
		ic.readMem(mem, mem.length, ad_1098);
		System.out.print("Memory address[74] - [91]:");
		System.out.println(mem);
		ic.readMem(mem, mem.length, ad_1226);
		System.out.print("Memory address[202] - [219]:");
		System.out.println(mem);
	}
	public void preRun_LDA() {

		//001001001010(1098) 101010 address: 1066
		//if I=0, this(001001001010101010) would be the content to be read into GPR
		ic.writeMem(test_content, test_content.length, ad_1066);
		//001011001010(1226) 101010 address: 1098
		//if I=0, this(001011001010101010) would be the content to be read into GPR
		ic.writeMem(test_content2, test_content.length, ad_1098);
		//write 001000000000 into X2
		cpu.writeXR(test_xr, id, test_xr.length);
		
		//output
		char[] pc = new char[12];
		cpu.readPC(pc, 12);
		System.out.print("PC: ");
		System.out.println(pc);
		char[] c = new char[18];
		cpu.readGPR(c, id, 18);
		System.out.print("GPR: ");
		System.out.println(c);
		//-----------------------------------------------------
	}
	public void preRun_LDX() {

		//write 001001001010(1066) 101010 into address: 1066
		//If I=0, it(001001001010) would be the content to be read into index register
		ic.writeMem(test_content, test_content.length, ad_1066);
		//001011001010(202) 101010 address: 1098
		ic.writeMem(test_content2, test_content.length, ad_1098);
		//001011001010 101010 address:1226
		//If I=1, it(001011001010) would be the content to be read into index register
		ic.writeMem(test_content2, test_content.length, ad_1226);
		//write 001000000000 into X2
		cpu.writeXR(test_xr, id, test_xr.length);
		
		//output
		char[] pc = new char[12];
		cpu.readPC(pc, 12);
		System.out.print("PC: ");
		System.out.println(pc);
		
		char[] c = new char[12];
		cpu.readXR(c, id, 12);
		System.out.print("XR: ");
		System.out.println(c);
		//-----------------------------------------------------
	}
	public void preRun_STX() {		
		//write 000001001010(74) 101010 into address: 1066
		//If I=0, this(000001001010) would be the memory address		
		ic.writeMem(test_content, test_content.length, ad_1066);
		//write 000001101010 101010 into address: 1098
		//If I=0, this(000001101010) would be the memory address
		ic.writeMem(test_content2, test_content.length, ad_1098);
		//write 001000000000 into X2
		cpu.writeXR(test_xr, id, test_xr.length);

		
		char[] pc = new char[12];
		cpu.readPC(pc, 12);
		System.out.print("PC: ");
		System.out.println(pc);
		
		char[] c = new char[12];
		ic.readMem(c, c.length, ad_1226);
		System.out.print("Memory address[43] - [60]:");
		System.out.println(c);
		//-----------------------------------------------------
	}
//	public void toRun() {
//		//test for LDR I=1
//		//000001 10 10 1 0101010 100
//		ic.writeMem(test_ins_LDR, test_ins_LDR.length, ad_100);
//		//000001001010(74) 101010 addr: 43
//		ic.writeMem(test_content, test_content.length, ad_1066);
//		//000011001010(202) 101010 addr: 74
//		ic.writeMem(test_content2, test_content.length, ad_1098);
//		//000011001010(202) 101010 addr: 202
//		ic.writeMem(test_content2, test_content.length, ad_1226);
//		//000000000001 in X2
//		cpu.writeXR(test_xr, id, test_xr.length);
//		//100
//		cpu.writePC(ad_100, ad_100.length);
		
//		LDR ldr = new LDR(cpu);
//		ldr.start();
//		char[] pc = new char[12];
//		cpu.readPC(pc, 12);
//		System.out.print("PC: ");
//		System.out.println(pc);
//		char[] gpr = new char[18];
//		cpu.readGPR(gpr, id, 18);
//		System.out.print("GPR: ");
//		System.out.println(gpr);
//		//-----------------------------------------------------
		
//		//test for LDR I=0
//		//000001 10 10 0 0101010 100
//		ic.writeMem(test_ins_LDR1, test_ins_LDR.length, ad_100);
		//000001001010101010 addr: 42
//		ic.writeMem(test_content, test_content.length, ad_42);
//		
//		//100
//		cpu.writePC(ad_100, ad_100.length);
//		
//		LDR ldr = new LDR(cpu);
//		ldr.start();
//		char[] pc = new char[12];
//		cpu.readPC(pc, 12);
//		System.out.print("PC: ");
//		System.out.println(pc);
//		char[] gpr = new char[18];
//		cpu.readGPR(gpr, id, 18);
//		System.out.print("GPR: ");
//		System.out.println(gpr);
//		//-----------------------------------------------------
		
		
//		//test for STR I=1
//		//000010 10 10 1 0101010 addr: 100
//		ic.writeMem(test_ins_STR, test_ins_LDR.length, ad_100);
//		//000001001010(74) 101010 addr: 43
//		ic.writeMem(test_content, test_content.length, ad_1066);
////		//
//		ic.writeMem(test_content2, test_content2.length, ad_1098);
//		//101010 10 10 1 0101011
//		//the content to be read into memory
//		cpu.writeGPR(test_ins, id, test_ins.length);
//
//		////000000000001 in X2
//		cpu.writeXR(test_xr, id, test_xr.length);
//		//pc = 100
//		cpu.writePC(ad_100, ad_100.length);
//		
//		STR str = new STR(cpu);
//		str.start();
//		char[] pc = new char[12];
//		cpu.readPC(pc, 12);
//		System.out.print("PC: ");
//		System.out.println(pc);
//		char[] mem = new char[18];
//		ic.readMem(mem, mem.length, ad_1226);
//		System.out.print("Memory address[74] - [91]:");
//		System.out.println(mem);
//		//-----------------------------------------------------
		
//		//test for STR I=0
//		//000010 10 10 0 0101010 addr: 100
//		ic.writeMem(test_ins_STR1, test_ins_LDR.length, ad_100);
//		//000000000001 X2
//		cpu.writeXR(test_xr, id, test_xr.length);
//		//101010 10 10 1 0101011
//		//the content to be read into memory
//		cpu.writeGPR(test_ins, id, test_ins.length);
//
//		//pc = 100
//		cpu.writePC(ad_100, ad_100.length);
//		
//		STR str = new STR(cpu);
//		str.start();
//		char[] pc = new char[12];
//		cpu.readPC(pc, 12);
//		System.out.print("PC: ");		
//		System.out.println(pc);
//		char[] mem = new char[18];
//		ic.readMem(mem, mem.length, ad_1066);
//		System.out.print("Memory address[42] - [59]:");
//		System.out.println(mem);
//		//-----------------------------------------------------
		
//		//test for LDA I=0
//		//000011 10 10 0 0101010 100
//		ic.writeMem(test_ins_LDA, test_ins_LDR.length, ad_100);
//
//		//cpu.writeGPR(test_ins, id, test_ins.length);
//		cpu.writeXR(test_xr, id, test_xr.length);
//		cpu.writePC(ad_100, ad_100.length);
//		
//		LDA lda = new LDA(cpu);
//		lda.start();
//		char[] pc = new char[12];
//		cpu.readPC(pc, 12);
//		System.out.print("PC: ");
//		System.out.println(pc);
//		
//		char[] c = new char[18];
//		cpu.readGPR(c, id, 18);
//		System.out.print("GPR: ");
//		System.out.println(c);
//		//-----------------------------------------------------
		
		
//		//test for LDA I=1
//		//000011 10 10 1 0101010 100
//		ic.writeMem(test_ins_LDA2, test_ins_LDR.length, ad_100);
//		//000001001010(74) 101010 addr: 43
//		ic.writeMem(test_content, test_content.length, ad_1066);
//		
//		//000011001010(202) 101010 addr: 74
//		//the content to be read into GPR
//		ic.writeMem(test_content2, test_content.length, ad_1098);
//		
//
//		//cpu.writeGPR(test_ins, id, test_ins.length);
//		cpu.writeXR(test_xr, id, test_xr.length);
//		cpu.writePC(ad_100, ad_100.length);
//		
//		LDA lda = new LDA(cpu);
//		lda.start();
//		char[] pc = new char[12];
//		cpu.readPC(pc, 12);
//		System.out.print("PC: ");
//		System.out.println(pc);
//		
//		char[] c = new char[18];
//		cpu.readGPR(c, id, 18);
//		System.out.print("GPR: ");
//		System.out.println(c);
//		//-----------------------------------------------------

//		//test for LDX I =0
//		//000011 10 10 0 0101010 100
//		ic.writeMem(test_ins_LDX, test_ins_LDR.length, ad_100);
//		
//		//000001001010(74) 101010 addr: 43
//		//the content to be read into index register
//		ic.writeMem(test_content2, test_content.length, ad_1066);
//		//write 0001 to X2
//		cpu.writeXR(test_xr, id, test_xr.length);
//		//set pc to 100
//		cpu.writePC(ad_100, ad_100.length);
//		
//		LDX ldx = new LDX(cpu);
//		ldx.start();
//		
//		char[] pc = new char[12];
//		cpu.readPC(pc, 12);
//		System.out.print("PC: ");
//		System.out.println(pc);
//		
//		char[] c = new char[12];
//		cpu.readXR(c, id, 12);
//		System.out.print("XR: ");
//		System.out.println(c);
//		//-----------------------------------------------------
		
//		//test for LDX I =1
//		//000011 10 10 1 0101010 100
//		ic.writeMem(test_ins_LDX2, test_ins_LDR.length, ad_100);
//		////000001001010(74) 101010 addr: 43
//		ic.writeMem(test_content, test_content.length, ad_1066);
//		//000011001010(202) 101010 addr: 74
//		ic.writeMem(test_content2, test_content.length, ad_1098);
//		
//		//000011001010 101010 addr:202
//		//the content to be read into index register
//		ic.writeMem(test_content, test_content.length, ad_1226);
//		//write 0001 to X2
//		cpu.writeXR(test_xr, id, test_xr.length);
//		//set pc to 100
//		cpu.writePC(ad_100, ad_100.length);
		
//		LDX ldx = new LDX(cpu);
//		ldx.start();
//		
//		char[] pc = new char[12];
//		cpu.readPC(pc, 12);
//		System.out.print("PC: ");
//		System.out.println(pc);
//		
//		char[] c = new char[12];
//		cpu.readXR(c, id, 12);
//		System.out.print("XR: ");
//		System.out.println(c);
//		//-----------------------------------------------------
		
//		//test for STX I =1
//		//000011 10 10 1 0101010 100
//		ic.writeMem(test_ins_STX2, test_ins_STX.length, ad_100);
//		
//		//000001001010(74) 101010 addr: 43
//		//the content to be read into index register
//		ic.writeMem(test_content, test_content.length, ad_1066);
//		//000001101010 101010 addr:74 
//		ic.writeMem(test_content2, test_content.length, ad_1098);
//		//write 0001 to X2
//		cpu.writeXR(test_xr, id, test_xr.length);
//		//set pc to 100
//		cpu.writePC(ad_100, ad_100.length);
//		
//		STX stx = new STX(cpu);
//		stx.start();
//		
//		char[] pc = new char[12];
//		cpu.readPC(pc, 12);
//		System.out.print("PC: ");
//		System.out.println(pc);
//		
//		char[] c = new char[12];
//		ic.readMem(c, c.length, ad_1226);
//		System.out.print("Memory address[43] - [60]:");
//		System.out.println(c);
//		//-----------------------------------------------------
//	}
	
	
	
}
