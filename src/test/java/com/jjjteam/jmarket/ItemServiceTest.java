package com.jjjteam.jmarket;


import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;

import org.springframework.test.context.TestPropertySource;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.jjjteam.jmarket.repository.ItemRepository;
import com.jjjteam.jmarket.service.ItemService;

import javax.persistence.EntityNotFoundException;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
@Transactional
@TestPropertySource(properties = {"spring.config.location=classpath:application.yml" })
public class ItemServiceTest {

	@Autowired
	ItemService itemService;

	@Autowired
	ItemRepository itemRepository;

	List<MultipartFile> createMultipartFiles() throws Exception {
		List<MultipartFile> multipartFileList = new ArrayList<>();

		// path 제일 앞에 경로를 /User로 수정함
		for (int i = 0; i < 5; i++) {
//			String path = "/Users/jincrates/projects/upload/shop/item/";
			String path = "C:\\shop\\item";
			String imageName = "image" + i + ".jpg";
			MockMultipartFile multipartFile = new MockMultipartFile(path, imageName, "image/jpg",
					new byte[] { 1, 2, 3, 4 });
			multipartFileList.add(multipartFile);
		}

		return multipartFileList;
	}



}