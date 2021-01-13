package com.ihason.learn.springstatemachine.framework;

import com.ihason.learn.springstatemachine.Events;
import com.ihason.learn.springstatemachine.States;
import org.springframework.statemachine.config.EnumStateMachineConfigurerAdapter;
import org.springframework.statemachine.config.builders.StateMachineConfigurationConfigurer;
import org.springframework.statemachine.config.builders.StateMachineStateConfigurer;
import org.springframework.statemachine.config.builders.StateMachineTransitionConfigurer;

import java.util.EnumSet;

public abstract class BaseStateMachineConfig extends EnumStateMachineConfigurerAdapter<States, Events> {

    protected abstract String getMachineId();

    @Override
    public void configure(StateMachineConfigurationConfigurer<States, Events> config) throws Exception {
        // 通用配置
        config
                .withConfiguration()
                .machineId(getMachineId())
                .autoStartup(true);
    }

    @Override
    public void configure(StateMachineStateConfigurer<States, Events> states) throws Exception {
        // 状态配置
        states.withStates()
                // 初始状态
                .initial(States.CREATING)
                // 全部状态
                .states(EnumSet.allOf(States.class));
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
                    .source(States.DELETING).target(States.DELETE_FAILED).event(Events.DELETE_FAILED)
                    .and()
                .withExternal()
                    .source(States.DELETING).target(States.DELETED).event(Events.DELETED)
                    .and()
                .withExternal()
                    .source(States.DELETE_FAILED).target(States.DELETING).event(Events.DELETE);
    }

}
