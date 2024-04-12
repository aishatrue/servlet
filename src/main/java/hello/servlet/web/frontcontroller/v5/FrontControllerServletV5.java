package hello.servlet.web.frontcontroller.v5;

import hello.servlet.web.frontcontroller.ModelView;
import hello.servlet.web.frontcontroller.MyView;
import hello.servlet.web.frontcontroller.v3.ControllerV3;
import hello.servlet.web.frontcontroller.v3.controller.MemberFormControllerV3;
import hello.servlet.web.frontcontroller.v3.controller.MemberListControllerV3;
import hello.servlet.web.frontcontroller.v3.controller.MemberSaveControllerV3;
import hello.servlet.web.frontcontroller.v4.ControllerV4;
import hello.servlet.web.frontcontroller.v5.adapter.ControllerV3HandlerAdapter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@WebServlet(name = "frontControllerServletV5",urlPatterns = "/front-controller/v5/*")
public class FrontControllerServletV5 extends HttpServlet {


    private final List<MyHandlerAdapter> adapterList = new ArrayList<>();
    private final Map<String,Object> handlerMappingMap = new HashMap<>();



    public FrontControllerServletV5() {
        initHandler();
        addAdapterList();


    }

    public void addAdapterList(){
        adapterList.add(new ControllerV3HandlerAdapter());
    }


    private void initHandler() {
        handlerMappingMap.put("/front-controller/v5/v3/members/new-form", new
                MemberFormControllerV3());
        handlerMappingMap.put("/front-controller/v5/v3/members/save", new
                MemberSaveControllerV3());
        handlerMappingMap.put("/front-controller/v5/v3/members", new
                MemberListControllerV3());
    }


    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String requestURI = request.getRequestURI();

        Object handler = handlerMappingMap.get(requestURI);

        if(handler==null){
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }else{

            ModelView modelView = getHandlerAdapter(request, response, handler);
            MyView myView = viewResolver(modelView.getViewName());
            myView.render(modelView.getModel(),request,response);


        }

    }

    private ModelView getHandlerAdapter(HttpServletRequest request, HttpServletResponse response, Object handler) throws ServletException, IOException {
        for (MyHandlerAdapter adapter : adapterList) {
            if(adapter.supports(handler)){
               return adapter.handle(request, response, handler);
            }
        }
        throw new IllegalArgumentException("존재하지 않는 어댑터입니다.");
    }

    private static MyView viewResolver(String viewName) {
        MyView myView = new MyView("/WEB-INF/views/" + viewName + ".jsp");
        return myView;
    }


}
