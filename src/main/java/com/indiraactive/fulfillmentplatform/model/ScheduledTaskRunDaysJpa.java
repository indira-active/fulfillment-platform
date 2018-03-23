package com.indiraactive.fulfillmentplatform.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class ScheduledTaskRunDaysJpa {
    @Column
    private boolean sunday;

    @Column
    private boolean monday;

    @Column
    private boolean tuesday;

    @Column
    private boolean wednesday;

    @Column
    private boolean thursday;

    @Column
    private boolean friday;

    @Column
    private boolean saturday;

    public boolean isSunday() {
        return sunday;
    }

    public void setSunday(boolean sunday) {
        this.sunday = sunday;
    }

    public boolean isMonday() {
        return monday;
    }

    public void setMonday(boolean monday) {
        this.monday = monday;
    }

    public boolean isTuesday() {
        return tuesday;
    }

    public void setTuesday(boolean tuesday) {
        this.tuesday = tuesday;
    }

    public boolean isWednesday() {
        return wednesday;
    }

    public void setWednesday(boolean wednesday) {
        this.wednesday = wednesday;
    }

    public boolean isThursday() {
        return thursday;
    }

    public void setThursday(boolean thursday) {
        this.thursday = thursday;
    }

    public boolean isFriday() {
        return friday;
    }

    public void setFriday(boolean friday) {
        this.friday = friday;
    }

    public boolean isSaturday() {
        return saturday;
    }

    public void setSaturday(boolean saturday) {
        this.saturday = saturday;
    }
}
