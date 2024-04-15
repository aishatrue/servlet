package hello.servlet.springmvc.v1;

import hello.servlet.domain.member.Member;
import hello.servlet.domain.member.MemoryMemberRepository;
import hello.servlet.web.frontcontroller.ModelView;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

@Controller
public class SpringMemberSaveControllerV1 {


    private MemoryMemberRepository memberRepository = MemoryMemberRepository.getInstance();


    @RequestMapping("/springmvc/v1/members/save")
    public ModelAndView process(HttpServletRequest request, HttpServletResponse response) {
        String username = request.getParameter("username");
        int age = Integer.parseInt(request.getParameter("age"));

        Member member = new Member(username, age);
        memberRepository.save(member);

        //Model에 데이터를 보관한다. 이 부분이 다름(request내부에 map같은게 있음. 지금 당장은 Model자체를 쓰는건 아님)
        String viewPath = "save-result";
        ModelAndView modelView = new ModelAndView(viewPath);

//        modelView.getModel().put("member",member);
        //modelAndView도 결국은 HashMap타입의 map을 가짐. 그래서 getModel안해도 되고, 바로 addObject하면 그 모델 맵에 넣을 수 있다.
        modelView.addObject("member",member);



        return modelView;
    }

}
