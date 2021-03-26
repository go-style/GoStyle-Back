package fr.epsi.gostyle.controller;

import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.security.*;
import java.util.Optional;


import fr.epsi.gostyle.models.User;
import fr.epsi.gostyle.repository.UsersRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@CrossOrigin("*")
@RestController
@RequestMapping("/go")
public class UsersController {

    private UsersRepository usersRepository;


    public UsersController(UsersRepository usersRepository) throws NoSuchAlgorithmException {
        this.usersRepository = usersRepository;
    }

    //get all user
    @GetMapping("/users")
    public Iterable<User> getUsers() {return usersRepository.findAll();}


    @GetMapping("/user/{usermail}")

    public  Optional<User>  getUsers(@PathVariable(value = "usermail") String usermail, @RequestParam("mdp") String mdp ) throws NoSuchAlgorithmException {

        String mdpSalt = mdp+ User.SALT;
        byte[] byteChaine = mdpSalt.getBytes();
        MessageDigest md = MessageDigest.getInstance("MD5");
        byte[] hash = md.digest(byteChaine);
        String s = new String(hash, StandardCharsets.UTF_8);

        Optional<User> optionalUser =  usersRepository.findByUsermail(usermail);
        User foundUser = optionalUser.get();
        if(foundUser.getUserpassword().equals(s)){
            return usersRepository.findByUsermail(usermail);
        }else{
            return null;
        }

    }

    @PostMapping("/user")
    public ResponseEntity<Void> addUser(@Validated @RequestBody User user) {
        try {

            User userCreated = usersRepository.save(user);
            URI location = ServletUriComponentsBuilder
                    .fromCurrentRequestUri()
                    .path("/{usermail}")
                    .buildAndExpand(userCreated.getUsermail())
                    .toUri();
            return ResponseEntity.created(location).build();
        } catch (Exception exception) {
            LogError(exception);
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    String.format("User: %s", user.toString())
            );
        }
    }

    private void LogError(Exception exception) {
        System.err.printf("Error, Class=%s\n", this.getClass().getCanonicalName());
        exception.printStackTrace(System.err);

    }

}