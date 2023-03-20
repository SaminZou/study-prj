package com.samin.auth.db;

import com.samin.auth.authentication.CustomUserDetails;
import java.util.HashMap;

/**
 * 数据库服务类
 *
 * @author samin
 * @date 2022-08-08
 */
public class DBService {

    /**
     * 存放用户数据
     */
    public static final HashMap<String, CustomUserDetails> userDetailsMap = new HashMap<String, CustomUserDetails>() {
        {
            put("samin", CustomUserDetails.getInstance("samin", "123"));
        }
    };
}
