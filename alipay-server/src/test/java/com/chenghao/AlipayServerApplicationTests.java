package com.chenghao;

import com.chenghao.domain.Account;
import com.chenghao.mapper.AccountMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AlipayServerApplicationTests {

    @Autowired
    private AccountMapper accountMapper;

    @Test
    public void contextLoads() {
        Account account = new Account();
        account.setAmount(10000);
        account.setUserId("chenghao");
        accountMapper.addAccount(account);
    }

}
