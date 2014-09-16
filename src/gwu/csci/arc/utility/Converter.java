package gwu.csci.arc.utility;

public class Converter {
	//this is a utility class which contains some useful function
	
	/**
	 * convert an int to char[]
	 * @param addr address
	 * @param output the calculated binary address
	 * @return 0
	 */
	public static int addrConverterI2S(int addr, char[] output) {
		//int len = IntegratedCircuit.getLenAddr();
		//int len = 10;
		int len = output.length;
		
		for (int i = 0; i < len; i++) {
			output[i] = '0';
			
		}
		if(addr > 1024) {
			System.out.println("the address exceed the boundary");
		}
		for (int i = 0; ((addr/2)>0 || addr == 1) && i < len; i++) {
			if (addr % 2 == 0) {
				output[len -1 -i] = '0';
			}
			else {
				output[len - 1 - i] = '1';
			}
			addr = (addr/2);
		}
		return 0;
	}
	/**
	 * address converter convert an char[] to int
	 * @param addr
	 * @param len
	 * @return int
	 */
	public static int addrConveterS2I(char[] addr, int len) {
		int result = 0;
		int j = 0;
		for (int i = 0; i < len; i++) {
			if (addr[len - 1- i] == '0') {
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
}
