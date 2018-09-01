
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.*;
public class GUI implements ActionListener{

	private JFrame frame;
	private JPanel startScreen,queryPanel,resultPanel,resultLeftPanel,resultRightPanel,buttonPanel;
	private JButton login,qb[],back,submit[];
	private ArrayList <String>q,result; 
	private JLabel heading,questionLabel;
	private database d;
	private TableCreater t;
	private JTable x;
	JTextField inputArea;
	JTextArea name,pass;
	
	
	private void set_inputarea()
	{
		inputArea=new JTextField();
		inputArea.setFont(new Font("Calibri",Font.PLAIN,12));
		inputArea.setSize(new Dimension(200,15));
	}
	public GUI() {

		t=new TableCreater();
		x=new JTable();
		
		//resultPanel initialise
		//resultPanel=new JPanel();
		resultPanel=new JPanel()
		{
			Image bg = new ImageIcon("images/blood5.jpg").getImage();
	    @Override
	    public void paintComponent(Graphics g) {
	        g.drawImage(bg, 0, 0, getWidth(), getHeight(), this);
	    
		}
    };
		resultPanel.setLayout(new FlowLayout(FlowLayout.LEADING));
		//resultPanel.add(Box.createRigidArea(new Dimension(30,20)) );
		JLabel resultHeading=new JLabel("                          RESULTS");
		resultHeading.setFont(new Font("Calibri",Font.BOLD,50));
		resultHeading.setPreferredSize(new Dimension(800,55));
		resultPanel.add(resultHeading);
		resultPanel.add(Box.createRigidArea(new Dimension(800,10)));
		
		
		resultLeftPanel=new JPanel();
		resultLeftPanel.setBackground(Color.yellow);
		resultLeftPanel.setLayout(new BoxLayout(resultLeftPanel,BoxLayout.Y_AXIS));
		resultLeftPanel.setPreferredSize(new Dimension(300,100));
		
		resultRightPanel=new JPanel();
			
		questionLabel=new JLabel();
		questionLabel.setBackground(Color.green);
		questionLabel.setOpaque(true);
		questionLabel.setFont(new Font("Calibri",Font.BOLD,15));
		this.set_inputarea();
		back=new JButton(" BACK ");
		back.addActionListener(this);
		
		
		//resultPanel finsihed		
		
		d=new database();
		initialise_queries();
		submit=new JButton[10];
		frame=new JFrame("BBMS");
		frame.setMinimumSize(new Dimension(800,600));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(new BorderLayout());
		//frame.setBackground(Color.black);
		qb=new JButton[10];
		
		startScreen=new JPanel();// For heading		
		startScreen.setLayout(new BoxLayout(startScreen,BoxLayout.Y_AXIS));
		heading=new JLabel("Blood Bank Management System");
		heading.setFont(new Font("Calibri",Font.BOLD,50));
		heading.setForeground(Color.RED);
		heading.setBorder(BorderFactory.createLineBorder(Color.red, 2));
		heading.setAlignmentX(Component.CENTER_ALIGNMENT);
		heading.setBackground(Color.WHITE);
		heading.setOpaque(true);
		
		JPanel midPanel=new JPanel();
		midPanel.setLayout(new FlowLayout());
		JPanel loginPanel=new JPanel();
		loginPanel.setLayout(new FlowLayout());
		//loginPanel.setBackground(Color.yellow);
		loginPanel.setPreferredSize(new Dimension(300,300));
		loginPanel.add(Box.createRigidArea(new Dimension(300,100)));
		JLabel username=new JLabel("USERNAME");
		username.setFont(new Font("Calibri",Font.BOLD,20));
		username.setPreferredSize(new Dimension(100,50));
		JLabel password=new JLabel("PASSWORD");
		password.setFont(new Font("Calibri",Font.BOLD,20));
		password.setPreferredSize(new Dimension(100,50));
		name=new JTextArea();
		name.setPreferredSize(new Dimension(150,20));
		name.setFont(new Font("Calibri",Font.PLAIN,16));
		pass=new JTextArea();
		pass.setPreferredSize(new Dimension(150,20));
		pass.setFont(new Font("Calibri",Font.PLAIN,16));
		loginPanel.add(username);loginPanel.add(name);
		loginPanel.add(password);loginPanel.add(pass);
		
		login=new JButton("LOGIN");
		JButton exit=new JButton("EXIT");
		exit.addActionListener(new ActionListener()
				{
					public void actionPerformed(ActionEvent e)
					{System.exit(0);}
				});
		
		login.addActionListener(this);
		loginPanel.add(Box.createRigidArea(new Dimension(300,30)));
		loginPanel.add(login);
		loginPanel.add(Box.createRigidArea(new Dimension(50,20)));
		loginPanel.add(exit);
		
		midPanel.add(loginPanel);
		ImageIcon image=new ImageIcon("images/blood.jpg","photo");
		JLabel photo=new JLabel(image);
		midPanel.add(Box.createRigidArea(new Dimension(100,300)));
		midPanel.add(photo);
		startScreen.add(heading);
		startScreen.add(midPanel);
		
		//startScreen.setBorder(BorderFactory.createLineBorder(Color.black));
		/**
		 * queryPanel initialize
		 * */
		
		
		// for results and input
		//	b1=new JButton("Continue");
		//b1.setPreferredSize(new Dimension(100,40));
		//b1.addActionListener(this);
		//queryPanel.add(b1);
		/***
		 * 
		 * back
		 */
		//resultPanel=new JPanel();
		//back=new JButton("BACK");
		//back.addActionListener(this);
		//resultPanel.add(back);
		//this.start();
		//frame.add(queryPanel,BorderLayout.CENTER);
		//frame.add(resultPanel,BorderLayout.SOUTH);
		
		for(int i=0;i<10;i++)
		{
			submit[i]=new JButton("SUBMIT");
			submit[i].addActionListener(this);
		}
		
		frame.add(startScreen,BorderLayout.NORTH);		
		frame.setVisible(true);
		
	}
	private void add_table()
	{
		x=t.get_Table(this.result);
		
		this.resultRightPanel.removeAll();
		JScrollPane temp=new JScrollPane(x);
		this.resultRightPanel.setLayout(new BorderLayout());
		this.resultRightPanel.add(temp,BorderLayout.CENTER);
		this.resultRightPanel.revalidate();
		this.resultRightPanel.repaint();
		
	}

