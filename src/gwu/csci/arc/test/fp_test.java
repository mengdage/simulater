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
							
				// separate signs, exponents, and mantissas
				fp_sign_x[0] = FP1[0];
				fp_sign_y[0] = FP2[0];
				
				for (int i = 1; i < 8; i++)
				{
					exp_x[i-1] = FP1[i];
					exp_y[i-1] = FP2[i];
				}
				for (int i = 8; i < 18; i++)
				{
					mts_x[i-8] = FP1[i];
					mts_y[i-8] = FP2[i];
				}
				
				int dec_exp_x = 0;
				int dec_exp_y = 0;
				double dec_x = 0;
				double dec_y = 0;
				
				// convert binary exponents to integer
				dec_exp_x = Converter.conveterS2I(exp_x, 7);
				dec_exp_y = Converter.conveterS2I(exp_y, 7);
				
				// convert binary mantissas to integers 
				for (int i = 0; i < 10; i++)
				{
					if (mts_x[i] == '1')
					{
						dec_x += Math.pow(2, -(i+1));
					}
					if (mts_y[i] == '1')
					{
						dec_y += Math.pow(2, -(i+1));
					}
				}
				
				dec_x = dec_x * Math.pow(2, dec_exp_x);
				dec_y = dec_y * Math.pow(2, dec_exp_y);
				
				// sign
				if (fp_sign_x[0] == '1')
				{	
					dec_x = dec_x * (-1);
				}
				if (fp_sign_y[0] == '1')
				{
					dec_y = dec_y * (-1);
				}
				
				// floating point addition in decimal
				dec_x += dec_y;
				
				// convert back to binary
				// reset sign bit
				if (dec_x >= 0) fp_sign_x[0] = '0';
				else
				{
					fp_sign_x[0] = '1';
					// ignore value sign
					dec_x = dec_x * (-1);
				}
				
				// divide exponent to get mantissa
				dec_x = dec_x / Math.pow(2, dec_exp_x);
				
				// convert mantissa to sum of fractions in binary
				for (int i = 0; i < 10; i++)
				{
					if (dec_x >= Math.pow(2, -(i+1)))
					{
						dec_x -= Math.pow(2, -(i+1));
						FP1[i+8] = '1';
					}
					else
						FP1[i+8] = '0';
				}
				
				System.out.print("Sum = ");
				for (int i = 0; i < 18; i++)
					System.out.print(FP1[i]);
	}
	

}
