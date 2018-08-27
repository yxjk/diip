package com.yx.schedule.event;

import org.springframework.context.ApplicationEvent;

public class FlowPauseEvent extends ApplicationEvent {
    public FlowPauseEvent(Object source) {
        super(source);
    }
}
