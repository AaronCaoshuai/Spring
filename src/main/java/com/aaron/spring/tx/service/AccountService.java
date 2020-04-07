package com.aaron.spring.tx.service;

public interface AccountService {
    //转账
    void transfer(String from , String to,Double money);
}
