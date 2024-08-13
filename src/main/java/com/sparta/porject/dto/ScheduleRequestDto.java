package com.sparta.porject.dto;


import lombok.Getter;
import lombok.Setter;

import java.sql.Date;

//클라이언트에서 요청 받는 클래스
@Getter
@Setter
public class ScheduleRequestDto {
    private String name;
    private String password;
    private String to_do;
}
