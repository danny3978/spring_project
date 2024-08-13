package com.sparta.porject.controller;

import com.sparta.porject.dto.ScheduleRequestDto;
import com.sparta.porject.dto.ScheduleResponseDto;
import com.sparta.porject.service.ScheduleService;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api")
public class ScheduleController {

   private final JdbcTemplate jdbcTemplate;

    public ScheduleController(JdbcTemplate jdbcTemplate){
       this.jdbcTemplate = jdbcTemplate;
    }

    //일정 생성
    @PostMapping("/add")
    public ScheduleResponseDto createSchedule (@RequestBody ScheduleRequestDto requestDto){
        ScheduleService scheduleService = new ScheduleService(jdbcTemplate);
        return scheduleService.createSchedule(requestDto);
    }

    //전체 일정 조회
    @GetMapping("/findAll")
    public List<ScheduleResponseDto> findAll(@RequestParam(required = false) LocalDate updateDay, @RequestParam(required = false) String name){
        ScheduleService scheduleService = new ScheduleService(jdbcTemplate);
        return scheduleService.findAll(updateDay, name);
    }

    // 선택한 일정 조회
    @GetMapping("/choiceFind/{id}")
    public ScheduleResponseDto choiceFind (@PathVariable Long id){
        ScheduleService scheduleService = new ScheduleService(jdbcTemplate);
        return scheduleService.choiceSelect(id);
    }

    //선택한 일정 수정
    @PutMapping("/update/{password}")
    public String updateSchedule(@PathVariable String password, @RequestBody ScheduleRequestDto scheduleRequestDto){
        ScheduleService scheduleService = new ScheduleService(jdbcTemplate);
        return scheduleService.updateSchedule(password,scheduleRequestDto);
    }

    //선택한 일정 삭제
    @DeleteMapping("/delete/{password}")
    public String deleteSchedule(@PathVariable String password){
        ScheduleService scheduleService = new ScheduleService(jdbcTemplate);
        return scheduleService.deleteSchedule(password);
    }
}
