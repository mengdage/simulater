package gwu.csci.arc.utility;

public class SAssembler {
	private int ldr = 1;
	private int str = 2;
	private int lda = 3;
	private int ldx = 33;
	private int stx = 34;
	private int jz = 8;
	private int jne = 9;
	private int jcc = 10;
	private int jmp = 11;
	private int jsr = 12;
	private int rfs = 13;
	private int sob = 14;
	private int jge = 15;
	private int amr = 4;
	private int smr = 5;
	private int air = 6;
	private int sir = 7;
	private int aix = 22;
	private int six = 23;
	private int stir = 42;
	private int stix = 43;
	private int in_ = 49;
	private int out_ = 50;
	
	public void assembler(char[] out, String in){
		String[] component = in.split(" ");
		System.out.println("--------"+component[0] + "--------");
		switch (component[0]) {
		case "LDR":
			//instruction[0-5]
			Converter.converterI2S(ldr, out, 6);
			//instruction[6-7]
			Converter.converterI2S(Integer.parseInt(component[1]), out,6,2);
			//instruction[8-9]
			Converter.converterI2S(Integer.parseInt(component[2]), out,8,2);
			//instruction[10-10]
			Converter.converterI2S(Integer.parseInt(component[3]), out,10,1);
			//instruction[11-17]
			Converter.converterI2S(Integer.parseInt(component[4]), out,11,7);
			
			break;
		case "STR":
			//instruction[0-5]
			Converter.converterI2S(str, out, 6);
			//instruction[6-7]
			Converter.converterI2S(Integer.parseInt(component[1]), out,6,2);
			//instruction[8-9]
			Converter.converterI2S(Integer.parseInt(component[2]), out,8,2);
			//instruction[10-10]
			Converter.converterI2S(Integer.parseInt(component[3]), out,10,1);
			//instruction[11-17]
			Converter.converterI2S(Integer.parseInt(component[4]), out,11,7);
			
			break;
		case "AIR":
			//instruction[0-5]
			Converter.converterI2S(air, out, 6);
			//instruction[6-7]
			Converter.converterI2S(Integer.parseInt(component[1]), out, 6,2);
			//instruction[8-10]
			for (int i = 0; i < 3; i++) {
				out[8+i] = '0';
			}
			//instruction[11-17]
			Converter.converterI2S(Integer.parseInt(component[2]), out, 11, 7);
			break;
		case "STIR":
			//instruction[0-5]
			Converter.converterI2S(stir, out, 6);
			//instruction[6-7]
			Converter.converterI2S(Integer.parseInt(component[1]), out, 6,2);
			//instruction[8-10]
			for (int i = 0; i < 3; i++) {
				out[8+i] = '0';
			}
			//instruction[11-17]
			Converter.converterI2S(Integer.parseInt(component[2]), out, 11, 7);
			break;
		case "SIR":
			//instruction[0-5]
			Converter.converterI2S(sir, out, 6);
			//instruction[6-7]
			Converter.converterI2S(Integer.parseInt(component[1]), out, 6,2);
			//instruction[8-10]
			for (int i = 0; i < 3; i++) {
				out[8+i] = '0';
			}
			//instruction[11-17]
			Converter.converterI2S(Integer.parseInt(component[2]), out, 11, 7);
			break;
		case "AIX":
			//instruction[0-5]
			Converter.converterI2S(aix, out, 6);
			//instruction[6-7]
			out[6] ='0';out[7]='0';
			//instruction[8-9]
			Converter.converterI2S(Integer.parseInt(component[1]), out, 8,2);
			//instruction[10-10]
			out[10]='0';
			//instruction[11-17]
			Converter.converterI2S(Integer.parseInt(component[2]), out, 11, 7);
			break;
		case "STIX":
			//instruction[0-5]
			Converter.converterI2S(stix, out, 6);
			//instruction[6-7]
			out[6] ='0';out[7]='0';
			//instruction[8-9]
			Converter.converterI2S(Integer.parseInt(component[1]), out, 8,2);
			//instruction[10-10]
			out[10]='0';
			//instruction[11-17]
			Converter.converterI2S(Integer.parseInt(component[2]), out, 11, 7);
			break;
		case "SIX":
			//instruction[0-5]
			Converter.converterI2S(six, out, 6);
			//instruction[6-7]
			out[6] ='0';out[7]='0';
			//instruction[8-9]
			Converter.converterI2S(Integer.parseInt(component[1]), out, 8,2);
			//instruction[10-10]
			out[10]='0';
			//instruction[11-17]
			Converter.converterI2S(Integer.parseInt(component[2]), out, 11, 7);
			break;
		case "SOB":
			//instruction[0-5]
			Converter.converterI2S(sob, out, 6);
			//instruction[6-7]
			Converter.converterI2S(Integer.parseInt(component[1]), out,6,2);
			//instruction[8-9]
			Converter.converterI2S(Integer.parseInt(component[2]), out,8,2);
			//instruction[10-10]
			Converter.converterI2S(Integer.parseInt(component[3]), out,10,1);
			//instruction[11-17]
			Converter.converterI2S(Integer.parseInt(component[4]), out,11,7);
			break;
		case "SMR":
			//instruction[0-5]
			Converter.converterI2S(smr, out, 6);
			//instruction[6-7]
			Converter.converterI2S(Integer.parseInt(component[1]), out,6,2);
			//instruction[8-9]
			Converter.converterI2S(Integer.parseInt(component[2]), out,8,2);
			//instruction[10-10]
			Converter.converterI2S(Integer.parseInt(component[3]), out,10,1);
			//instruction[11-17]
			Converter.converterI2S(Integer.parseInt(component[4]), out,11,7);
			break;
		case "JMP":
			//instruction[0-5]
			Converter.converterI2S(jmp, out, 6);
			//instruction[6-7]
			out[6] ='0';out[7]='0';
			//instruction[8-9]
			Converter.converterI2S(Integer.parseInt(component[1]), out,8,2);
			//instruction[10-10]
			Converter.converterI2S(Integer.parseInt(component[2]), out,10,1);
			//instruction[11-17]
			Converter.converterI2S(Integer.parseInt(component[3]), out,11,7);
			break;
		case "IN":
			//instruction[0-5]
			Converter.converterI2S(in_, out, 6);
			//instruction[6-7]
			Converter.converterI2S(Integer.parseInt(component[1]), out, 6,2);
			//instruction[8-10]
			for (int i = 0; i < 3; i++) {
				out[8+i] = '0';
			}
			//instruction[11-17]
			Converter.converterI2S(Integer.parseInt(component[2]), out,11,7);
			break;
		case "OUT":
			//instruction[0-5]
			Converter.converterI2S(out_, out, 6);
			//instruction[6-7]
			Converter.converterI2S(Integer.parseInt(component[1]), out, 6,2);
			//instruction[8-10]
			for (int i = 0; i < 3; i++) {
				out[8+i] = '0';
			}
			//instruction[11-17]
			Converter.converterI2S(Integer.parseInt(component[2]), out,11,7);
			break;
		case "JCC":
			//instruction[0-5]
			Converter.converterI2S(jcc, out, 6);
			//instruction[6-7]
			Converter.converterI2S(Integer.parseInt(component[1]), out,6,2);
			//instruction[8-9]
			Converter.converterI2S(Integer.parseInt(component[2]), out,8,2);
			//instruction[10-10]
			Converter.converterI2S(Integer.parseInt(component[3]), out,10,1);
			//instruction[11-17]
			Converter.converterI2S(Integer.parseInt(component[4]), out,11,7);
			break;

		default:
			System.err.println("illegal instruciton!");
			break;
		}
	}
}
