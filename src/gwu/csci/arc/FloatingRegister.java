package gwu.csci.arc;

public class FloatingRegister {
	// floating point register length
	private static final int LENGTH = 18;
	
	// two floating point registers
	private char[] FR0 = new char[LENGTH];
	private char[] FR1 = new char[LENGTH];
	
	// initialization
	public FloatingRegister()
	{
		for (int i = 0; i < LENGTH; i++)
		{
			FR0[i] = '0';
			FR1[i] = '0';
		}	
	}
	
	// return register length
	public static int getLength()
	{
		return LENGTH;
	}
	
	// return content of FR0
	public int getFR0(char[] fr0, int len)
	{
		for(int i = 0; i < len && i < LENGTH; i++)
		{
			fr0[i] = FR0[i];
		}
		
		return 0;
	}
	
	// set content of FR0
	public int setFR0(char[] fr0, int len)
	{
		return setFR(fr0, FR0, len);
	}
	
	// return content of FR1
	public int getFR1(char[] fr1, int len)
	{
		for(int i = 0; i < len && i < LENGTH; i++)
		{
			fr1[i] = FR1[i];
		}
		
		return 0;
	}
	
	// set content of FR1
	public int setFR1(char[] fr1, int len)
	{
		return setFR(fr1, FR1, len);
	}
	
	private int setFR(char[] fr, char[] FR, int len)
	{
		if (len > LENGTH)
		{
			len = LENGTH;
		}
		
		if (len < LENGTH)
		{
			for (int i = 0; i < LENGTH; i++)
			{
				if (i < (LENGTH - len))
				{
					FR[i] = '0';
				}
				else
				{
					FR[i] = fr[i-(LENGTH-len)];
				}
			}
		}
		else
		{
			for (int i = 0; i < LENGTH; i++)
			{
				FR[i] = fr[i];
			}
		}
		
		return 0;
	}
}
