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
	
	Label Addlabel1 = new Label("Id : ");   //此处往下为静态标签，作为用户填写信息的引导
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
	JTextField AddFieldID = new JTextField(){ // 标签，文本框，按钮
		public Dimension getPreferredSize() { // 使用匿名类，扩展TextField
			return new Dimension(80, 25); // 覆盖getPreferredSize方法
		}
	};
	JTextField AddFieldName = new JTextField(){ // 标签，文本框，按钮
		public Dimension getPreferredSize() { // 使用匿名类，扩展TextField
			return new Dimension(80, 25); // 覆盖getPreferredSize方法
		}
	};
	JTextField AddFieldAuthor = new JTextField(){ // 标签，文本框，按钮
		public Dimension getPreferredSize() { // 使用匿名类，扩展TextField
			return new Dimension(80, 25); // 覆盖getPreferredSize方法
		}
	};
	JTextField AddFieldPrice = new JTextField(){ // 标签，文本框，按钮
		public Dimension getPreferredSize() { // 使用匿名类，扩展TextField
			return new Dimension(80, 25); // 覆盖getPreferredSize方法
		}
	};
	JTextField DeleteFieldID = new JTextField(){ // 标签，文本框，按钮
		public Dimension getPreferredSize() { // 使用匿名类，扩展TextField
			return new Dimension(80, 25); // 覆盖getPreferredSize方法
		}
	};
	JTextField ChangeFieldID = new JTextField(){ // 标签，文本框，按钮
		public Dimension getPreferredSize() { // 使用匿名类，扩展TextField
			return new Dimension(80, 25); // 覆盖getPreferredSize方法
		}
	};
	JTextField ChangeFieldName = new JTextField(){ // 标签，文本框，按钮
		public Dimension getPreferredSize() { // 使用匿名类，扩展TextField
			return new Dimension(80, 25); // 覆盖getPreferredSize方法
		}
	};
	JTextField ChangeFieldAuthor = new JTextField(){ // 标签，文本框，按钮
		public Dimension getPreferredSize() { // 使用匿名类，扩展TextField
			return new Dimension(80, 25); // 覆盖getPreferredSize方法
		}
	};
	JTextField ChangeFieldPrice = new JTextField(){ // 标签，文本框，按钮
		public Dimension getPreferredSize() { // 使用匿名类，扩展TextField
			return new Dimension(80, 25); // 覆盖getPreferredSize方法
		}
	};
	JTextField SearchFieldID = new JTextField(){ // 标签，文本框，按钮
		public Dimension getPreferredSize() { // 使用匿名类，扩展TextField
			return new Dimension(80, 25); // 覆盖getPreferredSize方法
		}
	};
	TextArea SearchResult = new TextArea(){
		public Dimension getPreferredSize() { // 使用匿名类，扩展TextField
			return new Dimension(160, 80); // 覆盖getPreferredSize方法
		}
	};
	JPanel AddPanel = new JPanel();  //以下为容器，每个容器具有一定的区域，同时实现不同的功能
	JPanel DeletePanel = new JPanel();
	JPanel ChangePanel = new JPanel();
	JPanel SearchPanel = new JPanel();
	JTabbedPane jTabbedPane = null;
	JScrollPane jscrollPane = null;
	JTable jTable = null;
	JButton Add =  new JButton("添加图书");  //以下为Button具有不同的功能
	JButton Delete = new JButton("删除图书");
	JButton Change = new JButton("更改图书");
	JButton Search = new JButton("查询图书");
	JButton Refresh = new JButton("刷新");
	JPanel RefreshTable = new JPanel();
	JPanel RefreshPanel = new JPanel();
	public ClientFrame(Vector<Book> model,LibraryClientIdea lci) {
		this.lci = lci;  //构造函数，初始化客户端，一下为初始化的代码信息，包括设置字体，设置大小以及位置
		
		jTable = new JTable(new MyTableModel(model)); // 根据模型创建UI
		jTable.getTableHeader().setFont(new Font("微软雅黑", Font.BOLD, 14));
		jTable.setSize(680,350);
		Font f = new Font("微软雅黑", Font.BOLD, 12);
		jTable.setFont(f); // 设置字体

		jTable.setFillsViewportHeight(true); // 高度和滚动窗格的高度一致
		// 设置列宽
		
		jTable.getColumnModel().getColumn(0).setPreferredWidth(100);
		jTable.getColumnModel().getColumn(2).setPreferredWidth(60);
		jTable.getColumnModel().getColumn(3).setPreferredWidth(170);
		// 设置行高
		jTable.setRowHeight(22);
		jscrollPane = new JScrollPane(jTable);
		// 设置滚动策略
		jscrollPane
				.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		jscrollPane
				.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		
		jTabbedPane = new JTabbedPane();
		// 添加一个选项卡，名称为Table
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
		// 设置显示窗口居于屏幕中央位置
		Dimension sd = Toolkit.getDefaultToolkit().getScreenSize();
		setLocation((sd.width - 600) / 2, (sd.height - 400) / 2);
		setSize(750, 465);
		setVisible(true);
		setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
	}
	
	public void SetModel() { // 视图中数据刷新函数
		MyTableModel mtm = new MyTableModel(lci.list);
	    jTable.setModel(mtm);
	    mtm.fireTableStructureChanged();
	    mtm.fireTableDataChanged();
	}
	
	public void addController(ClientFrameController cfc) {// 视图的事件交给控制器去处理
		Add.addActionListener(cfc); // 将控制器直接注册给视图中产生事件的组件 } //并没有进行听众接口的转换处理
		Change.addActionListener(cfc);
		Delete.addActionListener(cfc);
		Search.addActionListener(cfc);
		Refresh.addActionListener(cfc);
	}
	
	public static void main(String[] args){
		
	}

}
