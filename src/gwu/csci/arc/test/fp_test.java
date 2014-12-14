package gwu.csci.arc.test;

import gwu.csci.arc.CPU;
import gwu.csci.arc.IntegratedCircuit;
import gwu.csci.arc.utility.Converter;

public class fp_test {
	
	public static void main(String[] args)
	{
		CPU cpu = CPU.getInstance();
		IntegratedCircuit ic = IntegratedCircuit.getInstance(cpu);
		
		
				char[] fp_sign_x = new char[1];
				char[] fp_sign_y = new char[1];
				char[] exp_x = new char[7];
				char[] exp_y = new char[7];
				char[] mts_x = new char[10];
				char[] mts_y = new char[10];
				
				char[] FP1 = {'0', '0', '0', '0', '1', '0', '0', '1','0', '0', '0', '0', '0', '0', '0', '1', '1', '0'};
				char[] FP2 = {'0', '0', '0', '0', '0', '1', '1', '1', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0'};
							
				double dec_x = 0;
				double dec_y = 0;
				
				// need to store exponent in order to convert back
				int dec_exp_x = 0;
				int dec_exp_y = 0;
				
				// convert 18 bits binary floating point numbers into decimal
				dec_x = Converter.converterF2S(FP1, FP1.length, dec_exp_x);
				dec_y = Converter.converterF2S(FP2, FP2.length, dec_exp_y);
				
				// floating point addition in decimal
				dec_x -= dec_y;
				
				// convert sum of 2 floating point numbers back to 18 bits binary from decimal
				Converter.converterS2F(dec_x, dec_exp_x);
				
				
				
				System.out.print("Sum = ");
				for (int i = 0; i < 18; i++)
					System.out.print(FP1[i]);
	}
	

}
