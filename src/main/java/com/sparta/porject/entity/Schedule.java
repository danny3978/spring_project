package com.sparta.porject.entity;

import com.sparta.porject.dto.ScheduleRequestDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

// DB랑 소통
@Getter
@Setter
@NoArgsConstructor
public class Schedule {
    private Long id;
    private String name;
    private String password;
    private String to_do;

    private LocalDateTime write_day;
    private LocalDateTime update_day;

    public Schedule(ScheduleRequestDto scheduleRequestDto) {
        this.name = scheduleRequestDto.getName();
        this.password = scheduleRequestDto.getPassword();
        this.to_do = scheduleRequestDto.getTo_do();
        this.write_day = LocalDateTime.now();
        this.update_day = LocalDateTime.now();
    }

    public void update(ScheduleRequestDto scheduleRequestDto){
        this.name = scheduleRequestDto.getName();
        this.password = scheduleRequestDto.getPassword();
        this.to_do = scheduleRequestDto.getTo_do();
        this.update_day = LocalDateTime.now();
    }
}
