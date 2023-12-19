package com.example.OnlineShop;

import java.util.ArrayList;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
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
@Component
public class DataInitializer {

	
	//for presentation purposes, the application needs some users
	//Adding them this way ensures that all fields are filled correctly
	
    private final UserRepository userRepo;
    private final RoleRepository roleRepo;
    private final ReviewRepository reviewRepo;
    private final CartRepository cartRepo;
    private final ProductRepository productRepo;
    private final PasswordEncoder encoder;

    @Autowired
	public DataInitializer(UserRepository userRepo, RoleRepository roleRepo, ReviewRepository reviewRepo,
			CartRepository cartRepo, ProductRepository productRepo,PasswordEncoder encoder) {
		super();
		this.userRepo = userRepo;
		this.roleRepo = roleRepo;
		this.reviewRepo = reviewRepo;
		this.cartRepo = cartRepo;
		this.productRepo = productRepo;
		this.encoder=encoder;
	}
    
    
    @PostConstruct
    public void init() {
    	if (userRepo.count() == 0) {
    		
    		RoleModel role =roleRepo.findByName("USER").orElse(null);
    		
    		UserModel[] users= new UserModel[9];
    		users[0] = new UserModel();
    		users[0].setFirstName("Anna");
    		users[0].setLastName("Dąbrowska");
    		users[0].setLogin("ania_33");
    		users[0].setEmail("annadabrowska@wp.pl");
    		users[0].setEncryptedPassword(encoder.encode("1234AAaa_"));
    		users[0].setRole(role);
    		//40 years old 
    		//lives in a big city
    		//Works as a manager in a museum
    		//Mother of two kids, one 16 y.o, one 12 y.o.
    		//Loves art
    		
    		users[1] = new UserModel();
    		users[1].setFirstName("Aleksandra");
    		users[1].setLastName("Wiśniewska");
    		users[1].setLogin("olkka7");
    		users[1].setEmail("aleksandra@gmail.com");
    		users[1].setEncryptedPassword(encoder.encode("1234BBbb_"));
    		users[1].setRole(role);
    		//28 years old 
    		//lives in a big city
    		//Works as a waitress in a famous restaurant
    		//Has 2 dogs
    		//Likes traveling and sports
    		
    		
    		users[2] = new UserModel();
    		users[2].setFirstName("Maciej");
    		users[2].setLastName("Kowalski");
    		users[2].setLogin("macX420");
    		users[2].setEmail("maciejkowalski@gmail.com");
    		users[2].setEncryptedPassword(encoder.encode("1234CCcc_"));
    		users[2].setRole(role);
    		//22
    		//lives in a big city
    		//student of German philology
    		//Likes board games and the fantasy genre
    		
    		users[3] = new UserModel();
    		users[3].setFirstName("Jan");
    		users[3].setLastName("Nowak");
    		users[3].setLogin("nowak");
    		users[3].setEmail("jannowak@onet.pl");
    		users[3].setEncryptedPassword(encoder.encode("1234DDdd_"));
    		users[3].setRole(role);
    		//lives in a smaller town
    		//55
    		//Construction worker
    		//has grandkids aged 4 month - 2 years
    		//has a cat
    		//interested in history
    		//account shared with his wife
    		
    		
    		
    		users[4] = new UserModel();
    		users[4].setFirstName("Wiesław");
    		users[4].setLastName("Kamiński");
    		users[4].setLogin("wieslaw");
    		users[4].setEmail("kaminski@onet.pl");
    		users[4].setEncryptedPassword(encoder.encode("1234JJjj_"));
    		users[4].setRole(role);
    		//lives is a smaller town
    		//66
    		//Pensioner
    		//grandkids ages 6-10
    		//likes cars
    		//account shared with his wife
    		
    		users[5] = new UserModel();
    		users[5].setFirstName("Ewa");
    		users[5].setLastName("Kowalczyk");
    		users[5].setLogin("ewcia1234");
    		users[5].setEmail("ewa2@o2.pl");
    		users[5].setEncryptedPassword(encoder.encode("1234EEee_"));
    		users[5].setRole(role);
    		//lives is a smaller town
    		//30
    		//works as a cashier in a market chain
    		//has a son aged 4 and a cat
    		//Likes buying clothes
    		
    		users[6] = new UserModel();
    		users[6].setFirstName("Weronika");
    		users[6].setLastName("Szymańska");
    		users[6].setLogin("verqa");
    		users[6].setEmail("wera@gmail.com");
    		users[6].setEncryptedPassword(encoder.encode("1234FFff_"));
    		users[6].setRole(role);
    		//lives in a village 
    		//18
    		//finishes high school soon
    		//has a dog, a cat and rats
    		//would like to become a psychologist in the future
    		//likes traveling and singing
    		
    		users[7] = new UserModel();
    		users[7].setFirstName("Magdalena");
    		users[7].setLastName("Wozniak");
    		users[7].setLogin("magda8");
    		users[7].setEmail("mwozniak@gmail.com");
    		users[7].setEncryptedPassword(encoder.encode("1234GGgg_"));
    		users[7].setRole(role);
    		//lives in a village 
    		//32
    		//Java developer
    		//has 2 cats and 2 kids aged 4-8
    		//loves cooking an reading
    		
    		users[8] = new UserModel();
    		users[8].setFirstName("Bartosz");
    		users[8].setLastName("Zieliński");
    		users[8].setLogin("bartek");
    		users[8].setEmail("bartek@gmail.com");
    		users[8].setEncryptedPassword(encoder.encode("1234HHhh_"));
    		users[8].setRole(role);
    		//lives in a village
    		//24
    		//runs a small bike service business
    		//has a 1 year old baby
    		//likes gardening
    		
    		
    		userRepo.saveAll(Arrays.asList(users));
    		
    	}
    }




  
    
    
    
}
