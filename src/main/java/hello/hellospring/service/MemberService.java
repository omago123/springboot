package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;

import java.util.List;
import java.util.Optional;

public class MemberService {

    private final MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }


    // 회원 가입
    public Long join(Member member){
        // 같은 이름이 있는 중복 회원X
        validateDuplicateMember(member);
        // 체크후 겹치지 않으면 회원정보 저장
        memberRepository.save(member);
        // 회원 ID return
        return member.getId();
    }

    private void validateDuplicateMember(Member member) {
        memberRepository.findByName(member.getName())
                .ifPresent(m->{
                       // 기존회원의 이름과 같다면 에러메세지 출력
                       throw new IllegalStateException("이미 존재하는 회원입니다.");
                });
    }


    // 전체 회원 조회
    public List<Member> findMembers(){
        return memberRepository.findAll();
    }

    public Optional <Member> findOne(Long memberId) {
        return memberRepository.findById(memberId);
    }
}
