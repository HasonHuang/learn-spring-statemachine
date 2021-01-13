package com.ihason.learn.springstatemachine.framework;

import com.ihason.learn.springstatemachine.States;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * 表示实体类
 *
 * @author Hason
 */
@Data
@AllArgsConstructor(staticName = "of")
public class StateMachineEntity {

    /** 实体状态 */
    private States state;

    /** 状态机 ID */
    private String machineId;

}
