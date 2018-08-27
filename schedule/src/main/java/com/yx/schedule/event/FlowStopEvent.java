package com.yx.schedule.event;

import org.springframework.context.ApplicationEvent;

public class FlowStopEvent extends ApplicationEvent {
    public FlowStopEvent(Object source) {
        super(source);
    }
}
