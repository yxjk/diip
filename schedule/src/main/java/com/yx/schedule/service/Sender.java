package com.yx.schedule.service;

import com.yx.schedule.event.FlowStartEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

@Service
public class Sender {
    @Autowired
    private ApplicationEventPublisher applicationEventPublisher;

    public void test() {
        applicationEventPublisher.publishEvent(new FlowStartEvent("flow1"));
    }
}
