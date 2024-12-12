package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.example.repository.AccountRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.ArrayList;
import java.util.List;
import com.example.entity.*;;
@Service
public class AccountService {
    @Autowired
    private AccountRepository accountRepository;

    public List<Account> getAllAccounts(){
        return accountRepository.findAll();
    }

    public Account createAccount(Account account){
        return accountRepository.save(new Account(account.getUsername(), account.getPassword()));
    }

    public boolean usernameExists(String username){
        return accountRepository.existsByUsername(username);
    }

    public boolean idExists(Integer ID){
        return accountRepository.existsByaccountId(ID);
    }
    
    public boolean passwordExists(String password){
        return accountRepository.existsByPassword(password);
    }

    public Account findById(Integer ID){
        return accountRepository.findByaccountId(ID);
    }

    public Account findByUsername(String username){
        return accountRepository.findByUsername(username);
    }

}
