package com.xmd.social.dingtalk.api;

/**
 * @Description
 * @Author lixiao
 * @Date 2021/12/6 下午2:27
 */
public class DingDingUserInfo {

    /**
     * 设备ID。
     */
    private String deviceId;

    /**
     * 用户名字。
     */
    private String name;

    /**
     * 是否是管理员。
     *
     * true：是
     *
     * false：不是
     */
    private String sys;

    /**
     * 级别。
     *
     * 1：主管理员
     *
     * 2：子管理员
     *
     * 100：老板
     *
     * 0：其他（如普通员工）
     */
    private String sys_level;

    private String unionid;

    /**
     * 用户的userid。
     */
    private String userId;

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSys() {
        return sys;
    }

    public void setSys(String sys) {
        this.sys = sys;
    }

    public String getSys_level() {
        return sys_level;
    }

    public void setSys_level(String sys_level) {
        this.sys_level = sys_level;
    }

    public String getUnionid() {
        return unionid;
    }

    public void setUnionid(String unionid) {
        this.unionid = unionid;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
