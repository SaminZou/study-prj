package com.samin.wx.common;

/**
 * 常量管理类
 */
public final class Constants {

    /**
     * 日期时间格式化模式
     */
    public static final String DATETIME_PATTERN = "yyyy-MM-dd HH:mm:ss";
    public static final String DATE_PATTERN = "yyyy-MM-dd";
    public static final String TIME_PATTERN = "HH:mm:ss";

    /**
     * 微信相关常量
     */
    public static final String WX_MINI_APPID_CONFIG_KEY = "wx.miniapp.appid";
    public static final String WX_MINI_SECRET_CONFIG_KEY = "wx.miniapp.secret";
    public static final String WX_MINI_LOGIN_PATH = "/v1/wx/login";
    public static final String WX_MINI_GET_PHONE_PATH = "/v1/wx/getPhoneNumber";

    /**
     * 日志相关常量
     */
    public static final String LOG_REQUEST_CODE_LENGTH = "请求code长度：{}";
    public static final String LOG_WX_LOGIN_SUCCESS = "微信登录成功，获取openId成功";
    public static final String LOG_WX_LOGIN_FAILED = "微信登录失败，错误码：{}";
    public static final String LOG_GET_PHONE_SUCCESS = "获取手机号成功";
    public static final String LOG_GET_PHONE_FAILED = "获取手机号失败，错误码：{}";

    /**
     * 通用常量
     */
    public static final String EMPTY_STRING = "";
    public static final int ZERO = 0;
    public static final int ONE = 1;
    public static final int TWO = 2;
    public static final int HUNDRED = 100;
    public static final int MAX_CODE_LENGTH = 255;

    /**
     * 私有构造方法，防止实例化
     */
    private Constants() {
    }
}