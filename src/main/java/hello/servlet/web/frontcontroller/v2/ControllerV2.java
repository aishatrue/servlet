package hello.servlet.web.frontcontroller.v2;

import hello.servlet.web.frontcontroller.MyView;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public interface ControllerV2 {

    //모든 servlet들은 최종적으로 jsp로 이동했었음.
    MyView process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;
}
