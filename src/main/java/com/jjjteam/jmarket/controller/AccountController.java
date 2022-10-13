package com.jjjteam.jmarket.controller;

import javax.validation.Valid;
import com.jjjteam.jmarket.service.AccountServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/account")
public class AccountController {

    private final AccountServiceImpl accountService;

    @PostMapping("")
    public ResponseEntity<?> insertAccount(
            @Valid @RequestBody AccountFormDTO dto,
            BindingResult bindingResult
    ) {
        if (bindingResult.hasErrors()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        Account accountDB = accountService.saveOrUpdateAccount(dto.toEntity());

        return new ResponseEntity<>(accountDB, HttpStatus.CREATED);
    }
}