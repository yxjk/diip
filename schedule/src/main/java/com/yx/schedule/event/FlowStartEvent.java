package com.yx.schedule.event;

import org.springframework.context.ApplicationEvent;

public class FlowStartEvent extends ApplicationEvent {
    public FlowStartEvent(Object source) {
        super(source);
    }
}
