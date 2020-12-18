package br.com.cin.sitcon.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.cin.sitcon.model.ItemNecessidadeEssencial;

public interface NecessidadeRepository extends JpaRepository<ItemNecessidadeEssencial, Integer> {

}
