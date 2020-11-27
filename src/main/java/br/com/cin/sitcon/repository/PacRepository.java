package br.com.cin.sitcon.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.cin.sitcon.model.Pac;

@Repository
public interface PacRepository extends JpaRepository<Pac,Long>{

}
