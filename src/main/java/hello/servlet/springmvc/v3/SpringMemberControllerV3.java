package hello.servlet.springmvc.v3;

import hello.servlet.domain.member.Member;
import hello.servlet.domain.member.MemoryMemberRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/springmvc/v3/members")
public class SpringMemberControllerV3 {
    private MemoryMemberRepository memberRepository = MemoryMemberRepository.getInstance();

    @RequestMapping(value = "/new-form",method = RequestMethod.GET)
    public String newform(){
        return "new-form"; //ModelAndView는 모델과 뷰 정보를 담아서 반환.
    }

    @RequestMapping(value = "/save",method = RequestMethod.POST)
    public String save(@RequestParam("username")String username,
                       @RequestParam("age")int age,
                       Model model

                       ) {

        Member member = new Member(username, age);
        memberRepository.save(member);


        model.addAttribute("member", member);



        return "save-result";
    }

    // springmvc/v2/members/members
    //여기 /members를 하면 위에서 내려온 주소+/members임.
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String membersList(Model model) {
        List<Member> memberList = memberRepository.findAll();

//        modelView.getModel().put("memberList", memberList);
        model.addAttribute("memberList", memberList);


        return "list";
    }
}
