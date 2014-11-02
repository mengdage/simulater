package gwu.csci.arc.test;

import gwu.csci.arc.CPU;
import gwu.csci.arc.IntegratedCircuit;
import gwu.csci.arc.isa.*;
import gwu.csci.arc.utility.Converter;
import gwu.csci.arc.utility.IOConnector;
import gwu.csci.arc.utility.SAssembler;

public class TestProgram1 {
public void run() {
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
	gwu.csci.arc.isa.OUT out = new gwu.csci.arc.isa.OUT(cpu);
	
	String program = "AIR 3 20;AIX 3 100;IN 1 0;STR 1 3 0 100;AIX 3 3;SOB 3 0 0 21";
	String program1 = "AIR 3 20;AIX 3 100;IN 1 0;STR 1 3 0 100;AIX 3 3;SOB 3 0 0 21;AIX 2 100;AIR 3 20;SIX 3 3;IN 1 0;STR 1 0 0 120;LDR 1 3 0 100;STR 1 2 0 94;SMR 1 0 0 120;STR 1 2 0 97;SIR 3 1;SIX 3 3;LDR 1 3 0 100;SMR 1 0 0 120;SMR 1 2 0 97;JCC 1 0 0 84;SOB 3 0 0 63;JMP 0 0 99;LDR 1 3 0 100;STR 1 2 0 94;SMR 1 0 0 120;STR 1 2 0 97;JMP 0 0 78;LDR 0 0 0 120;OUT 0 1;LDR 0 2 0 94;OUT 0 1";
	String program2 = "AIR 3 20;AIX 3 100;IN 1 0;STR 1 3 0 100;AIX 3 3;SOB 3 0 0 9;AIX 2 100;AIR 3 20;AIX 1 100;LDR 1 10 0 100;OUT 1 1;SIX 1 3;SOB 3 0 0 33 ;AIR 3 20;SIX 3 3;IN 1 0;STR 1 0 0 120;LDR 1 3 0 100;STR 1 2 0 94;SMR 1 0 0 120;STR 1 2 0 97;SIR 3 1;SIX 3 3;LDR 1 3 0 100;SMR 1 0 0 120;SMR 1 2 0 97;JCC 1 0 0 90;SOB 3 0 0 69;JMP 0 0 105;LDR 1 3 0 100;STR 1 2 0 94;SMR 1 0 0 120;STR 1 2 0 97;JMP 0 0 84;LDR 0 2 0 94;OUT 0 1;LDR 0 0 0 120;OUT 0 1";
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
	
	
//	char[] pc = {'0','0','0','0','0','0', '0', '0','1', '1', '1', '1'};
//	char[] ir = new char[18];
//	cpu.writePC(pc, pc.length);
//	cpu.readPC(pc, pc.length);
//	cpu.readMem(ir, ir.length, pc);
//	
//	int ins;
//	
//	ins= Converter.conveterS2I(ir, 6);
//	io.printString("----------------Start----------------");
//	while(ins != 0) {
//		switch (ins) {
//		case 1:
//			ldr.start();
//			break;
//		case 2:
//			str.start();
//			break;
//		case 5:
//			smr.start();
//			break;
//		case 7:
//			sir.start();
//		case 6:
//			air.start();
//			break;
//		case 10:
//			jcc.start();
//			break;
//		case 11:
//			jmp.start();
//			break;
//		case 14:
//			sob.start();
//			break;
//		case 22:
//			aix.start();
//			break;
//		case 23:
//			six.start();
//			break;
//		case 49:
//			in.start();
//			break;
//		case 50:
//			out.start();
//			break;
//		default:
//			io.printString("Runner: Invalid instruction!");
//			break;
//		}
//		cpu.readPC(pc, pc.length);
//		cpu.readMem(ir, ir.length, pc);
//		ins= Converter.conveterS2I(ir, 6);
//	}
//	if(ins == 0) {
//		io.printString("Runner: Complete!");
//	}
//	ic.printP1In();
	
	
}

}
