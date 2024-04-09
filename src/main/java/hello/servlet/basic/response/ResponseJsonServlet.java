package hello.servlet.basic.response;

import com.fasterxml.jackson.databind.ObjectMapper;
import hello.servlet.basic.HelloData;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "responseJsonServlet",urlPatterns = "/response-json")
public class ResponseJsonServlet extends HttpServlet {

    ObjectMapper objectMapper = new ObjectMapper();
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        //content-Type: application/json
        res.setContentType("application/json");
        res.setCharacterEncoding("utf-8");

        HelloData helloData = new HelloData();
        helloData.setUsername("와우");
        helloData.setAge(20);

        //json형태로 바꾸기
        String result = objectMapper.writeValueAsString(helloData);

        res.getWriter().write(result);
    }
}
