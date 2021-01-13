package com.ihason.learn.springstatemachine.handwrite;

import com.ihason.learn.springstatemachine.Events;
import com.ihason.learn.springstatemachine.States;
import com.ihason.learn.springstatemachine.handwrite.action.Action;
import lombok.Builder;
import lombok.Value;

/**
 * 上下文：现态、事件、动作、次态
 *
 * @author Hason
 */
@Value(staticConstructor = "of")
@Builder
public class StateContext {

    /** 现态 */
    private States currentState;

    /** 现态 */
    private Events event;

    /** 动作 */
    private Action action;

    /** 次态 */
    private States nextState;

}
