package gwu.csci.arc.test;

import gwu.csci.arc.utility.Converter;

public class TestConverter {
public static void main(String[] args) {
	int i=0;
	double a = -3.5;

	char[] r = Converter.converterF2S(a);
	a = Converter.converterS2F(r, r.length);
//	System.out.println(i);
	System.out.println(r);
	System.out.println(a);
}
}
