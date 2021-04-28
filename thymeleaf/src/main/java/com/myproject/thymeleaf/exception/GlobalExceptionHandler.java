package com.myproject.thymeleaf.exception;

import com.myproject.thymeleaf.model.vo.ResponseBo;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.AuthorizationException;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.LinkedList;
import java.util.List;

/**
 * 全局异常捕捉类
 */
@ControllerAdvice
@Slf4j
@Order(value = Ordered.HIGHEST_PRECEDENCE)
public class GlobalExceptionHandler {
    @ExceptionHandler(value = AuthorizationException.class)
    public String handleAuthorizationException() {
        return "403";
    }

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    @ResponseBody
    public ResponseBo validationBodyException(MethodArgumentNotValidException exception) {

        BindingResult bindingResult = exception.getBindingResult();
        List<String> errorMessages = new LinkedList<>();
        if (bindingResult.hasErrors()) {
            List<ObjectError> errors = bindingResult.getAllErrors();
            errors.forEach(p -> {

                FieldError fieldError = (FieldError) p;
                log.error("Data check failure : object{" + fieldError.getObjectName() + "},field{" + fieldError.getField() +
                        "},errorMessage{" + fieldError.getDefaultMessage() + "}");
                errorMessages.add(fieldError.getDefaultMessage());

            });
        }
        return ResponseBo.error(errorMessages.toString());
    }

    @ExceptionHandler(value = RuntimeException.class)
    @ResponseBody
    public ResponseBo submitException(RuntimeException exception) {

        String message = exception.getMessage();
        return ResponseBo.error(message);
    }
}