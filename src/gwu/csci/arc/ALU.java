package gwu.csci.arc;

import gwu.csci.arc.utility.Converter;

public class ALU {
	//mengTest
	private static ALU alu;
	private CPU cpu;
	private char[] cc = new char[4];
	
	private ALU(CPU cpu){
		this.cpu = cpu;
	}
	public static ALU getInstance(CPU cpu) {
		if(alu == null){
			alu = new ALU(cpu);
		}
		return alu;
	}
	/**
	 * do addition
	 * @param op1
	 * @param op2
	 * @param result
	 * @return 
	 */
	public int addition(char[] op1, char[] op2, char result[]) {
		//char[] result = new char[IntegratedCircuit.getLenWord()];
//		int len = IntegratedCircuit.getLenAddr();
		int p1 = Converter.conveterS2I(op1, op1.length);
		int p2 = Converter.conveterS2I(op2, op2.length);
		Converter.converterI2S(p1+p2, result);
		return 0;
	}
	
	/**
	 * do subtraction
	 * @param op1
	 * @param op2
	 * @param result
	 * @return
	 */
	public int subtraction(char[] op1, char[] op2, char result[]) {
//		int len = IntegratedCircuit.getLenAddr();
		int p1 = Converter.conveterS2I(op1, op1.length);
		int p2 = Converter.conveterS2I(op2, op2.length);
		if(p1>p2) {
			for (int i = 0; i < cc.length; i++) {
				cc[i] = '0';
			}
			cpu.writeCC(cc, cc.length);
			Converter.converterI2S(p1-p2, result);
		}
		else {
			for (int i = 0; i < cc.length; i++) {
				cc[i] = '0';
			}
			if(p1==p2) {
				cc[3] ='1';
			} else {
				cc[1] = '1';
			}
			cpu.writeCC(cc, cc.length);
			Converter.converterI2S(p2-p1, result);
		}
		
		return 0;
	}
}
 