package aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

import static aspect.EventType.CALL;
import static aspect.EventType.RETURN;

/**
 * Created by david on 2017/07/30.
 */
@Aspect
public class TracingAspect {
    private EventStore eventStore = EventStore.getInstance();
    private PlantUmlWriter writer = new PlantUmlWriter();
    private String includedPackage = "application";
    private TracingShutdownThread shutdownThread = new TracingShutdownThread();

    @Around("execution(* * (..))")
    public Object aroundAdvice(ProceedingJoinPoint joinPoint) throws Throwable {
        try {
            JoinPointInfo info = AspectUtils.getJoinPointInfo(joinPoint);

            if (info.getCallingClassName().startsWith(includedPackage) &&
                    info.getCalledClassName().startsWith(includedPackage)) {
                System.out.println(writer.writeCallSequence(info));
                eventStore.addEvent(new JoinPointEvent(CALL, info, 0));
            }

            long start = System.currentTimeMillis();
            Object returnValue = joinPoint.proceed();
            long end = System.currentTimeMillis();
            long duration = end - start;

            if (info.getCallingClassName().startsWith(includedPackage) &&
                    info.getCalledClassName().startsWith(includedPackage)) {
                System.out.println(writer.writeReturnSequence(info, duration));
                eventStore.addEvent(new JoinPointEvent(RETURN, info, duration));
            }

            return returnValue;
        } catch (Throwable t) {
            t.printStackTrace();
            throw t;
        }
    }
}
