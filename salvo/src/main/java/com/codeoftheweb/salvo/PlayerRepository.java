package com.codeoftheweb.salvo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

//This is an interface that extends JpaRepository which is a Spring data interface for CRUD operations
//on a repository for a specific type
@RepositoryRestResource
public interface PlayerRepository extends JpaRepository<Player, Long> {
}
