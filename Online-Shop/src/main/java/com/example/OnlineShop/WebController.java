package com.example.OnlineShop;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.OnlineShop.Database.Dtos.ItemInCartDto;
import com.example.OnlineShop.Database.Dtos.ProductDto;
import com.example.OnlineShop.Database.Dtos.ProductLessDto;
import com.example.OnlineShop.Database.Dtos.ReviewDto;
import com.example.OnlineShop.Database.Dtos.UserDto;
import com.example.OnlineShop.Database.Dtos.UserRegistrationDto;
import com.example.OnlineShop.Database.Models.CategoryModel;
import com.example.OnlineShop.Objects.Objects.Cart;
import com.example.OnlineShop.Objects.Objects.Product;
import com.example.OnlineShop.Objects.Objects.Review;
import com.example.OnlineShop.Objects.Objects.User;
import com.example.OnlineShop.Other.Filter;
import com.example.OnlineShop.Other.SimpleResponse;
import com.example.OnlineShop.Kafka.Message.Message;

@RestController
public class WebController {

	
	
	private final User user;
    private final Product product;
    private final Cart cart;
    private final Review review;
    private KafkaTemplate<Long, Message> kafkatemplate;
	private String topic;
	
	@Autowired
	public WebController (User user,Product product,Cart cart,Review review,KafkaTemplate<Long, Message> kafkatemplate) {
			
		this.user=user;
		this.product=product;
		this.cart=cart;
		this.review=review;
		this.kafkatemplate=kafkatemplate;
		this.topic="user-activity";
	}
	
	@GetMapping("/home")
	public ResponseEntity<List<ProductLessDto>> home() {
		
		List<ProductLessDto> popularProducts = product.getPopular();		
		return ResponseEntity.ok(popularProducts);
	}
	

	@PostMapping("/filter")
	public ResponseEntity<List<ProductLessDto>> applyfilter(@RequestBody Filter filter) {
		
		List<ProductLessDto> filteredProducts = product.getByCategoryandFilter(filter);
		
		return ResponseEntity.ok(filteredProducts);
	}
	
