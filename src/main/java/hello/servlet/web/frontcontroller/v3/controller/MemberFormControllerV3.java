package hello.servlet.web.frontcontroller.v3.controller;

import hello.servlet.web.frontcontroller.ModelView;
import hello.servlet.web.frontcontroller.v3.ControllerV3;

import java.util.Map;

public class MemberFormControllerV3 implements ControllerV3 {
    @Override
    public ModelView process(Map<String, String> paramMap) {
        //기존 코드
        String viewPath ="new-form";
        //RequestDispatcher requestDispatcher = request.getRequestDispatcher(viewPath);  //컨트롤러에서 뷰로 이동하려고 사용.
        //requestDispatcher.forward(request,response);

        return new ModelView(viewPath);
    }
}
