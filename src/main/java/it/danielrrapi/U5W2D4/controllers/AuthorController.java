package it.danielrrapi.U5W2D4.controllers;

import it.danielrrapi.U5W2D4.entities.Author;
import it.danielrrapi.U5W2D4.servicies.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/authors")
public class AuthorController {
    @Autowired
    private AuthorService authorService;

    @GetMapping
    public Page<Author> getAllAuthors(@RequestParam(defaultValue = "0") int page,
                                      @RequestParam(defaultValue = "10") int size,
                                      @RequestParam(defaultValue = "id") String orderBy
                                      ) {
        return this.authorService.getAuthors(page, size, orderBy);
    }

    @GetMapping("/{id}")
    public Author findById(@PathVariable int id) {
        return this.authorService.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Author saveAuthor(@RequestBody Author author) {
        return this.authorService.saveAuthor(author);
    }

    @PutMapping("/{id}")
    public void findByIdAndUpdate(@PathVariable int id, @RequestBody Author author) { this.authorService.findByIdAndUpdate(id, author);}

    @DeleteMapping("/{id}")
    public void findByIdAndDelete(@PathVariable int id) { this.authorService.findByIdAndDelete(id);}

}
