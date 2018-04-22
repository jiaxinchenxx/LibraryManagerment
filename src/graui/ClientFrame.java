package graui;

import graui.MyTableModel;

import java.awt.*;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.*;

import base.Book;
import base.LibraryClientIdea;

public class ClientFrame extends JFrame{
	
	public LibraryClientIdea lci;
	
	Label Addlabel1 = new Label("Id : ");   //�˴�����Ϊ��̬��ǩ����Ϊ�û���д��Ϣ������
	Label Addlabel2 = new Label("Name : ");
	Label Addlabel3 = new Label("Author : ");
	Label Addlabel4 = new Label("Price : ");
	Label Changelabel1 = new Label("Id : ");
	Label Changelabel2 = new Label("Name : ");
	Label Changelabel3 = new Label("Author : ");
	Label Changelabel4 = new Label("Price : ");
	Label Deletelabel1 = new Label("Id : ");
    Label Searchlabel1 = new Label("Id : ");
	JPanel FunctionPanel = new JPanel();
	JTextField AddFieldID = new JTextField(){ // ��ǩ���ı��򣬰�ť
		public Dimension getPreferredSize() { // ʹ�������࣬��չTextField
			return new Dimension(80, 25); // ����getPreferredSize����
		}
	};
	JTextField AddFieldName = new JTextField(){ // ��ǩ���ı��򣬰�ť
		public Dimension getPreferredSize() { // ʹ�������࣬��չTextField
			return new Dimension(80, 25); // ����getPreferredSize����
		}
	};
	JTextField AddFieldAuthor = new JTextField(){ // ��ǩ���ı��򣬰�ť
		public Dimension getPreferredSize() { // ʹ�������࣬��չTextField
			return new Dimension(80, 25); // ����getPreferredSize����
		}
	};
	JTextField AddFieldPrice = new JTextField(){ // ��ǩ���ı��򣬰�ť
		public Dimension getPreferredSize() { // ʹ�������࣬��չTextField
			return new Dimension(80, 25); // ����getPreferredSize����
		}
	};
	JTextField DeleteFieldID = new JTextField(){ // ��ǩ���ı��򣬰�ť
		public Dimension getPreferredSize() { // ʹ�������࣬��չTextField
			return new Dimension(80, 25); // ����getPreferredSize����
		}
	};
	JTextField ChangeFieldID = new JTextField(){ // ��ǩ���ı��򣬰�ť
		public Dimension getPreferredSize() { // ʹ�������࣬��չTextField
			return new Dimension(80, 25); // ����getPreferredSize����
		}
	};
	JTextField ChangeFieldName = new JTextField(){ // ��ǩ���ı��򣬰�ť
		public Dimension getPreferredSize() { // ʹ�������࣬��չTextField
			return new Dimension(80, 25); // ����getPreferredSize����
		}
	};
	JTextField ChangeFieldAuthor = new JTextField(){ // ��ǩ���ı��򣬰�ť
		public Dimension getPreferredSize() { // ʹ�������࣬��չTextField
			return new Dimension(80, 25); // ����getPreferredSize����
		}
	};
	JTextField ChangeFieldPrice = new JTextField(){ // ��ǩ���ı��򣬰�ť
		public Dimension getPreferredSize() { // ʹ�������࣬��չTextField
			return new Dimension(80, 25); // ����getPreferredSize����
		}
	};
	JTextField SearchFieldID = new JTextField(){ // ��ǩ���ı��򣬰�ť
		public Dimension getPreferredSize() { // ʹ�������࣬��չTextField
			return new Dimension(80, 25); // ����getPreferredSize����
		}
	};
	TextArea SearchResult = new TextArea(){
		public Dimension getPreferredSize() { // ʹ�������࣬��չTextField
			return new Dimension(160, 80); // ����getPreferredSize����
		}
	};
	JPanel AddPanel = new JPanel();  //����Ϊ������ÿ����������һ��������ͬʱʵ�ֲ�ͬ�Ĺ���
	JPanel DeletePanel = new JPanel();
	JPanel ChangePanel = new JPanel();
	JPanel SearchPanel = new JPanel();
	JTabbedPane jTabbedPane = null;
	JScrollPane jscrollPane = null;
	JTable jTable = null;
	JButton Add =  new JButton("���ͼ��");  //����ΪButton���в�ͬ�Ĺ���
	JButton Delete = new JButton("ɾ��ͼ��");
	JButton Change = new JButton("����ͼ��");
	JButton Search = new JButton("��ѯͼ��");
	JButton Refresh = new JButton("ˢ��");
	JPanel RefreshTable = new JPanel();
	JPanel RefreshPanel = new JPanel();
	public ClientFrame(Vector<Book> model,LibraryClientIdea lci) {
		this.lci = lci;  //���캯������ʼ���ͻ��ˣ�һ��Ϊ��ʼ���Ĵ�����Ϣ�������������壬���ô�С�Լ�λ��
		
		jTable = new JTable(new MyTableModel(model)); // ����ģ�ʹ���UI
		jTable.getTableHeader().setFont(new Font("΢���ź�", Font.BOLD, 14));
		jTable.setSize(680,350);
		Font f = new Font("΢���ź�", Font.BOLD, 12);
		jTable.setFont(f); // ��������

		jTable.setFillsViewportHeight(true); // �߶Ⱥ͹�������ĸ߶�һ��
		// �����п�
		
		jTable.getColumnModel().getColumn(0).setPreferredWidth(100);
		jTable.getColumnModel().getColumn(2).setPreferredWidth(60);
		jTable.getColumnModel().getColumn(3).setPreferredWidth(170);
		// �����и�
		jTable.setRowHeight(22);
		jscrollPane = new JScrollPane(jTable);
		// ���ù�������
		jscrollPane
				.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		jscrollPane
				.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		
		jTabbedPane = new JTabbedPane();
		// ���һ��ѡ�������ΪTable
		RefreshPanel.setPreferredSize(new Dimension(720,350));
		RefreshPanel.setLayout(new BorderLayout());
		RefreshPanel.add(jscrollPane);
		Refresh.setSize(80, 25);
		RefreshTable.add(RefreshPanel);
		RefreshTable.add(Refresh);
		RefreshTable.setLayout(new FlowLayout());
		
		jTabbedPane.add("Table", RefreshTable);
		Addlabel1.setFont(f);
		Addlabel2.setFont(f);
		Addlabel3.setFont(f);
		Addlabel4.setFont(f);
		Changelabel1.setFont(f);
		Changelabel2.setFont(f);
		Changelabel3.setFont(f);
		Changelabel4.setFont(f);
		Deletelabel1.setFont(f);
		FunctionPanel.setLayout(new FlowLayout());
		AddPanel.setSize(680,100);
		AddPanel.setLayout(new FlowLayout());
		AddPanel.add(Add);
		AddPanel.add(Addlabel1);
		AddPanel.add(AddFieldID);
		AddPanel.add(Addlabel2);
		AddPanel.add(AddFieldName);
		AddPanel.add(Addlabel3);
		AddPanel.add(AddFieldAuthor);
		AddPanel.add(Addlabel4);
		AddPanel.add(AddFieldPrice);
		DeletePanel.setSize(680,100);
		DeletePanel.setLayout(new FlowLayout());
		DeletePanel.add(Delete);
		DeletePanel.add(Deletelabel1);
		DeletePanel.add(DeleteFieldID);
		ChangePanel.setSize(680,100);
		ChangePanel.setLayout(new FlowLayout());
		ChangePanel.add(Change);
		ChangePanel.add(Changelabel1);
		ChangePanel.add(ChangeFieldID);
		ChangePanel.add(Changelabel2);
		ChangePanel.add(ChangeFieldName);
		ChangePanel.add(Changelabel3);
		ChangePanel.add(ChangeFieldAuthor);
		ChangePanel.add(Changelabel4);
		ChangePanel.add(ChangeFieldPrice);
		SearchPanel.setSize(680,100);
		SearchPanel.setLayout(new FlowLayout());
		SearchPanel.add(Search);
		SearchPanel.add(Searchlabel1);
		SearchPanel.add(SearchFieldID);
		SearchPanel.add(SearchResult);
		FunctionPanel.add(AddPanel);
		FunctionPanel.add(ChangePanel);
		FunctionPanel.add(DeletePanel);
		FunctionPanel.add(SearchPanel);
		jTabbedPane.add("FunctionPanel",FunctionPanel);
		
		getContentPane().setLayout(new BorderLayout());
		getContentPane().add(jTabbedPane);
		//getContentPane().add(Add);
		// ������ʾ���ھ�����Ļ����λ��
		Dimension sd = Toolkit.getDefaultToolkit().getScreenSize();
		setLocation((sd.width - 600) / 2, (sd.height - 400) / 2);
		setSize(750, 465);
		setVisible(true);
		setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
	}
	
	public void SetModel() { // ��ͼ������ˢ�º���
		MyTableModel mtm = new MyTableModel(lci.list);
	    jTable.setModel(mtm);
	    mtm.fireTableStructureChanged();
	    mtm.fireTableDataChanged();
	}
	
	public void addController(ClientFrameController cfc) {// ��ͼ���¼�����������ȥ����
		Add.addActionListener(cfc); // ��������ֱ��ע�����ͼ�в����¼������ } //��û�н������ڽӿڵ�ת������
		Change.addActionListener(cfc);
		Delete.addActionListener(cfc);
		Search.addActionListener(cfc);
		Refresh.addActionListener(cfc);
	}
	
	public static void main(String[] args){
		
	}

}
