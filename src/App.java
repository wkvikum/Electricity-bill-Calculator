


// ebill app

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class App extends JFrame {
    private JTextField TxtAccNo, TxtPreValue, TxtNowValue,TxtPoint,TxtAmount,TxtAccNo1,TxtAddress,TxtName;
    private JComboBox<String> CmbType,CmbType1;
    private JTextArea TxetArea,TextAreaAddress;
    private JButton BtnCal, BtnEnter, BtnClear, BtnClearGrid, BtnClose, BtnClearT,BtnSearch,BtnClear1;
    private JTable Tabel1;
    private DefaultTableModel TabelData;
   

    public App() {
        setTitle("Electricity Bill Calculator");
        setSize(1100,820);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);

        JLabel l1=new JLabel("Ceylon Electricity Board");
		l1.setFont(new Font("Times new Roman",Font.PLAIN,40));
		l1.setBounds(320,52,650,50);
		add(l1);
        
        JLabel LblAccNo = new JLabel("Account Number");
        LblAccNo.setBounds(100,150,200,25);
        LblAccNo.setFont(new Font("Times new Roman",Font.PLAIN,25));
        add(LblAccNo);
        TxtAccNo = new JTextField();
        TxtAccNo.setBounds(300,145,200,35);
        TxtAccNo.setFont(new Font("Times new Roman",Font.PLAIN,25));
        add(TxtAccNo);

        JLabel LblType = new JLabel("Customer Type");
        LblType.setBounds(100,200,200,25);
        LblType.setFont(new Font("Times new Roman",Font.PLAIN,25));
        add(LblType);
        String[] Ctype = {"Home", "Business", "Special"};
        CmbType = new JComboBox<>(Ctype);
        CmbType.setBounds(300,200,200,35);
        CmbType.setFont(new Font("Times new Roman",Font.PLAIN,20));
        add(CmbType);

        JLabel LblPreValue = new JLabel("Previous Value");
        LblPreValue.setBounds(100,250,200,25);
        LblPreValue.setFont(new Font("Times new Roman",Font.PLAIN,25));
        add(LblPreValue);
        TxtPreValue = new JTextField();
        TxtPreValue.setBounds(300, 250,200,35);
        TxtPreValue.setFont(new Font("Times new Roman",Font.PLAIN,25));
        add(TxtPreValue);

        JLabel LblNowValue = new JLabel("Now Value");
        LblNowValue.setBounds(100,300,200,25);
        LblNowValue.setFont(new Font("Times new Roman",Font.PLAIN,25));
        add(LblNowValue);
        TxtNowValue = new JTextField();
        TxtNowValue.setBounds(300, 300, 200, 35);
        TxtNowValue.setFont(new Font("Times new Roman",Font.PLAIN,25));
        add(TxtNowValue);
        
        JLabel LblPoint = new JLabel("Point");
        LblPoint.setBounds(600,190,200,25);
        LblPoint.setFont(new Font("Times new Roman",Font.PLAIN,25));
        add(LblPoint);
        TxtPoint = new JTextField();
        TxtPoint.setBounds(700, 188, 200, 35);
        TxtPoint.setFont(new Font("Times new Roman",Font.PLAIN,25));
        TxtPoint.setHorizontalAlignment(SwingConstants.RIGHT);
        add(TxtPoint);
        
        JLabel LblAmount = new JLabel("Amount");
        LblAmount.setBounds(600,260,200,25);
        LblAmount.setFont(new Font("Times new Roman",Font.PLAIN,25));
        add(LblAmount);
        TxtAmount = new JTextField();
        TxtAmount.setBounds(700, 250, 200, 50);
        TxtAmount.setFont(new Font("Times new Roman",Font.PLAIN,35));
        TxtAmount.setHorizontalAlignment(SwingConstants.RIGHT);
        add(TxtAmount);

//        calculate button 
        BtnCal = new JButton("Calculate");
        BtnCal.setBounds(370, 360, 170,45);
        BtnCal.setFont(new Font("Times new Roman",Font.PLAIN,25));
        add(BtnCal);
        
        BtnClear = new JButton("Clear");
        BtnClear.setBounds(600, 360, 170,45);
        BtnClear.setFont(new Font("Times new Roman",Font.PLAIN,25));
        add(BtnClear);
        
        BtnSearch = new JButton("Search");
        BtnSearch.setBounds(510,145, 120,35);
        BtnSearch.setFont(new Font("Times new Roman",Font.PLAIN,20));
        add(BtnSearch);
        
//        Tabel create
        DefaultTableModel TabelData = new DefaultTableModel();
        TabelData.addColumn("Acc No");
        TabelData.addColumn("Date");
        TabelData.addColumn("Pre Value");
        TabelData.addColumn("Meter Value");
        TabelData.addColumn("Used Point");
        TabelData.addColumn("Amount");
        
        
        JTable Tabel1 = new JTable(TabelData);
        Tabel1.setAutoCreateRowSorter(true);
        Tabel1.getTableHeader().setReorderingAllowed(true);
        Tabel1.setBounds(100, 420, 800, 150);
        add(Tabel1);

        JScrollPane Scroll = new JScrollPane(Tabel1);
        Scroll.setBounds(100, 420, 800, 150);
        add(Scroll);
        
        
        BtnClearT = new JButton("Clear All");
        BtnClearT.setBounds(740, 573, 150,35);
        BtnClearT.setFont(new Font("Times new Roman",Font.PLAIN,20));
        add(BtnClearT);
        
        
        BtnClearGrid = new JButton("Clear Grid");
        BtnClearGrid.setBounds(200, 180, 150, 30);
//        add(clearGridButton);

        BtnClose = new JButton("Close");
        BtnClose.setBounds(935, 720, 150, 45);
        BtnClose.setFont(new Font("Times new Roman",Font.PLAIN,20));
        add(BtnClose);

        TxetArea = new JTextArea();
        TxetArea.setBounds(20, 260, 330, 60);
        TxetArea.setEditable(false);
//        add(TxetArea);

        BtnCal.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CalculateBill();
            }
        });
        
        BtnSearch.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	 SearchData();
            }
        });

       
        BtnClear.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CalTextClear();
            }
        });
        
