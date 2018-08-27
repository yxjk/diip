package com.yx.schedule.event;

import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class AllEventListener {
    @EventListener
    public void flowStartListener(FlowStartEvent e) {
        System.out.println(e.getSource());
    }

    @EventListener
    public void flowPauseListener(FlowPauseEvent e) {

    }

    @EventListener
    public void flowResumeListener(FlowResumeEvent e) {

    }

    @EventListener
    public void flowStopListener(FlowStopEvent e) {

    }


}
