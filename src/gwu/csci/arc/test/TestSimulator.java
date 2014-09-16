package gwu.csci.arc.test;

import gwu.csci.arc.CPU;
import gwu.csci.arc.IntegratedCircuit;
import gwu.csci.arc.Memory;
import gwu.csci.arc.isa.LDA;
import gwu.csci.arc.isa.LDR;
import gwu.csci.arc.isa.LDX;
import gwu.csci.arc.isa.STR;
import gwu.csci.arc.isa.STX;

public class TestSimulator {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//test for github

		
		CPU cpu = CPU.getInstance();
		IntegratedCircuit ic = IntegratedCircuit.getInstance(cpu);
		Memory memory = Memory.getInstance();
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
		
//		cpu.writePC(test_pc, Mainboard.getLenAddr());
//		memory.write(test_ir, Mainboard.getLenInstruction(), 128);
//		mb.decode();
		
		
		
//		cpu.writeXR(test_xr, id, 12);
//		cpu.writePC(test_pc, IntegratedCircuit.getLenAddr());
		//memory.write(test_ins2, IntegratedCircuit.getLenInstruction(), ad_100);
//		ic.decode();
//		ic.calcEA();
		//int i = 1025;
		
		//System.out.println(addrConvertorI2S(i));
		
		
//		int i = 100;
//		String s = "Hello World!";
//		char[] c = new char[12];
//		System.out.println(s.toCharArray().length);
//		memory.write(s.toCharArray(), 12, addrConvertorI2S(i));
//		memory.read(c, 12, addrConvertorI2S(i));
//		System.out.println(c);
		
//		String s = "Hello World!";
//		char[] c = s.toCharArray();
//		System.out.println(c[11] + ": " + c.length);
//		
//		String s2 = new String(c);
//		System.out.println(s2);
		
//		ALU alu = new ALU();
//		String s1 = "000101000101";
//		String s2 = "000001000101";
//		char[] result = new char[12];
//		alu.subtraction(s1.toCharArray(), s2.toCharArray(), 12, result);
//		System.out.println(result);
		
		
//		ic.writeMem(test_ins3, test_ins3.length, ad_100);
//		cpu.writeXR(test_xr, id, test_xr.length);
//		cpu.writePC(ad_100, ad_100.length);
//		cpu.decode();
//		cpu.calcEA();
		
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
		
//		//test for LDX I =1
//		//000011 10 10 1 0101010 100
//		ic.writeMem(test_ins_LDX2, test_ins_LDR.length, ad_100);
//		////000001001010(74) 101010 addr: 43
//		ic.writeMem(test_content, test_content.length, ad_43);
//		//000011001010(202) 101010 addr: 74
//		ic.writeMem(test_content2, test_content.length, ad_74);
//		
//		//000011001010 101010 addr:202
//		//the content to be read into index register
//		ic.writeMem(test_content, test_content.length, ad_202);
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
		
//		//test for STX I =1
//		//000011 10 10 1 0101010 100
//		ic.writeMem(test_ins_STX2, test_ins_STX.length, ad_100);
//		
//		//000001001010(74) 101010 addr: 43
//		//the content to be read into index register
//		ic.writeMem(test_content, test_content.length, ad_43);
//		//000001101010 101010 addr:74 
//		ic.writeMem(test_content2, test_content.length, ad_74);
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
//		ic.readMem(c, c.length, ad_202);
//		System.out.print("XR: ");
//		System.out.println(c);
//		//-----------------------------------------------------
		
		//test for STX I =0
		//000011 10 10 0 0101010 100
		ic.writeMem(test_ins_STX, test_ins_STX.length, ad_100);
		
		//000001001010(74) 101010 addr: 43
		//the content to be read into index register
		ic.writeMem(test_content, test_content.length, ad_43);
//		//000001101010 101010 addr:74 
//		ic.writeMem(test_content2, test_content.length, ad_74);
		//write 0001 to X2
		cpu.writeXR(test_xr, id, test_xr.length);
		//set pc to 100
		cpu.writePC(ad_100, ad_100.length);
		
		STX stx = new STX(cpu);
		stx.start();
		
		char[] pc = new char[12];
		cpu.readPC(pc, 12);
		System.out.print("PC: ");
		System.out.println(pc);
		
		char[] c = new char[12];
		ic.readMem(c, c.length, ad_43);
		System.out.print("Memory address[43] - [60]:");
		System.out.println(c);
		//-----------------------------------------------------
		
		
//		Initialization i = new Initialization();
//		i.toRun();
		
		
	}

}
