package it.danielrrapi.U5W2D4.servicies;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import it.danielrrapi.U5W2D4.config.ServerConfig;
import it.danielrrapi.U5W2D4.entities.Author;
import it.danielrrapi.U5W2D4.exceptions.BadRequestException;
import it.danielrrapi.U5W2D4.exceptions.NotFoundException;
import it.danielrrapi.U5W2D4.payloads.NewAuthorDTO;
import it.danielrrapi.U5W2D4.repositories.AuthorDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public class AuthorService {
@Autowired
private AuthorDAO authorDAO;
@Autowired
private Cloudinary cloudinaryUploader;

    public Page<Author> getAuthors(int pageNumber, int size, String orderBy) {
        if(size > 100) size = 100;
        Pageable pageable = PageRequest.of(pageNumber, size, Sort.by(orderBy));
        return authorDAO.findAll(pageable);
    }
    public Author saveAuthor(NewAuthorDTO payload) {
        // Verifico se l'email è già in uso
        authorDAO.findByEmail(payload.email()).ifPresent(author -> {
            throw new BadRequestException("L'email " + payload.email() + " è gia in uso");
        });

        // Creo l'autore tramite il payload
        Author newAuthor = new Author(payload.nome(), payload.cognome(), payload.email(), payload.dob());
        return authorDAO.save(newAuthor);
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
    public String uploadImage(MultipartFile image, int id) throws IOException {
        String url = (String) cloudinaryUploader.uploader().upload(image.getBytes(), ObjectUtils.emptyMap()).get("url");
        Author author = this.findById(id);
        author.setAvatar(url);
        this.findByIdAndUpdate(id, author);
        return url;
    }
}