	@PostMapping("/register")
	public ResponseEntity<SimpleResponse> register(@RequestBody UserRegistrationDto newUser) {
		
		String message="";
		boolean success=false;
		if(validateInput(newUser)) {
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
	
	@PostMapping("/login")
	public String login() {

		//handled by spring security
		
		return "";
	}
	
	@PostMapping("/product")
	public ResponseEntity<ProductDto> getProductDetails(@RequestBody Long[] ids) {//array[0]-user id array[1]-product id
		
		ProductDto p= product.getById(ids[1]);
		List<String> categories = new ArrayList<String>();
		
		for (CategoryModel c:p.categories()) {
			categories.add(c.getName());
		}
		
		long userId=ids[0];
		Message message= new Message.Builder()
				.productId(ids[1])
				.price(p.price())
				.categories(categories)
				.amountOfVisits(1L)
				.build();
		kafkatemplate.send(topic,userId,message);
		
		return ResponseEntity.ok(p);
	}
	
	@PostMapping("/product/add-to-cart")
	public ResponseEntity<SimpleResponse> addproductToCart(@RequestBody Long[] ids) {//array[0]-user id array[1]-product id	
		
		String message="";
		boolean success=false;
		
		success=cart.addToCart(ids[0], ids[1]);
		
		if (success) message="ittem added to cart";
		else message="error occured while adding ittem to cart";
		
		SimpleResponse r = new SimpleResponse(success,message);		
		return ResponseEntity.ok(r);
	}
	@PostMapping("/product/update-amount")
	public ResponseEntity<Void> updateProductAmountInCart(@RequestBody ItemInCartDto item ){	
		cart.updateAmount(item.id(),item.amount());	
		return ResponseEntity.ok().build();
	}
	@PostMapping("/product/remove")
	public ResponseEntity<SimpleResponse> removeProductFromCart(@RequestBody Long id) {	
		
		String message="";
		boolean success=false;
		
		success=cart.removeFromCart(id);
		
		if (success) message="ittem removed";
		else message="error occured while removing ittem from cart";
		SimpleResponse r = new SimpleResponse(success,message);		
		return ResponseEntity.ok(r);
	}
	@PostMapping("/product/remove-all")
	public ResponseEntity<SimpleResponse> removeAllFromCart(@RequestBody Long userId){
		
		String message="";
		boolean success=false;
		
		success=cart.removeAll(userId);
		
		if (success) message="ittem removed";
		else message="error occured while removing ittem from cart";
		SimpleResponse r = new SimpleResponse(success,message);		
		return ResponseEntity.ok(r);
	}
	
	@PostMapping("/product/review")
	public ResponseEntity<SimpleResponse> addProductReview(@RequestBody ReviewDto rev) {
		
		String message="";
		boolean success=false;
		
		success = review.addReview(rev);
		
		if (success) message="review added";
		else message="something went wrong";
		SimpleResponse r = new SimpleResponse(success,message);	
		
		return ResponseEntity.ok(r);
	}
	@PostMapping("/product/review/edit")
	public ResponseEntity<SimpleResponse> editProductReview(@RequestBody ReviewDto rev) {
		String message="";
		boolean success=false;
		
		success = review.editReview(rev);
		
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
	
	@PostMapping("/profile")
	public ResponseEntity<UserDto> getUserprofile(@RequestBody Long id) {
		UserDto userDto=user.getUserDetails(id);
		return ResponseEntity.ok(userDto);
	}
	@PostMapping("/profile/edit")
	public ResponseEntity<SimpleResponse>  editProfile(@RequestBody UserDto userDto) {
		String message="";
		boolean success=false;
		
		success = user.updateProfile(userDto);
		
		if (success) message="profile updated";
		else message="something went wrong";
		SimpleResponse r = new SimpleResponse(success,message);	
		
		return ResponseEntity.ok(r);
	}
	@PostMapping("/profile/delete")
	public ResponseEntity<SimpleResponse> deleteProfile(@RequestBody Long id) {

		String message="";
		boolean success=false;
		
		success = review.removeReview(id);
		
		if (success) message="profile deleted";
		else message="something went wrong";
		SimpleResponse r = new SimpleResponse(success,message);	
		
		return ResponseEntity.ok(r);	
		
	}
	
	
	@PostMapping("/cart")
	public ResponseEntity<List<ItemInCartDto>> getUsersCart(@RequestBody Long userId) {

		List<ItemInCartDto> items=cart.getProducts(userId);		
		return ResponseEntity.ok(items);
	}
	
	@PostMapping("/checkout")
	public ResponseEntity<SimpleResponse> getPayment(Long userId) {
		String message="";
		boolean success=false;
		//We assume the user bought all products in cart
		
		List<ItemInCartDto> items=cart.getProducts(userId);		
		
		for(ItemInCartDto i : items) {
			Message log= new Message.Builder()
					.productId(i.id())
					.price(i.price())
					.amountBought(i.amount())
					.build();
			kafkatemplate.send(topic,userId,log);
		}
		
		if (success) message="items bought";
		else message="something went wrong";
		SimpleResponse r = new SimpleResponse(success,message);	
		
		return ResponseEntity.ok(r);
	}
	
	
	//for registration of a new user
	private boolean validateInput(UserRegistrationDto user) {
		boolean isValid=false;	
		
		if(!(user.login().length()<4 ||user.email().isEmpty() || user.password().length()<8)) {			
			if(!(user.login().length()>20 || user.password().length()>32)) {				
				if((user.login().matches("^[a-zA-Z0-9._-]+$") 
						&& user.email().matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$") 
						&& user.password().matches("^(?=.*[a-zA-Z])(?=.*[0-9])(?=.*[._-]).*$"))) 
						{						
							isValid=true;
				}			
			}		
		}
		return isValid;		
	}
}
