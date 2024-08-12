package com.sparta.porject.controller;

import com.sparta.porject.dto.ScheduleRequestDto;
import com.sparta.porject.dto.ScheduleResponseDto;
import com.sparta.porject.service.ScheduleService;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class ScheduleController {

    private final JdbcTemplate jdbcTemplate;

    public ScheduleController(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }

    //일정 등록
    @PostMapping("/add")
    public ScheduleResponseDto scheduleResponseDto(ScheduleRequestDto requestDto){
        ScheduleService scheduleService = new ScheduleService(jdbcTemplate);
        return scheduleService.createSchedule(requestDto);
    }
}
