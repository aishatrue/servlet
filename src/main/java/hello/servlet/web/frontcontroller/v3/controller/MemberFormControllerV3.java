package hello.servlet.web.frontcontroller.v3.controller;

import hello.servlet.web.frontcontroller.ModelView;
import hello.servlet.web.frontcontroller.MyView;
import hello.servlet.web.frontcontroller.v3.ControllerV3;

import java.util.Map;

public class MemberFormControllerV3 implements ControllerV3 {
    @Override
    public ModelView process(Map<String, String> paramMap) {
        //기존 코드
//        String viewPath ="/WEB-INF/views/new-form.jsp";
        //RequestDispatcher requestDispatcher = request.getRequestDispatcher(viewPath);  //컨트롤러에서 뷰로 이동하려고 사용.
        //requestDispatcher.forward(request,response);
        String viewPath = "new-form";
        return new ModelView(viewPath);
    }
}