	private void print_result()
	{
		for(int i=0;i<result.size();i++)
			System.out.println(result.get(i));
	}
	private void initialise_queries()
	{
		q=new ArrayList<String>();
		q.add("1. Get Info of a Particular Donor");
		q.add("2. Check Camp organized on a particular day");
		q.add("3. Info of Blood Stock That is Available");
		q.add("4. List of Centres with Availabilty of a Particular Blood Group");
		q.add("5. Details of Camp that are being organised");
		q.add("6. Employees That were not present in a particular camp organised by their Centre");
		q.add("7. Details of a Particular Employee");
		q.add("8. Max 2 Donors");
		q.add("9. Camps That have collected more than Average Number of Donations per Camp");
		q.add("10. Employee that has worked in Maximum Number of Camps");
	}
	
	private void query1()
	{
		//Get Info of a Particular Donor
		resultPanel.setLayout(new FlowLayout(FlowLayout.LEADING));
		resultRightPanel=new JPanel();
		frame.getContentPane().removeAll();
		frame.revalidate();
		frame.repaint();
		questionLabel.setText("Enter Name of Donor:");
		
		resultLeftPanel.removeAll();
		resultLeftPanel.add(questionLabel);
		this.set_inputarea();
		resultLeftPanel.add(inputArea);
		buttonPanel=new JPanel();
		buttonPanel.add(back); buttonPanel.add(submit[0]);
		resultLeftPanel.add(buttonPanel);
		resultPanel.removeAll();
		resultPanel.add(resultLeftPanel);	
		this.resultRightPanel.removeAll();
		resultPanel.add(this.resultRightPanel);
		
		frame.add(resultPanel);
		frame.repaint();
		frame.revalidate();
	}
	private void query2()
	{
		//Get Info of a Particular Blood Group
		resultPanel.setLayout(new FlowLayout(FlowLayout.LEADING));
		resultRightPanel=new JPanel();
		frame.getContentPane().removeAll();
		questionLabel.setText("Enter Date(YYYY-MM-DD):");
		
		resultLeftPanel.removeAll();
		resultLeftPanel.add(questionLabel);
		this.set_inputarea();
		resultLeftPanel.add(inputArea);
		buttonPanel=new JPanel();
		buttonPanel.add(back); buttonPanel.add(submit[1]);
		resultLeftPanel.add(buttonPanel);
		resultPanel.removeAll();
		resultPanel.add(resultLeftPanel);	
		resultPanel.add(resultRightPanel);
		
		
		frame.add(resultPanel);
		frame.revalidate();
		frame.repaint();
		
	}
	private void query3()
	{
		//Info of Blood Stock
		resultPanel.setLayout(new FlowLayout(FlowLayout.LEADING));
		resultRightPanel=new JPanel();
		frame.getContentPane().removeAll();
		questionLabel.setText("Enter Centre ID");

		resultLeftPanel.removeAll();		
		resultLeftPanel.add(questionLabel);
		this.set_inputarea();
		resultLeftPanel.add(inputArea);
		buttonPanel=new JPanel();
		buttonPanel.add(back); buttonPanel.add(submit[2]);
		resultLeftPanel.add(buttonPanel);
		
		resultPanel.removeAll();
		resultPanel.add(resultLeftPanel);
		resultPanel.add(resultRightPanel);
		
		frame.add(resultPanel);
		frame.repaint();
		frame.revalidate();	}
	private void query4()
	{
		//List Centres with availabilty of a Particular Blood Group
		resultPanel.setLayout(new FlowLayout(FlowLayout.LEADING));
		resultRightPanel=new JPanel();
		frame.getContentPane().removeAll();
		questionLabel.setText("Enter Blood Group:");

		resultLeftPanel.removeAll();
		
		resultLeftPanel.add(questionLabel);
		this.set_inputarea();
		resultLeftPanel.add(inputArea);
		buttonPanel=new JPanel();
		buttonPanel.add(back); buttonPanel.add(submit[3]);
		resultLeftPanel.add(buttonPanel);		
		
		resultPanel.removeAll();
		resultPanel.add(resultLeftPanel);
		resultPanel.add(resultRightPanel);
		
		frame.add(resultPanel);
		frame.repaint();
		frame.revalidate();}
	private void query5()
	{
		//Details of Camps organised
		frame.getContentPane().removeAll();
		d.connect();
		result=d.run_statement5();
		d.disconnect();
		resultPanel.removeAll();
		resultPanel.setLayout(new BorderLayout());
		
		x=t.get_Table(result);
		JScrollPane temp=new JScrollPane(x);
		JPanel temp2=new JPanel(){
				Image bg = new ImageIcon("images/blood5.jpg").getImage();
			    @Override
			    public void paintComponent(Graphics g) {
			        g.drawImage(bg, 0, 0, getWidth(), getHeight(), this);
			    
				}};
			
		temp2.add(temp,BorderLayout.CENTER);
		
		resultPanel.add(temp2,BorderLayout.CENTER);
		resultPanel.add(back, BorderLayout.SOUTH);
		resultPanel.revalidate();resultPanel.repaint();
				
		frame.add(resultPanel);
		frame.repaint();
		frame.revalidate();
		}
	private void query6()
	{
		//Get list of employees that were not present in a particular camp
		resultPanel.setLayout(new FlowLayout(FlowLayout.LEADING));
		resultRightPanel=new JPanel();
		frame.remove(queryPanel);
		questionLabel.setText("Enter Camp ID:");

		resultLeftPanel.removeAll();		
		resultLeftPanel.add(questionLabel);
		resultLeftPanel.add(inputArea);
		buttonPanel=new JPanel();
		buttonPanel.add(back); buttonPanel.add(submit[5]);
		resultLeftPanel.add(buttonPanel);


		resultPanel.removeAll();
		resultPanel.add(resultLeftPanel);
		resultPanel.add(resultRightPanel);
		
		frame.add(resultPanel);
		frame.repaint();
		frame.revalidate();}
	private void query7()
	{
		//Get Info of a Particular Employee
		resultPanel.setLayout(new FlowLayout(FlowLayout.LEADING));
		resultRightPanel=new JPanel();
		frame.getContentPane().removeAll();
		questionLabel.setText("Enter Employee Name:");

		resultLeftPanel.removeAll();		
		resultLeftPanel.add(questionLabel);
		this.set_inputarea();
		resultLeftPanel.add(inputArea);
		buttonPanel=new JPanel();
		buttonPanel.add(back); buttonPanel.add(submit[6]);
		resultLeftPanel.add(buttonPanel);
		
		resultPanel.removeAll();
		resultPanel.add(resultLeftPanel);
		resultPanel.add(resultRightPanel);
		
		frame.add(resultPanel);
		frame.repaint();
		frame.revalidate();}
	private void query8()
	{
		//Max 2 donors

		frame.getContentPane().removeAll();
		d.connect();
		result=d.run_statement8();
		d.disconnect();
		resultPanel.removeAll();
		resultPanel.setLayout(new BorderLayout());
		
		x=t.get_Table(result);
		JScrollPane temp=new JScrollPane(x);
		JPanel temp2=new JPanel(){
				Image bg = new ImageIcon("images/blood5.jpg").getImage();
			    @Override
			    public void paintComponent(Graphics g) {
			        g.drawImage(bg, 0, 0, getWidth(), getHeight(), this);
			    
				}};
			
		temp2.add(temp,BorderLayout.CENTER);
		
		resultPanel.add(temp2,BorderLayout.CENTER);
		resultPanel.add(back, BorderLayout.SOUTH);
		resultPanel.revalidate();resultPanel.repaint();
		
		frame.add(resultPanel);
		frame.repaint();
		frame.revalidate();}
	private void query9()
	{
		
		//camps with average number of donations
		
		frame.getContentPane().removeAll();
		d.connect();
		result=d.run_statement9();
		d.disconnect();
		resultPanel.removeAll();
		resultPanel.setLayout(new BorderLayout());
		
		x=t.get_Table(result);
		JScrollPane temp=new JScrollPane(x);
		JPanel temp2=new JPanel(){
				Image bg = new ImageIcon("images/blood5.jpg").getImage();
			    @Override
			    public void paintComponent(Graphics g) {
			        g.drawImage(bg, 0, 0, getWidth(), getHeight(), this);
			    
				}};
			
		temp2.add(temp,BorderLayout.CENTER);
		
		resultPanel.add(temp2,BorderLayout.CENTER);
		resultPanel.add(back, BorderLayout.SOUTH);
		resultPanel.revalidate();resultPanel.repaint();
		
		frame.add(resultPanel);
		frame.repaint();
		frame.revalidate();
	}
	private void query10()
	{
		//Employee worked in maximum camps
		d.connect();
		result=d.run_statement10();
		d.disconnect();
		
		frame.getContentPane().removeAll();
		
		resultPanel.removeAll();		
		String[] s1=result.get(0).split(",");
		String[] s2=result.get(1).split(",");
		
		JPanel g=new JPanel()
		{
			Image bg = new ImageIcon("images/blood5.jpg").getImage();
	    @Override
	    public void paintComponent(Graphics g) {
	        g.drawImage(bg, 0, 0, getWidth(), getHeight(), this);
	    
		}
    };
		g.setLayout(new GridLayout(3,1));
		
		JLabel temp1=new JLabel(s1[0]+": "+s2[0]);
		temp1.setFont(new Font("Calibri",Font.PLAIN,40));
		g.add(temp1);
		JLabel temp2=new JLabel(s1[1]+": "+s2[1]);
		temp2.setFont(new Font("Calibri",Font.PLAIN,40));
		g.add(temp2);
		
		JPanel x=new JPanel();
		back.setMaximumSize(new Dimension(30,20));
		
		x.setLayout(new BorderLayout());
		x.add(g,BorderLayout.CENTER);
		x.add(back,BorderLayout.SOUTH);
		x.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		
		
		resultPanel.setLayout(new BorderLayout());		
		resultPanel.add(x,BorderLayout.CENTER);
		resultPanel.revalidate();resultPanel.repaint();
		
		frame.add(resultPanel);
		frame.repaint();
		frame.revalidate();}
	
	
	private void initialise_mainpage()
	{	
		queryPanel=new JPanel()
					{
						Image bg = new ImageIcon("images/blood4.jpg").getImage();
				    @Override
				    public void paintComponent(Graphics g) {
				        g.drawImage(bg, 0, 0, getWidth(), getHeight(), this);
				    
					}
			    };
		
		queryPanel.setLayout(new BoxLayout(queryPanel,BoxLayout.Y_AXIS));
		
		JLabel queryHeading=new JLabel("WHAT WOULD YOU LIKE TO DO?");
		queryHeading.setFont(new Font("Calibri",Font.BOLD,40));
		//queryHeading.setBorder(BorderFactory.createLineBorder(Color.red, 2));
		queryHeading.setAlignmentX(Component.CENTER_ALIGNMENT);
		queryHeading.setBackground(Color.WHITE);
		queryHeading.setOpaque(true);
		queryPanel.add(Box.createRigidArea(new Dimension(0,20)));
		queryPanel.add(queryHeading);
		queryPanel.add(Box.createRigidArea(new Dimension(0,20)));
		
	
		for(int i=0;i<10;i++)
		{
			qb[i]=new JButton(q.get(i));
			qb[i].setFont(new Font("Comic Sans MS",Font.PLAIN,14));
			qb[i].addActionListener(this);
			qb[i].setAlignmentX(Component.CENTER_ALIGNMENT);
			queryPanel.add(qb[i]);
			queryPanel.add(Box.createRigidArea(new Dimension(0,10)));
		}
		JButton exit=new JButton("EXIT");
		exit.setFont(new Font("Comic Sans MS",Font.BOLD,14));
		exit.setBackground(Color.red);
		exit.setAlignmentX(Component.CENTER_ALIGNMENT);
		exit.setForeground(Color.white);
		exit.addActionListener(new ActionListener()
				{
					public void actionPerformed(ActionEvent e)
					{System.exit(0);}
				});
		queryPanel.add(exit);
		frame.remove(startScreen);
		frame.remove(resultPanel);
		frame.add(queryPanel);
		frame.repaint();
		frame.revalidate();
	}
	
	

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource().equals(login))
		{	
			//initialise_mainpage();
			if((name.getText().equals("harsh") || name.getText().equals("ajit") ) && this.pass.getText().equals("123"))
				{
					initialise_mainpage();
				}
			else
				JOptionPane.showMessageDialog(frame, "Wrong Username or Password"," Login Error !",JOptionPane.ERROR_MESSAGE);
			
		}
		else if(e.getSource().equals(back))
		{
			this.initialise_mainpage();
		}
		else if(e.getSource().equals(qb[0]))
		{
			this.query1();
		}
		else if(e.getSource().equals(submit[0]))
		{
			
			d.connect();
			this.result=d.run_statement1(inputArea.getText());			
			d.disconnect();
			this.print_result();
			this.add_table();
		}
		else if(e.getSource().equals(qb[1]))
		{
			//System.out.println("Here");
			this.query2();
		}
		else if(e.getSource().equals(submit[1]))
		{
			
			d.connect();
			result=d.run_statement2(inputArea.getText());
			d.disconnect();
			this.print_result();
			this.add_table();
		}
		else if(e.getSource().equals(qb[2]))
		{
			this.query3();
		}
		else if(e.getSource().equals(submit[2]))
		{
			d.connect();
			result=d.run_statement3(inputArea.getText());
			d.disconnect();
			this.print_result();
			this.add_table();
		}
		else if(e.getSource().equals(qb[3]))
		{
			this.query4();		
		}
		else if(e.getSource().equals(submit[3]))
		{
			d.connect();
			result=d.run_statement4(inputArea.getText());
			d.disconnect();
			this.print_result();
			this.add_table();
		}
		else if(e.getSource().equals(qb[4]))
		{
			this.query5();
		}
		else if(e.getSource().equals(submit[4]))
		{
			d.connect();
			result=d.run_statement5();
			d.disconnect();
			this.print_result();
			this.add_table();
		}
		else if(e.getSource().equals(qb[5]))
		{
			this.query6();
		}
		else if(e.getSource().equals(submit[5]))
		{
			d.connect();
			result=d.run_statement6(inputArea.getText());
			d.disconnect();
			this.print_result();
			this.add_table();
		}
		else if(e.getSource().equals(qb[6]))
		{
			this.query7();
		}
		else if(e.getSource().equals(submit[6]))
		{
			d.connect();
			result=d.run_statement7(inputArea.getText());
			d.disconnect();
			this.print_result();
			this.add_table();
		}
		else if(e.getSource().equals(qb[7]))
		{
			this.query8();
		}
		else if(e.getSource().equals(submit[7]))
		{
			d.connect();
			result=d.run_statement8();
			d.disconnect();
			this.print_result();
			this.add_table();
		}
		else if(e.getSource().equals(qb[8]))
		{
			this.query9();
		}
		else if(e.getSource().equals(submit[8]))
		{
			
			this.print_result();
			this.add_table();
		}
		else if(e.getSource().equals(qb[9]))
		{
			this.query10();
		}
		else if(e.getSource().equals(submit[9]))
		{
			
			this.print_result();
			this.add_table();
		}
		
	}
	
	public static void main(String[] args)
	{
		GUI g=new GUI();
	}

}
