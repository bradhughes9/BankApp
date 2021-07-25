import javax.swing.*;
import java.text.DecimalFormat;
import java.util.Enumeration;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.text.DecimalFormat;
import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;
public class BankGUI extends JFrame implements ActionListener{
	public Bank bank = new Bank();
	
	//Both formats for the descriptions
	DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
	DecimalFormat decimalFormat = new DecimalFormat("'$'0.00");	

	//Creating the label before the buttons.
	public JLabel lblType = new JLabel("Type: ");	
	//Making a new button group.
	ButtonGroup bgType=new ButtonGroup();	
	//Creating the radio buttons.
	JRadioButton rbDeposit=new  JRadioButton("Deposit");
	JRadioButton rbWithdrawal=new  JRadioButton("Withdrawal");
	JRadioButton rbMisc=new  JRadioButton("Miscellaneous");
	
	//Text box to hold the amount for transaction.
	public JLabel lblAmount = new JLabel("Amount: ");
	public JTextField txtAmount = new JTextField(6);
	
	//Creating the Description part.
	public JLabel lblDescription = new JLabel("Description: ");
	public JTextField txtDescription = new JTextField(35);
	
	//Creating the save button.
	public JButton btnSave=new JButton("Save");

	
	//Creating a Label to hold the user balance
	//It stats empty so the user can assign its value
	public JLabel lblBalance = new JLabel("$" + String.valueOf(bank.getBalance()));
	
	
	//Determine the status of the transaction
	public JLabel lblOkay = new JLabel("");

	public JTextArea textArea = new JTextArea(5, 40);
	public JScrollPane scrollPane = new JScrollPane(textArea);
	

	public static void main(String args[]) {
		new BankGUI();
		
	}
	BankGUI() {

		//Creating the GUI
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//Main Panel
		JPanel pMain = new JPanel();
		JPanel pType = new JPanel();
		JPanel pAmount = new JPanel();
		JPanel pDescription = new JPanel();
		JPanel pSave = new JPanel();
		JPanel pBalance = new JPanel();
		JPanel pOkay = new JPanel();
		JPanel pHistory = new JPanel();
		btnSave.addActionListener(this);	
		
		//Grouping Radio Buttons	
		bgType.add(rbDeposit);
		bgType.add(rbWithdrawal);
		bgType.add(rbMisc);
		//Select deposit as default value
		rbDeposit.setSelected(true);
	
		
		//Adding Label then Radio Buttons.
		pType.add(lblType);
		pType.add(rbDeposit);
		pType.add(rbWithdrawal);
		pType.add(rbMisc);
		pMain.add(pType);
		
		//Adding the Amount
		pAmount.add(lblAmount);
		pAmount.add(txtAmount);
		pMain.add(pAmount);
		
		//Add the Description
		pDescription.add(lblDescription);
		pDescription.add(txtDescription);
		pMain.add(pDescription);
		
		//Save button.
		pSave.add(btnSave);
		pMain.add(pSave);
		
		//User Balance
		pBalance.add(lblBalance);
		pMain.add(pBalance);
		
		//Adding the transaction status.
		pOkay.add(lblOkay);
		pMain.add(pOkay);
		
		//Adding the JScrollpane and text area for
		//each transaction to be listed into.
		textArea.setEditable(false);
		pHistory.add(scrollPane);
		pMain.add(pHistory);
		
		//Stacking all tge items in a vertical layout
		//along the Y axis and establishing the size.
		pMain.setLayout(new BoxLayout(pMain,BoxLayout.Y_AXIS));
		this.getContentPane().add (pMain);
		this.setSize(650, 650);
		
	}
	
	//When the button is clicked, the action listener will perform
	//the actions below.
    public void actionPerformed(ActionEvent e)
	{
		//Wrap everything in a try catch to handle any errors that may slip through.
		try
		{	
			
			double userInput = Double.parseDouble(txtAmount.getText());
			String formUserInput = decimalFormat.format(Double.parseDouble(txtAmount.getText()));
			LocalDateTime time = LocalDateTime.now();
			String formTime = dateFormat.format(time);
			String descriptionToGui = (formTime + "     " +  formUserInput + "    Deposit: " +txtDescription.getText() + "\n");
			
			//deposit radio button
			if (rbDeposit.isSelected())
			{
				if (userInput <= 0)
				{
					lblOkay.setText("You don' have enough money for this transaction.");
				}
				else
				{
					bank.deposit(userInput);
					lblBalance.setText("$" + String.valueOf(bank.getBalance()));
					//sets the insert at the 0 index.
					textArea.insert(descriptionToGui, 0);
				}			
			}
			//withdrawal radio button
			if (rbWithdrawal.isSelected())
			{
				if (userInput <= bank.getBalance() && userInput > 0)
				{
					bank.withdrawal(userInput);
					lblBalance.setText("$" + String.valueOf(bank.getBalance()));
					textArea.insert(descriptionToGui, 0);
				}
				else 
				{
					lblOkay.setText("You can only enter positive numbers.");
				}
			}
			//misc radio button
			if (rbMisc.isSelected())
			{
				if (userInput < 0)
				{
					if (userInput + bank.getBalance() < 0)
					{		
						lblOkay.setText("You do not have enough money for this transaction");
					}
					else 
					{
					//multiply userInput by -1 to make it positive
					//because the withdrawal method only allows positives.
					bank.withdrawal(userInput * -1);
					lblBalance.setText("$" + String.valueOf(bank.getBalance()));
					textArea.insert(descriptionToGui, 0);
					}
				}
				else
				{
					bank.deposit(userInput);
					lblBalance.setText("$" + String.valueOf(bank.getBalance()));
					textArea.insert(descriptionToGui, 0);
				}
			}
		}
		catch (Exception input)
		{
			lblOkay.setText("You have entered an invalid input, please enter an integer.");
		}
		
	}
}