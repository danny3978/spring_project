package com.sparta.porject.dto;

import com.sparta.porject.entity.Schedule;
import lombok.Getter;

import java.sql.Date;

//클라이언트에 응답을 보내는 클래스
@Getter
public class ScheduleResponseDto {
    private String name;
    private String password;
    private String to_do;
    private Date write_day;
    private Date update_day;

    public ScheduleResponseDto(Schedule schedule){
        this.name = schedule.getName();
        this.password = schedule.getPassword();
        this.to_do = schedule.getTo_do();
        this.write_day = schedule.getWrite_day();
        this.update_day = schedule.getUpdate_day();
    }

    public ScheduleResponseDto(String name, String password, String to_do, Date write_day, Date update_day){
        this.name = name;
        this.password = password;
        this.to_do = to_do;
        this.write_day = write_day;
        this.update_day = update_day;
    }
}
