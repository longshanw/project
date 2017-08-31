package com.wls.diypro.model;

/**
 * Created by wls on 2017/8/24.
 */
import java.text.DateFormat;
import java.util.Date;

import com.wls.integrateplugs.jpa.IUserRepository;
import com.wls.projects.diypro.model.User;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Profile;
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

        iUserRepository.save(new User("aa","aa","aa","aa","aa","aa",12));
//        iUserRepository.save(new User("bb","bb","bb","bb","bb","bb",13));
//        iUserRepository.save(new User("cc","cc","cc","cc","cc","cc",14));

//        Assert.assertEquals(3, iUserRepository.findAll().size());
        Assert.assertEquals("aa", iUserRepository.findByUserNameOrEmail("aa", "aa").getNickName());
        iUserRepository.delete(iUserRepository.findByUserName("bb"));
    }

}
