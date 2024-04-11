package hello.servlet.web.frontcontroller.v2.controller;

import hello.servlet.web.frontcontroller.MyView;
import hello.servlet.web.frontcontroller.v2.ControllerV2;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class MemberFormControllerV2 implements ControllerV2 {
    @Override
    public MyView process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //기존 코드
        String viewPath ="/WEB-INF/views/new-form.jsp";
        //RequestDispatcher requestDispatcher = request.getRequestDispatcher(viewPath);  //컨트롤러에서 뷰로 이동하려고 사용.
        //requestDispatcher.forward(request,response);

        return new MyView(viewPath);
    }
}
