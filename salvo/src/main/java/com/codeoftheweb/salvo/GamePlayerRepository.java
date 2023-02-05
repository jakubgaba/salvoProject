package com.codeoftheweb.salvo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource //annotation to create a RESTful repository, which is a way to expose entities through a RESTful API
public interface GamePlayerRepository extends JpaRepository<GamePlayer, Long> {
    // This interface extends JpaRepository, which provides basic CRUD operations for an entity of type GamePlayer with a primary key of type Long
}
