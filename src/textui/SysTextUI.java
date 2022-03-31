package textui;

import java.util.Scanner;

public class SysTextUI implements TextUI
{
	private final static Scanner keyboard = new Scanner(System.in);
	@Override
	public void print(String s)
	{
		System.out.print(s);
	}

	@Override
	public String get()
	{
		System.out.print("\n>");
		return keyboard.nextLine();
	}

	@Override
	public void clear()
	{
		for(int i = 0; i < 100; ++i)
		{
			System.out.println("");
		}
	}
}
