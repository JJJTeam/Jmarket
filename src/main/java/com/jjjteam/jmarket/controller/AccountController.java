package com.jjjteam.jmarket.controller;

import com.jjjteam.jmarket.dto.AccountFormDTO;
import com.jjjteam.jmarket.model.Account;
import com.jjjteam.jmarket.service.AccountServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/account")
public class AccountController {

    private final AccountServiceImpl accountService;

    @PostMapping("")
    public ResponseEntity<?> insertAccount(@Valid @RequestBody AccountFormDTO dto, BindingResult bindingResult) {
        System.out.println("실행");
        if (bindingResult.hasErrors()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        Account accountDB = accountService.saveOrUpdateAccount(dto.toEntity());

        return new ResponseEntity<>(accountDB, HttpStatus.CREATED);
    }
}