package lib.password;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JPasswordField;
import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.GridLayout;
import java.util.Arrays;
import java.awt.EventQueue;

public class PasswordDialog {
	 
    public static void main(String[] args) {
     
        
         EventQueue.invokeLater(new Runnable()
         {
             
            @Override
             public void run()
             {
                 
                 createGuiFrame();          
             }
         });
              
    }
     
   
    private static void createGuiFrame()
    {
        
        JFrame guiFrame = new JFrame();
        //make sure the program exits when the frame closes
        guiFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        guiFrame.setTitle("Dialog Box Example");
        guiFrame.setSize(500,300);
        
        //This will center the JFrame in the middle of the screen
        guiFrame.setLocationRelativeTo(null);
        guiFrame.setVisible(true);
        
        //Using a JTextArea to display feedback
        //(i.e., the username and password entered
        JTextArea tracker = new JTextArea("Password Tracker:");
        guiFrame.add(tracker);
        
        //Using a JPanel as the message for the JOptionPane
        JPanel userPanel = new JPanel();
        userPanel.setLayout(new GridLayout(2,2));
        
        JLabel usernameLbl = new JLabel("Username:");
        JLabel passwordLbl = new JLabel("Password:");
        JTextField username = new JTextField();
        JPasswordField passwordFld = new JPasswordField();
        
        userPanel.add(usernameLbl);
        userPanel.add(username);
        userPanel.add(passwordLbl);
        userPanel.add(passwordFld);
        
       
        int input = JOptionPane.showConfirmDialog(guiFrame, userPanel, "Enter your password:"
                            ,JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
         
        char[] correctPassword = {'E', 'x', 'c', 'a', 'l', 'i', 'b', 'u', 'r'};
        if (input == 0) //OK Button = 0
        {
            tracker.append("\nUsername entered was: " + username.getText());
        
            //Retrieve password
            char[] enteredPassword = passwordFld.getPassword();
            tracker.append("\nPassword entered was: " + String.valueOf(enteredPassword));
            
            
            if (Arrays.equals(correctPassword, enteredPassword))
            {
                tracker.append("\nThe password entered is correct!");
            }
            else
            {
                tracker.append("\nCall security - it's an imposter!");
            }
                   
        
            
            Arrays.fill(enteredPassword, '0');
        }
        else
        {
            //either the cancel button or the 'x' 
            //has been pressed
            tracker.append("\nDialog cancelled..");
        }
    
    }

}


