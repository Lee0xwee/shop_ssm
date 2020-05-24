package cn.Lee.ssm.index.controller.exception;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@ControllerAdvice
public class CustomExceptionResolver implements HandlerExceptionResolver {


    @ExceptionHandler(value = CustomException.class)
    @ResponseBody
    public ModelAndView resolveException(HttpServletRequest request,
                                         HttpServletResponse response, Object handler, Exception e) {


        CustomException customException = null;

        if (e instanceof CustomException) {

            customException = (CustomException) e;


        } else {

            customException = new CustomException("未知错误！");
        }

        String msg = customException.getMessage();

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("msg", msg);
        modelAndView.setViewName("error");
        return modelAndView;


    }

}
