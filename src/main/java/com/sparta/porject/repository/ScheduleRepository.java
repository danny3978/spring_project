package com.sparta.porject.repository;

import com.sparta.porject.dto.ScheduleRequestDto;
import com.sparta.porject.dto.ScheduleResponseDto;
import com.sparta.porject.entity.Schedule;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ScheduleRepository {

    private final JdbcTemplate jdbcTemplate;

    public ScheduleRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    //DB 저장
    public Schedule save(Schedule schedule) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        String sql = "INSERT INTO scheduleuser (name, password, to_do, write_day, update_day) VALUES (?, ?, ?, ?, ?)";

        jdbcTemplate.update(con -> {
            PreparedStatement preparedStatement = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, schedule.getName());
            preparedStatement.setString(2, schedule.getPassword());
            preparedStatement.setString(3, schedule.getTo_do());
            preparedStatement.setTimestamp(4, Timestamp.valueOf(schedule.getWrite_day()));
            preparedStatement.setTimestamp(5, Timestamp.valueOf(schedule.getUpdate_day()));
            return preparedStatement;
        }, keyHolder);

        Long id = keyHolder.getKey().longValue();
        schedule.setId(id);
        return schedule;
    }

    //전체 일정 조회
    public List<ScheduleResponseDto> finAll(LocalDate updateDay, String name) {

        String sql = "SELECT * FROM scheduleuser WHERE 1 = 1";
        List<Object> params = new ArrayList<>();
        if(updateDay != null){
            sql += " AND DATE(update_day) = ?";
            params.add(Date.valueOf(updateDay));
        }
        if(name != null){
            sql += " AND name = ?";
            params.add(name);
        }

        sql += " ORDER BY update_day DESC";


        return jdbcTemplate.query(sql, params.toArray(), new RowMapper<ScheduleResponseDto>() {
            @Override
            public ScheduleResponseDto mapRow(ResultSet rs, int rowNum) throws SQLException {
                Long id = rs.getLong("id");
                String name = rs.getString("name");
                String password = rs.getString("password");
                String to_do = rs.getString("to_do");
                LocalDateTime write_day = rs.getTimestamp("write_day").toLocalDateTime();
                LocalDateTime update_day = rs.getTimestamp("update_day").toLocalDateTime();
                return new ScheduleResponseDto(id, name, password, to_do, write_day, update_day);
            }
        });
    }

    //선택한 일정 수정
    public void updateSchedule(String name, String password, ScheduleRequestDto scheduleRequestDto) {
        String sql = "UPDATE scheduleuser SET name = ?, to_do = ? ,update_day = ? WHERE name = ? AND password = ?";
        jdbcTemplate.update(sql, scheduleRequestDto.getName(), scheduleRequestDto.getTo_do(), Timestamp.valueOf(LocalDateTime.now()), name, password);
    }

    //선택한 일정 삭제
    public void deleteSchedule(String name, String password) {
        String sql = "DELETE FROM scheduleuser WHERE name = ? AND password = ?";
        jdbcTemplate.update(sql, name, password);
    }

    //선택한 일정 조회 (id)
    public Schedule findById(Long id) {

        String sql = "SELECT * FROM scheduleuser WHERE id = ?";

        return jdbcTemplate.query(sql, resultSet -> {
            if(resultSet.next()) {
                Schedule schedule = new Schedule();
                schedule.setId(resultSet.getLong("id"));
                schedule.setName(resultSet.getString("name"));
                schedule.setPassword(resultSet.getString("password"));
                schedule.setTo_do(resultSet.getString("to_do"));
                schedule.setWrite_day(resultSet.getTimestamp("write_day").toLocalDateTime());
                schedule.setUpdate_day(resultSet.getTimestamp("update_day").toLocalDateTime());
                return schedule;
            } else {
                return null;
            }
        }, id);
    }

    //선택한 일정 조회 (name, password)
    public Schedule findByNamePassword(String name, String password) {

        String sql = "SELECT * FROM scheduleuser WHERE name = ? AND password = ?";

        return jdbcTemplate.query(sql, resultSet -> {
            if(resultSet.next()) {
                Schedule schedule = new Schedule();
                schedule.setId(resultSet.getLong("id"));
                schedule.setName(resultSet.getString("name"));
                schedule.setPassword(resultSet.getString("password"));
                schedule.setTo_do(resultSet.getString("to_do"));
                schedule.setWrite_day(resultSet.getTimestamp("write_day").toLocalDateTime());
                schedule.setUpdate_day(resultSet.getTimestamp("update_day").toLocalDateTime());
                return schedule;
            } else {
                return null;
            }
        }, name,password);
    }


}

