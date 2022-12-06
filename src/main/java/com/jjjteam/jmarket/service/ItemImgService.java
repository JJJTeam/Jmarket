package com.jjjteam.jmarket.service;

import lombok.RequiredArgsConstructor;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.thymeleaf.util.StringUtils;




@RequiredArgsConstructor
@Transactional
@Service
public class ItemImgService {
	@Value("${itemImgLocation}")
	private String itemImgLocation;


	private final FileService fileService;

}
