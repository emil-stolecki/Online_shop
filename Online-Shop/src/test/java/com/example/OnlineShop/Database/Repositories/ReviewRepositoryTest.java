package com.example.OnlineShop.Database.Repositories;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.example.OnlineShop.Database.Dtos.ReviewDto;

import jakarta.transaction.Transactional;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Sql("/import.sql")
public class ReviewRepositoryTest {

	@Autowired
	private ReviewRepository reviewRepo;
	
	
	@Test
	@Transactional
	public void findDtoByUser_idTest() {
		List<ReviewDto> reviews = reviewRepo.findDtoByUser_id(1L);
		System.out.println(reviews);
		assertThat(reviews).isNotNull()
							.isNotEmpty();
		
	}
	@Test
	@Transactional
	public void findDtoByProduct_idTest() {
		List<ReviewDto> reviews = reviewRepo.findDtoByProduct_id(1L);
		System.out.println(reviews);
		assertThat(reviews).isNotNull()
							.isNotEmpty();
	}
	@Test
	@Transactional
	public void findDtoByProduct_idAndRatingBetweenTest() {
		List<ReviewDto> reviews = reviewRepo.findDtoByProduct_idAndRatingBetween(1L, 3, 4);
		System.out.println(reviews);
		assertThat(reviews).isNotNull()
							.isNotEmpty();
	}
}
