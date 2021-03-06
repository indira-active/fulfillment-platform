package com.indiraactive.fulfillmentplatform.ViewModel;

import com.indiraactive.fulfillmentplatform.dal.ScriptRunAuditEntryRepository;
import com.indiraactive.fulfillmentplatform.dal.SupplierRepository;
import com.indiraactive.fulfillmentplatform.model.RunHistoryModel;
import com.indiraactive.fulfillmentplatform.model.db.ScriptRunAuditEntry;
import com.indiraactive.fulfillmentplatform.model.db.Supplier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import sun.font.ScriptRun;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

public class RunHistoryViewModel {
    private List<ScriptRunAuditEntry> scriptRunAuditEntries;
    public List<Supplier> suppliers;

    public RunHistoryViewModel(List<ScriptRunAuditEntry> scriptRunAuditEntries,  List<Supplier> suppliers)
    {
        this.scriptRunAuditEntries = scriptRunAuditEntries;
        this.suppliers = suppliers;
    }

    public List<RunHistoryModel> getRunHistoryModel() {
        List<RunHistoryModel> runHistoryModels = new LinkedList<>();
        for (ScriptRunAuditEntry scriptRunAuditEntry: scriptRunAuditEntries) {
            RunHistoryModel runHistoryModel = new RunHistoryModel();
            runHistoryModel.setEndDateTime(new Date(scriptRunAuditEntry.getFinishDateTime()));
            runHistoryModel.setStartDateTime(new Date(scriptRunAuditEntry.getStartDateTime()));
            runHistoryModel.setScriptRunAuditEntryId(scriptRunAuditEntry.getScriptRunAuditEntryId());
            runHistoryModel.setSuccessCode(scriptRunAuditEntry.getSuccessCode());
            runHistoryModel.setSupplierName(getSupplierNameById(scriptRunAuditEntry.getSupplierId()));
            runHistoryModel.setUserTriggered(scriptRunAuditEntry.getUserTriggered());

            runHistoryModels.add(runHistoryModel);
        }

        return runHistoryModels;
    }

    private String getSupplierNameById(Integer supplierId) {
        for (Supplier supplier: suppliers) {
            if (supplier.getSupplierId() == supplierId.intValue()) {
                return supplier.getSupplierName();
            }
        }

        return "No Supplier Name Found";
    }
}
