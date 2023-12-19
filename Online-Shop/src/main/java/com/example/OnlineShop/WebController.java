package com.example.OnlineShop;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.OnlineShop.Database.Dtos.AuthenticatedUserDto;
import com.example.OnlineShop.Database.Dtos.CategoryDto;
import com.example.OnlineShop.Database.Dtos.ItemInCartDto;
import com.example.OnlineShop.Database.Dtos.NewReviewDto;
import com.example.OnlineShop.Database.Dtos.PasswordDto;
import com.example.OnlineShop.Database.Dtos.ProductDto;
import com.example.OnlineShop.Database.Dtos.ProductLessDto;
import com.example.OnlineShop.Database.Dtos.Product_amount;
import com.example.OnlineShop.Database.Dtos.ReviewDto;
import com.example.OnlineShop.Database.Dtos.UserCheckLoginDto;
import com.example.OnlineShop.Database.Dtos.UserDto;
import com.example.OnlineShop.Database.Dtos.UserLoginInputDto;
import com.example.OnlineShop.Database.Dtos.UserRegistrationDto;
import com.example.OnlineShop.Database.Models.CategoryModel;
import com.example.OnlineShop.Database.Models.UserModel;
import com.example.OnlineShop.Database.Repositories.UserRepository;
import com.example.OnlineShop.Objects.Objects.Cart;
import com.example.OnlineShop.Objects.Objects.Product;
import com.example.OnlineShop.Objects.Objects.Review;
import com.example.OnlineShop.Objects.Objects.User;
import com.example.OnlineShop.Other.Filter;
import com.example.OnlineShop.Other.Loginresponse;
import com.example.OnlineShop.Other.SimpleResponse;
import com.example.OnlineShop.SecurityConfig.UserPrincipal;
import com.example.OnlineShop.SecurityConfig.JWT.JWTdecoder;
import com.example.OnlineShop.SecurityConfig.JWT.JWTissuer;
import com.example.OnlineShop.Kafka.Message.Message;

@RestController
@CrossOrigin(origins = "http://localhost:3000",allowCredentials="true")
public class WebController {

	
	
	private final User user;
    private final Product product;
    private final Cart cart;
    private final Review review;
    private final AuthenticationManager authManager;
    private final KafkaTemplate<Long, Message> kafkatemplate;
	private final String topic;
	private final JWTissuer jwtIssuer;
	private final JWTdecoder decoder;

	
	@Autowired
	public WebController (User user,Product product,Cart cart,Review review,
			KafkaTemplate<Long, Message> kafkatemplate,
			AuthenticationManager authManager, JWTissuer jwtIssuer,JWTdecoder decoder) {
			
		this.user=user;
		this.product=product;
		this.cart=cart;
		this.review=review;
		this.kafkatemplate=kafkatemplate;
		this.topic="user-activity";
		this.authManager=authManager;
		this.jwtIssuer=jwtIssuer;
		this.decoder=decoder;
		
		
	}
	
	@PostMapping("/login")
	public ResponseEntity<Loginresponse> login(@RequestBody UserLoginInputDto input) {
		

		Boolean success =null;		
		var token="";
		Long id=null;
		try {

			 var authentication = authManager.authenticate(
				new UsernamePasswordAuthenticationToken(input.login(),input.password())				
				);			
			 	AuthenticatedUserDto authUser = user.getAuthenticatedUser(authentication.getName());
			 	success = true;
			 	SecurityContextHolder.getContext().setAuthentication(authentication);
			 	var principal = (UserPrincipal) authentication.getPrincipal();
			 	System.out.println(principal.getId());
			 	
			 	token =jwtIssuer.issue(
			 			principal.getId(),
			 			principal.getUsername(),
			 			principal.getAuthorities());
			 	id=principal.getId();
		}catch(Exception e) {
			success=false;
			e.printStackTrace();
		}
		Loginresponse r = new Loginresponse(success,token,id);
		return ResponseEntity.ok(r);
		
	}
	
	
	
	@GetMapping("/home")
	public ResponseEntity<List<ProductLessDto>> home() {
		
		
		List<ProductLessDto> popularProducts = product.getPopular();		
		return ResponseEntity.ok(popularProducts);
	}
	@GetMapping("/categories")
	public ResponseEntity<List<CategoryDto>> getCategories(){
		
		List<CategoryDto> list=product.getCategories();
		return ResponseEntity.ok(list);		
	}

