import java.util.*;
import java.io.*;
public class VRCr
{
	public static void main(String args[])
	{
		String s="";
		File o=new File("VRC.txt");
		boolean ob=o.exists();
		if(ob==true)
			try
			{
				FileReader o_read=new FileReader(o);
				int r=o_read.read();
				while(r!=-1)
				{
					s+=((char)r);
					 r=o_read.read();
				}
				o_read.close();
			}catch (IOException ex){}
		else
		{
			System.out.println("File Not Avaliable");
		}
		logic(s);
	}
	static void logic(String s)
	{
		int w;
		w=(int)(s.chars().filter(ch -> ch == '1').count());
		if(w%2==1)
		{
				System.out.println("Error");
				s+=1;
				System.out.println(s);
				logic(s);
		}
		else
		{
			System.out.println("No Error");
			s+=0;
			System.out.println(s);
		}
	}
}