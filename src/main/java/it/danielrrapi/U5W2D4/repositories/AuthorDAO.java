package it.danielrrapi.U5W2D4.repositories;

import it.danielrrapi.U5W2D4.entities.Author;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorDAO extends JpaRepository<Author, Integer> {
}
