import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;

public class Main implements Runnable, ActionListener{

  // Class Variables  
  JPanel mainPanel;

  JLabel prompt;
  JLabel feedback;
  JLabel answer;

  JTextField userGuess;

  JButton submit;
  JButton newNumber;


  // Method to assemble our GUI
  public void run(){
    // Creats a JFrame that is 800 pixels by 600 pixels, and closes when you click on the X
    JFrame frame = new JFrame("Title");
    // Makes the X button close the program
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    // makes the windows 800 pixel wide by 600 pixels tall
    frame.setSize(800,600);
    // shows the window
    frame.setVisible(true);

    //initialize main panel
    mainPanel = new JPanel();
    //remove layout helpers
    mainPanel.setLayout(null);

    //starts method that selects random number
    randSelector();

    //initialize labels
    prompt = new JLabel("Guess a number between 0 and 100");
    feedback = new JLabel();

    //sets location and size of labels
    prompt.setBounds(250, 20, 300, 20);
    feedback.setBounds(325, 120, 300, 20);

    //initialize text field
    userGuess = new JTextField();

    //sets location for user text field
    userGuess.setBounds(225, 60, 300, 20);

    //initialize buttons
    submit = new JButton("Submit");
    newNumber = new JButton("New Number");

    //sets location and size of buttons
    submit.setBounds(225, 80, 120, 40);
    newNumber.setBounds(355, 80, 170, 40);

    //adds action listener and action command to buttons
    submit.addActionListener(this);
    newNumber.addActionListener(this);

    submit.setActionCommand("submit");
    newNumber.setActionCommand("numNew");

    //adds everything to main panel 
    mainPanel.add(prompt);
    mainPanel.add(feedback);
    mainPanel.add(submit);
    mainPanel.add(newNumber);
    mainPanel.add(userGuess);

    //adds main panel to frame
    frame.add(mainPanel);
 
    
  }

  //method that sets a random number
  public void randSelector(){
    //creates random number
    Random rand = new Random();
    int randNum = rand.nextInt(100) + 0;

    //initialize label for random number
    answer = new JLabel("" + randNum);

    //sets the answer so that the user cannot see it
    answer.setBounds(0, 0, 0, 0);
  }


  // method called when a button is pressed
  public void actionPerformed(ActionEvent e){
    // get the command from the action
    String command = e.getActionCommand();
    //gets number and rand num
    String userString = userGuess.getText();
    String randString = answer.getText();

    //converts numbers to int
    int userNum = Integer.parseInt(userString);
    int randNum = Integer.parseInt(randString);

    if(command.equals("submit")){
      if(userNum == randNum){
        feedback.setText(userNum + " is the right answer!");
        randSelector();
      }else if(userNum > randNum){
        feedback.setText(userNum + " is too high!");
      }else if(userNum < randNum){
        feedback.setText(userNum + " is too low!");
      }

    }else if(command.equals("numNew")){
      randSelector();
      userGuess.setText("");
      feedback.setText("");
    }

  }

  // Main method to start our program
  public static void main(String[] args){
    // Creates an instance of our program
    Main gui = new Main();
    // Lets the computer know to start it in the event thread
    SwingUtilities.invokeLater(gui);
  }
}
