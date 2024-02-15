package it.danielrrapi.U5W2D4.controllers;

import it.danielrrapi.U5W2D4.entities.BlogPost;
import it.danielrrapi.U5W2D4.servicies.BlogPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/blog-posts")
public class BlogPostController {
    @Autowired
    private BlogPostService blogPostService;

    @GetMapping
    public Page<BlogPost> getAllBlogPosts(@RequestParam(defaultValue = "0") int page,
                                          @RequestParam(defaultValue = "10") int size,
                                          @RequestParam(defaultValue = "id") String orderBy
                                          ) {
        return this.blogPostService.getBlogPosts(page, size, orderBy);
    }

    @GetMapping("/{id}")
    public BlogPost getBlogPost(@PathVariable int id) {
        return this.getBlogPost(id);
    }

    @PostMapping
    public BlogPost addBlogPost(@RequestBody BlogPost newBlogPost) {
        return this.blogPostService.saveBlogPost(newBlogPost);
    }

    @PutMapping("/{id}")
    public BlogPost updateBlogPost(@PathVariable int id ,@RequestBody BlogPost newBlogPost) {
        return this.blogPostService.findByIdAndUpdate(id, newBlogPost);
    }

    @DeleteMapping("/{id}")
    public void deleteBlogPost(@PathVariable int id) {
        this.blogPostService.findByIdAndDelete(id);
    }
}
