package com.ihason.learn.springstatemachine.framework;

import com.ihason.learn.springstatemachine.Events;
import com.ihason.learn.springstatemachine.States;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.statemachine.config.EnableStateMachineFactory;
import org.springframework.statemachine.listener.StateMachineListener;
import org.springframework.statemachine.listener.StateMachineListenerAdapter;
import org.springframework.statemachine.persist.DefaultStateMachinePersister;
import org.springframework.statemachine.persist.StateMachinePersister;
import org.springframework.statemachine.state.State;

/**
 * 状态机配置类
 *
 * @author Hason
 */
@Slf4j
@Configuration
@EnableStateMachineFactory(contextEvents = false, name = StateMachineConfig.MACHINE_FACTORY_ID)
public class StateMachineConfig extends BaseStateMachineConfig {

    public static final String MACHINE_ID = "enumStateMachine";
    public static final String MACHINE_FACTORY_ID = "enumStateMachineFactory";

    @Override
    protected String getMachineId() {
        return MACHINE_ID;
    }

    @Bean
    public StateMachineListener<States, Events> defaultListener() {
        // 注册默认的监听器
        return new StateMachineListenerAdapter<States, Events>() {
            @Override
            public void stateChanged(State from, State to) {
                log.info("State change to [{}] from [{}]", to, from);
            }
        };
    }

    @Bean
    public StateMachinePersister<States, Events, StateMachineEntity> stateMachinePersister() {
        // 状态机持久化处理器
        return new DefaultStateMachinePersister<>(new PropertyStateMachinePersist());
    }

}
