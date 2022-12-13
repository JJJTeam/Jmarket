package com.jjjteam.jmarket.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Getter @Setter
public class OrderItem extends BaseEntity{ // BaseEntity: 등록한사람, 수정한사람만 있는 entity + 상속받은 등록일 수정일 entity 도 있음

    @Id
    @GeneratedValue
    @Column(name = "order_item_id")
    private Long id; //상품아이디

    @ManyToOne(fetch = FetchType.LAZY) // 한 아이템은 여러 주문에 들어갈 수 있다.
    @JoinColumn(name = "item_id")
    private Item item;

    @ManyToOne (fetch = FetchType.LAZY) // 한 주문에 여러 아이템이 들어갈 수 있다.
    @JoinColumn(name = "order_id")
    private Order order;
    private int orderPrice; // 주문가격
    private int count; // 수량
    
    @Lob
    private String repimg;
    


    // 주문할 상품,주문 수량으로 orderItem 객체 생성
    public static OrderItem createOrderItem(Item item, int count, String repimg){

        OrderItem orderItem = new OrderItem();
        orderItem.setItem(item); // 주문할 상품 setter
        orderItem.setCount(count); // 주문 수량 setter
        orderItem.setRepimg(repimg);
        orderItem.setOrderPrice(item.getPrice()); // 현재 시간 기준으로 상품가격=주문가격 (상품가격은 관리자가 세팅하는 값에 따라 달라지니까 현재 주문한 시간으로 딱 정해야 함1!!)
        
        //주문 수량만큼 상품의 재고 수량을 감소
        item.removeStock(count); // 상품 재고 수량에서 주문 수량을 뺌
        return orderItem;
    }

    
    //주문 가격과 주문 수량을 곱해서 해당 상품을 주문한 총 가격을 계산하는 메서드
    public int getTotalPrice(){
        return orderPrice * count; // 총 가격: 주문가격*주문수량
    }

    // Item 클래스에서 주문 취소 시 주문 수량을 상품의 재고에 더해주는 로직
    // == 주문 취소 시 주문 수량만큼 상품의 재고를 증가 (item 의 메소드 호출)
    public void cancel() {
        this.getItem().addStock(count);
    }

	

	
}
