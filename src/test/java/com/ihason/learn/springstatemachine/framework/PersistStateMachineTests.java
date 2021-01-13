package com.ihason.learn.springstatemachine.framework;

import com.ihason.learn.springstatemachine.Application;
import com.ihason.learn.springstatemachine.Events;
import com.ihason.learn.springstatemachine.States;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.config.StateMachineFactory;
import org.springframework.statemachine.persist.StateMachinePersister;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * 持久化状态机测试用例
 *
 * @author Hason
 */
@Slf4j
@SpringBootTest(classes = Application.class)
public class PersistStateMachineTests {

    @Autowired
    @Qualifier(StateMachineConfig.MACHINE_FACTORY_ID)
    private StateMachineFactory<States, Events> factory;
    @Autowired
    @Qualifier(StateMachine2Config.MACHINE_FACTORY_ID)
    private StateMachineFactory<States, Events> factory2;
    @Autowired
    private StateMachinePersister<States, Events, StateMachineEntity> persister;

    @Test
    public void should_execute_success_from_persist() throws Exception {
        StateMachine<States, Events> machine = factory.getStateMachine();
        persister.restore(machine, StateMachineEntity.of(States.CONFIGURING, machine.getId()));
        send(machine, Events.CONFIGURE_FAILED);
        assertThat(machine.getState().getId()).isEqualTo(States.CONFIGURE_FAILED);
    }

    private void send(StateMachine<States, Events> machine, Events event) {
        boolean availableEvent = machine.sendEvent(event);
        log.info("Available event [{}]: {}", event, availableEvent);
        log.info("state : {}", machine.getState());
    }

}
