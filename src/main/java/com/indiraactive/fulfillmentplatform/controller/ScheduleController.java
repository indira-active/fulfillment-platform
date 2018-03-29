package com.indiraactive.fulfillmentplatform.controller;

import com.indiraactive.fulfillmentplatform.dao.scheduledTask.ScheduledTaskRepository;
import com.indiraactive.fulfillmentplatform.dao.supplier.SupplierRepository;
import com.indiraactive.fulfillmentplatform.service.scheduledTask.ScheduledTaskService;
import com.indiraactive.fulfillmentplatform.domain.ScheduledTask;
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

    @Autowired
    private ScheduledTaskService scheduledTaskService;

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
     * @param scheduledTask ScheduledTaskJpa to add to the fulfillment platform system
     * @return A success screen if there are no errors thrown during the process of running the screen
     */
    @PostMapping("/scheduler/add")
    public String addSupplier(@ModelAttribute ScheduledTask scheduledTask) {
        scheduledTaskService.addNewScheduledTask(scheduledTask);
        return "success";
    }

//    /**
//     * Deletes a given scheduled task by their id
//     *
//     * @param scheduledTaskId Id of a scheduled task to be deleted
//     * @return A success screen if there are no errors thrown during the process of running the screen
//     */
//    @DeleteMapping("/scheduler/delete/{scheduledTaskId}")
//    public String deleteScheduledTask(@PathVariable int scheduledTaskId) {
//        scheduledTaskRepository.delete(scheduledTaskId);
//        return "success";
//    }

//    @GetMapping("/schedule/temp")
//    public void temp() {
//        ScheduledTaskJpa scheduledTask = new ScheduledTaskJpa();
//
//        ScheduledTaskRunDaysJpa scheduledTaskRunDaysJpa = new ScheduledTaskRunDaysJpa();
//        scheduledTaskRunDaysJpa.setSunday(true);
//        scheduledTaskRunDaysJpa.setMonday(true);
//        scheduledTaskRunDaysJpa.setFriday(true);
//
//
//        List<ScheduledTaskRunTimeJpa> scheduledTaskRunTimeJpas = new LinkedList<>();
//        ScheduledTaskRunTimeJpa scheduledTaskRunTimeJpa1 = new ScheduledTaskRunTimeJpa();
//        scheduledTaskRunTimeJpa1.setTimeEpochToRunTask(120000L);
//        ScheduledTaskRunTimeJpa scheduledTaskRunTimeJpa2 = new ScheduledTaskRunTimeJpa();
//        scheduledTaskRunTimeJpa2.setTimeEpochToRunTask(123200L);
//        ScheduledTaskRunTimeJpa scheduledTaskRunTimeJpa3 = new ScheduledTaskRunTimeJpa();
//        scheduledTaskRunTimeJpa3.setTimeEpochToRunTask(340003L);
//        scheduledTaskRunTimeJpas.add(scheduledTaskRunTimeJpa1);
//        scheduledTaskRunTimeJpas.add(scheduledTaskRunTimeJpa2);
//        scheduledTaskRunTimeJpas.add(scheduledTaskRunTimeJpa3);
//
//
//        Supplier supplier = new Supplier();
//        supplier.setSupplierName("supname");
//        supplier.setShopifyApiKey("apikey");
//        supplier.setShopifyPassword("psw");
//        supplier.setShopName("shpnpame");
//        supplier.setFulfillSubdomain("subdom");
//        supplier.setFulfillApiKey("fulla");
//        supplier.setSupplierCode("code");
//        Supplier supplierSaved = supplierRepository.save(supplier);
//
//        scheduledTask.setTaskRunTimes(scheduledTaskRunTimeJpas);
//        scheduledTask.setSupplier(supplierSaved);
//        scheduledTask.setRunOnDays(scheduledTaskRunDaysJpa);
//        scheduledTask.setRunOnDate(new Date());
//        scheduledTask.setCreatedByUser("user");
//        scheduledTask.setReoccurringJob(true);
//
//        scheduledTaskRepository.save(scheduledTask);
//    }
}