import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableColumnModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.JButton;
import javax.swing.JTextPane;
import javax.swing.JTextField;
import javax.swing.JTable;
import javax.swing.JScrollPane;

public class ProgramView extends JPanel {

	
	private ClassTest classTest;

	private JPanel optionsPanel;
	private JPanel outputPanel;
	private JTextField findTextField;
	private JButton btnAverage;
	private JButton btnMin;
	private JButton btnMax;
	private JButton btnSortlist;
	private JButton btnFind;

	private DefaultTableModel tableModel1 = new DefaultTableModel(new String[] { "No.", "Name", "Surname", "Grade" },0);

	private JScrollPane scrollPane;
	private JTable table;

	public ProgramView(ClassTest classTest) {
		this.classTest = classTest;
		
		initialize();
		decorate();
	}

	public void initialize() {

		setBorder(new EmptyBorder(5, 5, 5, 5));
		setLayout(null);

		optionsPanel = new JPanel();
		optionsPanel.setBorder(new TitledBorder(null, "Options", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		optionsPanel.setBounds(10, 11, 124, 292);
		add(optionsPanel);
		optionsPanel.setLayout(null);

		btnAverage = new JButton("Average");
		btnAverage.setBounds(10, 21, 104, 23);
		optionsPanel.add(btnAverage);

		btnMin = new JButton("MIN");
		btnMin.setBounds(20, 49, 81, 23);
		optionsPanel.add(btnMin);

		btnMax = new JButton("MAX");
		btnMax.setBounds(20, 77, 81, 23);
		optionsPanel.add(btnMax);

		btnSortlist = new JButton("Sort&List");
		btnSortlist.setBounds(10, 105, 104, 23);
		optionsPanel.add(btnSortlist);

		outputPanel = new JPanel();
		outputPanel.setBorder(
				new TitledBorder(null, "Find by surname", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		outputPanel.setBounds(0, 157, 124, 135);
		optionsPanel.add(outputPanel);
		outputPanel.setLayout(null);

		findTextField = new JTextField();
		findTextField.setBounds(10, 22, 104, 20);
		outputPanel.add(findTextField);
		findTextField.setColumns(10);

		btnFind = new JButton("Find");
		btnFind.setBounds(34, 53, 58, 23);
		outputPanel.add(btnFind);

		scrollPane = new JScrollPane();
		scrollPane.setBounds(163, 22, 318, 267);
		add(scrollPane);

		table = new JTable();
		scrollPane.setViewportView(table);
		table.setModel(tableModel1);

	}

	public void decorate() {
		btnAverage.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String average = String.valueOf(classTest.getAverage());
				table.setModel(new DefaultTableModel(new Object[][] { { average } }, new String[] { "Average" }));
			}
		});

		btnSortlist.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Entity[] list = classTest.getSortedList();
				DefaultTableModel model = new DefaultTableModel(new String[] { "No.", "Name", "Surname", "Grade" }, 0);
				table.setModel(model);

				for (int i = 0; i < list.length; i++) {
					model.addRow(new Object[] { i + 1, list[i].getName(), list[i].getSurname(), list[i].getGrade() });
				}
				setSizeOfColumns();

			}
		});

		btnMin.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				int min = classTest.getMin();
				table.setModel(new DefaultTableModel(new Object[][] { { min } }, new String[] { "Minimum" }));
			}
		});

		btnMax.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				int max = classTest.getMax();
				table.setModel(new DefaultTableModel(new Object[][] { { max } }, new String[] { "Maximum" }));
			}
		});

		btnFind.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Entity[] list = classTest.getStudentBySurname(findTextField.getText());
				if (list.length == 0) {
					table.setModel(
							new DefaultTableModel(new Object[][] { { "Not found" } }, new String[] { "Searching" }));
				} else {
					DefaultTableModel model = new DefaultTableModel(new String[] { "No.", "Name", "Surname", "Grade" },
							0);
					table.setModel(model);
					for (int i = 0; i < list.length; i++) {
						model.addRow(
								new Object[] { i + 1, list[i].getName(), list[i].getSurname(), list[i].getGrade() });
					}
					setSizeOfColumns();
				}

			}
		});
	}

	private void setSizeOfColumns() {
		table.getColumnModel().getColumn(0).setPreferredWidth(25);
		table.getColumnModel().getColumn(1).setPreferredWidth(130);
		table.getColumnModel().getColumn(2).setPreferredWidth(130);
		table.getColumnModel().getColumn(3).setPreferredWidth(40);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_LAST_COLUMN);
	}
}
