package com.indiraactive.fulfillmentplatform.service.inventoryUpdater;

import com.indiraactive.fulfillmentplatform.dao.scriptRunAuditEntry.ScriptRunAuditEntry;
import com.indiraactive.fulfillmentplatform.dao.scriptRunAuditEntry.ScriptRunAuditEntryRepository;
import com.indiraactive.fulfillmentplatform.dao.supplier.Supplier;
import com.indiraactive.fulfillmentplatform.dao.supplier.SupplierRepository;
import com.indiraactive.fulfillmentplatform.domain.RunHistoryModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

@Component
public class InventoryUpdaterManagerImpl {
    /**
     * Runs the sync_inventory script based on a supplier provided to it
     */
    @Autowired
    private InventoryUpdater inventoryUpdater;

    /**
     * Abstraction to physical database that creates, reads, and deletes suppliers
     */
    @Autowired
    private SupplierRepository supplierRepository;

    @Autowired
    private ScriptRunAuditEntryRepository scriptRunAuditEntryRepository;

    public boolean updateInventory(Supplier supplier) {
        try {
            System.out.println("STARTED RUNNING SYNC_INVENTORY.PY");
            inventoryUpdater.updateInventory(supplier.getSupplierId());
            System.out.println("FINISHED RUNNING SYNC_INVENTORY.PY");
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    public List<RunHistoryModel> getRunHistory(String order, String property) {
        List<Supplier> supplierRepositories = new LinkedList<>();
        supplierRepository.findAll().forEach(supplierRepositories::add);

        List<ScriptRunAuditEntry> scriptRunAuditEntries = new LinkedList<>();
        if (order != null) {
            if (order.equalsIgnoreCase("asc") && property.equalsIgnoreCase("startDate")) {
                scriptRunAuditEntries.addAll(scriptRunAuditEntryRepository.findAllByOrderByStartDateTimeAsc());
            } else if (order.equalsIgnoreCase("desc") && property.equalsIgnoreCase("startDate")) {
                scriptRunAuditEntries.addAll(scriptRunAuditEntryRepository.findAllByOrderByStartDateTimeDesc());
            } else if (order.equalsIgnoreCase("asc") && property.equalsIgnoreCase("endDate")) {
                scriptRunAuditEntries.addAll(scriptRunAuditEntryRepository.findAllByOrderByFinishDateTimeAsc());
            } else if (order.equalsIgnoreCase("desc") && property.equalsIgnoreCase("endDate")) {
                scriptRunAuditEntries.addAll(scriptRunAuditEntryRepository.findAllByOrderByFinishDateTimeDesc());
            }
        } else {
            scriptRunAuditEntries.addAll(scriptRunAuditEntryRepository.findAll());
        }

        return getRunHistoryModel(scriptRunAuditEntries, supplierRepositories);
    }

    public Iterable<Supplier> getSuppliersToUpdate() {
        return supplierRepository.findAll();
    }

    public List<RunHistoryModel> getRunHistoryModel(List<ScriptRunAuditEntry> scriptRunAuditEntries, List<Supplier> suppliers) {
        List<RunHistoryModel> runHistoryModels = new LinkedList<>();
        for (ScriptRunAuditEntry scriptRunAuditEntry: scriptRunAuditEntries) {
            RunHistoryModel runHistoryModel = new RunHistoryModel();
            runHistoryModel.setEndDateTime(new Date(scriptRunAuditEntry.getFinishDateTime()));
            runHistoryModel.setStartDateTime(new Date(scriptRunAuditEntry.getStartDateTime()));
            runHistoryModel.setScriptRunAuditEntryId(scriptRunAuditEntry.getScriptRunAuditEntryId());
            runHistoryModel.setSuccessCode(scriptRunAuditEntry.getSuccessCode());
            runHistoryModel.setSupplierName(getSupplierNameById(suppliers, scriptRunAuditEntry.getSupplierId()));
            runHistoryModel.setUserTriggered(scriptRunAuditEntry.getUserTriggered());

            runHistoryModels.add(runHistoryModel);
        }

        return runHistoryModels;
    }

    private String getSupplierNameById(List<Supplier> suppliers, Integer supplierId) {
        for (Supplier supplier: suppliers) {
            if (supplier.getSupplierId() == supplierId.intValue()) {
                return supplier.getSupplierName();
            }
        }

        return "No Supplier Name Found";
    }
}
