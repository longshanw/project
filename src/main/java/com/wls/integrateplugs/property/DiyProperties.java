package com.wls.integrateplugs.property;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * Created by wls on 2017/8/24.
 */
@Component
public class DiyProperties {

    @Value("${com.diy.title}")
    private String title;
    @Value("${com.diy.description}")
    private String description;
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
}
