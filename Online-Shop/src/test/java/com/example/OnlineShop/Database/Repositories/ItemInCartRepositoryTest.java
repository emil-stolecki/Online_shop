package com.example.OnlineShop.Database.Repositories;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.example.OnlineShop.Database.Dtos.ItemInCartDto;


import java.util.List;
import jakarta.transaction.Transactional;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Sql("/import.sql")
public class ItemInCartRepositoryTest {

	@Autowired
	private CartRepository cartRepo;
	
	@Test
	@Transactional
	public void findDtoById(){
		
		ItemInCartDto item =cartRepo.findDtoById(1L).orElse(null);
		
		assertThat(item).isNotNull()
						.hasFieldOrPropertyWithValue("productId", 1L)
						.hasFieldOrPropertyWithValue("productName", "Smartwatch elegancki");
	}
	
	@Test
	@Transactional
	public void findDtoByUser_id() {
		List<ItemInCartDto> items = cartRepo.findDtoByUser_id(1L);
		System.out.println(items);
		assertThat(items).isNotNull()
						.isNotEmpty();
		
		
	}
}
