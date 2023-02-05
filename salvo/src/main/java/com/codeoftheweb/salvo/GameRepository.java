package com.codeoftheweb.salvo;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

//This code is a repository for the Game class, using the Spring Data JPA framework.

@RepositoryRestResource
public interface GameRepository extends JpaRepository<Game, Long> {
    //The @RepositoryRestResource annotation is used to expose the GameRepository as a RESTful resource.
    //The interface extends the JpaRepository class, providing CRUD functionality for the Game class.
}
