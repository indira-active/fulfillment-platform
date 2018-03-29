package com.indiraactive.fulfillmentplatform.factory;

import com.indiraactive.fulfillmentplatform.dal.scheduledTask.ScheduledTaskRunDaysJpa;

/**
 * Creates a child object for ScheduledTaskJpa. The ScheduledTaskRunOnDays object
 * represents which days of the week the job will be ran on.
 */
public class ScheduledTaskRunOnDaysJpaFactory {
    /**
     * Creates a scheduledTaskRunDaysJpa object. This object contains the days of the week, if
     * the values are set to false the job will not run on that given day. This will assist creating
     * this object if there is any obscure logic that needs to be applied.
     * @param sun Execute on Sunday
     * @param mon Execute on Monday
     * @param tue Execute on Tuesday
     * @param wed Execute on Wednesday
     * @param thu Execute on Thursday
     * @param fri Execute on Friday
     * @param sat Execute on Saturday
     * @return ScheduledTaskRunDaysJpa, data representation of what days of the week the job will run on.
     */
    public ScheduledTaskRunDaysJpa createScheduledTaskRunOnDaysJpa(boolean sun, boolean mon, boolean tue, boolean wed, boolean thu, boolean fri, boolean sat) {
        ScheduledTaskRunDaysJpa scheduledTaskRunDaysJpa = new ScheduledTaskRunDaysJpa();
        scheduledTaskRunDaysJpa.setSunday(sun);
        scheduledTaskRunDaysJpa.setSunday(mon);
        scheduledTaskRunDaysJpa.setSunday(tue);
        scheduledTaskRunDaysJpa.setSunday(wed);
        scheduledTaskRunDaysJpa.setSunday(thu);
        scheduledTaskRunDaysJpa.setSunday(fri);
        scheduledTaskRunDaysJpa.setSunday(sat);

        return scheduledTaskRunDaysJpa;
    }
}
