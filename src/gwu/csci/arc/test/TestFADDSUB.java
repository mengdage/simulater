package gwu.csci.arc.test;

import gwu.csci.arc.CPU;
import gwu.csci.arc.isa.*;
import gwu.csci.arc.utility.Converter;
import gwu.csci.arc.utility.SAssembler;

public class TestFADDSUB {

	public static void main(String[] args) {
CPU cpu = CPU.getInstance();
		
		ISA air = new AIR(cpu);
		ISA aix = new AIX(cpu);
		ISA str = new STR(cpu);
		ISA in = new gwu.csci.arc.isa.IN(cpu);
		ISA sob = new SOB(cpu);
		ISA six = new SIX(cpu);
		ISA ldr = new LDR(cpu);
		ISA smr = new SMR(cpu);
		ISA sir = new SIR(cpu);
		ISA jmp = new JMP(cpu);
		ISA jcc = new JCC(cpu);
		ISA stir = new STIR(cpu);
		ISA stix = new STIX(cpu);
		ISA out = new gwu.csci.arc.isa.OUT(cpu);
		ISA fout = new FOUT(cpu);
		ISA vadd = new VADD(cpu);
		ISA vsub = new VSUB(cpu);
		ISA fadd = new FADD(cpu);
		ISA fsub = new FSUB(cpu);
		ISA ldfr = new LDFR(cpu);
		ISA stfr = new STFR(cpu);
		
		String program = "STIX 3 0;STIR 1 8;AIX 3 64;SOB 1 0 0 9;FADD 0 3 0 0;FOUT 0 1;FADD 0 3 0 0;FOUT 0 1;STFR 0 3 0 3;LDFR 1 3 0 3;FOUT 1 1;FADD 1 3 0 3;FOUT 1 1;FSUB 1 3 0 0;FOUT 1 1;";
		String[] instruction = program.split(";");
		
		
		
		
		SAssembler sa = new SAssembler();
		char[] m_code = new char[18];
		char[] addr = new char[12];
		String m_code_string = "";
		for (int i = 0; i < instruction.length; i++) {
			sa.assembler(m_code, instruction[i]);
			cpu.writeIns(m_code, m_code.length, addr);
			m_code_string += new String(m_code) + "\n";
		}
		System.out.println(m_code_string);
		
		String add_v1 = "001000000000";
		char[] addBuffer = add_v1.toCharArray();
		String f_3_5 = "000000101110000000";
		cpu.writeMem(f_3_5.toCharArray(), f_3_5.length(), add_v1.toCharArray());
		
//		int[] v1 = {3,4,5};
//		char[] v1Element = new char[18];
//		int[] v2 = {2,3,4};
//		char[] v2Element = new char[18];
//		for(int i = 0; i < v1.length; i++) {
//			Converter.converterI2S(v1[i], v1Element);
//			Converter.converterI2S(v2[i], v2Element);
//			
//			System.out.println("write "+new String(v1Element)+" to: " + new String(addBuffer));
//			cpu.writeMem(v1Element, v1Element.length, addBuffer);
//			int a = Converter.conveterS2I(addBuffer, addBuffer.length);
//			a = a + 3 * 3;
//			Converter.converterI2S(a, addBuffer);
//			System.out.println("write "+new String(v2Element)+"  to: " + new String(addBuffer));
//			cpu.writeMem(v2Element, v2Element.length, addBuffer);
//			a = a - 3 * 3 + 3;
//			Converter.converterI2S(a, addBuffer);
//		}
//		
//		addBuffer = add_v1.toCharArray();
//		char[] memBuffer = new char[18*3];
//		cpu.readMem(memBuffer, memBuffer.length, addBuffer);
//		
//		System.out.println(memBuffer);
//		
//		int b = Converter.conveterS2I(addBuffer, addBuffer.length);
//		b = b + 3 * 3;
//		
//		Converter.converterI2S(b, addBuffer);
//		System.out.println("read from: " + new String(addBuffer));
//		cpu.readMem(memBuffer, memBuffer.length, addBuffer);
//		
//		System.out.println(memBuffer);
//		
//		
//		
//		char[] xri = {'0', '1'};
//		cpu.writeXR(add_v1.toCharArray(), xri, add_v1.length());
//		
//		String fpr = "000000000000000011";
//		char[] fri = {'0', '0'};
//		cpu.writeFR(fpr.toCharArray(), fri, fpr.length());
		
		char[] pc = {'0','0','0','0','0','0', '0', '0','0', '0', '1', '1'};
//		String fpr = "000000000000000011";
//		char[] fri = {'0', '0'};
//		cpu.writeFR(fpr.toCharArray(), fri, fpr.length());
		char[] ir = new char[18];
		cpu.writePC(pc, pc.length);
		cpu.readPC(pc, pc.length);
		cpu.readMem(ir, ir.length, pc);
		
		
		
		int ins;
		
		ins= Converter.conveterS2I(ir, 6);
		System.out.println("----------------Start----------------");
		while(ins != 0) {
			switch (ins) {
			case 1:
				ldr.start();
				break;
			case 2:
				str.start();
				break;
			case 5:
				smr.start();
				break;
			case 7:
				sir.start();
				break;
			case 6:
				air.start();
				break;
			case 10:
				jcc.start();
				break;
			case 11:
				jmp.start();
				break;
			case 14:
				sob.start();
				break;
			case 22:
				aix.start();
				break;
			case 23:
				six.start();
				break;
			case 27:
				fadd.start();
				break;
			case 28:
				fsub.start();
				break;
			case 29:
				vadd.start();
				break;
			case 30:
				vsub.start();
				break;
			case 40:
				ldfr.start();
				break;
			case 41: stfr.start();
				break;
			case 42:
				stir.start();
				break;
			case 43:
				stix.start();
				break;
			case 49:
				in.start();
				break;
			case 50:
				out.start();
				break;
			case 52:
				fout.start();
				break;
			default:
				break;
			}
			cpu.readPC(pc, pc.length);
			cpu.readMem(ir, ir.length, pc);
			ins= Converter.conveterS2I(ir, 6);
		}
		if(ins == 0) {
			System.out.println("Runner: Complete!");
		}
	}
}
