package com.example.restfulwebservice;


import lombok.AllArgsConstructor;
import lombok.Data;

@Data   // Setter Getter 다 생성됨
@AllArgsConstructor // 생성자 기능 @NoArgsConstructor는 매개변수가 없는 생성자 생성
public class HelloWorldBean {

    private String message;

}
