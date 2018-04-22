package graui;

import java.util.ArrayList;
import java.util.Vector;

import javax.swing.event.TableModelListener;
import javax.swing.table.*;

import base.Book;


public class MyTableModel extends DefaultTableModel {
	private Vector<Book> al; // ���ݼ��ص����ݼ�������һ�����ģ��
	private int rowCount = 0; // �����������
	
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
		// TODO �Զ����ɵķ������
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
		case 0:  // ѧ�ţ��������Ա��ʼ�����ʾΪ�ַ�������
		case 1:
		case 2:
			return String.class;
		case 3: // Java�ɼ���������ʾΪ��������
			return Double.class;
		}
		return null;
	}

	@Override
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		// TODO �Զ����ɵķ������
		return false;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		Book s = al.get(rowIndex); // �õ�ĳ������
		switch (columnIndex) { // ���������ظ������ݵ�ĳ���е�ֵ
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
		// TODO �Զ����ɵķ������
		
	}

	@Override
	public void addTableModelListener(TableModelListener l) {
		// TODO �Զ����ɵķ������
		
	}

	@Override
	public void removeTableModelListener(TableModelListener l) {
		// TODO �Զ����ɵķ������
		
	}

}
