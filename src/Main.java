import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class Main extends JFrame{
	
	ClassTest classTest;
	ProgramView programView;
	
	public Main(){
		setMinimumSize(new Dimension(450, 350));
		setTitle("Grade Analysis");
		setSize(400, 400);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		
	}
	
	public void runProgram(ClassTest classTest){
		this.classTest = classTest;
		this.programView = new ProgramView(classTest);
		setContentPane(programView);
		setMinimumSize(new Dimension(500, 350));

	}

	public static void main(String[] args) {
		
		Main mainWindow = new Main();
		StartingView startingView = new StartingView(mainWindow);

		mainWindow.setContentPane(startingView);

		mainWindow.pack();
		mainWindow.setVisible(true);
		mainWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

}
