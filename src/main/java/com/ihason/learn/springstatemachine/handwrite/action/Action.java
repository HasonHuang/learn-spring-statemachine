package com.ihason.learn.springstatemachine.handwrite.action;

import com.ihason.learn.springstatemachine.handwrite.StateContext;

/**
 * 状态迁移过程中需要执行的动作
 *
 * @author Hason
 */
public interface Action {

    void execute(StateContext context);

}
