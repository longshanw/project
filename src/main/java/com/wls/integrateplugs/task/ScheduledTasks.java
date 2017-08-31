package com.wls.integrateplugs.task;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ScheduledTasks {

    private static final Logger log = LoggerFactory.getLogger(ScheduledTasks.class);

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

    @Value("${cron}")
    public String cron;

//    @Scheduled(fixedRate = 5000)
    @Scheduled(cron = "${cron}")
    public void reportCurrentTimeByCron() {
        log.info("cron--The time is now {}", dateFormat.format(new Date()));
    }

    @Scheduled(initialDelay = 1000,fixedDelay = 5000)
    public void reportCurrentTime() {
        log.info("delay--The time is now {}", dateFormat.format(new Date()));
    }
}