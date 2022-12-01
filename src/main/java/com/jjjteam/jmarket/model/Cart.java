package com.jjjteam.jmarket.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Table(name="cart")
@Entity
@ToString
public class Cart extends BaseEntity {
  @Id
  @Column(name="cart_id")
  @GeneratedValue(strategy= GenerationType.AUTO)
  private Long id;
  
  @OneToOne(fetch= FetchType.LAZY)
  @JoinColumn(name= "user_id")
  private User user;
  
  public static Cart createCart(User user) {
	  Cart cart = new Cart();
	  cart.setUser(user);
	  return cart;
  }
}
