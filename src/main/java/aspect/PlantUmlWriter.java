package aspect;

/**
 * Created by david on 2017/07/30.
 */
public class PlantUmlWriter {
    public String writeCallSequence(JoinPointInfo info) {
        return new StringBuilder()
            .append(info.getCallingName())
            .append(" -> ")
            .append(info.getCalledName())
            .append(" : ").append(info.getCalledMethodName()).append("(").append(info.getParamsString()).append(")")
            .toString();
    }

    public String writeReturnSequence(JoinPointInfo info, long duration) {
        return new StringBuilder()
                .append(info.getCalledName())
                .append(" --> ")
                .append(info.getCallingName())
                .append(" : ").append(info.getReturnType().getSimpleName()).append(" (").append(duration).append("ms)")
                .toString();
    }
}
