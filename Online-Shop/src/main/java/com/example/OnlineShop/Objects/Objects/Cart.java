package com.example.OnlineShop.Objects.Objects;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.OnlineShop.Database.Dtos.ItemInCartDto;
import com.example.OnlineShop.Database.Dtos.ProductDto;
import com.example.OnlineShop.Database.Models.ItemInCartModel;
import com.example.OnlineShop.Database.Repositories.CartRepository;
import com.example.OnlineShop.Database.Repositories.ProductRepository;
import com.example.OnlineShop.Database.Repositories.UserRepository;
import com.example.OnlineShop.Objects.Services.CartService;

@Service
public class Cart implements CartService{
	
	private UserRepository userRepo;
	private ProductRepository productRepo;
	private CartRepository cartRepo;
	
	@Autowired
	public Cart(UserRepository userRepo,ProductRepository productRepo,CartRepository cartRepo) {
		
		this.userRepo=userRepo;
		this.productRepo=productRepo;
		this.cartRepo=cartRepo;
	}


	@Override
	public boolean addToCart(Long userId, Long productId) {
		
		boolean isSuccessfull=false;
		ItemInCartModel iic = new ItemInCartModel();
		iic.setUser(userRepo.findById(userId).orElse(null));
		iic.setProduct(productRepo.findById(productId).orElse(null));
		iic.setAmount(1);
		
		
		if (iic.getUserId() !=null && iic.getProduct()!=null) {
			ItemInCartModel result=cartRepo.save(iic);
			if (result!=null) isSuccessfull=true;
				
		}
		
		return isSuccessfull;
	}

	@Override
	public boolean removeFromCart(Long id) {
		boolean isSuccessfull=false;
		try {
			cartRepo.deleteById(id);
			 isSuccessfull=true;
		} catch (Exception e) {		       
	        e.printStackTrace(); 
	    }
		return isSuccessfull;
	}

	@Override
	public boolean updateAmount(Long id, int amount) {
		boolean isSuccessfull=false;
		
		ItemInCartModel item = cartRepo.findById(id).orElse(null);
		if(item!=null) {
			item.setAmount(amount);
			ItemInCartModel result =cartRepo.save(item);
			
			if(result!=null) isSuccessfull=true;
		}
		return isSuccessfull;
	}

	@Override
	public List<ItemInCartDto> getProducts(Long userId) {
		List<ItemInCartDto>list=cartRepo.findDtoByUser_id(userId);
		return list;
	}

	@Override
	public boolean removeAll(Long userId) {
		boolean isSuccessfull=false;
		try {
			cartRepo.removeByUser_id(userId);
			isSuccessfull=true;
		} catch (Exception e) {		       
	        e.printStackTrace(); 
	    }
		return isSuccessfull;
	}

}
