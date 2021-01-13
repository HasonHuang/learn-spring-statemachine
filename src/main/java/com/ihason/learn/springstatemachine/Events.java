package com.ihason.learn.springstatemachine;

/**
 * 事件
 *
 * @author Hason
 */
public enum Events {
    /** 申请 */
    APPLY,
    /** 创建失败 */
    CREATE_FAILED,
    /** 分派 */
    DISPATCH,
    /** 审批不通过 */
    REJECT,
    /** 同意审批 */
    CONSENT,
    /** 下发配置 */
    CONFIGURE,
    /** 已下发/已配置 */
    CONFIGURED,
    /** 下发失败/配置失败 */
    CONFIGURE_FAILED,
    /** 删除 */
    DELETE,
    /** 已删除 */
    DELETED,
    /** 删除失败 */
    DELETE_FAILED,
}
