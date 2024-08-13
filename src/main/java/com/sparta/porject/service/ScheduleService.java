package com.sparta.porject.service;

import com.sparta.porject.dto.ScheduleRequestDto;
import com.sparta.porject.dto.ScheduleResponseDto;
import com.sparta.porject.entity.Schedule;
import com.sparta.porject.repository.ScheduleRepository;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class ScheduleService {

   private final JdbcTemplate jdbcTemplate;

   public ScheduleService (JdbcTemplate jdbcTemplate){
       this.jdbcTemplate = jdbcTemplate;
   }

   //일정 생성
    public ScheduleResponseDto createSchedule(ScheduleRequestDto requestDto) {
        //Entity로 변경
        Schedule schedule = new Schedule(requestDto);

        //DB 등록
        ScheduleRepository scheduleRepository = new ScheduleRepository(jdbcTemplate);
        Schedule saveSchedule = scheduleRepository.save(schedule);

        //ResponseDto로 변경
        ScheduleResponseDto scheduleResponseDto = new ScheduleResponseDto(schedule);
        return scheduleResponseDto;
    }

    //선택한 일정 조회
    public ScheduleResponseDto choiceSelect(Long id) {
       ScheduleRepository scheduleRepository = new ScheduleRepository(jdbcTemplate);
       Schedule schedule = scheduleRepository.findById(id);
       if(schedule != null){
           return new ScheduleResponseDto(schedule);
       }else {
           throw new IllegalArgumentException("선택하신 번호가 없습니다.");
       }

    }

    //전체 일정 조회
    public List<ScheduleResponseDto> findAll(LocalDate updateDay, String name) {
       ScheduleRepository scheduleRepository = new ScheduleRepository(jdbcTemplate);
       return scheduleRepository.finAll(updateDay, name);
    }

    //선택한 일정 수정
    public String updateSchedule(String password ,ScheduleRequestDto scheduleRequestDto) {
       ScheduleRepository scheduleRepository = new ScheduleRepository(jdbcTemplate);
       Schedule schedule = scheduleRepository.findByPassword(password);
       if(schedule != null){
           scheduleRepository.updateSchedule(password, scheduleRequestDto);
           return password;
       }else {
           throw new IllegalArgumentException("비밀번호가 맞지 않습니다.");
       }
    }

    //선택한 일정 삭제
    public String deleteSchedule(String password) {
       ScheduleRepository scheduleRepository = new ScheduleRepository(jdbcTemplate);
       Schedule schedule = scheduleRepository.findByPassword(password);
       if(schedule != null){
           scheduleRepository.deleteSchedule(password);
           return password;
       }else {
           throw new IllegalArgumentException("비밀번호가 맞지 않습니다.");
       }
    }

}
