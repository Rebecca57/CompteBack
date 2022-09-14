package fr.m2i.rest.thread;

import org.springframework.beans.factory.annotation.Autowired;

import fr.m2i.rest.models.Compte;
import fr.m2i.rest.repository.CompteRepository;
import fr.m2i.rest.repository.CreditRepository;

public class ThreadTransaction implements Runnable{

	CompteRepository cr;
	CreditRepository cred;
	
	public ThreadTransaction(CompteRepository cr, CreditRepository cred) {
		this.cr = cr;
		this.cred = cred;
	}

	
	@Override
	public void run() {
		
		Compte compteT = cr.findAll().get(0);	
		//System.out.println(compteT);
		new Thread(new ThreadSalaire(5000L,cr,compteT)).start();
		new Thread(new ThreadDebits(5000L,cr,compteT)).start();	
		new Thread(new ThreadCredit(1000L,cr,compteT,cred)).start();	
	}

}
