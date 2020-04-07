package com.aaron.spring.tx;

import com.aaron.spring.tx.domain.Account;
import com.aaron.spring.tx.service.AccountService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring-tx.xml")
public class TxText {

    @Autowired
    private AccountService accountService;

    @Test
    public void txTest(){
        accountService.transfer("李四","张三",100D);
    }

}
