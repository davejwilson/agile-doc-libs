package aspect;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * Created by david on 2017/07/30.
 */
@Getter
@Setter
@AllArgsConstructor
@ToString
public class JoinPointInfo {
    private String callingClassName;
    private String callingMethodName;
    private String calledClassName;
    private String calledMethodName;
    private Class[] paramTypes;
    private String[] paramNames;
    private Object[] paramValues;
    private Class returnType;

    public String getCallingName() {
        return callingClassName.substring(callingClassName.lastIndexOf(".") + 1);
    }

    public String getCalledName() {
        return calledClassName.substring(calledClassName.lastIndexOf(".") + 1);
    }

    public String getParamsString() {
        String[] paramNames = getParamNames();
        Class[] paramTypes = getParamTypes();
        StringBuilder paramString = new StringBuilder();
        for (int i = 0; i < paramNames.length; i++) {
            if (i > 0) {
                paramString.append(", ");
            }
            paramString
                    .append(paramTypes[i].getSimpleName())
                    .append(' ')
                    .append(paramNames[i])
                    .append(" = ")
                    .append(paramValues[i]);
        }
        return paramString.toString();
    }
}
