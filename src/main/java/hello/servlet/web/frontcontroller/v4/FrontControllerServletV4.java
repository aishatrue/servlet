package hello.servlet.web.frontcontroller.v4;

import hello.servlet.web.frontcontroller.MyView;
import hello.servlet.web.frontcontroller.v4.controller.MemberFormControllerV4;
import hello.servlet.web.frontcontroller.v4.controller.MemberListControllerV4;
import hello.servlet.web.frontcontroller.v4.controller.MemberSaveControllerV4;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


@WebServlet(name = "FrontControllerServletV4",urlPatterns = "/front-controller/v4/*")
public class FrontControllerServletV4 extends HttpServlet {

    private Map<String, ControllerV4> controllerV4Map = new HashMap<>();
    public FrontControllerServletV4() {
        controllerV4Map.put("/front-controller/v4/members/new-form", new MemberFormControllerV4());
        controllerV4Map.put("/front-controller/v4/members/save", new MemberSaveControllerV4());
        controllerV4Map.put("/front-controller/v4/members", new MemberListControllerV4());

    }
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("FrontControllerServletV4.service");
        //model에 저장할 데이터를 request를 통해 가져와, map에 저장.
        Map<String,String> paramMap = new HashMap<>();
        Map<String, Object> model = new HashMap<>();

        request.getParameterNames().asIterator()
                .forEachRemaining(paramName-> paramMap.put(paramName, request.getParameter(paramName)));

        String requestURI = request.getRequestURI();
        ControllerV4 controllerV4 = controllerV4Map.get(requestURI);

        if(controllerV4==null){
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }else{
            //request로 받아서 저장한 map을 modelview의 model에 저장또는 등등.
            String viewName = controllerV4.process(paramMap,model);

            //최종 렌더링을 하는곳.
            MyView myView = viewResolver(viewName);
            myView.render(model,request,response);


        }
    }

    private static MyView viewResolver(String viewName) {
        MyView myView = new MyView("/WEB-INF/views/" + viewName + ".jsp");
        return myView;
    }
}
