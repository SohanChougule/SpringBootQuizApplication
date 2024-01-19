package com.example.quizservice.aspects;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {

    @Before("execution(* com.example.quizservice.controller.QuizController.getQuiz(..))")
    public void Beforelogger(JoinPoint jp) {

        String arg = jp.getArgs()[0].toString();
        System.out.println("before getQuiz loggers " + jp.getSignature()
                + " " + jp.getSourceLocation() + " " + jp.getKind()
                + " with argument " + arg);
    }

    @After("execution(* com.example.quizservice.controller.QuizController.getQuiz(..))")
    public void Afterlogger() {
        System.out.println("after getQuiz loggers");
    }
}
