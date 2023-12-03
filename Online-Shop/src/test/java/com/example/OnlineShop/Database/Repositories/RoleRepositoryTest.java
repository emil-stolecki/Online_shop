package com.example.OnlineShop.Database.Repositories;

import static org.assertj.core.api.Assertions.assertThat;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.example.OnlineShop.Database.Models.RoleModel;

import jakarta.transaction.Transactional;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)

public class RoleRepositoryTest {

	
	@Autowired
	private RoleRepository roleRepo;
	
	
	@Test
	@Transactional
	public void userTest() {
		String name = "USER";
		RoleModel role = roleRepo.findByName(name).orElse(null);
		assertThat(role).isNotNull()
						.hasFieldOrPropertyWithValue("name", "USER");
	}
	@Test
	@Transactional
	public void adminTest() {
		String name = "ADMIN";
		RoleModel role = roleRepo.findById(3L).orElse(null);
		assertThat(role).isNotNull()
						.hasFieldOrPropertyWithValue("name", "ADMIN");
	}

}