	@PostMapping("/filter")
	public ResponseEntity<List<ProductLessDto>> applyfilter(@RequestBody Filter filter) {
		
		List<ProductLessDto> filteredProducts = product.getByCategoryandFilter(filter);
		
		
		return ResponseEntity.ok(filteredProducts);
	}
	@PostMapping("/filter/count")
	public ResponseEntity<Long> getfilterCount(@RequestBody Filter filter) {
		
		Long count = product.getByCategoryandFilterCount(filter);
		
		
		
		return ResponseEntity.ok(count);
	}
	@PostMapping("/register")
	public ResponseEntity<SimpleResponse> register(@RequestBody UserRegistrationDto newUser) {
		
		String message="";
		boolean success=false;
		if(validateLogin(newUser.login()) && validateEmail(newUser.email())&& validatePassword(newUser.password())) {
			if(newUser.password().equals(newUser.password2())){
				try {
				this.user.register(newUser);
				success=true;
				}catch(Exception e){								
					message="login or Email already in use";			
				}
					message="account created";
			}
			else {
				message="Passwords doesn't match";
			}		
		}
		else {
			message="empty or invalid input";	
		}
		
		SimpleResponse r = new SimpleResponse(success,message);
		
		return ResponseEntity.ok(r);
	}
	
	
	@PostMapping("/product")
	public ResponseEntity<ProductDto> getProductDetails(@RequestBody long productId) {
		
		ProductDto p= product.getById(productId);
		List<String> categories = new ArrayList<String>();
		for (CategoryModel c:p.categories()) {
			categories.add(c.getName());
		}
		System.out.println(p.reviews());
	
		Message message= new Message.Builder()
				.productId(productId)
				.price(p.price())
				.categories(categories)
				.amountOfVisits(1L)
				.build();
		
		//kafkatemplate.send(topic,up.userId(),message);
		
		return ResponseEntity.ok(p);
	}
	
	@PostMapping("/cart/add-product")
	public ResponseEntity<SimpleResponse> addproductToCart(@RequestBody Product_amount pa) {
		String message="";
		boolean success=false;
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (authentication != null && authentication.isAuthenticated()) {	
		   var principal = (UserPrincipal) authentication.getPrincipal();
		   Long userId = principal.getId();
		   	   
		   success=cart.addToCart( userId,pa.productId(),pa.amount());
		}
		if (success) message="ittem added to cart";
		else message="error occured while adding ittem to cart";
		
		SimpleResponse r = new SimpleResponse(success,message);		
		return ResponseEntity.ok(r);
	}
	@PostMapping("/cart/update-product-amount")
	public ResponseEntity<Void> updateProductAmountInCart(@RequestBody Product_amount pa ){	
		cart.updateAmount(pa.productId(),pa.amount());	
		return ResponseEntity.ok().build();
	}
	@PostMapping("/cart/remove-product")
	public ResponseEntity<SimpleResponse> removeProductFromCart(@RequestBody Long id) {	
		
		String message="";
		boolean success=false;
		
		success=cart.removeFromCart(id);
		
		if (success) message="ittem removed";
		else message="error occured while removing ittem from cart";
		SimpleResponse r = new SimpleResponse(success,message);		
		return ResponseEntity.ok(r);
	}
	
	@PostMapping("/product/review-add")
	public ResponseEntity<ReviewDto> addProductReview(@RequestBody NewReviewDto newReview) {
		

		ReviewDto addedreview=null;
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (authentication != null && authentication.isAuthenticated()) {	
		   var principal = (UserPrincipal) authentication.getPrincipal();
		   Long userId = principal.getId();
		   ReviewDto rev=new ReviewDto.Builder()
					.userId(userId)
					.productId(newReview.productId())
					.rating(newReview.rating())
					.content(newReview.content())
					.build();
		   addedreview = review.addReview(rev);   

		}
	
		return ResponseEntity.ok(addedreview);
	}
	@PostMapping("/product/review/edit")
	public ResponseEntity<SimpleResponse> editProductReview(@RequestBody ReviewDto reviewDto) {
		
		String message="";
		boolean success=false;		
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		
		if (authentication != null && authentication.isAuthenticated()) {	
			
			var principal = (UserPrincipal) authentication.getPrincipal();
			   Long userId = principal.getId();
			   ReviewDto rev=new ReviewDto.Builder()
					    .id(reviewDto.id())
						.userId(userId)
						.productId(reviewDto.productId())
						.rating(reviewDto.rating())
						.content(reviewDto.content())
						.build();
			
			
			success = review.editReview(rev);
		}
		
		
		if (success) message="review edited";
		else message="something went wrong";
		SimpleResponse r = new SimpleResponse(success,message);	
		
		return ResponseEntity.ok(r);
	}
	@PostMapping("/product/review/delete")
	public ResponseEntity<SimpleResponse> deleteProductReview(@RequestBody Long id) {
		String message="";
		boolean success=false;
		
		success = review.removeReview(id);
		
		if (success) message="review deleted";
		else message="something went wrong";
		SimpleResponse r = new SimpleResponse(success,message);	
		
		return ResponseEntity.ok(r);
	}
	
