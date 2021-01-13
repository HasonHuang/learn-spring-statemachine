package com.ihason.learn.springstatemachine.handwrite.action;

import com.ihason.learn.springstatemachine.handwrite.StateContext;
import lombok.extern.slf4j.Slf4j;

/**
 * 表示没有操作的动作
 *
 * @author Hason
 */
@Slf4j
public class NopAction implements Action {
    @Override
    public void execute(StateContext context) {
        log.info("No operation for state [{}] to [{}], context : {}", context.getCurrentState(), context.getNextState(),  context);
    }
}
