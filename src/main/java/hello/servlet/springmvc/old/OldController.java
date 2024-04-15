package hello.servlet.springmvc.old;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

@Component("/springmvc/old-controller") //스프링 빈의 이름을 이걸로
public class OldController implements Controller {
    @Override
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
        System.out.println("OldController.handleRequest");
        return new ModelAndView("new-form");  //new-form이라는 스프링빈이 있으면,BeanNameViewResolver를 우선으로 썼을것. 암튼 모델 데이터의 뷰의 이름을 설정해 반환.

    }
}
