package bank;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSConsumer;
import javax.jms.JMSContext;
import javax.jms.JMSException;
import javax.jms.JMSProducer;
import javax.jms.JMSRuntimeException;
import javax.jms.Message;
import javax.jms.ObjectMessage;
import javax.jms.TextMessage;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import jpa.Payment;
import jpa.Purchase;

public class Msg_Bank {
	private ConnectionFactory connectionFactory;
	private Destination destination_store;
	private Destination destination_bank;
	private Destination destination_client;
	private Destination destination_topic;
	
	public Msg_Bank() throws NamingException {
		this.connectionFactory = InitialContext.doLookup("jms/RemoteConnectionFactory");
		this.destination_store = InitialContext.doLookup("jms/queue/Store7");
		this.destination_bank=InitialContext.doLookup("jms/queue/Bank7");
		this.destination_client=InitialContext.doLookup("jms/queue/Client7");
		this.destination_topic=InitialContext.doLookup("jms/topic/Topic7");

		
	}
	
	private void send_request_payments(String text, String msg_property, int msg_property_2) {

		try (JMSContext context = connectionFactory.createContext("Project3", "project3");) {
			JMSProducer messageProducer = context.createProducer();
			TextMessage msg = context.createTextMessage();
			msg.setText(text);
			msg.setStringProperty("type", msg_property);
			msg.setIntProperty("card_numb", msg_property_2);

			messageProducer.send(destination_bank, msg);
		} catch (Exception re) {
			re.printStackTrace();
		}
	}
	
	private ArrayList<Payment> receive_payments(int msg_property_2) {
		ObjectMessage msg = null;
		ArrayList<Payment> list_payments = null;
		try (JMSContext context = connectionFactory.createContext("Project3", "project3");) {
			JMSConsumer mc = context.createConsumer(destination_bank);

			msg = (ObjectMessage) mc.receive();
			System.out.println((ArrayList<Payment>) msg.getObject());
			if (msg.getStringProperty("type").equals("completed_payments")
					&& msg.getIntProperty("card_numb") == msg_property_2) {
				list_payments = (ArrayList<Payment>) msg.getObject();
			}

			if (msg.getStringProperty("type").equals("pending_payments")
					&& msg.getIntProperty("card_numb") == msg_property_2) {
				list_payments = (ArrayList<Payment>) msg.getObject();
			}
			
			if (msg.getStringProperty("type").equals("pending_available_payments")
					&& msg.getIntProperty("card_numb") == msg_property_2) {
				list_payments = (ArrayList<Payment>) msg.getObject();
			}
			
			if (msg.getStringProperty("type").equals("failed_payments")
					&& msg.getIntProperty("card_numb") == msg_property_2) {
				list_payments = (ArrayList<Payment>) msg.getObject();
			}
			
		} catch (JMSException re) {
			re.printStackTrace();
		}
		return list_payments;
	}
		
	public static void main(String[] args) throws NamingException, JMSException {
		Msg_Bank bank=new Msg_Bank();
		boolean run = true;
		functions functions = new functions();
		Scanner sc = new Scanner(System.in);
		System.out.println("Bem vindo! O que pretende fazer?");
		do {
			System.out.println("Menu:");
			System.out.println("1-Ver pagamentos pendentes");
			System.out.println("2-Ver pagamentos aprovados");
			System.out.println("3-Ver pagamentos rejeitados");
			System.out.println("4-Aprovar Pagamentos");
			System.out.println("5-Sair");
			String firstmenu = sc.nextLine();
			System.out.println("Introduza o número do cartão que pretende analisar:");
			int card_number = sc.nextInt();
			System.out.println(card_number);
			sc.nextLine();
			if (firstmenu.equals("1")) {
				bank.send_request_payments("Visualizar pagamentos pendentes", "pending_payments", card_number);
				functions.Print_Info_Payment(bank.receive_payments(card_number));
			}
	
			else if (firstmenu.equals("2"))
			{
				bank.send_request_payments("Visualizar pagamentos aprovados", "completed_payments", card_number);
				functions.Print_Info_Payment(bank.receive_payments(card_number));
			}
			else if (firstmenu.equals("3"))
			{
				bank.send_request_payments("Visualizar pagamentos falhados", "failed_payments", card_number);
				functions.Print_Info_Payment(bank.receive_payments(card_number));
			}
			
			else if(firstmenu.equals("4")) {
				bank.send_request_payments("Visualizar pagamentos pendentes available", "pending_available_payments", card_number);
				functions.Print_Info_Payment(bank.receive_payments(card_number));
				System.out.println("Introduza o id dos pagamentos, separados por ';' : ");
					String ids = sc.nextLine();
					
					
			}
				
			else if (firstmenu.equals("5")){
				run=false;
			}
			}while (run);
	}
}
		
		
	


	
	
	

	
