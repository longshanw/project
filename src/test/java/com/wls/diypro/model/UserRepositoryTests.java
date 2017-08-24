package com.wls.diypro.model;

/**
 * Created by wls on 2017/8/24.
 */
import java.text.DateFormat;
import java.util.Date;

import com.wls.diypro.DiyProApplication;
import com.wls.diypro.mapper.jpa.IUserRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;



@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class UserRepositoryTests {

    @Autowired
    private IUserRepository iUserRepository;

    @Test
    public void test() throws Exception {
        Date date = new Date();
        DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG);
        String formattedDate = dateFormat.format(date);

        iUserRepository.save(new User("aa1", "aa@126.com", "aa", "aa123456",formattedDate));
        iUserRepository.save(new User("bb2", "bb@126.com", "bb", "bb123456",formattedDate));
        iUserRepository.save(new User("cc3", "cc@126.com", "cc", "cc123456",formattedDate));

        Assert.assertEquals(3, iUserRepository.findAll().size());
        Assert.assertEquals("bb@126.com", iUserRepository.findByUserNameOrEmail("bb123456", "bb2").getNickName());
        iUserRepository.delete(iUserRepository.findByUserName("bb123456"));
    }

}
