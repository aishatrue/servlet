package hello.servlet.web.frontcontroller.v5;

import hello.servlet.web.frontcontroller.ModelView;
import hello.servlet.web.frontcontroller.MyView;
import hello.servlet.web.frontcontroller.v3.ControllerV3;
import hello.servlet.web.frontcontroller.v3.controller.MemberFormControllerV3;
import hello.servlet.web.frontcontroller.v3.controller.MemberListControllerV3;
import hello.servlet.web.frontcontroller.v3.controller.MemberSaveControllerV3;
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


    private final Map<String, Object> handlerMappingMap = new HashMap<>();
    private final List<MyHandlerAdapter> handlerAdapterList = new ArrayList<>();
    public FrontControllerServletV5() {
        initHandlerMappingMap();
        initHandlerAdapters();


    }

    private void initHandlerAdapters() {
        handlerAdapterList.add(new ControllerV3HandlerAdapter());
    }

    private void initHandlerMappingMap() {
        handlerMappingMap.put("/front-controller/v5/v3/members/new-form", new MemberFormControllerV3());
        handlerMappingMap.put("/front-controller/v5/v3/members/save", new MemberSaveControllerV3());
        handlerMappingMap.put("/front-controller/v5/v3/members", new MemberListControllerV3());
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("FrontControllerServletV5.service");

        Object handler = getHandler(request);

        if(handler==null){
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }else{

            MyHandlerAdapter adapter = getHandlerAdapter(handler);
            ModelView mv = adapter.handle(request, response, handler);


            //request로 받아서 저장한 map을 modelview의 model에 저장또는 등등.
            String viewName = mv.getViewName();
            MyView view = viewResolver(viewName);

            //최종 렌더링을 하는곳.
            view.render(mv.getModel(),request,response);


        }
    }

    private MyHandlerAdapter getHandlerAdapter(Object handler) {
        for (MyHandlerAdapter adapter : handlerAdapterList) {
            if(adapter.supports(handler)){
                return adapter;
            }

        }
        throw new IllegalArgumentException("handler adapter를 찾을 수 없습니다. handler="+handler);

    }

    private Object getHandler(HttpServletRequest request) {
        String requestURI = request.getRequestURI();
        return handlerMappingMap.get(requestURI);
    }
    private static MyView viewResolver(String viewName) {
        MyView myView = new MyView("/WEB-INF/views/" + viewName + ".jsp");
        return myView;
    }

}
