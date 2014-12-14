package gwu.csci.arc.utility;

public class Converter {
	//this is a utility class which contains some useful function
	
	/**
	 * convert an int to char[]
	 * @param addr address
	 * @param output the calculated binary address
	 * @return 0 success; 1 failure
	 */
	public static int converterI2S(int addr, char[] output) {
		return converterI2S(addr, output, output.length);
	}
	
	/**
	 * convert an int to char[]
	 * @param addr address
	 * @param output the calculated binary address
	 * @return 0 success; 1 failure
	 */
	public static int converterI2S(int addr, char[] output, int len) {
		//int len = IntegratedCircuit.getLenAddr();
		//int len = 10;
		
		
		return converterI2S(addr, output, 0, len);
	}
	
	/**
	 * convert an int to char[]
	 * @param addr address
	 * @param output the calculated binary address
	 * @return 0 success; 1 failure
	 */
	public static int converterI2S(int addr, char[] output, int startPos, int len) {
		//int len = IntegratedCircuit.getLenAddr();
		//int len = 10;
		
		
		for (int i = 0; i < len; i++) {
			output[startPos+i] = '0';
			
		}
		if(addr >= 0) {
			for (int i = 0; ((addr/2)>0 || addr == 1) && i < len; i++) {
				if (addr % 2 == 0) {
					output[startPos+len -1 -i] = '0';
				}
				else {
					output[startPos+len - 1 - i] = '1';
				}
				addr = (addr/2);
			}
		} else {
			System.err.println("Converter: less than 0 are not supported!");
			return 1;
		}
		return 0;
	}
	/**
	 * converter convert an char[] to int
	 * @param addr
	 * @param len
	 * @return int
	 */
	public static int conveterS2I(char[] addr, int len) {
		return conveterS2I(addr, 0, len);
	}
	
	public static int conveterS2I(char[] addr, int startPos, int len) {
		int result = 0;
		int j = 0;
		for (int i = 0; i < len; i++) {
			if (addr[len+startPos - 1- i] == '0') {
				if(j == 0) {
					j = 1;
				}
				else {
					j = j * 2;
				}
					
				result = result + 0;
			}
			else {
				if(j == 0) {
					j = 1;
				}
				else {
					j = j * 2;
				}
				result = result + j;
			}
		}
		return result;
	}
	
	/**
	 * 
	 * @param s 18 bits binary floating point number
	 * @param len length of the binary floating point number
	 * @param dec_exp exponent of the floating point number in decimal
	 * @return the floating point number in decimal
	 */
	public static double converterF2S(char[] s, int len, int dec_exp)
	{
		char sign;
		char[] exp = new char[7];
		char[] mts = new char[10];
		
		// floating point number in decimal
		double dec = 0;
		
		// set sign
		sign = s[0];
		
		// separate exponent and mantissa
		for (int i = 1; i < 8; i++)
		{
			exp[i-1] = s[i];
		}
		for (int i = 8; i < 18; i++)
		{
			mts[i-8] = s[i];
		}
		
		// convert exponent to decimal
		dec_exp = conveterS2I(exp, 7);
		
		// convert mantissa to decimal sum
		for (int i = 0; i < 10; i++)
		{
			if (mts[i] == '1')
			{
				dec += Math.pow(2, -(i+1));
			}
		}
		
		// combine decimal mantissa sum and exponent
		dec = dec * Math.pow(2, dec_exp);
		
		// combine decimal floating point number and its sign
		if (sign == '1')
		{	
			dec = dec * (-1);
		}	
		
		return dec;
	}
	
	/**
	 * 
	 * @param dec the floating point number in decimal
	 * @param dec_exp the exponent of the floating point number in decimal
	 * @return the floating point number in 18 bits binary
	 */
	public static char[] converterS2F(double dec, int dec_exp)
	{
		char[] s = new char[18];
		char[] exp = new char[7];
		
		if (dec >= 0) s[0] = '0';
		else
		{
			s[0] = '1';
			// ignore value sign
			dec = dec * (-1);
		}
		
		// convert decimal exponent back to 7 bits binary
		converterI2S(dec_exp, exp, 7);
		for (int i = 0; i < 7; i++)
		{
			s[i+1] = exp[i];
		}
		
		// divide exponent to get mantissa
		dec = dec / Math.pow(2, dec_exp);
		
		// convert mantissa to sum of fractions in binary
		for (int i = 0; i < 10; i++)
		{
			if (dec >= Math.pow(2, -(i+1)))
			{
				dec -= Math.pow(2, -(i+1));
				s[i+8] = '1';
			}
			else
				s[i+8] = '0';
		}
		
		return s;
	}
}
