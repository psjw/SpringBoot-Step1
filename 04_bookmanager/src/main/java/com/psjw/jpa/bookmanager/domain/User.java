package com.psjw.jpa.bookmanager.domain;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;


//@Getter
//@Setter
//@ToString
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
//@EqualsAndHashCode //자바의 동등성 비교하기 위해서 사용
@Data
@Builder
@Entity //Entity는 PK가 반드시 필요
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NonNull  //필수값이 됨 lombok 제공
    private String name;
    @NonNull
    private String email;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    //다른테스트를 위해 주석 IsEmpty()
//    @OneToMany(fetch = FetchType.EAGER)
//    private List<Address> address;

}
