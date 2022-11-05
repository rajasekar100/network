import java.util.*;
import java.io.*;
public class charCountReciver
{
	public static void main(String args[])
	{
		StringBuffer s=new StringBuffer("");
		StringBuffer t=new StringBuffer();
		File o=new File("charCount.txt");
		boolean ob=o.exists();
		boolean c; 
		int z,z1=0;
		if(ob==true)
			try
			{
				FileReader o_read=new FileReader(o);
				int r=o_read.read();
				while(r!=-1)
				{
					s.append((char)r);
					 r=o_read.read();
				}
			}catch (IOException e)
				{System.out.println("expn");}	
		else
		{
			System.out.println("file not avaliable");
		}
		for (int i=0;i<s.length();i++)
		{
			if(Character.isLetter(s.charAt(i)))
			{
				z1++;
			}
			else if (Character.isDigit(s.charAt(i)))
			{
				if(Character.getNumericValue(s.charAt(i))==z1)
				{
					z1=0;
					continue;
				}
				else
				{
					System.out.println("Number Edited");
					System.exit(0);
				}
			}
		}
		for (int i=0;i<s.length();i++)
		{
			c=Character.isDigit(s.charAt(i));
			if(c)
			{
				System.out.print(" ");
				continue;
			}
			else
			{
				System.out.print(s.charAt(i));
			}
		}
	}
}
