package com.ihason.learn.springstatemachine;

/**
 * 状态
 *
 * @author Hason
 */
public enum States {
    /** 创建中 */
    CREATING,
    /** 创建失败 */
    CREATE_FAILED,
    /** 审批中 */
    APPROVING,
    /** 审批不通过 */
    APPROVE_REJECTED,
    /** 已审批 */
    APPROVED,
    /** 下发中 */
    CONFIGURING,
    /** 下发失败 */
    CONFIGURE_FAILED,
    /** 已下发 */
    CONFIGURED,
    /** 删除中 */
    DELETING,
    /** 删除失败 */
    DELETE_FAILED,
    /** 已删除 */
    DELETED,
;

    public static void main(String[] args) {
        StringBuilder a = new StringBuilder();
        for (States value : values()) {
            a.append("\"").append(value).append("\",");
        }
        System.out.println(a.substring(0, a.length() - 1));
    }
}
