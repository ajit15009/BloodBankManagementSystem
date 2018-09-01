import javax.swing.*;
import javax.swing.table.*;

import java.awt.Dimension;
import java.util.*;
public class TableCreater {

	private JTable t;
	private DefaultTableModel model;
	private int count;
	private int rowindex,increase;
	private Object o[];
	
	public TableCreater() 
	{
		increase=10;
	}
	
	public JTable get_Table(ArrayList<String> l)
	{
		String[] column_names;
		column_names=l.get(0).split(",");
		for(int c=0;c<column_names.length;c++)
			System.out.println(column_names[c]);
		
		t=new JTable(new DefaultTableModel(column_names,0));
		rowindex=0;
		model=(DefaultTableModel)t.getModel();
		for(count=1;count<increase && count<l.size();count++)
		{
			model.addRow(data(l.get(count)));
		}
		
		return t;
			
	}
	
	private Object[] data(String str)
	{
		String[] temp=str.split(",");
		Object o[]=new Object[temp.length];
		for(int i=0;i<temp.length;i++)
		{
			o[i]=(Object)temp[i];
		}		
		return o;
	}

}
