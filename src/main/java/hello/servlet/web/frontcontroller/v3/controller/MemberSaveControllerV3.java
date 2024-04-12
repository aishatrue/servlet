package hello.servlet.web.frontcontroller.v3.controller;

import hello.servlet.domain.member.Member;
import hello.servlet.domain.member.MemoryMemberRepository;
import hello.servlet.web.frontcontroller.ModelView;
import hello.servlet.web.frontcontroller.v3.ControllerV3;

import java.util.Map;

public class MemberSaveControllerV3 implements ControllerV3 {

    private MemoryMemberRepository memberRepository = MemoryMemberRepository.getInstance();
    @Override
    public ModelView process(Map<String, String> paramMap) {
        String username = paramMap.get("username");
        int age = Integer.parseInt(paramMap.get("age"));

        Member member = new Member(username, age);
        memberRepository.save(member);

        //Model에 데이터를 보관한다. 이 부분이 다름(request내부에 map같은게 있음. 지금 당장은 Model자체를 쓰는건 아님)
        String viewPath = "save-result";
        ModelView modelView = new ModelView(viewPath);

        modelView.getModel().put("member",member);



        return modelView;
    }
}
