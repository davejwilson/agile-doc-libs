package aspect;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

/**
 * Created by david on 2017/07/30.
 */
@Getter
@Setter
@AllArgsConstructor
@ToString
public class SequenceEvent {
    private String callingClassName;
    private String callingMethodName;
    private String calledClassName;
    private String calledMethodName;
    private List<Class> paramTypes;
    private List<Object> params;
    private Class returnType;
    private Object returnValue;
}
