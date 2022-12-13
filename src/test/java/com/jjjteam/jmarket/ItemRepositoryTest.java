package com.jjjteam.jmarket;

import com.jjjteam.jmarket.model.Item;
import com.jjjteam.jmarket.repository.ItemRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

@SpringBootTest
@TestPropertySource(properties = {"spring.config.location=classpath:application.yml"})
public class ItemRepositoryTest {

    @Autowired
    ItemRepository itemRepository;

    @Test
    @DisplayName("상품 저장 테스트")
    public void createItemTest() {
        Item item = Item.builder()
                .itemNm("테스트 상품")
                .price(10000)
                .itemDetail("테스트 상품 상세설명")
                .stockNumber(100)
                .build();
    	
//    	Item item = new Item();
//    	item.setItemNm("벨트 울 코트");
//    	item.setItemIntroduction("울 혼방 소재 코트. 버튼 탭이 달린 긴소매 라펠 칼라 디자인. 같은 소재의 벨트 디테일. 앞면 파이핑 포켓.");
//    	item.setPrice(259000);
//    	item.setStockNumber(10);
//    	item.setItemDetail("");
//    	item.setClothingItems("sweater");
//    	item.setItemSize("테스트상품");
//    	item.setItemColor("테스트상품");
    	
        Item savedItem = itemRepository.save(item);
    }
}
