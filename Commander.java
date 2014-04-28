/*
 * Programmer: Max Shishkov
 * Created: June 2, 2013 
 * File Name: Commander.java
 * Description: 
 *				This sofware will promt user to enter a string and then
 *				return the string as a command via speach synthesizer.
 * 
 */

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;


/**
 * The Commander is a class that extends JFrame.
 * The commander contains a jTextField that takes an input from the user and passes it
 * to the string called command.
 * It also has a JComboBox that contains three choices that determine the 
 * the rate and pitch of speech synthesizer. The combo box also determines the context of 
 * two helper strings "helper" and "finisher".
 * The last component of the frame is the Speak button. The speak button
 * passes the variable strings helper, command and finisher to the Talk class's
 * variable called the speaker.
 * @return The class returns a voiced command based on the user's choices and inputs.
 */
public class Commander extends JFrame
{
	//a speach synthesyzer.
	Talk speaker;
	
	//A string that holds the user's input
	String command;
	
	//helper strings
	String helper = " ";
	String finisher = " ";
	
	//The text field to enter the text
	JTextField jtext1;
	
	//A combo box to chose the style of voice
	JComboBox jcbb;

	//String array of choices for the combo box
	String choice[] = {" ", "Not Lazy", "Nutural", "Lazy"};
	
	//A button to speak the command
	JButton jbtnSpeak;
	
	//constructor that calls method createUserInterface
	Commander()
	{
		createUserInterface();
	}
	
	
	//The class that defines properties of the GUI. 
	public void createUserInterface()
	{
		// Returns the contentPane object for this frame.
		Container contentPane = getContentPane();
		
		speaker = new Talk();
		
		// Enable explicit positioning of FUI components
		contentPane.setLayout(null);
		
		//create a JTextField
		jtext1 = new JTextField("Enter a command and press Enter", 15);
		jtext1.setBounds(30,10,240,35);
		
		//Add action listener for the text field.
		//The action listener listens for the Enter button to be press
		//and then saves the content of the JTextField to the variable command.
		jtext1.addActionListener(new ActionListener()
			{
				public void actionPerformed(ActionEvent le)
				{
					command = jtext1.getText();
				}
			});
	
		//Set up jbtnSpeak
		jbtnSpeak = new JButton("Speak");
		jbtnSpeak.setBounds(100,60,100,30);
		
		//Add action Listener that passes the strings to the speaker variable's
		// method called speak to speak the entered command using voice synthesizer.
		jbtnSpeak.addActionListener(new ActionListener ()
			{
				public void actionPerformed(ActionEvent ae)
				{
					
					speaker.helloVoice.speak(helper + command + finisher);
				}
				
			});
		
		//create a JComboBox
		jcbb = new JComboBox(choice);
		jcbb.setBounds(300,10,150,35);
		
		
		
		//Add action listener for the combo box
		//The action listener chacks the combo box for the selected choice
		//and then changes the charecteristics of the speach synthesizer
		//and the content of the helper strings based on the user choice.
		jcbb.addActionListener (new ActionListener()
			{
				public void actionPerformed(ActionEvent le)
				{
					//get a reference to the item selected
					int indx = (int)jcbb.getSelectedIndex();
					
					if (indx == 1)
					{
					    speaker.helloVoice.setPitch(350.0f);
						speaker.helloVoice.setRate(130.0f);
						helper = "It's time to ";
						finisher = " sweety";
					}
					else if (indx == 2)
					{
					    speaker.helloVoice.setPitch(100.0f);
						speaker.helloVoice.setRate(130.0f);
						helper = "Go and ";
						finisher = "right now";
					}
					else if (indx == 3)
					{
					    speaker.helloVoice.setPitch(85.0f);
						speaker.helloVoice.setRate(145.0f);
						helper = "Get up!    " + " It's about the time you      ";
						finisher =  "    you piece of lazy biomass!";
					}
					
					
				}
			});
		
		//Add to the content pane
		contentPane.add(jtext1);
		contentPane.add(jbtnSpeak);
		contentPane.add(jcbb);
		/*
		 * The following code sets up both the size of the window
		 * and centers the window on the screen. The Toolkit class
		 * is the abstract superclass of all actual implementations 
		 * of the Abstract Window Toolkit.  The getScreenSize( ) method
		 * gets the size of the screen. On systems with multiple 
		 * displays, the primary display is used.
		 */
		
		int width = 500;
		int height = 200;
		Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
		int x = (screen.width-width)/2;
		int y = (screen.height-height)/2;
		setBounds(x,y,width,height);
		
		setTitle("Commander");
		setSize(width, height);
		setVisible(true);
	}
	
	
	
	public static void main (String args[])
	{
		Commander application = new Commander();
		application.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}