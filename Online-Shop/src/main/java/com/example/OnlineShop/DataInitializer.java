package com.example.OnlineShop;

import java.util.ArrayList;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.OnlineShop.Database.Models.RoleModel;
import com.example.OnlineShop.Database.Models.UserModel;
import com.example.OnlineShop.Database.Repositories.CartRepository;
import com.example.OnlineShop.Database.Repositories.ProductRepository;
import com.example.OnlineShop.Database.Repositories.ReviewRepository;
import com.example.OnlineShop.Database.Repositories.RoleRepository;
import com.example.OnlineShop.Database.Repositories.UserRepository;
import com.example.OnlineShop.Objects.Objects.Cart;
import com.example.OnlineShop.Objects.Objects.Product;
import com.example.OnlineShop.Objects.Objects.Review;
import com.example.OnlineShop.Objects.Objects.User;

import jakarta.annotation.PostConstruct;
//@Component
public class DataInitializer {

	
	//for presentation purposes, the application needs some users
	//Adding them this way ensures that all fields are filled correctly as opposed to 
	//initiating from file.sql
	//products are initialized from file they don't have complicated constrains with other tables
	
    private final UserRepository userRepo;
    private final RoleRepository roleRepo;
    private final ReviewRepository reviewRepo;
    private final CartRepository cartRepo;
    private final ProductRepository productRepo;

    @Autowired
	public DataInitializer(UserRepository userRepo, RoleRepository roleRepo, ReviewRepository reviewRepo,
			CartRepository cartRepo, ProductRepository productRepo) {
		super();
		this.userRepo = userRepo;
		this.roleRepo = roleRepo;
		this.reviewRepo = reviewRepo;
		this.cartRepo = cartRepo;
		this.productRepo = productRepo;
	}
    
    
    @PostConstruct
    public void init() {
    	if (userRepo.count() == 0) {
    		
    		RoleModel role =roleRepo.findByName("USER").orElse(null);
    		
    		UserModel[] users= new UserModel[8];

    		//users[0]
    		
    		UserModel ania = new UserModel();
    		//40 years old 
    		//lives in a big city
    		//Works as a manager in a museum
    		//Mother of two kids, one 16 y.o, one 12 y.o.
    		//Loves art
    		
    		UserModel ola = new UserModel();
    		//28 years old 
    		//lives in a big city
    		//Works as a waitress in a famous restaurant
    		//Has 2 dogs
    		//Likes traveling and sports
    		UserModel maciek = new UserModel();
    		//22
    		//lives in a big city
    		//student of German philology
    		//Likes board games and the fantasy genre
    		
    		UserModel jan = new UserModel();
    		//lives in a smaller town
    		//55
    		//Construction worker
    		//has grandkids aged 4 month - 2 years
    		//has a cat
    		//interested in history
    		//account shared with his wife
    		UserModel wieslaw= new UserModel();
    		//lives is a smaller town
    		//66
    		//Pensioner
    		//grandkids ages 6-10
    		//likes cars
    		//account shared with his wife
    		
    		UserModel Ewa= new UserModel();
    		//lives is a smaller town
    		//30
    		//works as a cashier in a market chain
    		//has a son aged 4 and a cat
    		//Likes buying clothes
    		
    		UserModel weronika= new UserModel();
    		//lives in a village 
    		//18
    		//finishes high school soon
    		//has a dog, a cat and rats
    		//would like to become a psychologist in the future
    		//likes traveling and singing
    		
    		UserModel magda= new UserModel();
    		//lives in a village 
    		//32
    		//Java developer
    		//has 2 cats and 2 kids aged 4-8
    		//loves cooking an reading
    		
    		UserModel bartek= new UserModel();
    		//lives in a village
    		//24
    		//runs a small bike service business
    		//has a 1 year old baby
    		//likes gardening
    		
    		
    		userRepo.saveAll(Arrays.asList(users));
    		
    	}
    }




  
    
    
    
}
