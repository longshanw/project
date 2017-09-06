package com.wls.test.integrateplugs.jpa;

import com.wls.integrateplugs.jpa.primary.model.User;
import com.wls.integrateplugs.jpa.primary.repository.UserRepository;
import com.wls.integrateplugs.jpa.second.model.Message;
import com.wls.integrateplugs.jpa.second.reposity.MessageRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class JpaTest {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private MessageRepository messageRepository;

    @Before
    public void setUp() {
    }

    @Test
    public void test() throws Exception {

//        userRepository.save(new User("aa","aa","aa","aa","aa","aa",12));
//        userRepository.save(new User("bb","bb","bb","bb","bb","bb",13));
//        userRepository.save(new User("cc","cc","cc","cc","cc","cc",14));

        Assert.assertEquals(2, userRepository.findAll().size());

//        messageRepository.save(new Message("o1", "aaaaaaaaaa"));
//        messageRepository.save(new Message("o2", "bbbbbbbbbb"));
//        messageRepository.save(new Message("o3", "cccccccccc"));

        Assert.assertEquals(3, messageRepository.findAll().size());

    }


}