package kr.dataportal.cms.repository;

import kr.dataportal.cms.domain.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class MemoryMemberRepositoryTest {
    MemoryMemberRepository repository = new MemoryMemberRepository();

    @AfterEach
    void afterEach() {
        repository.clearStore();
    }

    @Test
    void save() {
        Member member = new Member();
        member.setDisplayName("Jin TaeYang");
        member.setUserName("taeyang");
        member.setPassword(member.getUserName() + "password");

        repository.save(member);

        Member result = repository.findById(member.getId()).get();

        assertThat(member).isEqualTo(result);
    }

    @Test
    void findByAuthId() {
        Member member1 = new Member();
        member1.setDisplayName("Jin TaeYang");
        member1.setUserName("taeyang");
        member1.setPassword(member1.getUserName() + "password");
        member1.setAuthId("local:" + member1.getUserName());
        repository.save(member1);

        Member member2 = new Member();
        member2.setDisplayName("Spring Framework");
        member2.setUserName("spring");
        member2.setPassword(member2.getUserName() + "password");
        member2.setAuthId("local:" + member2.getUserName());
        repository.save(member2);

        Member result = repository.findByAuthId("local:taeyang").get();
        assertThat(member1).isEqualTo(result);
    }

    @Test
    void findAll() {
        Member member1 = new Member();
        member1.setDisplayName("Jin TaeYang");
        member1.setUserName("taeyang");
        member1.setPassword(member1.getUserName() + "password");
        member1.setAuthId("local:" + member1.getUserName());
        repository.save(member1);

        Member member2 = new Member();
        member2.setDisplayName("Spring Framework");
        member2.setUserName("spring");
        member2.setPassword(member2.getUserName() + "password");
        member2.setAuthId("local:" + member2.getUserName());
        repository.save(member2);

        List<Member> result = repository.findAll();

        assertThat(result.size()).isEqualTo(2);
    }
}