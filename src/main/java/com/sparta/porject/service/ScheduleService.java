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

        //password가 있는지 확인
        ScheduleRepository scheduleRepository = new ScheduleRepository(jdbcTemplate);
        boolean checkPassword = scheduleRepository.checkPassword(schedule.getPassword());

        if(checkPassword){
            throw new IllegalArgumentException("이미 존재하는 비밀번호입니다.");
        }

        //DB 등록
        Schedule saveSchedule = scheduleRepository.save(schedule);


        //ResponseDto로 변경
        ScheduleResponseDto scheduleResponseDto = new ScheduleResponseDto(saveSchedule);
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
    public String updateSchedule(String name, String password ,ScheduleRequestDto scheduleRequestDto) {
       ScheduleRepository scheduleRepository = new ScheduleRepository(jdbcTemplate);
       Schedule schedule = scheduleRepository.findByNamePassword(name, password);
       if(schedule != null){
           scheduleRepository.updateSchedule(name, password, scheduleRequestDto);
           return "일정 수정 완료";
       }else {
           throw new IllegalArgumentException("비밀번호가 맞지 않습니다.");
       }
    }

    //선택한 일정 삭제
    public String deleteSchedule(String name, String password) {
       ScheduleRepository scheduleRepository = new ScheduleRepository(jdbcTemplate);
       Schedule schedule = scheduleRepository.findByNamePassword(name, password);
       if(schedule != null){
           scheduleRepository.deleteSchedule(name, password);
           return "일정 삭제 완료";
       }else {
           throw new IllegalArgumentException("비밀번호가 맞지 않습니다.");
       }
    }

}
