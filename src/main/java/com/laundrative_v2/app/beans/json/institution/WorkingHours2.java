package com.laundrative_v2.app.beans.json.institution;

import com.laundrative_v2.app.beans.db.institutionDb.InstitutionWorkingDb;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.sql.Time;

@Data
@Getter
@ToString
@NoArgsConstructor
public class WorkingHours2
{
    private Time startingTime;
    private Time endingTime;
    private int day;

    public WorkingHours2(Time startingDate, Time endingDate, int day)
    {
        this.startingTime = startingDate;
        this.endingTime   = endingDate;
        this.day = day;
    }

    public static WorkingHours2 from(InstitutionWorkingDb db)
    {
        WorkingHours2 workingHours2 = new WorkingHours2();

        Time time1 = new Time(db.getStartingTime().getTime());
        Time time2 = new Time(db.getEndingTime().getTime());

        workingHours2.setDay(db.getDay());
        workingHours2.setStartingTime(time1);
        workingHours2.setEndingTime(time2);

        return workingHours2;
    }
}