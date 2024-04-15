package hello.servlet.web.frontcontroller.v5;

import hello.servlet.web.frontcontroller.ModelView;
import hello.servlet.web.frontcontroller.MyView;
import hello.servlet.web.frontcontroller.v5.adapter.InjectHandlerAndAdapter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.util.List;
import java.util.Map;


@WebServlet(name = "frontControllerServletV5",urlPatterns = "/front-controller/v5/*")
public class FrontControllerServletV5 extends HttpServlet {


    private final List<MyHandlerAdapter> adapterList;
    private final Map<String,Object> handlerMappingMap;


    @Autowired
    public FrontControllerServletV5(InjectHandlerAndAdapter injectHandlerAndAdapter) {
        this.adapterList = injectHandlerAndAdapter.getAdapterList();
        this.handlerMappingMap = injectHandlerAndAdapter.getHandlerMappingMap();
    }




    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String requestURI = request.getRequestURI();

        Object handler = handlerMappingMap.get(requestURI);

        if(handler==null){
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }else{


            MyHandlerAdapter handlerAdapter = getHandlerAdapter(request, response, handler);
            ModelView modelView = handlerAdapter.handle(request, response, handler);

            MyView myView = viewResolver(modelView.getViewName());
            myView.render(modelView.getModel(),request,response);


        }

    }

    private MyHandlerAdapter getHandlerAdapter(HttpServletRequest request, HttpServletResponse response, Object handler) throws ServletException, IOException {
        for (MyHandlerAdapter adapter : adapterList) {
            if(adapter.supports(handler)){
               return adapter;
            }
        }
        throw new IllegalArgumentException("존재하지 않는 어댑터입니다.");
    }

    private static MyView viewResolver(String viewName) {
        MyView myView = new MyView("/WEB-INF/views/" + viewName + ".jsp");
        return myView;
    }


}
