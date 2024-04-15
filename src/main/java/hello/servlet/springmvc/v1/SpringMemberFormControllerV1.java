package hello.servlet.springmvc.v1;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller //스프링이 자동으로 스프링 빈으로 등록. Controller안에 @Component있어서.
//RequestMappingHandlerMapping은 스프링 빈 중에서 @RequestMapping또는 @Controller가 클래스레벨에
//붙어있는 경우에 매핑정보로 인식함. -> 핸들러 정보구나 하고 꺼낼 수 있는 대상이 됨.
public class SpringMemberFormControllerV1 {

    @RequestMapping("/springmvc/v1/members/new-form")
    public ModelAndView process(){
        return new ModelAndView("new-form"); //ModelAndView는 모델과 뷰 정보를 담아서 반환.
    }
}
