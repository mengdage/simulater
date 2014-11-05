package gwu.csci.arc.test;

import gwu.csci.arc.CPU;
import gwu.csci.arc.IntegratedCircuit;
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
import gwu.csci.arc.utility.Converter;
import gwu.csci.arc.utility.IOConnector;
import gwu.csci.arc.utility.SAssembler;

public class TestProgram2 {
	public static void main(String[] args) {
		System.out.println("start writing paragraph into memory");
		WriteStringIntoMemory.main(args);
		
		
		String program2 = "STIX 3 0;STIR 1 8;AIX 3 64;SOB 1 0 0 9;STIX 2 0;STIR 1 16;AIX 2 64;SOB 1 0 0 21;STIX 1 0;STIR 1 17;AIX 1 64;SOB 1 0 0 33;STIR 2 1;STR 2 1 0 0;STR 2 1 0 3;LDR 2 1 0 0;SIR 2 7;STIR 0 127;AIR 0 127;AIR 0 58;STR 0 1 0 29;JCC 3 1 1 30;LDR 2 3 0 0;SMR 2 2 0 0;STIR 0 117;AIR 0 0;STR 0 1 0 29;JCC 3 1 1 30;STIR 2 0;STR 2 1 0 15;STIX 2 0;STIR 1 16;AIX 2 64;SOB 1 0 0 99;STIR 0 127;AIR 0 74;STR 0 1 0 29;JMP 1 1 30;LDR 2 1 0 24;SIR 2 1;SMR 2 1 0 15;STIR 0 127;AIR 0 35;STR 0 1 0 29;JCC 3 1 1 30;LDR 2 1 0 15;AIR 2 1;STR 2 1 0 15;AIX 2 3;STIR 0 127;AIR 0 74;STR 0 1 0 29;JMP 1 1 30;LDR 3 1 0 0;OUT 3 1;LDR 3 1 0 3;OUT 3 1;STIR 2 0;STR 2 1 0 15;STIX 2 0;STIR 1 16;AIX 2 64;STIR 0 127;AIR 0 59;STR 0 1 0 29;SOB 1 1 1 30;LDR 2 3 0 0;SIR 2 32;STIR 0 127;AIR 0 127;AIR 0 4;STR 0 1 0 29;JCC 3 1 1 30;LDR 2 3 0 0;SIR 2 46;STIR 0 127;AIR 0 127;AIR 0 28;STR 0 1 0 29;JCC 3 1 1 30;STIR 0 127;AIR 0 127;AIR 0 43;STR 0 1 0 29;JMP 1 1 30;LDR 2 1 0 3;AIR 2 1;STR 2 1 0 3;STIR 0 127;AIR 0 127;AIR 0 43;STR 0 1 0 29;JMP 1 1 30;LDR 2 1 0 0;AIR 2 1;STR 2 1 0 0;STIR 2 0;STR 2 1 0 3;AIX 3 3;STIR 0 48;AIR 0 0;STR 0 1 0 29;JMP 1 1 30;LDR 2 1 0 3;";
		CPU cpu = CPU.getInstance();
		IntegratedCircuit ic = IntegratedCircuit.getInstance(cpu);
		IOConnector io = IOConnector.getInstance();
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
		
		String[] instruction = program2.split(";");
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
		
		
		char[] pc = {'0','0','0','0','0','0', '0', '0','0', '0', '1', '1'};
		char[] ir = new char[18];
		cpu.writePC(pc, pc.length);
		cpu.readPC(pc, pc.length);
		cpu.readMem(ir, ir.length, pc);
		
		int ins;
		
		ins= Converter.conveterS2I(ir, 6);
		io.printString("----------------Start----------------");
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
			io.printString("Runner: Complete!");
		}
		//ic.printP1In();
	}
	
}
