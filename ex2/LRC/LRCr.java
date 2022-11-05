import java.io.*;
import java.util.*;
public class LRCr
{
	public static void main(String args[])
	{
		String s="";
		int e=8,x=0;
		File o=new File("LRC.txt");
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
			}catch (IOException ex){}
		else
		{
			System.out.println("File Not Avaliable");
		}
		String []a=s.split("(?<=\\G.{"+e+"})");
		try
		{
			for(int i=0;i<9;i++)
			{
				for(int j=0;j<a.length;j++)
				{
					if((a[j].charAt(i))=='1')
						x+=1;
				}
				if(x%2==0)
				{
					x=0;
					continue;
				}
				else
				{
					System.out.println("Error");
					System.exit(0);
				}
			}
		}catch(Exception exp){}
		System.out.println("No Error");

	}
}