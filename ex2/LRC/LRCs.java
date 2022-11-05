import java.io.*;
import java.util.*;
public class LRCs
{
	public static void main(String args[])
	{
		Scanner in=new Scanner(System.in);
		System.out.println("Enter The Values");
		String e=in.nextLine();
		String c="",s,b;
		int x=0,y=0,j;
		String a[]=new String[(e.length()/8)+2];
		for(int i=0;i<e.length();i++)
		{
			c+=e.charAt(i);
			x++;
			if(x==8)
			{
				a[y]=c;
				x=0;
				c="";
				y++;
			}
		}
		x=8-c.length();
		try
		{
			for(int i=0;i<x;i++)
			{
				c="0"+c;
			}
			a[y]=c;
			y++;
		}catch(Exception expn){System.err.println();}
		x=0;
		b="";
		try
		{
			for(int i=0;i<=8;i++)
			{
				for(j=0;j<a.length-1;j++)
				{
					if(a[j].charAt(i)=='1')
						x+=1;
				}
				if(x%2==0)
					b+="0";
				else
					b+="1";
				x=0;
			}
		}catch(Exception ex){}
		a[y]=b; 
		b="";
		for(int i=0;i<a.length;i++)
			b+=a[i];
		File o=new File("LRC.txt");
		boolean ob=o.exists();
		try
		{
			if(ob==false)
				o.createNewFile();
			FileWriter o_write=new FileWriter(o);
			o_write.write(b);
			System.out.println("Write Successfully");
			o_write.flush();
			o_write.close();
		}catch (IOException exp)
			{System.out.println("expn");}	
		
	}
}