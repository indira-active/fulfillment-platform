package com.indiraactive.fulfillmentplatform.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Handles incoming requests relating to the scheduling component
 * of this web application.
 */
@Controller
public class ScheduleController {

    @GetMapping("/scheduler")
    public String schedule() {
        return "scheduler";
    }
}
