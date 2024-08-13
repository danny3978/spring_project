package com.sparta.porject.dto;

import com.sparta.porject.entity.Schedule;
import lombok.Getter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

// 클라이언트에 응답을 보내는 클래스
@Getter
public class ScheduleResponseDto {
    private Long id;
    private String name;
    private String to_do;
    private String write_day;
    private String update_day;

    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");


    public ScheduleResponseDto(Schedule schedule) {
        this.id = schedule.getId();
        this.name = schedule.getName();
        this.to_do = schedule.getTo_do();
        this.write_day = formatDate(schedule.getWrite_day());
        this.update_day = formatDate(schedule.getUpdate_day());
    }

    public ScheduleResponseDto(Long id, String name, String password, String to_do, LocalDateTime write_day, LocalDateTime update_day){
        this.id = id;
        this.name = name;
        this.to_do = to_do;
        this.write_day = formatDate(write_day);
        this.update_day = formatDate(update_day);
    }

    private String formatDate(LocalDateTime dateTime) {
        return dateTime != null ? dateTime.format(DATE_FORMATTER) : null;
    }
}

