package com.indiraactive.fulfillmentplatform.controller;

import com.indiraactive.fulfillmentplatform.dal.ScheduledTaskRepository;
import com.indiraactive.fulfillmentplatform.dal.SupplierRepository;
import com.indiraactive.fulfillmentplatform.model.ScheduledTask;
import com.indiraactive.fulfillmentplatform.model.Supplier;
import com.indiraactive.fulfillmentplatform.viewModel.ScheduleHistoryViewModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

/**
 * Handles incoming requests relating to the scheduling component
 * of this web application.
 */
@Controller
public class ScheduleController {

    @Autowired
    private ScheduledTaskRepository scheduledTaskRepository;

    @Autowired
    private SupplierRepository supplierRepository;


    @GetMapping("/scheduler")
    public String schedule(Model model) {
        ScheduleHistoryViewModel scheduleHistoryViewModel = new ScheduleHistoryViewModel(scheduledTaskRepository.findAll(), supplierRepository);

        model.addAttribute("scheduledTask", new ScheduledTask());
        model.addAttribute("scheduleHistoryModels", scheduleHistoryViewModel.getScheduleHistoryModels());
        return "scheduler";
    }

    /**
     * Adds a new scheduled task to the system
     *
     * @param scheduledTask ScheduledTask to add to the fulfillment platform system
     * @return A success screen if there are no errors thrown during the process of running the screen
     */
    @PostMapping("/scheduler/add")
    public String addSupplier(@ModelAttribute ScheduledTask scheduledTask) {
        scheduledTaskRepository.save(scheduledTask);
        return "success";
    }

    /**
     * Deletes a given scheduled task by their id
     *
     * @param scheduledTaskId Id of a scheduled task to be deleted
     * @return A success screen if there are no errors thrown during the process of running the screen
     */
    @DeleteMapping("/scheduler/delete/{scheduledTaskId}")
    public String deleteScheduledTask(@PathVariable int scheduledTaskId) {
        scheduledTaskRepository.delete(scheduledTaskId);
        return "success";
    }
}




