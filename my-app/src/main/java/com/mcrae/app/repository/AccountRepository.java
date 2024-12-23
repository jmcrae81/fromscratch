package com.mcrae.app.repository;

import com.mcrae.app.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, Long> {
    public Account findAccountByUsernameAndPassword(String name, String password);
    public Account findAccountByUsername(String name);
}


