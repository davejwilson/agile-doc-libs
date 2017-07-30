package aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;

import java.util.Arrays;

/**
 * Created by david on 2017/07/30.
 */
@Aspect
public class TracingAspect {
    @Around("execution(* * (..))")
    public Object aroundAdvice(ProceedingJoinPoint joinPoint) throws Throwable {
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();

//        EventStore.getInstance().addEvent(
//                new SequenceEvent(
//                        methodSignature.getDeclaringTypeName(),
//                        methodSignature.getName(),
//                        methodSignature.getDeclaringTypeName(),
//                        methodSignature.getName(),
//                        Arrays.asList(methodSignature.getParameterTypes()),
//                        Arrays.asList(joinPoint.getArgs()),
//                        joinPoint.
//                ));

//        String callingClassName = methodSignature.getMethod().getDeclaringClass().getCanonicalName();
//        System.out.println(joinPoint + " " + callingClassName);

        System.out.println(joinPoint);
        System.out.println(joinPoint.getSignature());

        Object returnValue = joinPoint.proceed();

        System.out.println(returnValue + " (" + returnValue.getClass().getName() + ")");

        return returnValue;
    }
}
