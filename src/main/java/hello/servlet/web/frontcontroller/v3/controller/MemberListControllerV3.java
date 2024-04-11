package hello.servlet.web.frontcontroller.v3.controller;

import hello.servlet.domain.member.Member;
import hello.servlet.domain.member.MemoryMemberRepository;
import hello.servlet.web.frontcontroller.ModelView;
import hello.servlet.web.frontcontroller.v3.ControllerV3;

import java.util.List;
import java.util.Map;

public class MemberListControllerV3 implements ControllerV3 {
    private MemoryMemberRepository memberRepository = MemoryMemberRepository.getInstance();
    @Override
    public ModelView process(Map<String, String> paramMap) {
        List<Member> memberList = memberRepository.findAll();
        String viewPath = "list";

        ModelView modelView = new ModelView(viewPath);
        modelView.getModel().put("memberList", memberList);


        return modelView;
    }
}
