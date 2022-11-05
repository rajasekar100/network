import java.util.*;
import java.io.*;
public class CRCR
{
	public static void main(String args[])
	{	
	String a[]=new String[2];
	int g=0;
		try
		{
			BufferedReader oBufRead=new BufferedReader(new FileReader("CRC.txt"));
			String line=oBufRead.readLine();
			while(line !=null)
			{
				a[g]=line;
				g++;
				line=oBufRead.readLine();
			}
		}catch(Exception ex){}
		String s=a[0],t=a[1];
		String u=s;
		String v="";
		String x=s.substring(0,4);
		for(int i=0;i<t.length()-1;i++)
			u+="0";
		String w=u;
		try
		{
		for(int j=0;j<s.length();j++)
		{
			v="";			
			for(int i=0;i<t.length();i++)
			{
				if(x.charAt(0)=='0'&&x.charAt(1)=='0')
					v+=(u.charAt(i)^'0');
				else
				v+=(u.charAt(i)^t.charAt(i));
			}
			x=v;
			v=v.substring(1,s.indexOf("(")+v.length()+1);
			u=v+w.charAt((t.length())+j);
		}
		}catch(Exception e){}
		System.out.println(x);
		g=0;
		for(int i=0;i<x.length();i++)
			if(x.charAt(i)=='0')
				g++;
		if(g==x.length())
			System.out.println("Not Edited");
		else
			System.out.println("Edited");

	}
}