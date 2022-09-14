package fr.m2i.rest.thread;

import fr.m2i.rest.models.Compte;
import fr.m2i.rest.repository.CompteRepository;

public class ThreadSalaire implements Runnable{

	private Long millis;
	
	CompteRepository cr;
	Compte compte;
	
	public ThreadSalaire(Long millis, CompteRepository cr, Compte c) {
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
					cr.updatSalaire(Long.valueOf(1));
					System.out.println("SALAIRE transaction: +"+this.compte.getSalaire()+" €"+
							"\n -----------------------------------------------");
					//Thread.sleep(3000L);
				}
				catch(IllegalThreadStateException e) {
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
