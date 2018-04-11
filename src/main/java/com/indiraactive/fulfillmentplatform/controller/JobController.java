package com.indiraactive.fulfillmentplatform.controller;

import com.indiraactive.fulfillmentplatform.dao.supplier.Supplier;
import com.indiraactive.fulfillmentplatform.domain.Job;
import com.indiraactive.fulfillmentplatform.service.job.JobManager;
import com.indiraactive.fulfillmentplatform.service.jobQueue.JobQueueManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class JobController {
    @Autowired
    private JobManager jobManager;

    @Autowired
    private JobQueueManager jobQueueManager;

    @GetMapping("/job")
    public String index(Model model) {
        model.addAttribute("job", new Job());
        model.addAttribute("activeJobs", jobManager.getActiveJobs());
        model.addAttribute("queuedJobs", jobQueueManager.getJobsQueued());

        return "job";
    }

    @PostMapping("/job/add")
    public String add(@ModelAttribute Job job) {
        cleanUpJobFromUser(job);
        jobManager.saveJob(job);
        return "success";
    }

    @GetMapping("job/update/{jobId}")
    public String updateJob(Model model, @PathVariable int jobId) {
        Job jobToUpdate = jobManager.getJobById(jobId);

        if (jobToUpdate == null) {
            return "error";
        }
        model.addAttribute("jobToUpdate", jobToUpdate);
        return "updateJob";
    }

    @PostMapping("/job/update")
    public String updateJob(@ModelAttribute Job job) {
        Job jobToUpdate = jobManager.getJobById(job.getId());
        jobToUpdate.setSupplierId(job.getSupplierId());
        jobToUpdate.setStartDateTime(job.getStartDateTime());
        jobToUpdate.setRunOnce(job.isRunOnce());
        jobToUpdate.setCronExpression(job.getCronExpression());
        boolean success = jobManager.updateJob(jobToUpdate);
        if (success) {
            return "success";
        }
        return "error";
    }

    @DeleteMapping("/job/delete/{jobId}")
    public String deleteSupplier(@PathVariable int jobId) {
        Job jobToDelete = jobManager.getJobById(jobId);
        jobToDelete.setActive(false);
        jobManager.updateJob(jobToDelete);
        return "success";
    }

    private void cleanUpJobFromUser(Job jobToClean) {
        jobToClean.setActive(true);
        String newCronExpression = jobToClean.getCronExpression().replaceAll("\t", " ");
        newCronExpression = "* "+ newCronExpression; // Add seconds manually, UI doesn't support this field
        jobToClean.setCronExpression(newCronExpression);
    }
}
