package bank;

import java.util.List;

import jpa.Payment;


public class functions {
	public void Print_Info_Payment(List<Payment> list_payments) {
		if (list_payments.size() == 0) {
			System.out.println("Não existem pagamentos neste estado");
		} else {

			System.out.printf("%-20s %-20s %-20s %-20s %-20s %n", "Id", "Data", "Preço", "Estado", "Info");
			for (int i = 0; i < list_payments.size(); i++) {
				System.out.printf("%-20s %-20s %-20s %-20s %-20s %n", list_payments.get(i).getId(),
						list_payments.get(i).getDate_time(), list_payments.get(i).getAmount(),
						list_payments.get(i).getStatus(), list_payments.get(i).getInfo());
			}
			System.out.println();
		}

	}
	
}
