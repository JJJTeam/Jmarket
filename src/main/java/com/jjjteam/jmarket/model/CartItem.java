package com.jjjteam.jmarket.model;

import javax.persistence.*;

import lombok.Getter;
import lombok.Setter;


@Entity
@Getter
@Setter
@Table(name="cart_item")
public class CartItem {
	@Id
	@GeneratedValue
	@Column(name="cart_item_id")
	private Long id;
	
	@ManyToOne
	@JoinColumn(name="user_id")
	private User user;
	
	@ManyToOne
	@JoinColumn(name="item_id")
	private Item item;
	
	private int count;
	
}
