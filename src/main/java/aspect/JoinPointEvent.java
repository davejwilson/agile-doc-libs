package aspect;

import lombok.AllArgsConstructor;
import lombok.Getter;

enum EventType {CALL, RETURN}

/**
 * Created by david on 2017/07/30.
 */
@Getter
@AllArgsConstructor
public class JoinPointEvent {
    private EventType eventType;
    private JoinPointInfo info;
}
