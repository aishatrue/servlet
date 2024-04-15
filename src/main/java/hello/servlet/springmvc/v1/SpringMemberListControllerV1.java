package hello.servlet.springmvc.v1;

import hello.servlet.domain.member.Member;
import hello.servlet.domain.member.MemoryMemberRepository;
import hello.servlet.web.frontcontroller.ModelView;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Map;

@Controller
public class SpringMemberListControllerV1 {
    private MemoryMemberRepository memberRepository = MemoryMemberRepository.getInstance();
    @RequestMapping("/springmvc/v1/members")
    public ModelAndView process(Map<String, String> paramMap) {
        List<Member> memberList = memberRepository.findAll();
        String viewPath = "list";

        ModelAndView modelView = new ModelAndView(viewPath);
//        modelView.getModel().put("memberList", memberList);
        modelView.addObject("memberList", memberList);


        return modelView;
    }
}
