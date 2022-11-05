import java.io.*;
import java.util.*;
public class LRC_VRCr
{
	public static void main(String args[])
	{
		Scanner in=new Scanner(System.in);
		System.out.println("LRC(1),VRC(2)");
		int x=in.nextInt();
		if(x==1)
			LRC();
		if(x==2)
			VRC();
	}
	public static void LRC()
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
			System.out.println("File Not Avaliable");
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
	public static void VRC()
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
				System.out.println(s);
				System.out.println("Error");
				s+=1;
				logic(s);
		}
		else
		{
			s+=0;
			System.out.println(s);
			System.out.println("No Error");
		}
	}
}