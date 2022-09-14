package fr.m2i.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import fr.m2i.rest.repository.CompteRepository;
import fr.m2i.rest.repository.CreditRepository;
import fr.m2i.rest.thread.ThreadTransaction;

@SpringBootApplication
public class CompteBackApplication implements CommandLineRunner{
	
	@Autowired
	CompteRepository cr;
	
	@Autowired
	CreditRepository cred;

	public static void main(String[] args) {
		SpringApplication.run(CompteBackApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		new Thread(new ThreadTransaction(cr,cred)).start();
		
	}

}
