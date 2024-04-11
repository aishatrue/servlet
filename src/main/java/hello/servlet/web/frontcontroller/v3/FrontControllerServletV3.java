package hello.servlet.web.frontcontroller.v3;

import hello.servlet.web.frontcontroller.ModelView;
import hello.servlet.web.frontcontroller.MyView;
import hello.servlet.web.frontcontroller.v2.ControllerV2;
import hello.servlet.web.frontcontroller.v3.controller.MemberFormControllerV3;
import hello.servlet.web.frontcontroller.v3.controller.MemberListControllerV3;
import hello.servlet.web.frontcontroller.v3.controller.MemberSaveControllerV3;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


@WebServlet(name = "frontControllerServletV3",urlPatterns = "/front-controller/v3/*")
public class FrontControllerServletV3 extends HttpServlet {

    private Map<String, ControllerV3> controllerV3Map = new HashMap<>();


    //이것도 궁금하다 생성자 호출한적 없잖슴.

    public FrontControllerServletV3() {
        controllerV3Map.put("/front-controller/v3/members/new-form", new MemberFormControllerV3());
        controllerV3Map.put("/front-controller/v3/members/save", new MemberSaveControllerV3());
        controllerV3Map.put("/front-controller/v3/members", new MemberListControllerV3());

    }

    @Override
    //프론트에서는 클라이언트와 소통하는 역할. request받고, 최종적으로 html을 내보낼 jsp를 호출.
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        System.out.println("FrontControllerServletV3.service");

        //model에 저장할 데이터를 request를 통해 가져와, map에 저장.
        Map<String,String> paramMap = new HashMap<>();
        request.getParameterNames().asIterator()
                .forEachRemaining(paramName-> paramMap.put(paramName, request.getParameter(paramName)));

        String requestURI = request.getRequestURI();
        ControllerV3 controllerV3 = controllerV3Map.get(requestURI);

        if(controllerV3==null){
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }else{
            //request로 받아서 저장한 map을 modelview의 model에 저장또는 등등.
            ModelView view = controllerV3.process(paramMap);
            String viewName = view.getViewName();

            //최종 렌더링을 하는곳.
            MyView myView = new MyView("/WEB-INF/views/" + viewName + ".jsp");
            myView.render(view.getModel(),request,response);


        }



    }


}
