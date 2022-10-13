package com.jjjteam.jmarket.service;

import com.jjjteam.jmarket.model.Account;
import com.jjjteam.jmarket.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;
    private final PasswordEncoder passwordEncoder;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return null;
    }

    @Override
    public Account saveOrUpdateAccount(Account account) {
        account.encodePassword(this.passwordEncoder);

        return this.accountRepository.save(account);
    }
}