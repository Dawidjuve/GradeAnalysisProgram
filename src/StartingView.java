import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.AbstractButton;
import javax.swing.InputVerifier;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;

import com.sun.org.apache.xalan.internal.xsltc.compiler.util.TestGenerator;

import java.awt.Color;

public class StartingView extends JPanel {
	
	Main main;
	ClassTest classTest;
	private JPanel panel1;
	private JPanel panel2;
	private JTextField textSurname;
	private JTextField textName;
	private JTextField textGrade;
	private JLabel nameError;
	private JLabel surnameError;
	private JLabel gradeError;
	private JLabel lblNumberOfEntered;
	private JComboBox<Integer> comboBox;
	private JButton btnOK;
	private JButton btnAdd;

	/**
	 * Create the frame.
	 */
	public StartingView(Main main) {
		this.main=main;
		initialize();
		decorate();
		
	}
	
	public void initialize(){
		
		setBorder(new EmptyBorder(5, 5, 5, 5));
		setLayout(null);
		
		panel1 = new JPanel();
		panel1.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panel1.setBounds(10, 11, 414, 45);
		add(panel1);
		panel1.setLayout(null);
		
		JLabel label = new JLabel("");
		label.setBounds(10, 11, 46, 14);
		panel1.add(label);
		
		comboBox = new JComboBox<>();
		for (int i = 2; i <= 25; i++) {
			comboBox.addItem(i);
		}
		comboBox.setBounds(28, 11, 61, 20);
		panel1.add(comboBox);
		
		btnOK = new JButton("OK");
		btnOK.setEnabled(false);
		btnOK.setBounds(113, 10, 52, 21);
		panel1.add(btnOK);
		
		JLabel lblChoiceNumber = new JLabel("Choice number of Students");
		lblChoiceNumber.setBounds(183, 11, 194, 20);
		panel1.add(lblChoiceNumber);
		
		panel2 = new JPanel();
		panel2.setForeground(Color.BLACK);
		panel2.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panel2.setBounds(10, 77, 414, 174);
		add(panel2);
		panel2.setLayout(null);
		
		JLabel lblName = new JLabel("Name: ");
		lblName.setBounds(65, 34, 79, 24);
		panel2.add(lblName);
		
		JLabel lblSurname = new JLabel("Surname:");
		lblSurname.setBounds(65, 64, 79, 24);
		panel2.add(lblSurname);
		
		JLabel lblGrade = new JLabel("Grade(1-100):");
		lblGrade.setBounds(65, 93, 79, 24);
		panel2.add(lblGrade);
		
		lblNumberOfEntered = new JLabel("0 Entered");
		lblNumberOfEntered.setHorizontalAlignment(SwingConstants.CENTER);
		lblNumberOfEntered.setBounds(107, 11, 191, 24);
		panel2.add(lblNumberOfEntered);
		
		btnAdd = new JButton("Add");
		btnAdd.setBounds(174, 142, 65, 21);
		panel2.add(btnAdd);
		
		textSurname = new JTextField();
		textSurname.setBounds(160, 65, 132, 22);
		panel2.add(textSurname);
		textSurname.setColumns(10);
		
		textName = new JTextField();
		textName.setColumns(10);
		textName.setBounds(160, 35, 132, 22);
		panel2.add(textName);
		
		textGrade = new JTextField();
		textGrade.setColumns(10);
		textGrade.setBounds(160, 95, 45, 22);
		panel2.add(textGrade);
		
		nameError = new JLabel("Invalid");
		nameError.setForeground(this.getBackground());
		nameError.setBounds(315, 39, 46, 14);
		panel2.add(nameError);
		
		surnameError = new JLabel("Invalid");
		surnameError.setForeground(this.getBackground());
		surnameError.setBounds(315, 69, 46, 14);
		panel2.add(surnameError);
		
		gradeError = new JLabel("Invalid");
		gradeError.setForeground(this.getBackground());
		gradeError.setBounds(231, 98, 46, 14);
		panel2.add(gradeError);
		
		for(Component cp : panel2.getComponents()){
			cp.setEnabled(false);
		}
	}
	
	public void decorate(){
		
		comboBox.addActionListener(new ActionListener() {	
			@Override
			public void actionPerformed(ActionEvent e) {
				btnOK.setEnabled(true);
			}
		});
		
		btnOK.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int number = (int) comboBox.getSelectedItem();
				classTest=new ClassTest(number);
				
				for(Component cp : panel2.getComponents()){
					cp.setEnabled(true);
				}
				for(Component cp : panel1.getComponents()){
					cp.setEnabled(false);
				}
			}
		});
		
		btnAdd.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(validateInput()){
					classTest.addEntity(new Entity(textName.getText(), textSurname.getText(), Integer.parseInt(textGrade.getText())));
					lblNumberOfEntered.setText(classTest.getNumberOfEntered() + "/" +classTest.getNumberOfStudents()+" Entered");
					textName.setText(""); textSurname.setText(""); textGrade.setText("");
					if(classTest.getNumberOfEntered()>=classTest.getNumberOfStudents()){
						main.runProgram(classTest);
					}
				}
			}
		});
	}
	public void addObserver(){
		
	}
	
	private boolean validateInput(){
		if(validateName() & validateSurname() & validateGrade())
			return true;
		return false;
	}
	
	private boolean validateName(){
		Pattern pattern = Pattern.compile("[A-Za-zàáâäãåąčćęèéêëėįìíîïłńòóôöõøùúûüųūÿýżźñçčšžÀÁÂÄÃÅĄĆČĖĘÈÉÊËÌÍÎÏĮŁŃÒÓÔÖÕØÙÚÛÜŲŪŸÝŻŹÑßÇŒÆČŠŽ∂ð]{2,20}");
		Matcher matcher = pattern.matcher(this.textName.getText());
		if(matcher.matches()){
			nameError.setForeground(this.getBackground());
			return true;
			}
		else {
			nameError.setForeground(Color.RED);
			return false;
		}
	}
	private boolean validateSurname(){
		Pattern pattern = Pattern.compile("^[a-zA-ZàáâäãåąčćęèéêëėįìíîïłńòóôöõøùúûüųūÿýżźñçčšžÀÁÂÄÃÅĄĆČĖĘÈÉÊËÌÍÎÏĮŁŃÒÓÔÖÕØÙÚÛÜŲŪŸÝŻŹÑßÇŒÆČŠŽ∂ð .'-]{2,25}");
		Matcher matcher = pattern.matcher(this.textSurname.getText());
		if(matcher.matches()){
			surnameError.setForeground(this.getBackground());
			return true;
			}
		else {
			surnameError.setForeground(Color.RED);
			return false;
		}
	}
	
	private boolean validateGrade(){
		try{
			int grade = Integer.parseInt(textGrade.getText());
			if(grade>=1 && grade<=100){
				gradeError.setForeground(this.getBackground());
				return true;
			}
				
		}catch(NumberFormatException e){
			
		}
		gradeError.setForeground(Color.RED);
		return false;
	}
	
	
	
}
