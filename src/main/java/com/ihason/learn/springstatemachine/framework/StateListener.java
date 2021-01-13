package com.ihason.learn.springstatemachine.framework;

import com.ihason.learn.springstatemachine.Events;
import com.ihason.learn.springstatemachine.States;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.Message;
import org.springframework.statemachine.ExtendedState;
import org.springframework.statemachine.StateContext;
import org.springframework.statemachine.annotation.*;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * 监听器
 *
 * @author Hason
 */
@Slf4j
@WithStateMachine(name = StateMachineConfig.MACHINE_ID)
public class StateListener {

    @OnEnumStateChanged
    public void anyStateChanged(@EventHeaders Map<String, Object> headers, Message<Events> message,
                                StateContext<String, String> stateContext) {
        // StateContext 泛型虽然是 string，实际上在运行时是 States, Events 枚举类型
        log.info("Any state changed listener, headers [{}] message [{}] stateContext [{}]", headers, message, stateContext);
    }

    @OnStateExit
    public void anyStateExit(StateContext<States, Events> stateContext) {
        log.info("Any exit, state: " + stateContext.getSource() + ", to " + stateContext.getTarget());
    }

    @OnStateExit(source = "CONFIGURING")
    public void onConfiguringExit() {
        log.info("exit state [{}]", States.CONFIGURING);
    }

    @OnStateEntry(target = "CONFIGURE_FAILED")
    public void onConfigureFailedEntry() {
        log.info("entry state [{}]", States.CONFIGURE_FAILED);
    }

    @OnEnumStateChanged(target = States.CONFIGURE_FAILED)
    public void onStateChanged(StateContext<String, String> stateContext) {
        log.info("Target state changed listener: {}", stateContext);
    }

    @OnEnumTransition(target = States.CONFIGURE_FAILED)
    public void onTargetTransition(StateContext<String, String> stateContext) {
        log.info("Target transition listener: {}", stateContext);
    }

    @OnTransitionStart(target = "CONFIGURE_FAILED")
    public void onTargetTransitionStart() {
        log.info("Transition start");
    }

    @OnTransitionEnd(target = "CONFIGURE_FAILED")
    public void onTargetTransitionEnd() {
        log.info("Transition end");
    }

}
