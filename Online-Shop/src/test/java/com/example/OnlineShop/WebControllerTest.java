package com.example.OnlineShop;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

import com.example.OnlineShop.Database.Dtos.ItemInCartDto;
import com.example.OnlineShop.Database.Dtos.ReviewDto;
import com.example.OnlineShop.Database.Dtos.UserDto;
import com.example.OnlineShop.Database.Dtos.UserRegistrationDto;
import com.example.OnlineShop.Database.Dtos.User_productDto;
import com.example.OnlineShop.Other.Filter;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.transaction.Transactional;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Sql("/import.sql")
public class WebControllerTest {

	
	
	@Autowired
    private MockMvc mockMvc;
	@Autowired
    private ObjectMapper objectMapper;
	
	@Test
    public void homeTest() throws Exception {
	
		String responseContent=mockMvc.perform(MockMvcRequestBuilders.get("/home"))
        .andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andReturn().getResponse().getContentAsString();
		
		System.out.println("/home: "+responseContent);
        
        
    }
	
	@Test
    public void emptyFilterTest() throws Exception {
		
		Filter filter = new Filter();//empty filter		
		String responseContent=mockMvc.perform(MockMvcRequestBuilders.post("/filter")
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(filter)))
					
        .andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andReturn().getResponse().getContentAsString();
		
		System.out.println("EMPTY /filter: "+responseContent);
        
        
    }

	
	@Test
    public void filterTest() throws Exception {
		
		Filter filter = new Filter();	
		
	
		filter.setMinPrice(50d);
		filter.setMaxPrice(100d);
		filter.setLimit(5);
		String responseContent=mockMvc.perform(MockMvcRequestBuilders.post("/filter")
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(filter)))
					
        .andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andReturn().getResponse().getContentAsString();
		
		System.out.println("/filter: "+responseContent);
             
    }
	
	@Test
	@Transactional
	public void registerTest() throws Exception{
		UserRegistrationDto newUser=new UserRegistrationDto("GoodLogin","Valid@email.com","ExampleName","OrdinarySurname","Strong_Password1","Strong_Password1");
		String responseContent=mockMvc.perform(MockMvcRequestBuilders.post("/register")
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(newUser)))
					
        .andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andReturn().getResponse().getContentAsString();
		
		System.out.println("/register: "+responseContent);
	}
	
	@Test
	public void getProductTest() throws Exception{
		User_productDto up = new User_productDto(1L,1L);
		
		
		String responseContent=mockMvc.perform(MockMvcRequestBuilders.post("/product")
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(up)))
					
        .andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andReturn().getResponse().getContentAsString();
		
		System.out.println("/product: "+responseContent);
	}
	
	@Test
	@Transactional
	public void addtocart() throws Exception{
		
		Long[] ids=new Long[] {3L,10L};//user id, product id
		
		String responseContent=mockMvc.perform(MockMvcRequestBuilders.post("/product/add-to-cart")
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(ids)))
					
        .andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andReturn().getResponse().getContentAsString();
		
		System.out.println("/product/add-to-cart: "+responseContent);
	}
	
	@Test
	@Transactional
	public void addReview() throws Exception{
		
		ReviewDto rev = new ReviewDto.Builder()
				.userId(1L)
				.product(30L)
				.rating(8)
				.content("very good")
				.build();
		
		
		String responseContent=mockMvc.perform(MockMvcRequestBuilders.post("/product/review")
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(rev)))
					
        .andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andReturn().getResponse().getContentAsString();
		
		System.out.println("/product/review: "+responseContent);
	}
	
	@Test
	@Transactional
	public void editReview() throws Exception{
		
		ReviewDto rev = new ReviewDto.Builder()
				.id(1L)
				.userId(3L)
				.product(30L)
				.rating(8)
				.content("very good")
				.build();
		
		
		String responseContent=mockMvc.perform(MockMvcRequestBuilders.post("/product/review/edit")
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(rev)))
					
        .andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andReturn().getResponse().getContentAsString();
		
		System.out.println("/product/review/edit: "+responseContent);
	}
	
	@Test
	@Transactional
	public void deleteReview() throws Exception{

		String responseContent=mockMvc.perform(MockMvcRequestBuilders.post("/product/review/delete")
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(1L)))
					
        .andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andReturn().getResponse().getContentAsString();
		
		System.out.println("/product/review/delete: "+responseContent);
	}
	
	@Test
	@Transactional
	public void getProfileTest() throws Exception{

		String responseContent=mockMvc.perform(MockMvcRequestBuilders.post("/profile")
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(1L)))
					
        .andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andReturn().getResponse().getContentAsString();
		
		System.out.println("/profile: "+responseContent);
	}
	
	
	@Test
	@Transactional
	public void updateProfileTest() throws Exception{
		UserDto userDto = new UserDto(1L,null,null,"NewName",null,null,null,null);
		String responseContent=mockMvc.perform(MockMvcRequestBuilders.post("/profile/edit")
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(userDto)))
					
        .andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andReturn().getResponse().getContentAsString();
		
		System.out.println("/profile/edit: "+responseContent);
	}
	
	@Test
	@Transactional
	public void deleteProfileTest() throws Exception{
		String responseContent=mockMvc.perform(MockMvcRequestBuilders.post("/profile/delete")
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(1L)))
					
        .andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andReturn().getResponse().getContentAsString();
		
		System.out.println("/profile/delete: "+responseContent);
	}
	@Test
	@Transactional
	public void getItemsInCartTest() throws Exception{
		
		String responseContent=mockMvc.perform(MockMvcRequestBuilders.post("/cart")
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(2L)))
					
        .andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andReturn().getResponse().getContentAsString();
		
		System.out.println("/cart: "+responseContent);
	}
	@Test
	@Transactional
	public void updateProductAmountInCartTest() throws Exception{
		ItemInCartDto item=new ItemInCartDto(1L,10L,"",3,0d);
		mockMvc.perform(MockMvcRequestBuilders.post("/product/update-amount")
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(item)))
					
        .andExpect(status().isOk());
        
	}
	@Test
	@Transactional
	public void removeAllFromCartTest() throws Exception{

		String responseContent=mockMvc.perform(MockMvcRequestBuilders.post("/product/remove-all")
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(1L)))
					
        .andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andReturn().getResponse().getContentAsString();
		
		System.out.println("/product/remove-all: "+responseContent);
	}
	@Test
	@Transactional
	public void removeProductFromCartTest() throws Exception{

		String responseContent=mockMvc.perform(MockMvcRequestBuilders.post("/product/remove")
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(2L)))
					
        .andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andReturn().getResponse().getContentAsString();
		
		System.out.println("/product/remove: "+responseContent);
	}
}
