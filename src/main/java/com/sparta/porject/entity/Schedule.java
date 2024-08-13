package com.sparta.porject.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.sparta.porject.dto.ScheduleRequestDto;
import com.sparta.porject.dto.ScheduleResponseDto;
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

    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime write_day;

    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
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
