package com.indiraactive.fulfillmentplatform.controller;

import com.indiraactive.fulfillmentplatform.domain.Job;
import com.indiraactive.fulfillmentplatform.service.job.JobManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class JobController {
    @Autowired
    private JobManager jobManager;

    @GetMapping("/job")
    public String index(Model model) {
        model.addAttribute("job", new Job());

        return "job";
    }

    @PostMapping("/job/add")
    public String add(@ModelAttribute Job job) {
        cleanUpJobFromUser(job);
        jobManager.saveJob(job);
        return "success";
    }

    private void cleanUpJobFromUser(Job jobToClean) {
        jobToClean.setActive(true);
        String newCronExpression = jobToClean.getCronExpression().replaceAll("\t", " ");
        newCronExpression = "* "+ newCronExpression; // Add seconds manually, UI doesn't support this field
        jobToClean.setCronExpression(newCronExpression);
    }
}
