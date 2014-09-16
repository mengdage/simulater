package gwu.csci.arc.test;

import gwu.csci.arc.CPU;
import gwu.csci.arc.IntegratedCircuit;
import gwu.csci.arc.Memory;
import gwu.csci.arc.isa.LDX;

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
	//000001101010101010
	char[] test_content2 = {'0','0','0','0','1','1', '0', '0', '1', '0', '1', '0', '1', '0', '1', '0', '1', '0'};
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
	char[] ad_100 = {'0','0','0','0','0','1', '1', '0','0', '1', '0', '0'};
	//43(10)
	char[] ad_43 = {'0', '0','0', '0', '0', '0','1', '0', '1', '0','1','1'};
	//74(10)
	char[] ad_74 = {'0','0','0','0','0','1','0','0','1','0','1','0'};
	//42(10)
	char[] ad_42 = {'0', '0','0', '0', '0', '0','1', '0', '1', '0','1','0'};
	//000011001010 202
	char[] ad_202 = {'0','0','0','0','1','1','0','0','1','0','1','0'};
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
	public void toRun() {
//		//test for LDR I=1
//		//000001 10 10 1 0101010 100
//		ic.writeMem(test_ins_LDR, test_ins_LDR.length, ad_100);
//		//000001001010(74) 101010 addr: 43
//		ic.writeMem(test_content, test_content.length, ad_43);
//		//000011001010(202) 101010 addr: 74
//		ic.writeMem(test_content2, test_content.length, ad_74);
//		//000011001010(202) 101010 addr: 202
//		ic.writeMem(test_content2, test_content.length, ad_202);
//		//000000000001 in X2
//		cpu.writeXR(test_xr, id, test_xr.length);
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
		
//		//test for LDR I=0
//		//000001 10 10 0 0101010 100
//		ic.writeMem(test_ins_LDR1, test_ins_LDR.length, ad_100);
//		//000001001010101010 addr: 42
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
//		ic.writeMem(test_content, test_content.length, ad_43);
////		//
//		ic.writeMem(test_content2, test_content2.length, ad_74);
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
//		ic.readMem(mem, mem.length, ad_202);
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
//		ic.readMem(mem, mem.length, ad_43);
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
//		ic.writeMem(test_content, test_content.length, ad_43);
//		
//		//000011001010(202) 101010 addr: 74
//		//the content to be read into GPR
//		ic.writeMem(test_content2, test_content.length, ad_74);
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
//		ic.writeMem(test_content2, test_content.length, ad_43);
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
		
		//test for LDX I =1
		//000011 10 10 1 0101010 100
		ic.writeMem(test_ins_LDX2, test_ins_LDR.length, ad_100);
		////000001001010(74) 101010 addr: 43
		ic.writeMem(test_content, test_content.length, ad_43);
		//000011001010(202) 101010 addr: 74
		ic.writeMem(test_content2, test_content.length, ad_74);
		
		//000011001010 101010 addr:202
		//the content to be read into index register
		ic.writeMem(test_content, test_content.length, ad_202);
		//write 0001 to X2
		cpu.writeXR(test_xr, id, test_xr.length);
		//set pc to 100
		cpu.writePC(ad_100, ad_100.length);
		
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
		
//		//test for STX I =0
//		//000011 10 10 0 0101010 100
//		ic.writeMem(test_ins_LDX, test_ins_LDR.length, ad_100);
//		
//		//000001001010(74) 101010 addr: 43
//		//the content to be read into index register
//		//ic.writeMem(test_content2, test_content.length, ad_43);
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
	}
	
	
	
}
