package com.example.OnlineShop.Database.Repositories;

import static org.assertj.core.api.Assertions.assertThat;


import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.data.domain.PageRequest;

import com.example.OnlineShop.Database.Dtos.ProductDto;
import com.example.OnlineShop.Database.Dtos.ProductLessDto;
import com.example.OnlineShop.Database.Models.ProductModel;
import com.example.OnlineShop.Database.Models.UserModel;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageRequest;

import jakarta.transaction.Transactional;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class ProductrepositoryTest {

	
	@Autowired
	private ProductRepository prodRepo;
	
	@Test
	@Transactional
	public void findByIdTest() {
		ProductModel product =prodRepo.findById(10L).orElse(null);
		assertThat(product).isNotNull()
						.hasFieldOrPropertyWithValue("name", "Słuchawki Gamingowe SENNHEISER")
						.hasFieldOrPropertyWithValue("seller", "ITX24")
						.hasFieldOrPropertyWithValue("price", 229.0)
						.hasFieldOrPropertyWithValue("amountInStock", 600);

						
	}
	
	
	@Test
	@Transactional
	public void findDtoByFilterTest() {
		String name="rower";
		String category="%";//match all
		Pageable pageable = PageRequest.of(0, 10);
		List<ProductLessDto> pList=prodRepo.findDtoByFilter(name,category,500d,800d,pageable);
		System.out.println(pList);
		assertThat(pList).isNotNull()
        				.isNotEmpty();
		
		for (ProductLessDto product : pList) {
            assertThat(product.name()).containsIgnoringCase(name);
            
        }
	}
}



