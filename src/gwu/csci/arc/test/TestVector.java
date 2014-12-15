package gwu.csci.arc.test;

import javax.security.auth.Subject;

import gwu.csci.arc.CPU;
import gwu.csci.arc.isa.AIR;
import gwu.csci.arc.isa.AIX;
import gwu.csci.arc.isa.JCC;
import gwu.csci.arc.isa.JMP;
import gwu.csci.arc.isa.LDR;
import gwu.csci.arc.isa.SIR;
import gwu.csci.arc.isa.SIX;
import gwu.csci.arc.isa.SMR;
import gwu.csci.arc.isa.SOB;
import gwu.csci.arc.isa.STIR;
import gwu.csci.arc.isa.STIX;
import gwu.csci.arc.isa.STR;
import gwu.csci.arc.isa.VADD;
import gwu.csci.arc.isa.VSUB;
import gwu.csci.arc.utility.Converter;
import gwu.csci.arc.utility.SAssembler;

public class TestVector {
	public static void main(String[] args) {
		
		CPU cpu = CPU.getInstance();
		
		AIR air = new AIR(cpu);
		AIX aix = new AIX(cpu);
		STR str = new STR(cpu);
		gwu.csci.arc.isa.IN in = new gwu.csci.arc.isa.IN(cpu);
		SOB sob = new SOB(cpu);
		SIX six = new SIX(cpu);
		LDR ldr = new LDR(cpu);
		SMR smr = new SMR(cpu);
		SIR sir = new SIR(cpu);
		JMP jmp = new JMP(cpu);
		JCC jcc = new JCC(cpu);
		STIR stir = new STIR(cpu);
		STIX stix = new STIX(cpu);
		gwu.csci.arc.isa.OUT out = new gwu.csci.arc.isa.OUT(cpu);
		VADD vadd = new VADD(cpu);
		VSUB vsub = new VSUB(cpu);
		
		String program = "STIX 3 0;STIR 1 8;AIX 3 64;SOB 1 0 0 9;STIR 1 6;STIR 2 7;SIR 2 1;STR 2 3 0 0;AIX 3 3;SOB 1 0 0 21;SIX 3 18;STIR 1 6;LDR 3 3 0 0;OUT 3 1;AIX 3 3;SOB 1 0 0 39;SIX 3 18;VADD 0 3 0 0;STIR 1 6;LDR 3 3 0 0;OUT 3 1;AIX 3 3;SOB 1 0 0 60;SIX 3 18;VSUB 0 3 0 0;STIR 1 6;LDR 3 3 0 0;OUT 3 1;AIX 3 3;SOB 1 0 0 81";
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
		
//		String add_v1 = "010000000000";
//		char[] addBuffer = add_v1.toCharArray();
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
		String fpr = "000000000000000011";
		char[] fri = {'0', '0'};
		cpu.writeFR(fpr.toCharArray(), fri, fpr.length());
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
			case 29:
				vadd.start();
				break;
			case 30:
				vsub.start();
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
		//ic.printP1In();
		
		
//		addBuffer = add_v1.toCharArray();
//		cpu.readMem(memBuffer, memBuffer.length, addBuffer);
//		
//		System.out.println(memBuffer);

	}
}
