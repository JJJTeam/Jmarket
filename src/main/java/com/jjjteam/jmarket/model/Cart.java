package com.jjjteam.jmarket.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

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