//        enter customer
        
        JLabel LblAccNo1 = new JLabel("Acc No");
        LblAccNo1.setBounds(100,620,150,25);
        LblAccNo1.setFont(new Font("Times new Roman",Font.PLAIN,20));
        add(LblAccNo1);
        TxtAccNo1 = new JTextField();
        TxtAccNo1.setBounds(250, 615, 200, 35);
        TxtAccNo1.setFont(new Font("Times new Roman",Font.PLAIN,20));
        add(TxtAccNo1);

        JLabel LblAddress = new JLabel("Address");
        LblAddress.setBounds(100,670,150,25);
        LblAddress.setFont(new Font("Times new Roman",Font.PLAIN,20));
        add(LblAddress);
        TxtAddress = new JTextField();
        TxtAddress.setBounds(250, 665, 200, 35);
        TxtAddress.setFont(new Font("Times new Roman",Font.PLAIN,20));
        add(TxtAddress);
        
        JLabel LblName = new JLabel("Name");
        LblName.setBounds(500,620,150,25);
        LblName.setFont(new Font("Times new Roman",Font.PLAIN,20));
        add(LblName);
        TxtName = new JTextField();
        TxtName.setBounds(660, 615, 200, 35);
        TxtName.setFont(new Font("Times new Roman",Font.PLAIN,20));
        add(TxtName);

        JLabel LblType1 = new JLabel("Cus Type");
        LblType1 .setBounds(500,665,150,25);
        LblType1 .setFont(new Font("Times new Roman",Font.PLAIN,20));
        add(LblType1 );
        String[] Ctype1 = {"Home", "Business", "Special"};
        CmbType1 = new JComboBox<>(Ctype1);
        CmbType1.setBounds(660,665,200,35);
        CmbType1.setFont(new Font("Times new Roman",Font.PLAIN,20));
        add(CmbType1);
        
        BtnEnter = new JButton("Enter");
        BtnEnter.setBounds(370, 720, 150, 45);
        BtnEnter.setFont(new Font("Times new Roman",Font.PLAIN,20));
        add(BtnEnter);
        
        BtnClear1 = new JButton("Clear");
        BtnClear1.setBounds(600, 720, 150,45);
        BtnClear1.setFont(new Font("Times new Roman",Font.PLAIN,20));
        add(BtnClear1);

        
        
        BtnEnter.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                InputDataBase();
            }
        });

        
        BtnClearGrid.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                TxetArea.setText("");
            }
        });

        BtnClose.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	Exit();
                
            }
        });
    }

    private void CalculateBill() {
        try {
        	    double preValue = Double.parseDouble(TxtPreValue.getText());
			    double nowValue = Double.parseDouble(TxtNowValue.getText());
			    String type = (String)CmbType.getSelectedItem();
			    double pay = 0,add=0;
			    int permition=0;
			    
			   if(preValue < 0 ) {
			    	JOptionPane.showMessageDialog(  null,"Error Value    "+preValue+"    please check  ");
			    	permition=1;
			    }
			    double point= nowValue - preValue;
			    double point1=point;
			    if(point < 0 ) {
			    	JOptionPane.showMessageDialog(  null,"Error Value    "+nowValue+"   please check  ");
			    	permition=1;
			     }else {

			       double p1=0,p2=0,p3=0,p4=0;
			        try {
				 
				    }
				    catch(Exception e1) {
				  
				    }
			    if(type.equals("Home")) {p1=10; p2=20; p3=30; p4=40;}
			    if(type.equals("Business")) {p1=15; p2=25; p3=35; p4=45;}
			    if(type.equals("Special")) {p1=10; p2=15; p3=25; p4=35;}
			    
			    
			    
			    if(point >90) {point -=90; point =point*p4;pay=p1*30+p2*30+p3*30+point; }
			    if(point >60 && point <90) {point -=60; point =point*p3;pay=p1*30+p2*30+point;}
			    if(point >30 && point <60) {point -=30; point =point*p2;pay=p1*30+point;}
			    if(point <=30) {pay =point*p1;;}
			    point=0;
			    
			     }
			   
			    if(permition != 1) {
			    TxtAmount.setText("Rs  "+Double.toString(pay));
			    TxtPoint.setText(Double.toString(point1));
			    }else {TxtAmount.setText("");}
			    
			    
//        	int AccNo = Integer.parseInt(TxtAccNo.getText());
//            int PreValue = Integer.parseInt(TxtPreValue.getText());
//            int NowValue = Integer.parseInt(TxtNowValue.getText());
//            int date=1111;
//            
//            int Points = NowValue - PreValue;
//            int charge = 0;
//            
//            String customerType = (String) CmbType.getSelectedItem();
//            
//            if (Points >= 0 && Points <= 30) {
//                charge = customerType.equals("Home") ? 10 : customerType.equals("Business") ? 15 : 10;
//            } else if (Points > 30 && Points <= 60) {
//                charge = customerType.equals("Home") ? 20 : customerType.equals("Business") ? 25 : 15;
//            } else if (Points > 60 && Points <= 90) {
//                charge = customerType.equals("Home") ? 30 : customerType.equals("Business") ? 35 : 25;
//            } else {
//                charge = customerType.equals("Home") ? 40 : customerType.equals("Business") ? 45 : 35;
//            }
//            int Amount = Points * charge;
//            
//            TxtPoint.setText(Double.toString( Points));
//            TxtAmount.setText("Rs  "+Double.toString(Amount));
            
//            tabe fill
//            TabelData.addRow(new Object[]{AccNo ,date, PreValue, NowValue,Points,Amount});
        
            
//            TableFill(PreValue, NowValue,Points,Amount);
            
            
//            outputTextArea.setText("Points: " + Points + "\nAmount: " + amount);
            
        } catch (NumberFormatException e) {
        	JOptionPane.showMessageDialog( BtnCal," Calculation is faild ");
//            outputTextArea.setText("Invalid input. Please enter numeric values.");
        }
    }
    
    public void TableFill(int PreValue,int NowValue,int Point,int Amount) {
    	
    	
    	
    }

    private void InputDataBase() {
        String AccNo = TxtAccNo1.getText();
        String Type = (String) CmbType1.getSelectedItem();
        String PreValue = TxtPreValue.getText();
        String NowValue = TxtNowValue.getText();
        String Point = TxtPoint.getText();
        String Amount = TxtAmount.getText();
        String Date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());

        try {
        	    String jdbcUrl = "jdbc:mysql://localhost:8888/phpMyAdmin5/Shop";
				String username = "root";
				String password = "root";
				
			Connection Conn = DriverManager.getConnection(jdbcUrl,username, password);
			
            String sql = "INSERT INTO BillData (AccNo, Type, PreValue, NowValue, Point, Amount, Timestamp)"
            		+ " VALUES (?, ?, ?, ?, ?, ?, ?)";
            
            PreparedStatement Statement = Conn.prepareStatement(sql);
            
            Statement.setString(1, AccNo);
            Statement.setString(2, Type);
            Statement.setString(3, PreValue);
            Statement.setString(4, NowValue);
            Statement.setString(5, Point);
            Statement.setString(6, Amount);
            Statement.setString(7, Date);
            Statement.executeUpdate();
            
            Conn.close();
            TxetArea.setText("Succussfuly insert");
            
        } catch (SQLException e) {
            TxetArea.setText("Error Not insert");
        }
    }

    private void CalTextClear() {
        TxtAccNo.setText("");
        TxtPreValue.setText("");
        TxtNowValue.setText("");
        TxtAmount.setText("");
        TxtPoint.setText("");
        TxetArea.setText("");
    }
