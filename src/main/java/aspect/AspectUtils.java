package aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.reflect.MethodSignature;

/**
 * Created by david on 2017/07/30.
 */
public class AspectUtils {
    public static JoinPointInfo getJoinPointInfo(JoinPoint joinPoint) {
        return new JoinPointInfo(
                Thread.currentThread().getStackTrace()[8].getClassName(),
                Thread.currentThread().getStackTrace()[8].getMethodName(),
                joinPoint.getSignature().getDeclaringType().getName(),
                ((MethodSignature) joinPoint.getSignature()).getMethod().getName(),
                ((MethodSignature) joinPoint.getSignature()).getParameterTypes(),
                ((MethodSignature) joinPoint.getSignature()).getParameterNames(),
                joinPoint.getArgs(),
                ((MethodSignature) joinPoint.getSignature()).getReturnType()
        );
    }
}
