package com.pwang.shopping.domain.member.service;

import com.nimbusds.jose.shaded.json.JSONObject;
import com.pwang.shopping.ShoppingApplication;
import com.pwang.shopping.domain.member.entity.Member;
import com.pwang.shopping.domain.member.entity.OAuthType;
import com.pwang.shopping.domain.member.entity.Role;
import com.pwang.shopping.domain.member.repository.MemberRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = ShoppingApplication.class)
class MemberserviceTest {

    @Autowired
    MemberRepository memberRepository;

    @Test
    @DisplayName("네이버_생성_및_조회")
    @Rollback(value = false)
    void saveMember() {
        Member member = new Member();
        JSONObject jsonObject = new JSONObject();
        String email = "test22@nav2er.com";
        jsonObject.put("email", email);
        jsonObject.put("name", "ktko");
        jsonObject.put("gender", "M");
        jsonObject.put("birthyear", "1994");
        jsonObject.put("mobile", "010-3078-1207");

        Member member1 = Member.builder()
                .name("test")
                .mobile("01030781207")
                .role(Role.ADMIN)
                .type(OAuthType.KAKAO)
                .password("123")
                .gender("M")
                .email("cacvvvvvvva@naver.com")
                .build();

        memberRepository.save(member1);

    }

    @Test
    @DisplayName("회원멤버_조회")
    void findMember() {
        String email = "sky@naver.com";
        Member member= memberRepository.findByEmailAndType(email, OAuthType.NAVER).orElseThrow();

        boolean result = true;
        System.out.println(member.getId());

        if(member.getId().equals("")) {
            result = false;
        }
        assertEquals(result, true);
    }

    @Test
    @DisplayName("전체회원멤버_조회")
    void findByAllMember() {

       List<Member> memberList = memberRepository.findAll();
    }

    @Test
    @DisplayName("회원멤버_삭제")
    void deleteMembeer() {
        String email = "sky@naver.com";
        memberRepository.delete(memberRepository.findByEmailAndType(email, OAuthType.NAVER)
                .orElseThrow()
        );
    }


    @Test
    @DisplayName("날짜 데이터")
    void TEST() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String date = LocalDateTime.now().format(formatter);

        LocalDateTime localDateTime = returnDate(LocalDateTime.parse(date, formatter));
        System.out.println("###");
        System.out.println(localDateTime);
    }


    public LocalDateTime returnDate(LocalDateTime date)  {


        return date;
    }
}