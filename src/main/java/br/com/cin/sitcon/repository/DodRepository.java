package br.com.cin.sitcon.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.cin.sitcon.model.Demanda;


public interface DodRepository extends JpaRepository<Demanda, Integer> {

}
