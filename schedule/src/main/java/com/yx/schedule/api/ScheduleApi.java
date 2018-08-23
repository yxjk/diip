package com.yx.schedule.api;

import org.springframework.web.bind.annotation.RestController;

@RestController
public class ScheduleApi {
    public String hi(String who) {
        return "say hi " + who;
    }
}
