package com.fran.curso.springboot.app.interceptor.springbootinterceptor.interceptors;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import java.awt.desktop.PreferencesEvent;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

@Component("timeInterceptor")
public class LoadingTimeInterceptor implements HandlerInterceptor {

    private static final Logger logger = LoggerFactory.getLogger(LoadingTimeInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        HandlerMethod controller = (HandlerMethod) handler;
        logger.info("LoadingTimeInterceptor: preHandle() entrando....."+ controller.getMethod().getName());

        long startTime = System.currentTimeMillis();

        request.setAttribute("startTime",startTime);

        Random random = new Random();

        int delay =random.nextInt(500);

        Thread.sleep(delay);

//        Map<String,String> json = new HashMap<>();
//        json.put("error","no tienes acceso a esta pagina");
//        json.put("date",new Date().toString());
//
//        ObjectMapper mapper = new ObjectMapper();
//
//        String jsonString = mapper.writeValueAsString(json);
//
//
//        response.setContentType("application/json");
//        response.setStatus(HttpStatus.UNAUTHORIZED.value());
//        response.getWriter().write(jsonString);

        return true;

        // No deja acceder al controlador y manda msj de error
//        return false;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        HandlerMethod controller = (HandlerMethod) handler;


        long endTime = System.currentTimeMillis();

        long startTime = (long) request.getAttribute("startTime");

        long result = endTime - startTime;
        logger.info("Tiempor transcurrido: " + result + " milisegundos!!");

        logger.info("LoadingTimeInterceptor: postHandle() saliendo....."+ controller.getMethod().getName());
    }
}
