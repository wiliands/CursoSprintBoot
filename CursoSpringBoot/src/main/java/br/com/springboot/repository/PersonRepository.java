package br.com.springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.springboot.data.entity.Person;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long>{

}
