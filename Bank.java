import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;

public class Bank{

	private double balance = 0;
	int userInput;
	public Bank(){
		balance = 0.00;
	}	
	public Bank(double balance){
		this.balance = balance;
	}
	public void deposit (double userInput){		
		double depo = userInput;
		balance = balance + depo;
	}	
	
	public void withdrawal (double userInput){
		double withdraw = userInput;
		balance = balance - withdraw;
	}	
	public void misc (double userInput){
		double miscellaneous = userInput;
		balance = balance + miscellaneous;
	}	
	public void setBalance(double balance){
		this.balance = balance;
	}
	public double getBalance(){
	return	balance;
	}
	
}