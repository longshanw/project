package com.wls.diypro.task;

import com.wls.integrateplugs.task.ScheduledTasks;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.text.SimpleDateFormat;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class ScheduledTasksTest {
    private static final Logger log = LoggerFactory.getLogger(ScheduledTasks.class);

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

    @Autowired
    private ScheduledTasks scheduledTasks;

    @Test
    public void reportCurrentTime() throws Exception {
        scheduledTasks.reportCurrentTime();
    }

}