package com.sparta.porject.dto;


import lombok.Getter;

import java.sql.Date;

//클라이언트에서 요청 받는 클래스
@Getter
public class ScheduleRequestDto {
    private Long id;
    private String name;
    private String password;
    private String to_do;
    private Date write_day;
    private Date update_day;


}
