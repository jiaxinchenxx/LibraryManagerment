package graui;

import java.util.ArrayList;
import java.util.Vector;

import javax.swing.event.TableModelListener;
import javax.swing.table.*;

import base.Book;


public class MyTableModel extends DefaultTableModel {
	private Vector<Book> al; // 根据加载的数据集合生成一个表格模型
	private int rowCount = 0; // 表格数据行数
	
	public MyTableModel(Vector<Book> al) {
		this.al = al;
		rowCount = al.size();
	}
	
	@Override
	public int getRowCount() {
		return rowCount;
	}

	@Override
	public int getColumnCount() {
		// TODO 自动生成的方法存根
		return 4;
	}

	@Override
	public String getColumnName(int columnIndex) {
		switch (columnIndex) {
		case 0:
			return "ID";
		case 1:
			return "Name";
		case 2:
			return "Author";
		case 3:
			return "Price";
		}
		return null;
	}

	@Override
	public Class<?> getColumnClass(int columnIndex) {
		switch (columnIndex) {
		case 0:  // 学号，姓名，性别，邮件，显示为字符串类型
		case 1:
		case 2:
			return String.class;
		case 3: // Java成绩和年龄显示为整型数据
			return Double.class;
		}
		return null;
	}

	@Override
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		// TODO 自动生成的方法存根
		return false;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		Book s = al.get(rowIndex); // 得到某行数据
		switch (columnIndex) { // 接下来返回该行数据的某个列的值
		case 0:
			return s.getID();
		case 1:
			return s.getName();
		case 2:
			return s.getAuthor();
		case 3:
			return s.getPrice();
		}
		return null;
	}

	@Override
	public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
		// TODO 自动生成的方法存根
		
	}

	@Override
	public void addTableModelListener(TableModelListener l) {
		// TODO 自动生成的方法存根
		
	}

	@Override
	public void removeTableModelListener(TableModelListener l) {
		// TODO 自动生成的方法存根
		
	}

}
