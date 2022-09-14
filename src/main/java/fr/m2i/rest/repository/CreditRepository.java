package fr.m2i.rest.repository;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import fr.m2i.rest.models.Compte;
import fr.m2i.rest.models.Credit;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

@Repository
public interface CreditRepository extends JpaRepository<Credit,Double>{
	
}