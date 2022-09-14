package fr.m2i.rest.thread;

import fr.m2i.rest.models.Compte;
import fr.m2i.rest.repository.CompteRepository;
import fr.m2i.rest.repository.CreditRepository;

public class ThreadCredit implements Runnable{

	private Long millis;
	CompteRepository cr;
	Compte compte;
	CreditRepository cred;
	
	public ThreadCredit(Long millis, CompteRepository cr, Compte c, CreditRepository cred) {
		this.millis = millis;
		this.cr = cr;
		this.compte = c;
		this.cred = cred;
	}
	
	@Override
	public void run() {
		
		while(true) {
			this.compte = cr.findAll().get(0);
			synchronized(this.cr) {		
				
				try {
					int nbCredits = cred.findAll().size();
					
					if (nbCredits >0) {
						Double allCredits = cr.getSommeCredits();
						cr.updatSolde(Long.valueOf(1), allCredits);
						cred.deleteAll();
						System.out.println("CREDIT transaction: +"+allCredits+" €+"
								+ "\n -----------------------------------------------");
					}else {
						System.out.println("No CREDIT transaction"
								+ "\n -----------------------------------------------");
					}
					
					//Thread.sleep(3000L);
				}catch(IllegalThreadStateException e) {
					System.out.println("CA MARCHE");
				} /**catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}**/
			}	
			try {
				Thread.sleep(this.millis);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
