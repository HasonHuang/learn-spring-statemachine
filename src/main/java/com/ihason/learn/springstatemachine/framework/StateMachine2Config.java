package com.ihason.learn.springstatemachine.framework;

import com.ihason.learn.springstatemachine.Events;
import com.ihason.learn.springstatemachine.States;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.statemachine.config.EnableStateMachineFactory;
import org.springframework.statemachine.config.builders.StateMachineTransitionConfigurer;

/**
 * 第二个状态机配置类
 *
 * @author Hason
 */
@Slf4j
@Configuration
@EnableStateMachineFactory(contextEvents = false, name = StateMachine2Config.MACHINE_FACTORY_ID)
public class StateMachine2Config extends BaseStateMachineConfig {

    public static final String MACHINE_ID = "enumStateMachine2";
    public static final String MACHINE_FACTORY_ID = "enumStateMachineFactory2";

    @Override
    protected String getMachineId() {
        return MACHINE_ID;
    }

    @Override
    public void configure(StateMachineTransitionConfigurer<States, Events> transitions) throws Exception {

        // 转移配置
        transitions
                .withExternal()
                .source(States.CREATING).target(States.APPROVING).event(Events.DISPATCH)
                .and()
                .withExternal()
                .source(States.CREATING).target(States.CREATE_FAILED).event(Events.CREATE_FAILED)
                .and()
                .withExternal()
                .source(States.APPROVING).target(States.APPROVE_REJECTED).event(Events.REJECT)
                .and()
                .withExternal()
                .source(States.APPROVING).target(States.APPROVED).event(Events.CONSENT)
                .and()
                .withExternal()
                .source(States.APPROVED).target(States.CONFIGURING).event(Events.CONFIGURE)
                .and()
                .withExternal()
                .source(States.CONFIGURING).target(States.CONFIGURE_FAILED).event(Events.CONFIGURE_FAILED)
                .and()
                .withExternal()
                .source(States.CONFIGURING).target(States.CONFIGURED).event(Events.CONFIGURED)
                .and()
                .withExternal()
                .source(States.CONFIGURED).target(States.DELETING).event(Events.DELETE)
                .and()
                .withExternal()
                .source(States.DELETING).target(States.DELETE_FAILED).event(Events.DELETE_FAILED);
    }
}
