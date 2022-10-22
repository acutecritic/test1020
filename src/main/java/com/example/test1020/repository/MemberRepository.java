package com.example.test1020.repository;


import com.example.test1020.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {

    Optional<Member> findAllByNickname(String nickname);
    //메소드 쿼리
    boolean existsByNickname(String nickname);

}
