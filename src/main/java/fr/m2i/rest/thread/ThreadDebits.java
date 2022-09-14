package fr.m2i.rest.thread;

import fr.m2i.rest.models.Compte;
import fr.m2i.rest.repository.CompteRepository;

public class ThreadDebits implements Runnable{

	private Long millis;
	CompteRepository cr;
	Compte compte;
	
	
	public ThreadDebits(Long millis, CompteRepository cr, Compte c) {
		this.millis = millis;
		this.cr = cr;
		this.compte = c;
	}
	
	@Override
	public void run() {
		
		while(true) {
			this.compte = cr.findAll().get(0);
			synchronized(this.cr) {		
				
				try {
					
					int nbDebits = cr.findAll().size();
					
					if (nbDebits >0) {
						double allDebits = cr.getSommeDebits();
						cr.updatSolde(Long.valueOf(1), allDebits);
						System.out.println("DEBIT transaction: "+allDebits+" €"+
						"\n -----------------------------------------------");
					}else {
						System.out.println("NO DEBIT transaction"+
								"\n -----------------------------------------------");
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
