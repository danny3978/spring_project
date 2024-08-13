package com.sparta.porject.dto;

import com.sparta.porject.entity.Schedule;
import lombok.Getter;

import java.time.LocalDateTime;

// 클라이언트에 응답을 보내는 클래스
@Getter
public class ScheduleResponseDto {
    private Long id;
    private String name;
    private String password;
    private String to_do;
    private LocalDateTime write_day;
    private LocalDateTime update_day;

    public ScheduleResponseDto(Schedule schedule) {
        this.id = schedule.getId();
        this.name = schedule.getName();
        this.password = schedule.getPassword();
        this.to_do = schedule.getTo_do();
        this.write_day = schedule.getWrite_day();
        this.update_day = schedule.getUpdate_day();
    }

    public ScheduleResponseDto(Long id, String name, String password, String to_do, LocalDateTime write_day, LocalDateTime update_day){
        this.id = id;
        this.name = name;
        this.password = password;
        this.to_do = to_do;
        this.write_day = write_day;
        this.update_day = update_day;
    }
}

