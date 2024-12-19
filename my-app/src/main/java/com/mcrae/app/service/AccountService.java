package com.mcrae.app.service;

import com.mcrae.app.entity.Account;
import com.mcrae.app.repository.AccountRepository;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Service
public class AccountService {
    AccountRepository accountRepository;
   
    @Autowired 
    public AccountService(AccountRepository accountRepo){
        this.accountRepository = accountRepo;
    }

    public Account persistAccount(Account account){
        String username = account.getUsername();
        String password = account.getPassword();
        Account returnedAccount;
        returnedAccount = this.accountRepository.save(account);

        return returnedAccount;
    }

    public Account getAccountByUsernameAndPassword(String name, String password){
        return this.accountRepository.findAccountByUsernameAndPassword(name, password);
    }

    public List<Account> getAllAccounts(){
        return this.accountRepository.findAll();
    }
    
}
