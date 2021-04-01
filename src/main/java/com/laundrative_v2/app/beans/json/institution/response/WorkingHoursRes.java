package com.laundrative_v2.app.beans.json.institution.response;


import com.laundrative_v2.app.beans.db.institutionDb.InstitutionWorkingDb;
import lombok.*;

import java.sql.Time;

@Data
@Getter
@Setter
@ToString
@NoArgsConstructor
public class WorkingHoursRes
{
    private int day;
    private Time startingTime;
    private Time endingTime;

    public static WorkingHoursRes from(InstitutionWorkingDb db)
    {
        WorkingHoursRes workingHoursRes = new WorkingHoursRes();

        workingHoursRes.setDay(db.getDay());
        workingHoursRes.setStartingTime(new Time(db.getStartingTime().getTime()));
        workingHoursRes.setEndingTime(new Time(db.getEndingTime().getTime()));

        return workingHoursRes;
    }
}
