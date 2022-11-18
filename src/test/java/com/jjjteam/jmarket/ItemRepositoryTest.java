package com.jjjteam.jmarket;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import com.jjjteam.jmarket.model.Item;
import com.jjjteam.jmarket.repository.ItemRepository;

@SpringBootTest
@TestPropertySource(properties = {"spring.config.location=classpath:application.yml"})
public class ItemRepositoryTest {

    @Autowired
    ItemRepository itemRepository;

    @Test
    @DisplayName("상품 저장 테스트")
    public void createItemTest() {
//        Item item = Item.builder()
//                .itemNm("테스트 상품")
//                .price(10000)
//                .itemDetail("테스트 상품 상세설명")
//                .stockNumber(100)
//                .build();
    	
    	Item item = new Item();
    	item.setItemNm("테스트상품");
        Item savedItem = itemRepository.save(item);
        System.out.println(savedItem.toString());
    }
}
