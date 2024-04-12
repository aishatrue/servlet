package hello.servlet.web.frontcontroller.v5.adapter;

import hello.servlet.web.frontcontroller.v3.controller.MemberFormControllerV3;
import hello.servlet.web.frontcontroller.v3.controller.MemberListControllerV3;
import hello.servlet.web.frontcontroller.v3.controller.MemberSaveControllerV3;
import hello.servlet.web.frontcontroller.v4.controller.MemberFormControllerV4;
import hello.servlet.web.frontcontroller.v4.controller.MemberListControllerV4;
import hello.servlet.web.frontcontroller.v4.controller.MemberSaveControllerV4;
import hello.servlet.web.frontcontroller.v5.MyHandlerAdapter;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class InjectHandlerAndAdapter {

    List<MyHandlerAdapter> adapterList = new ArrayList<>();
    Map<String,Object> handlerMappingMap = new HashMap<>();

    public InjectHandlerAndAdapter() {
        initHandler();
        addAdapterList();

    }

    public void addAdapterList(){
        adapterList.add(new ControllerV3HandlerAdapter());
        adapterList.add(new ControllerV4HandlerAdapter());
    }


    private void initHandler() {
        handlerMappingMap.put("/front-controller/v5/v3/members/new-form", new
                MemberFormControllerV3());
        handlerMappingMap.put("/front-controller/v5/v3/members/save", new
                MemberSaveControllerV3());
        handlerMappingMap.put("/front-controller/v5/v3/members", new
                MemberListControllerV3());
        handlerMappingMap.put("/front-controller/v5/v4/members/new-form", new
                MemberFormControllerV4());
        handlerMappingMap.put("/front-controller/v5/v4/members/save", new
                MemberSaveControllerV4());
        handlerMappingMap.put("/front-controller/v5/v4/members", new
                MemberListControllerV4());
    }

    public List<MyHandlerAdapter> getAdapterList() {
        return adapterList;
    }

    public Map<String, Object> getHandlerMappingMap() {
        return handlerMappingMap;
    }
}
