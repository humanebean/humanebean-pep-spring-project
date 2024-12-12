package com.example.repository;
import com.example.entity.Account;
import java.util.ArrayList;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
@Repository
public interface AccountRepository extends JpaRepository<Account,Integer>{
    public boolean existsByUsername(String username);
    public boolean existsByaccountId(Integer ID);
    public boolean existsByPassword(String password);
    public Account findByaccountId(Integer ID);
    public Account findByUsername(String username);
}
