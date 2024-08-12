package com.sparta.porject.service;

import com.sparta.porject.dto.ScheduleRequestDto;
import com.sparta.porject.dto.ScheduleResponseDto;
import com.sparta.porject.entity.Schedule;
import com.sparta.porject.repository.ScheduleRepository;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;

public class ScheduleService {
    private final JdbcTemplate jdbcTemplate;
    public ScheduleService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public ScheduleResponseDto createSchedule(ScheduleRequestDto requestDto) {

        //entity 로 변경
        Schedule schedule = new Schedule(requestDto);

        //DB에 저장
        ScheduleRepository scheduleRepository = new ScheduleRepository(jdbcTemplate);
        Schedule saveSchedule = scheduleRepository.save(schedule);

        ScheduleResponseDto scheduleResponseDto = new ScheduleResponseDto(schedule);
        return scheduleResponseDto;
    }
}
