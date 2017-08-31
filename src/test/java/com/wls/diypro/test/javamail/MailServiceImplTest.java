package com.wls.diypro.test.javamail;

import com.wls.projects.diypro.service.IMailService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MailServiceImplTest {

    @Autowired
    private IMailService iMailService;

    @Value("${spring.mail.username}")
    private String from;

    @Test
    public void sendSimpleMail() throws Exception {
        iMailService.sendSimpleMail("158822436@qq.com","testSubject","testContent");
    }

    @Test
    public void sendHtmlMail() throws Exception {
        iMailService.sendHtmlMail("158822436@qq.com","testSubjectHTML","testContentHTML");
    }

}