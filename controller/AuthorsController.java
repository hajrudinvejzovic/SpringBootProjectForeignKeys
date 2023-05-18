package com.SpringProject.SpringBootProject.controller;

import com.SpringProject.SpringBootProject.entity.Authors;
import com.SpringProject.SpringBootProject.exception.ResourceNotFoundException;
import com.SpringProject.SpringBootProject.repository.AuthorsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/authors")
public class AuthorsController {
    @Autowired
    private AuthorsRepository authorsRepository;

    //GET ALL
    @GetMapping
    public List<Authors> allAuthors(){
        return this.authorsRepository.findAll();
    }

    @GetMapping("/{id}")
    public Authors authorById(@PathVariable (value = "id") long author_id){
    return this.authorsRepository.findById(author_id)
            .orElseThrow(()-> new ResourceNotFoundException("Author with this Id is NOT FOUND!" + author_id));
    }
    @PostMapping
    public Authors author(@RequestBody Authors author){
        return this.authorsRepository.save(author);
    }

    @PutMapping("/{id}")
    public Authors updateAuthor(@RequestBody Authors author, @PathVariable ( value = "id") long authorId){
        Authors existingAuthor = this.authorsRepository.findById(authorId)
                .orElseThrow(()-> new ResourceNotFoundException("Author with this Id NOT FOUND!" + authorId));
        existingAuthor.setName(existingAuthor.getName());
        existingAuthor.setSurname(existingAuthor.getSurname());
        existingAuthor.setBook_Authors(existingAuthor.getBook_Authors());
        existingAuthor.setBirth(existingAuthor.getBirth());
        existingAuthor.setCity(existingAuthor.getCity());
        return this.authorsRepository.save(existingAuthor);
    }
}
/*
    //update user -PUT
    @PutMapping("/{id}")
    public User updateUser(@RequestBody User user, @PathVariable("id") long userId){
        User existingUser = this.userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with this id: " + userId));
        existingUser.setname(existingUser.getname());
        existingUser.setname(existingUser.getname());
        existingUser.setEmail(user.getEmail());
        return this.userRepository.save(existingUser);

    }*/