package hello.hellospring.repository;

import hello.hellospring.domain.Member;

import java.util.*;

public class MemoryMemberRepository implements MemberRepository {

    // 1. 회원정보를 저장할 수단으로 Map을 사용한다.
    private static Map<Long, Member> store = new HashMap<>();
    private static long sequence = 0L;

    //멤버의 id와 name 저장
    @Override
    public Member save(Member member) {
        // member의 id 설정  sequence 값은 순차적으로 올라간다.(index)
        member.setId(++sequence);
        // key : id , value : member
        store.put(member.getId(), member);
        return member;
    }

    @Override                                   // id가 없으면 null을 반환하기때문에 NPE를 피하기 위해서 Optional로 감싸준다.
    public Optional<Member> findById(Long id) {
        return Optional.ofNullable(store.get(id));
    }

    @Override
    public Optional<Member> findByName(String name) {
        return store.values().stream()
                .filter(member -> member.getName().equals(name))  // 회원목록중에 일치하는 이름이 있는지 필터링
                .findAny();  //일치하는 값이 하나라도 나오면 즉시 return
    }


    @Override         // store에 저장되어있는 모든 회원의 이름들을 반환한다.
    public List<Member> findAll() {
        return new ArrayList<>(store.values());
    }


    public void clearStore (){
        store.clear();
    }



}
