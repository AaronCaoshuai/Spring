package com.aaron.spring.tx.service;

import com.aaron.spring.tx.dao.AccountDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

//@Transactional
@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountDao accountDao;

    @Override
    public void transfer(String from, String to, Double money) {
        Double fromMoney = accountDao.queryMoney(from);
        accountDao.update(from,fromMoney - money);


//        System.out.println(1/0);

        Double toMoney = accountDao.queryMoney(to);
        accountDao.update(to,toMoney + money);
    }
}
