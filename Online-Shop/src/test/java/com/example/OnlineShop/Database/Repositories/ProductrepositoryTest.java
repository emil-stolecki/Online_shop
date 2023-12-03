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

import com.example.OnlineShop.Database.Dtos.ProductLessDto;
import com.example.OnlineShop.Database.Models.ProductModel;
import com.example.OnlineShop.Database.Models.UserModel;

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
						.hasFieldOrPropertyWithValue("price", 229.0f)
						.hasFieldOrPropertyWithValue("amountInStock", 600);

						
	}
	@Test
	@Transactional
	public void findDtoByNameTest() {
		String name="Bluza";
		
		List<ProductLessDto> pList=prodRepo.findDtoByName(name);
		System.out.println(pList);
		assertThat(pList).isNotNull()
        				.isNotEmpty();
		
		for (ProductLessDto product : pList) {
            assertThat(product.name()).containsIgnoringCase(name);
            
        }
	}
	@Test
	@Transactional
	public void findDtoByCategoryTest() {
		String name="Dom";
		
		List<ProductLessDto> pList=prodRepo.findDtoByCategory(name);
		System.out.println(pList);
		assertThat(pList).isNotNull()
        				.isNotEmpty();
				
	}
	
	@Test
	@Transactional
	public void findDtoByNameAndPriceTest() {
		String name="rower";
		
		List<ProductLessDto> pList=prodRepo.findDtoByNameAndRange(name,500,800);
		System.out.println(pList);
		assertThat(pList).isNotNull()
        				.isNotEmpty();
		
		for (ProductLessDto product : pList) {
            assertThat(product.name()).containsIgnoringCase(name);
            
        }
	}
	@Test
	@Transactional
	public void findDtoByCategoryAndPriceTest() {
		String name="Edukacja";
		
		List<ProductLessDto> pList=prodRepo.findDtoByCategoryAndRange(name,30,100);
		System.out.println(pList);
		assertThat(pList).isNotNull()
        				.isNotEmpty();
				
	}
}



