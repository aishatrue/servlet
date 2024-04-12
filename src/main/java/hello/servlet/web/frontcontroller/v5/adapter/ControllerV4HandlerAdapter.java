package hello.servlet.web.frontcontroller.v5.adapter;

import hello.servlet.web.frontcontroller.ModelView;
import hello.servlet.web.frontcontroller.v3.ControllerV3;
import hello.servlet.web.frontcontroller.v4.ControllerV4;
import hello.servlet.web.frontcontroller.v5.MyHandlerAdapter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ControllerV4HandlerAdapter implements MyHandlerAdapter {
    @Override
    public boolean supports(Object handler) {
        return (handler instanceof ControllerV4);
    }

    @Override
    public ModelView handle(HttpServletRequest request, HttpServletResponse response, Object handler) throws ServletException, IOException {

        System.out.println("ControllerV4HandlerAdapter.handle");
        Map<String,String> paramMap = createParamMap(request);
        Map<String,Object> model = new HashMap<>();

        ControllerV4 controllerV4 = (ControllerV4) handler;



        //process에서는 model에 데이터를 저장함.
        String name = controllerV4.process(paramMap, model);


        //이 Modelview는 그 데이터가 저장된 model로 setting되야함.
        //v3handler는 modelview의 model을 get해와서 값을 변경하지만, v4handler는 model을 따로 만들어서 넘기기 때문에 modelview의 model은 비어있음.
        //frontcontroller랑 다른 점은, frontcontroller는 modelview를 안쓰고 자체적으로 model을 만들어서 넘김.
        ModelView modelView = new ModelView(name);

        modelView.setModel(model);  //**************중요 코드****************

        return modelView;

    }

    private Map<String, String> createParamMap(HttpServletRequest request) {
        Map<String,String> paramMap = new HashMap<>();
        request.getParameterNames().asIterator()
                .forEachRemaining(paramName-> paramMap.put(paramName, request.getParameter(paramName)));
        return paramMap;
    }
}
