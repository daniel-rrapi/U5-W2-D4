package it.danielrrapi.U5W2D4.servicies;

import it.danielrrapi.U5W2D4.entities.Author;
import it.danielrrapi.U5W2D4.exceptions.NotFoundException;
import it.danielrrapi.U5W2D4.repositories.AuthorDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class AuthorService {
@Autowired
private AuthorDAO authorDAO;

    public Page<Author> getAuthors(int pageNumber, int size, String orderBy) {
        if(size > 100) size = 100;
        Pageable pageable = PageRequest.of(pageNumber, size, Sort.by(orderBy));
        return authorDAO.findAll(pageable);
    }
    public Author saveAuthor(Author newUser) {
       return authorDAO.save(newUser);
    }
    public Author findById(int id) {
        return authorDAO.findById(id).orElseThrow(() -> new NotFoundException(id));
    }
    public Author findByIdAndUpdate(int id, Author updateUser) {
        Author found = this.findById(id);
        found.setNome(updateUser.getNome());
        found.setCognome(updateUser.getCognome());
        found.setEmail(updateUser.getEmail());
        found.setDataDiNascita(updateUser.getDataDiNascita());
        return authorDAO.save(found);
    }
    public void findByIdAndDelete(int id) {
        Author found = this.findById(id);
        authorDAO.delete(found);
    }
}
