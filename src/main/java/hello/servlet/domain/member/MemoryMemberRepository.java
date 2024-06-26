package hello.servlet.domain.member;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class MemoryMemberRepository {

    private static Map<Long,Member> store = new HashMap<>();
    private static long sequence = 0L;
    private static final MemoryMemberRepository instance = new MemoryMemberRepository();

    public static MemoryMemberRepository getInstance(){
        return instance;
    }

    private MemoryMemberRepository(){

    }

    public Member save(Member member){
        member.setId(++sequence);
        store.put(member.getId(), member);
        return member;
    }
    public Member findById(Long id){
        return store.get(id);
    }
    //store의 값들을 변경시키기 싫어서
    public List<Member> findAll(){
        return new ArrayList<>(store.values());

    }
    public void clearStore(){
        store.clear();
    }


}
