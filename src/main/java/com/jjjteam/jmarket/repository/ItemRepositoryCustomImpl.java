package com.jjjteam.jmarket.repository;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.thymeleaf.util.StringUtils;

import com.jjjteam.jmarket.constant.ItemSellStatus;
import com.jjjteam.jmarket.dto.ItemListDTO;
import com.jjjteam.jmarket.dto.ItemSearchDTO;
import com.jjjteam.jmarket.dto.QItemListDTO;
import com.jjjteam.jmarket.model.Item;
import com.jjjteam.jmarket.model.QItem;
import com.querydsl.core.QueryResults;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;



/*
 * Querydsl 을 SpringDataJpa 와 함께 사용하기 위해서는 사용자 정의 repository 를 작성해야 함
 * 사용자 정의 repository 는 총 3단계임
 * 1. 사용자 정의 인터페이스 작성
 * 2. 사용자 정의 인터페이스 구현 ***
 * 3. Spring Data Jpa repository 에서 사용자 정의 인터페이스 상속 (ItemRepository)
 */


//2. 사용자 정의 인터페이스 구현 (클래스 뒤에 Impl 을 꼭 붙여야 한다.)
public class ItemRepositoryCustomImpl implements ItemRepositoryCustom{
	
	private JPAQueryFactory queryFactory; // JPAQueryFactory 클래스: 동적으로 쿼리를 생성해줌
	
	
	public ItemRepositoryCustomImpl(EntityManager em) {
		// EntityManager: JPAQueryFactory 의 생성자로 많이 사용
		this.queryFactory = new JPAQueryFactory(em);
	}

	// BooleanExpression: where 절에서 사용할 수 있는 값을 지원해줌
	
	// 상품 판매 조건으로 조회
	private BooleanExpression searchSellStatusEq(ItemSellStatus searchSellStatus) {
		// 상품 판매 조건이 전체일 경우 : null 리턴 (이러면은 where 절에서 조건이 무시됨)
        // 상품 판매 조건이 판매중 or 품절일 경우 :  where 조건 활성화
		return searchSellStatus == null ? null : QItem.item.itemSellStatus.eq(searchSellStatus);
	}
	
	private BooleanExpression regDtsAfter(String searchDateType) {
		// searchDateType에 따라 dataTime의 값으로 목록? 세팅 후, 그 시간 이후로 등록된 상품만 조회
		
		LocalDateTime dateTime = LocalDateTime.now();
		
		if(StringUtils.equals("all", searchDateType) || searchDateType ==  null) {
			return null;
		}else if(StringUtils.equals("1d", searchDateType)) {
			dateTime = dateTime.minusDays(1);
		}else if(StringUtils.equals("1w", searchDateType)) {
			dateTime = dateTime.minusWeeks(1);
		}else if(StringUtils.equals("1m", searchDateType)) {
			dateTime = dateTime.minusMonths(1);
		}else if(StringUtils.equals("6m", searchDateType)) {
			dateTime = dateTime.minusMonths(6);
		}
		
		return QItem.item.regTime.after(dateTime);
	}
	
	private BooleanExpression searchByLike(String searchBy, String searchQuery) {
		// searchBy 값에 따라 (where조건) 상품을 조회하도록 조건값을 반환
		
		// StringUtils.equals(문자열, 비교할 문자열)
		// '문자열'과 '비교할 문자열'과 같다면 true를 반환
		if(StringUtils.equals("itemNm",searchBy)) {
			return QItem.item.itemNm.like("%" + searchQuery + "%");
        } else if(StringUtils.equals("createdBy", searchBy)){
            return QItem.item.createdBy.like("%" + searchQuery + "%");
		}
		
		return null;
	}
	
	
	// queryFactory 로 쿼리 동적 생성
	@Override
	public Page<Item> getAdminItemPage(ItemSearchDTO itemSearchDTO, Pageable pageable){
		
		QueryResults<Item> results = queryFactory
				.selectFrom(QItem.item)
				.where(regDtsAfter(itemSearchDTO.getSearchDateType()),
						searchSellStatusEq(itemSearchDTO.getSearchSellStatus()),
						searchByLike(itemSearchDTO.getSearchBy(),
								itemSearchDTO.getSearchQuery())) //',' = and
				.orderBy(QItem.item.id.desc())
				.offset(pageable.getOffset()) // 데이터를 가지고 시작인덱스 지정
				.limit(pageable.getPageSize()) // 한 번에 가지고 최대 개수 지정
				.fetchResults();
//		List<Item> results = queryFactory
//				.selectFrom(QItem.item)
//				.where(regDtsAfter(itemSearchDTO.getSearchDateType()),
//						searchSellStatusEq(itemSearchDTO.getSearchSellStatus()),
//						searchByLike(itemSearchDTO.getSearchBy(),
//								itemSearchDTO.getSearchQuery())) //',' = and
//				.orderBy(QItem.item.id.desc())
//				.offset(pageable.getOffset()) // 데이터를 가지고 시작인덱스 지정
//				.limit(pageable.getPageSize()) // 한 번에 가지고 최대 개수 지정
//				.fetch();
		
		List<Item> content = results.getResults();
		long total = results.getTotal();
		
		return new PageImpl<>(content, pageable, total);
	}
	
	//item like 검색
	private BooleanExpression itemNmLike(String searchQuery) {
		return StringUtils.isEmpty(searchQuery) ? null : QItem.item.itemNm.like("%"+ searchQuery +"%");
	}

	@Override
	public Page<ItemListDTO> getMainItemPage(ItemSearchDTO itemSearchDTO, Pageable pageable) {
		QItem item = QItem.item;
        QueryResults<ItemListDTO> results = queryFactory
                .select(
                        new QItemListDTO( // 원래는 entity 로 변환해줘야 하는데, mainitemdto 의 어노테이션 (QueryProjection)덕분에 dto 로 그냥 사용
                                item.id,
                                item.itemNm,
                                item.repimg,
                                item.price,
                                item.itemMaterial,
                                item.itemWashing,
                                item.itemFabric)
                )
				.from(item)
//                .from(itemImg)
//                .join(itemImg.item, item) // 내부 조인
//                .where(itemImg.repimgYn.eq("Y")) // 대표 상품인 경우에는 Y
                .where(itemNmLike(itemSearchDTO.getSearchQuery()))
                .orderBy(item.id.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetchResults();

        List<ItemListDTO> content = results.getResults();
        long total = results.getTotal();
        return new PageImpl<>(content, pageable, total);
	}
	
}