//    search
    private void SearchData() {
    	
    	try{

    	    String jdbcUrl = "jdbc:mysql://localhost:8888/phpMyAdmin5/Shop";
			String username = "root";
			String password = "root";
			
		Connection Conn = DriverManager.getConnection(jdbcUrl,username, password);
		
		String query="SELECT * FROM  CustemerData WHERE AccNo=Acc ";
        
        PreparedStatement Statement = Conn.prepareStatement(query);
        
			 ResultSet resultSet = Statement.executeQuery(query);

	            if (resultSet.next()) {
	                String value = resultSet.getString("PreValue");
	               TxtPreValue.setText(value);
	            } else {
	            	JOptionPane.showMessageDialog( null," Account Number is not fund");
	            }
	        resultSet.close();
        
        
        Conn.close();
        
        
    	}
    	catch(SQLException e) {
    		JOptionPane.showMessageDialog( null," Serching Fail ");
    	}
    }
//   close programme 
    private void Exit() {
    	int Value;
    	Value=JOptionPane.showConfirmDialog( null," Are you sure to Close program ");
    	
    	if(Value ==JOptionPane.YES_OPTION) {
    		System.exit(0);
    	}
    	
    	else if(Value ==JOptionPane.NO_OPTION) {
    		
    	}
        
    	else if(Value ==JOptionPane.CANCEL_OPTION) {
    		
    	}
    	
    	
    }

    public static void main(String[] args) {
        App calculator = new App();
        calculator.setVisible(true);
    }
}



