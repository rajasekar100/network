import java.util.*;
import java.io.*;
public class ECS
{
	public static void main(String args[])
	{	
		Scanner in=new Scanner(System.in);                                                        //
		System.out.println("Enter the Binary Value");                                            // The value is 1001101
		String s=in.nextLine();                                                                              // s=1001101
		String c="";                                                                                             //
		int m=s.length(),r=0,k=0;                                                                         // m=7
		char q='x';                                                                                               //
		for(int i=1;i<s.length();i++)                     	                                              //  1 to 7 because r value must below the s.length
		{                                                                                                             //
			if((int)Math.pow(2,i)>m+i+1)                                                               //  2^ (0-7) > 7+(0-7)+1  Formula
			{                                                                                                         // if condition satisfied assign r value &
				r=i;                                                                                                  //  break the loop
				break;                                                                                              //
			}                                                                                                         //
			else                                                                                                     // else continue
				continue;                                                                                         //
		}                                                                                                             //
		String a[]=new String[m+r];                                                                     // create array for m=7 and r=4
		for(int i=0;i<r;i++)                                                                                   // loop 0-3
			a[(int)Math.pow(2,i)-1]=String.valueOf(q);                                            // 	a[2 power value ] assign x
		r=s.length()-1;                                                                                          // r=6
			for(int i=0;i<a.length;i++)                                                                     // 0 to (7+4=11)
			{                                                                                                         //
				if(a[i]!=null)                                                                                     // if in the array have x value then continue
					continue;                                                                                     // 
				else                                                                                                 // if it is empty 
				{                                                                                                     //
					a[i]=Character.toString(s.charAt(r));                                             // assign s last value (6th index value first) in the array
					r--;                                                                                              // r=6 change to 5 then loop again
				}                                                                                                     //
			}                                                                                                         //
		//System.out.println(Arrays.toString(a));                                                   //
		r=a.length;                                                                                               //r=11
		String b[]=new String[r];                                                                          //create array (size 11)for add binary value 0 to 11
		for(int i=0;i<r;i++)                                                                                   // loop 0 to 11
			b[i]=Integer.toBinaryString(i+1);                                                           // add binary value+1 in the array based upon the corrosponding index position(2nd index 11(3))
		for(int i=0;i<b.length;i++)                                                                         //loop 0 to 11
			while(b[r-1].length()!=b[i].length())                                                       // last binary value length != current value length (loop b[i] value) then add 0 in front
				b[i]="0"+b[i];                                                                                  //
		for(int j=1;j<=b[b.length-1].length();j++)                                                   //  j loop 0 to last binary value length (here 4 (1011(11)))
		{                                                                                                             //
			s="";                                                                                                   //
			for(int i=0;i<b.length;i++)                                                                     // i loop 0 to 11
			{                                                                                                         //
				if(Character.toString(b[i].charAt(b[i].length()-j )).equals("1"))             // b[0](0001).charAt means ({0001(4)-1(j)}3)(index 3 means 1)==1
				{						                                                                                //
					if(a[i].equals(Character.toString(q)))                                               // a[0](x)==q(x), a[1](x)==q(x) or a[2](1)==q(x)
					{                                                                                                   //
						r=i;                                                                                           // assign r=0
						continue;                                                                                   //
					}                                                                                                   //
					else                                                                                               // 
						s+=a[i];                                                                                     // s=empty first now add a[2](1) assign into s
				}                                                                                                       //
			}                                                                                                           //inner loop end
			int w=(int)(s.chars().filter(ch -> ch == '1').count());                                // occurence of 1 in s
			if(w%2==1)                                                                                          // if add number of ones 
				a[r]="1";                                                                                           // a[0](x)(r value in 55th line) assign =1
			else                                                                                                       // if even number of ones
				a[r]="0";                                                                                           // a[1](x)(r value in 55th line) assign =0
		}                                                                                                               //
		//System.out.println(Arrays.toString(a));                                                     //
		for(int i=0;i<a.length;i++)                                                                           //convert array to string
			c+=a[i];                                                                                                //
		System.out.println(c);
		File o=new File("EC.txt");                                                                         //
		boolean ob=o.exists();                                                                               //
		try                                                                                                            //
		{                                                                                                               //
			if(ob==false)                                                                                         //
				o.createNewFile();                                                                             // 
			FileWriter o_write=new FileWriter(o);                                                    //
			o_write.write(c);                                                                                    //write in the file
			System.out.println("Write Successfully");                                               //
			o_write.flush();                                                                                      //
			o_write.close();                                                                                     //
		}catch (IOException exp){}
	}
}