import javax.swing.*;
import javax.swing.event.*;
import java.awt.Toolkit;
import java.awt.Font;
import java.awt.Color;
import java.awt.event.*;
import javax.swing.border.LineBorder;
import java.util.*;
import java.net.*;
import java.io.*;
public class client
{
	public static void main(String args[])throws Exception
	{
		new frame();
	}
	public static String[] network(String rno)throws Exception
	{	
		String[] array=new String[19];
		try
		{
		Socket skt=new Socket("localhost",2255);
		DataOutputStream odos=new DataOutputStream(skt.getOutputStream());
		odos.writeUTF(rno);
		
		InputStream is = skt.getInputStream();
        ObjectInputStream ois = new ObjectInputStream(is);
		
		List<Message> listOfMessages = (List<Message>) ois.readObject();
       // listOfMessages.forEach((msg)-> System.out.println(msg.getText()));
		
		System.out.println("");
		
		for(int i=0;i<20;i++)
		{
			array[i]=listOfMessages.get(i).getText();
		}
		System.out.println(Arrays.toString(array));
		skt.close();
		
		}catch(Exception e)
		{System.out.println("exception");}
		return array;
	}
}
class Message implements Serializable{
    private final String text;

    public Message(String text) {
        this.text = text;
    }
    public String getText() {
        return text;
    }
}
class frame extends JFrame implements ActionListener
{
		
		ScreenSize o=new ScreenSize();
		JButton b1;
		JLabel l3,l2,l1;
		JPanel p1,p2;
		JTextField t1;
		JTextArea ta1;
		static JList lst1;
		JScrollPane sp;
		//Color c="152,34,28,5";
		public frame()
	{
		this.setSize(o.getX(),o.getY());
		this.setLayout(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		Font f1=new Font("times new roman",Font.PLAIN,64);
		Font f2=new Font("times new roman",Font.PLAIN,20);
		Font f3=new Font("times new roman",Font.PLAIN,18);
		
		l1=new JLabel("FTP PROTOCAL");
		l1.setFont(f1);
		l1.setBounds(600,10,800,80);
		this.add(l1);
		
		p1=new JPanel();
		p1.setLayout(null);
		p1.setBounds(250,150,250,270);
		p1.setBorder(new LineBorder(Color.BLACK, 1));
		add(p1);
		p2=new JPanel();
		p2.setLayout(null);
		p2.setBounds(700,150,720,720);
		p2.setBorder(new LineBorder(Color.BLACK, 1));
		add(p2);
		
		l2=new JLabel("Enter Your Roll NO :");
		l2.setFont(f2);
		l2.setBounds(20,10,200,20);
		p1.add(l2);
	
		ta1=new JTextArea();
		ta1.setFont(f2);
		ta1.setBounds(10,10,700,700);
		p2.add(ta1);
		
		t1=new JTextField();
		t1.setFont(f3);
		t1.setBounds(20,40,200,25);
		p1.add(t1);
		//this.setBackground(c);
		b1=new JButton("Click");
		b1.setFocusable(false);  
		b1.setFont(f3);
		b1.setBounds(20,75,120,25);
		p1.add(b1);
		b1.addActionListener(this);
		this.setVisible(true);
	}
	public void actionPerformed(ActionEvent e)
	{
		
		if(e.getSource()==b1)
		{
				client oc=new client();
				try
				{
					String[] res =oc.network(t1.getText());
				
				ta1.setText("\n"
							+"	============================================"+"\n"
							+"		        STUDENT  DETAIL "+"\n"
							+"	============================================"+"\n"
							+"		           Personal Details"+"\n"
							+"		Student RollNo	:"+res[0]+"\n"
							+"		Admission No		: "+res[1]+"\n"
							+"		Name		: "+res[2]+"\n"
							+"		Department		: "+res[3]+"\n"
							+"		Batch		: "+res[4]+"\n"
							+"		Quota		: "+res[5]+"\n"
							+"		City		: "+res[6]+"\n"
							+"		Mobile		: "+res[7]+"\n"
							+"		BloodGroup		: "+res[8]+"\n"
							+"		Dayscholer/Hostler	:"+res[9]+"\n"
							+"	============================================"+"\n"
							+"		           Mark Details"+"\n\n"
							+"\t    Internal 1"+"\t           "+"Internal 2"+"          "+"Internal 3"+"\n"
							+"\t Mark 1  :"+res[10]+"\t        "+"Mark 1  :"+res[13]+"      "+"Mark 1  :"+res[16]+"\n"
							+"\t Mark 2  :"+res[11]+"\t        "+"Mark 2  :"+res[14]+"      "+"Mark 2  :"+res[17]+"\n"
							+"\t Mark 3  :"+res[11]+"\t        "+"Mark 3  :"+res[15]+"      "+"Mark 3  :"+res[18]+"\n"
							+"	============================================"+"\n"
			);
				
				}catch(Exception ep)
				{System.out.println("exception");}
		}
	}
}
class ScreenSize
{
	static int X,Y;
	static Toolkit tk=Toolkit.getDefaultToolkit();
		public static int getX()
	{
		X=(int)tk.getScreenSize().getWidth();
		return X;
	}
	public static int getY()
	{
		Y=(int)tk.getScreenSize().getHeight();
		return Y;
	}
}



 