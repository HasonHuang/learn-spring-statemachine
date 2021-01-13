package com.ihason.learn.springstatemachine.handwrite;

import com.ihason.learn.springstatemachine.Events;
import com.ihason.learn.springstatemachine.States;
import com.ihason.learn.springstatemachine.handwrite.action.NopAction;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.springframework.util.Assert.notNull;

/**
 * 状态机
 *
 * @author Hason
 */
public class StateMachine {

    /** 状态转移 */
    private List<StateContext> transitions = new ArrayList<>();

    /** 当前状态 */
    private States currentState;

    private StateMachine(States currentState) {
        this.currentState = currentState;
        init();
    }

    private void init() {
        transitions.addAll(Arrays.asList(
                StateContext.builder()
                    .currentState(States.CREATING)
                    .event(Events.CREATE_FAILED)
                    .nextState(States.CREATE_FAILED)
                    .action(new NopAction())
                .build(),
                StateContext.builder()
                    .currentState(States.CREATING)
                    .event(Events.DISPATCH)
                    .nextState(States.APPROVING)
                    .action(new NopAction())
                .build(),
                StateContext.builder()
                    .currentState(States.APPROVING)
                    .event(Events.REJECT)
                    .nextState(States.APPROVE_REJECTED)
                    .action(new NopAction())
                .build(),
                StateContext.builder()
                    .currentState(States.APPROVING)
                    .event(Events.CONSENT)
                    .nextState(States.APPROVED)
                    .action(new NopAction())
                .build(),
                StateContext.builder()
                    .currentState(States.APPROVED)
                    .event(Events.CONFIGURE)
                    .nextState(States.CONFIGURING)
                    .action(new NopAction())
                .build(),
                StateContext.builder()
                    .currentState(States.CONFIGURING)
                    .event(Events.CONFIGURE_FAILED)
                    .nextState(States.CONFIGURE_FAILED)
                    .action(new NopAction())
                .build(),
                StateContext.builder()
                    .currentState(States.CONFIGURING)
                    .event(Events.CONFIGURED)
                    .nextState(States.CONFIGURED)
                    .action(new NopAction())
                .build(),
                StateContext.builder()
                    .currentState(States.CONFIGURED)
                    .event(Events.DELETE)
                    .nextState(States.DELETING)
                    .action(new NopAction())
                .build(),
                StateContext.builder()
                    .currentState(States.DELETING)
                    .event(Events.DELETE_FAILED)
                    .nextState(States.DELETE_FAILED)
                    .action(new NopAction())
                .build(),
                StateContext.builder()
                    .currentState(States.DELETE_FAILED)
                    .event(Events.DELETE)
                    .nextState(States.DELETING)
                    .action(new NopAction())
                .build(),
                StateContext.builder()
                    .currentState(States.DELETING)
                    .event(Events.DELETED)
                    .nextState(States.DELETED)
                    .action(new NopAction())
                .build()
        ));
    }

    /**
     * 向状态机发送一个事件
     *
     * @param event 事件
     * @return true 表示已接收事件，false 表示无法识别的事件
     */
    public boolean send(Events event) {
        notNull(event, "Argument [event] cannot be null");

        for (StateContext transition : transitions) {
            if (transition.getCurrentState() == currentState && transition.getEvent() == event) {
                transition.getAction().execute(transition);
                this.currentState = transition.getNextState();
                return true;
            }
        }

        return false;
    }

    /**
     * 返回状态机的当前状态
     */
    public States currentState() {
        return currentState;
    }

    public static StateMachine newInstance(States state) {
        return new StateMachine(state);
    }

}
