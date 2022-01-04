package hello.hellospring.service.repository;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemoryMemberRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class MemoryMemberRepositoryTest {

     MemoryMemberRepository repository = new MemoryMemberRepository();

     // 테스트가 끝날때마다 저장소를 비워서 메모리충돌이 안 일어나게 설계
     // 각 테스트가 끝날때마다 실행
     @AfterEach
     public void afterEach() {
          repository.clearStore();
     }



     @Test
     public void save(){
          Member member = new Member();
          member.setName("string");

          repository.save(member);

          Member result = repository.findById(member.getId()).get();
          assertThat(member).isEqualTo(result);

     }

     @Test
     public void findByName(){
          Member member1 = new Member();
          member1.setName("spring1");
          repository.save(member1);

          Member member2 = new Member();
          member2.setName("spring2");
          repository.save(member2);

          Member result = repository.findByName("spring1").get();

          assertThat(result).isEqualTo(member1);
     }

     @Test
     public void findAll() {
          Member member1 = new Member();
          member1.setName("spring1");
          repository.save(member1);

          Member member2 = new Member();
          member2.setName("spring2");
          repository.save(member2);

          List<Member> result = repository.findAll();

          assertThat(result.size()).isEqualTo(2);
     }

}
