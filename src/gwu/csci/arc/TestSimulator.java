package gwu.csci.arc;

public class TestSimulator {

	/**
	 * Load a file specified by filepath, and return the content
	 * of the content in String type
	 * @param filepath
	 * @return Content
	 */
	private static String loadFileContent(String filepath) {
		String content = new String();
		//balabalabala
		return content;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//test for github

		
		CPU cpu = CPU.getInstance();
		IntegratedCircuit ic = IntegratedCircuit.getInstance(cpu);
		Memory memory = Memory.getInstance();
		//000001001010101010
		char[] test_content = {'0','0','0','0','0','1', '0', '0', '1', '0', '1', '0', '1', '0', '1', '0', '1', '0'};
		//000000000001
		char[] test_xr = {'0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '1'};
		//101010 10 10 1 0101011
		char[] test_ins = {'1', '0', '1', '0','1', '0', '1', '0','1', '0', '1', '0','1', '0', '1', '0','1','1'};
		//101010 10 00 1 0101011
		char[] test_ins2 = {'1', '0', '1', '0','1', '0', '1', '0','0', '0', '1', '0','1', '0', '1', '0','1','1'};
		//101010 10 10 0 0101011
		char[] test_ins3 = {'1', '0', '1', '0','1', '0', '1', '0','1', '0', '0', '0','1', '0', '1', '0','1','1'};
		//101010 10 00 0 0101011
		char[] test_ins4 = {'1', '0', '1', '0','1', '0', '1', '0','0', '0', '0', '0','1', '0', '1', '0','1','1'};
		//000001 10 10 1 0101101
		char[]  test_ins_LDR = {'0', '0', '0', '0','0', '1', '1', '0','1', '0', '1', '0','1', '0', '1', '0','1','0'};
		//100(10)
		char[] ad1 = {'0','0','0','0','0','1', '1', '0','0', '1', '0', '0'};
		//43(10)
		char[] ad2 = {'0', '0','0', '0', '0', '0','1', '0', '1', '0','1','1'};
		
//		cpu.writePC(test_pc, Mainboard.getLenAddr());
//		memory.write(test_ir, Mainboard.getLenInstruction(), 128);
//		mb.decode();
		
		
		char[] id = {'1', '0'};
//		cpu.writeXR(test_xr, id, 12);
//		cpu.writePC(test_pc, IntegratedCircuit.getLenAddr());
		//memory.write(test_ins2, IntegratedCircuit.getLenInstruction(), ad1);
//		ic.decode();
//		ic.calcEA();
		//int i = 1025;
		
		//System.out.println(addrConvertorI2S(i));
		
		
//		int i = 100;
//		String s = "Hello World!";
//		char[] c = new char[12];
//		System.out.println(s.toCharArray().length);
//		memory.write(s.toCharArray(), 12, addrConvertorI2S(i));
//		memory.read(c, 12, addrConvertorI2S(i));
//		System.out.println(c);
		
//		String s = "Hello World!";
//		char[] c = s.toCharArray();
//		System.out.println(c[11] + ": " + c.length);
//		
//		String s2 = new String(c);
//		System.out.println(s2);
		
//		ALU alu = new ALU();
//		String s1 = "000101000101";
//		String s2 = "000001000101";
//		char[] result = new char[12];
//		alu.subtraction(s1.toCharArray(), s2.toCharArray(), 12, result);
//		System.out.println(result);
		
		
//		ic.writeMem(test_ins3, test_ins3.length, ad1);
//		cpu.writeXR(test_xr, id, test_xr.length);
//		cpu.writePC(ad1, ad1.length);
//		cpu.decode();
//		cpu.calcEA();
		
		//test for LDR
		ic.writeMem(test_ins_LDR, test_ins_LDR.length, ad1);
		ic.writeMem(test_content, test_content.length, ad2);
		cpu.writeXR(test_xr, id, test_xr.length);
		cpu.writePC(ad1, ad1.length);
		LDR ldr = new LDR(cpu);
		ldr.start();
		
		
	}

}
