package com.sparta.porject.entity;


import com.sparta.porject.dto.ScheduleRequestDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;

//DB랑 소통
@Getter
@Setter
@NoArgsConstructor
public class Schedule {
    private Long id;
    private String name;
    private String password;
    private String to_do;
    private Date write_day;
    private Date update_day;

    public Schedule(ScheduleRequestDto scheduleRequestDto) {
        this.id = scheduleRequestDto.getId();
        this.name = scheduleRequestDto.getName();
        this.password = scheduleRequestDto.getPassword();
        this.to_do = scheduleRequestDto.getTo_do();
        this.write_day = scheduleRequestDto.getWrite_day();
        this.update_day = scheduleRequestDto.getUpdate_day();
    }
}
