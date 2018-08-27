package com.yx.schedule.event;

import org.springframework.context.ApplicationEvent;

public class FlowResumeEvent extends ApplicationEvent {
    public FlowResumeEvent(Object source) {
        super(source);
    }
}