	@GetMapping("/profile")
	public ResponseEntity<UserDto> getUserprofile() {
		UserDto profile = null;
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (authentication != null && authentication.isAuthenticated()) {		   
		   var principal = (UserPrincipal) authentication.getPrincipal();
		   Long userId = principal.getId();
		   profile = user.getUserDetails(userId);
		 }
		return ResponseEntity.ok(profile);
	}
	@PostMapping("/profile/edit")
	public ResponseEntity<SimpleResponse>  editProfile(@RequestBody UserDto userDto) {
		String message="";
		boolean success=false;
		
		if(validateLogin(userDto.login()) && validateEmail(userDto.email())) {
			try {
			success = user.updateProfile(userDto);
			}
			catch(Exception e) {
				
			}
		}
			
		if (success) message="profile updated";
		else message="something went wrong";
		SimpleResponse r = new SimpleResponse(success,message);	
		
		return ResponseEntity.ok(r);
	}
	
	@PostMapping("/profile/change-password")
	public ResponseEntity<SimpleResponse>  changePassword(@RequestBody PasswordDto passwords) {
		String message="";
		boolean success=false;

		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (authentication != null && authentication.isAuthenticated()) {		   
		   var principal = (UserPrincipal) authentication.getPrincipal();
		   Long userId = principal.getId();
		   
		   if(validatePassword(passwords.password())) {
				if(passwords.password().equals(passwords.password2())){
					success = user.changePassword(passwords.password(), userId);
				}
			}
  
		 }
			
		
		if (success) message="password changed";
		else message="something went wrong";
		SimpleResponse r = new SimpleResponse(success,message);	
		
		return ResponseEntity.ok(r);
	}
	@GetMapping("/profile/delete")
	public ResponseEntity<SimpleResponse> deleteProfile() {

		String message="";
		boolean success=false;
		
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (authentication != null && authentication.isAuthenticated()) {		   
		   var principal = (UserPrincipal) authentication.getPrincipal();
		   Long userId = principal.getId();
		   try {
		   user.deleteprofile(userId);
		   success=true;
		   }
		   catch(Exception e) {
			   
		   }
		}
		
		if (success) message="profile deleted";
		else message="something went wrong";
		SimpleResponse r = new SimpleResponse(success,message);	
		
		return ResponseEntity.ok(r);	
		
	}
	
	
	@GetMapping("/cart")
	public ResponseEntity<List<ItemInCartDto>> getUsersCart() {

		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		List<ItemInCartDto> items=null;
		if (authentication != null && authentication.isAuthenticated()) {		   
		   var principal = (UserPrincipal) authentication.getPrincipal();
		   Long userId = principal.getId();
		   items=cart.getProducts(userId);	
		 }
	
		return ResponseEntity.ok(items);
	}
	
	@GetMapping("/checkout")
	public ResponseEntity<SimpleResponse> getPayment() {
		String message="";
		boolean success=false;
		//We assume the user bought all products in cart
		
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		List<ItemInCartDto> items=null;
		if (authentication != null && authentication.isAuthenticated()) {		   
		   var principal = (UserPrincipal) authentication.getPrincipal();
		   Long userId = principal.getId();
		   
		   items=cart.getProducts(userId);
		   
		   for(ItemInCartDto i : items) {
			   
				Message log= new Message.Builder()
						.productId(i.id())
						.price(i.price())
						.amountBought(i.amount())
						.build();
				kafkatemplate.send(topic,userId,log);
				
			}
		   success=true;
		 }
		
		if (success) message="items bought";
		else message="something went wrong";
		SimpleResponse r = new SimpleResponse(success,message);	
		
		return ResponseEntity.ok(r);
	}
	
	@PostMapping("/check-token")
	public ResponseEntity<Boolean> logout(@RequestBody String token) {
		Boolean response=null;
		try {			
			response = decoder.checkExpired(token);
			response=true;
		}catch(Exception e) {			
			response =false;
		}
		return ResponseEntity.ok(response);
	}
	
	
	
	private boolean validateLogin(String login) {
		boolean isValid=false;
		if(login.length()>4) {
			if(login.length()<20) {
				if(login.matches("^[a-zA-Z0-9._-]+$")) {
					isValid=true;
				}
			}
		}
		return isValid;	
	}
	private boolean validateEmail(String email) {
		boolean isValid=false;
		if(!email.isEmpty()) {			
			if(!email.isBlank()) {				
				if(email.matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$")) {
										isValid=true;
				}
			}
		}
		return isValid;	
	}
	private boolean validatePassword(String password) {
		boolean isValid=false;
		if(password.length()>8) {
			if(password.length()<32) {
				if(password.matches("^(?=.*[a-zA-Z])(?=.*[0-9])(?=.*[._-]).*$")) {				
						isValid=true;
				}
			}
		}
		return isValid;	
	}
}
