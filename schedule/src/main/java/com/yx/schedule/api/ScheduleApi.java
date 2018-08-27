package com.yx.schedule.api;

import com.yx.schedule.service.Sender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EurekaDiscoveryClient;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
@RibbonClient(name = "tests")
public class ScheduleApi {
    @Autowired
    Sender sender;

    @Bean
    @LoadBalanced
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Autowired
    private RestTemplate restTemplate;

    @RequestMapping("/hi")
    public String hi() {
        sender.test();
        return "say hi ";
    }

    @Autowired
    DiscoveryClient discoveryClient;

    @RequestMapping("/list")
    public List<ServiceInstance> getServer() {
        List<ServiceInstance> instances = discoveryClient.getInstances("schedule");
        for (ServiceInstance instance : instances) {
            EurekaDiscoveryClient.EurekaServiceInstance eurekaServiceInstance = (EurekaDiscoveryClient.EurekaServiceInstance) instance;
            System.out.println("appGroupName = " + eurekaServiceInstance.getInstanceInfo().getAppGroupName());
        }
        return instances;
    }
}
