package com.ihason.learn.springstatemachine.handwrite;

import com.ihason.learn.springstatemachine.Events;
import com.ihason.learn.springstatemachine.States;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class StateMachineTests {

    // 方法命名规则：should_be_次状态_when_事件_from_现态

    @Test
    public void should_be_approving_when_dispatch_from_creating() {
        // 创建中 -> 审批中
        // Given
        StateMachine machine = StateMachine.newInstance(States.CREATING);
        // When
        machine.send(Events.DISPATCH);
        // Then
        assertThat(machine.currentState()).isEqualTo(States.APPROVING);
    }

    @Test
    public void should_be_create_failed_when_creating_from_fail() {
        // 创建中 -> 创建失败
        // Given
        StateMachine machine = StateMachine.newInstance(States.CREATING);
        // When
        machine.send(Events.CREATE_FAILED);
        // Then
        assertThat(machine.currentState()).isEqualTo(States.CREATE_FAILED);
    }

    @Test
    public void should_be_approve_rejected_when_reject_from_approving() {
        // 审批中 -> 审批不通过
        // Given
        StateMachine machine = StateMachine.newInstance(States.APPROVING);
        // When
        machine.send(Events.REJECT);
        // Then
        assertThat(machine.currentState()).isEqualTo(States.APPROVE_REJECTED);
    }

    @Test
    public void should_be_approved_when_consent_from_approving() {
        // 审批中 -> 已审批
        // Given
        StateMachine machine = StateMachine.newInstance(States.APPROVING);
        // When
        machine.send(Events.CONSENT);
        // Then
        assertThat(machine.currentState()).isEqualTo(States.APPROVED);
    }

    @Test
    public void should_be_configuring_when_configure_from_approved() {
        // 已审批 -> 下发中
        // Given
        StateMachine machine = StateMachine.newInstance(States.APPROVED);
        // When
        machine.send(Events.CONFIGURE);
        // Then
        assertThat(machine.currentState()).isEqualTo(States.CONFIGURING);
    }

    @Test
    public void should_be_configure_failed_when_fail_from_configuring() {
        // 下发中 -> 下发失败
        // Given
        StateMachine machine = StateMachine.newInstance(States.CONFIGURING);
        // When
        machine.send(Events.CONFIGURE_FAILED);
        // Then
        assertThat(machine.currentState()).isEqualTo(States.CONFIGURE_FAILED);
    }

    @Test
    public void should_be_configured_failed_when_configured_from_configuring() {
        // 下发中 -> 已连接
        // Given
        StateMachine machine = StateMachine.newInstance(States.CONFIGURING);
        // When
        machine.send(Events.CONFIGURED);
        // Then
        assertThat(machine.currentState()).isEqualTo(States.CONFIGURED);
    }

    @Test
    public void should_be_deleting_when_delete_from_configured() {
        // 已连接 -> 删除中
        // Given
        StateMachine machine = StateMachine.newInstance(States.CONFIGURED);
        // When
        machine.send(Events.DELETE);
        // Then
        assertThat(machine.currentState()).isEqualTo(States.DELETING);
    }

    @Test
    public void should_be_delete_failed_when_failed_from_deleting() {
        // 删除中 -> 删除失败
        // Given
        StateMachine machine = StateMachine.newInstance(States.DELETING);
        // When
        machine.send(Events.DELETE_FAILED);
        // Then
        assertThat(machine.currentState()).isEqualTo(States.DELETE_FAILED);
    }

    @Test
    public void should_be_deleted_when_deleted_from_deleting() {
        // 删除中 -> 已删除
        // Given
        StateMachine machine = StateMachine.newInstance(States.DELETING);
        // When
        machine.send(Events.DELETED);
        // Then
        assertThat(machine.currentState()).isEqualTo(States.DELETED);
    }

    @Test
    public void should_be_deleting_when_delete_from_delete_failed() {
        // 删除失败 -> 删除中
        // Given
        StateMachine machine = StateMachine.newInstance(States.DELETE_FAILED);
        // When
        machine.send(Events.DELETE);
        // Then
        assertThat(machine.currentState()).isEqualTo(States.DELETING);
    }

}
