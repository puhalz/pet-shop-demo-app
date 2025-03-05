package com.puhal.petstore.petshopdemo.services;

import com.puhal.petstore.petshopdemo.model.Category;
import com.puhal.petstore.petshopdemo.model.Pet;
import com.puhal.petstore.petshopdemo.repository.CategoryRepository;
import com.puhal.petstore.petshopdemo.repository.PetRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor // Lombok annotation for constructor injection
@Transactional
public class PetService {

    private final PetRepository petRepository;
    private final CategoryRepository categoryRepository;

    public Pet savePet(Pet pet) {
        if (pet.getCategory() != null) {
            Category category = pet.getCategory();

            if (category.getId() == null) {
                // Save the category first if it's new
                category = categoryRepository.save(category);
            } else {
                // Ensure category exists in DB
                category = categoryRepository.findById(category.getId())
                        .orElseThrow(() -> new RuntimeException("Category not found"));
            }

            pet.setCategory(category); // Assign managed category to Pet
        } else {
            throw new RuntimeException("Category cannot be null");
        }

        return petRepository.save(pet);
    }

    public List<Pet> getAllPets() {
        return petRepository.findAll();
    }
}

