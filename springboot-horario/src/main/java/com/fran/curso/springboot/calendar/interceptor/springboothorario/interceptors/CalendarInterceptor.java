package com.fran.curso.springboot.calendar.interceptor.springboothorario.interceptors;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component("calendarInterceptor")
public class CalendarInterceptor implements HandlerInterceptor {

    @Value("${config.calendar.open}")
    private Integer open;

    @Value("${config.calendar.close}")
    private Integer closed;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        Calendar calendar = Calendar.getInstance();

        int hour = calendar.get(Calendar.HOUR);

//        System.out.println(hour);

        if (open >= hour && hour <= closed) {
            String message = "Bienvenido al horario de atencion al clieente: " + ", atendemos desde las " +
                    open +
                    " hrs." +
                    " hasta las " +
                    closed +
                    " hrs." +
                    "Gracias por su visita.";
            request.setAttribute("message", message);
            return true;
        }

        ObjectMapper mapper = new ObjectMapper();
        Map<String, Object> map = new HashMap<>();

        String message = "Cerrado, fuera del horario de atencion al cliente" + " por favor visitenos desde " +
                open +
                " hasta las " +
                closed +
                " hrs. Gracias!!";
        map.put("message", message);
        map.put("date", new Date().toString());

        response.setContentType("application/json");
        response.setStatus(HttpStatus.UNAUTHORIZED.value());
        response.getWriter().write(mapper.writeValueAsString(map));

        return false;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
    }
}
