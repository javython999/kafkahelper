package com.errday.kafkahelper.adapter.in.web;

import com.errday.kafkahelper.domain.service.KafkaBrokerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequiredArgsConstructor
public class DashboardController {

    private final KafkaBrokerService kafkaBrokerService;

    @GetMapping("/")
    public String root(Model model) {
        return dashboard(model);
    }

    @GetMapping("/dashboard")
    public String dashboard(Model model) {
        // Set page title
        model.addAttribute("title", "Dashboard");
        
        // Set active tab for navigation
        model.addAttribute("activeTab", "dashboard");
        
        // Add breadcrumbs
        List<Map<String, String>> breadcrumbs = new ArrayList<>();
        Map<String, String> home = new HashMap<>();
        home.put("label", "Home");
        home.put("url", "/");
        breadcrumbs.add(home);
        
        Map<String, String> dashboard = new HashMap<>();
        dashboard.put("label", "Dashboard");
        dashboard.put("url", "/dashboard");
        breadcrumbs.add(dashboard);
        
        model.addAttribute("breadcrumbs", breadcrumbs);
        
        // Add dashboard data
        try {
            model.addAttribute("brokerCount", kafkaBrokerService.findAll().size());
        } catch (Exception e) {
            model.addAttribute("brokerCount", 0);
        }
        
        // These would be populated from actual services in a real implementation
        model.addAttribute("topicCount", 0);
        model.addAttribute("producedMessages", 0);
        model.addAttribute("consumedMessages", 0);
        
        return "index";
    }

    @GetMapping("/test")
    public String test(Model model) {
        return "test";
    }
}