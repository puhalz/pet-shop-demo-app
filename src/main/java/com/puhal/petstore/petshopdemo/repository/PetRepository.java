package com.puhal.petstore.petshopdemo.repository;

import com.puhal.petstore.petshopdemo.model.Pet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PetRepository extends JpaRepository<Pet, Long> {
}
