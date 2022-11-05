import java.util.*;
import java.io.*;
public class TwoDimParityS
{
	public static void main(String args[])
	{
		Scanner in=new Scanner(System.in);
		System.out.println("Enter The Values");
		String e=in.nextLine();
		String c="",s,b;
		int x=0,y=0,j;
		String a[]=new String[(e.length()/7)+2];
		for(int i=0;i<e.length();i++)
		{
			c+=e.charAt(i);
			x++;
			if(x==7)
			{
				a[y]=c;
				x=0;
				c="";
				y++;
			}
		}
		x=7-c.length();
		for(int i=0;i<x;i++)
		{
			c="0"+c;
		}
		a[y]=c;
		x=0;
		for(int i=0;i<a.length-1;i++)
		{
			b=a[i];
			j=0;
			while(j<b.length())
			{
				if((Character.getNumericValue(b.charAt(j)))==1)
					x+=1;
				j++;
			}
			if(x%2==0)
			{
				b+="0";
				a[i]=b;
			}
			else
			{
				b+="1";
				a[i]=b;
			}
			x=0;
		}
		b="";
		try
		{
			for(int i=0;i<=8;i++)
			{
				for(j=0;j<a.length-1;j++)
				{
					if((Character.getNumericValue(a[j].charAt(i)))==1)
						x+=1;
				}
				if(x%2==0)
					b+="0";
				else
					b+="1";
				x=0;
			}
		}catch(Exception ex){}
		a[a.length-1]=b; 
		b="";
		for(int i=0;i<a.length;i++)
			b+=a[i];
		File o=new File("TDP.txt");
		boolean ob=o.exists();
		try
		{
			if(ob==false)
			{
				o.createNewFile();
			}
			FileWriter o_write=new FileWriter(o);
			o_write.write(b);
			System.out.println("Write Successfully");
			o_write.flush();
			o_write.close();
		}catch (IOException exp)
			{System.out.println("expn");}	
	}
}