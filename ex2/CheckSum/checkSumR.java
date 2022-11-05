import java.util.*;
import java.io.*;
public class checkSumR
{
	public static void main(String args[])
	{
		String e="";
		File o=new File("CheckSum.txt"); 
		boolean ob=o.exists();
		if(ob==true)
			try
			{
				FileReader o_read=new FileReader(o);
				int r=o_read.read();
				while(r!=-1)
				{
					e+=((char)r);
					 r=o_read.read();
				}
			}catch (IOException ex){}
		else
			System.out.println("File Not Avaliable");
		int sum=0;
		String t="",u="";
		String []a=e.split("(?<=\\G.{"+8+"})");
		System.out.println(Arrays.toString(a));
		int k[]=new int[a.length];
		for(int i=0;i<a.length;i++)
			k[i]=Integer.parseInt(a[i],2);
		for(int i=0;i<k.length;i++)
			sum+=k[i];
		t=Integer.toBinaryString(sum);
		System.out.println(t.length());
		for(int i=0;i<8;i++)
			u=t.charAt(t.length()-i-1)+u;
		t=u;
		System.out.println(t);
		u="";
		for(int i=0;i<t.length();i++)
		{
			if(t.charAt(i)=='0')
				u+="1";
			else
				u+="0";
		}
		System.out.println(u);
		t=Integer.toBinaryString((Integer.parseInt(u,2)+1));
		for(int i=0;i<=9-t.length();i++)
			t="0"+t;
		u="";
		for(int i=0;i<8;i++)
			u=t.charAt(t.length()-i-1)+u;
		System.out.println(u);
		sum=0;
		for(int i=0;i<u.length();i++)
			if(u.charAt(i)=='0')
				sum++;
			if(sum==u.length())
				System.out.println("Not Edited");
			else
				System.out.println("Edited");
	}
}