import java.util.*;
import java.io.*;
public class PLCreciver
{
	public static void main(String args[])
	{		
		boolean b=false;
		StringBuffer s=new StringBuffer("");
		File o=new File("file.txt");
		boolean ob=o.exists();
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
			System.out.println("File Not Avaliable");
		}
		
		for (int i=2;i<s.length()-2;i+=2)
		{
			if(Character.getNumericValue(s.charAt(i))!=0&&Character.getNumericValue(s.charAt(i))!=1)
			{
				System.out.println("Input Error");
				System.exit(0);
			}
			else if(Character.getNumericValue(s.charAt(i))==0&&Character.getNumericValue(s.charAt(i+1))==0)
			{
				System.out.println("message Error");
				System.exit(0);
			}
			else
			{
				continue;
			}
		}
		if(s.length()%2!=0)
		{
			System.out.println("Error");
		}
		else if(Character.getNumericValue(s.charAt(0))!=0||Character.getNumericValue(s.charAt(1))!=0)
		{
			System.out.println("Starting Error");
		}
		else if(Character.getNumericValue(s.charAt(s.length()-1))!=1||Character.getNumericValue(s.charAt(s.length()-2))!=1)
		{
			System.out.println("Ending Error");
		}
		else
		{
			for (int i=2;i<s.length()-2;i=i+2)
			{
				if(s.charAt(i)==1&&s.charAt(i+1)==1)
				{
					break;
				}
				else				
					System.out.print(s.charAt(i));
			}
		}
	}
}
