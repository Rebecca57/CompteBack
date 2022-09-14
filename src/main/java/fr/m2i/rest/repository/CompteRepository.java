package fr.m2i.rest.repository;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import fr.m2i.rest.models.Compte;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

@Repository
public interface CompteRepository extends JpaRepository<Compte,Double>{
	
	@Transactional
	@Modifying
	@Query("update Compte c set c.solde = c.salaire+c.solde where c.id = ?1")
	public void updatSalaire(Long id);
	
	@Transactional
	@Modifying
	@Query("update Compte c set c.solde = ?2+c.solde where c.id = ?1")
	public void updatSolde(Long id,double value);
	
	@Query("select -SUM(d.somme) from Debit d")
	public double getSommeDebits();

	@Query("select -SUM(c.somme) from Credit c")
	public double getSommeCredits();

	
	//@Query("select SUM(c.somme) from Credit c")
	//double getSommeCredits();
}