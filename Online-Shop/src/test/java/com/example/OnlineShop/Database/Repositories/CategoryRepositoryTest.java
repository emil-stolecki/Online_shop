package com.example.OnlineShop.Database.Repositories;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;


import com.example.OnlineShop.Database.Models.CategoryModel;


@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ExtendWith(SpringExtension.class)
@DataJpaTest
public class CategoryRepositoryTest {

	@Autowired
	public CategoryRepository catRepo;
	
	@Test
	
	public void findByNameTest() {
		String name = "Edukacja";
		Optional<CategoryModel> optionalCat = catRepo.findByName(name);
		CategoryModel cat = optionalCat.orElseThrow();
		assertThat(cat).isNotNull()
					   .hasFieldOrPropertyWithValue("name", "Edukacja")
					   .hasFieldOrPropertyWithValue("id", 9L);
	}
	@Test
	
	public void findByIdTest() {
		Long id = 5L;
		Optional<CategoryModel> optionalCat = catRepo.findById(id);
		CategoryModel cat = optionalCat.orElseThrow();
		assertThat(cat).isNotNull()
					   .hasFieldOrPropertyWithValue("name", "Rozrywka")
					   .hasFieldOrPropertyWithValue("id", 5L);
	}
}
