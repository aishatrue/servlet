package hello.servlet.web.frontcontroller.v5.adapter;

import com.sun.jdi.VMOutOfMemoryException;
import hello.servlet.web.frontcontroller.ModelView;
import hello.servlet.web.frontcontroller.MyView;
import hello.servlet.web.frontcontroller.v3.ControllerV3;
import hello.servlet.web.frontcontroller.v4.ControllerV4;
import hello.servlet.web.frontcontroller.v5.MyHandlerAdapter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ControllerV3HandlerAdapter implements MyHandlerAdapter {
    @Override
    public boolean supports(Object handler) {

        // 이 핸들러가 controllerv3의 인스턴스인지 확인해, 해당하는 어댑터가 있는지 확인하는것.
       return (handler instanceof ControllerV3);

    }

    @Override
    public ModelView handle(HttpServletRequest request, HttpServletResponse response, Object handler) throws ServletException, IOException {


        System.out.println("ControllerV3HandlerAdapter.handle");
        //model에 저장할 데이터를 request를 통해 가져와, map에 저장.

        Map<String,String> paramMap = createParamMap(request);

        ControllerV3 controllerV3 = (ControllerV3) handler;



            //request로 받아서 저장한 map을 modelview의 model에 저장또는 등등.
        ModelView modelView = controllerV3.process(paramMap);

       return modelView;




    }

    private Map<String, String> createParamMap(HttpServletRequest request) {
        Map<String,String> paramMap = new HashMap<>();
        request.getParameterNames().asIterator()
                .forEachRemaining(paramName-> paramMap.put(paramName, request.getParameter(paramName)));
        return paramMap;
    }


}
