package gwu.csci.arc;

import gwu.csci.arc.utility.Converter;

public class ALU {
	private char[] cc = new char[4];
	
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
		Converter.converterI2S(p1-p2, result);
		
		return 0;
	}
}
 