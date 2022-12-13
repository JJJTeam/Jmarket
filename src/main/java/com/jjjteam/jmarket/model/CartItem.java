package com.jjjteam.jmarket.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "cart_item")
public class CartItem extends BaseEntity {
	@Id
	@GeneratedValue
	@Column(name = "cart_item_id")
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id")
	private Cart cart;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "item_id")
	private Item item;

	private int count;
	private String repimg;
	
	private String itemMaterial;
	private String itemWashing;
	private String itemFabric;

	
	
	

	public static CartItem createCartItem(Cart cart, Item item, int count, String repimg) {
		CartItem cartItem = new CartItem();
		cartItem.setCart(cart);
		cartItem.setItem(item);
		cartItem.setCount(count);
		cartItem.setRepimg(repimg);
		

		return cartItem;
	}

	public void addCount(int count) {
		this.count += count;
	}

	public void updateCount(int count) {
		this.count = count;
	}

}
