package com.errday.kafkahelper.adapter.in.web;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

@Slf4j
@Controller
@RequiredArgsConstructor
public class KafkaBrokerController {

    @ModelAttribute
    public void setPageData(Model model) {
        model.addAttribute("module", "brokers");
        model.addAttribute("title", "brokers");
    }

    @GetMapping(value = {"/", "/kafka/brokers"})
    public String brokers() {
        return "kafka/brokers/list";
    }
}
