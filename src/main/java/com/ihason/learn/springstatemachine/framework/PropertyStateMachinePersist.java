package com.ihason.learn.springstatemachine.framework;

import com.ihason.learn.springstatemachine.Events;
import com.ihason.learn.springstatemachine.States;
import org.springframework.statemachine.StateMachineContext;
import org.springframework.statemachine.StateMachinePersist;
import org.springframework.statemachine.persist.StateMachinePersister;
import org.springframework.statemachine.support.DefaultStateMachineContext;

/**
 * 状态机上下文的序列化处理器。实际应用中不会保存完整的状态上下文，只是保存状态到资源表中。
 *
 * 需要在配置类中初始化 {@link StateMachinePersister}
 *
 * @author Hason
 */
public class PropertyStateMachinePersist implements StateMachinePersist<States, Events, StateMachineEntity> {
    @Override
    public void write(StateMachineContext<States, Events> context, StateMachineEntity contextObj) throws Exception {
        // 无需真的持久化实体
    }

    @Override
    public StateMachineContext<States, Events> read(StateMachineEntity contextObj) throws Exception {
        return new DefaultStateMachineContext<>(
                contextObj.getState(),
                null,
                null,
                null,
                null,
                contextObj.getMachineId());
    }

}
