package com.samin.auth.entity;

import com.samin.auth.authentication.CustomUserDetails;
import com.samin.auth.config.RequestThreadLocal;
import java.time.LocalDateTime;
import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.Data;
import org.hibernate.annotations.Comment;

@Data
@Entity
@Table(name = "system_log", schema = "system")
public class SystemLog {

    public static SystemLog getInstance(HttpServletRequest request, HttpServletResponse response,
                                        RequestThreadLocal.Request requestData, CustomUserDetails customUserDetails,
                                        String remark, long executionTime) {
        SystemLog ins = new SystemLog();

        if (Objects.nonNull(customUserDetails)) {
            ins.setUserId(customUserDetails.getUser()
                                           .getId());
        }
        ins.setClientIpAddress(request.getRemoteAddr());
        ins.setRequestTime(requestData.getRequestTime());
        ins.setRequestPath(request.getRequestURI());
        ins.setRequestParameters(requestData.getBody());
        ins.setRequestMethod(request.getMethod());
        ins.setResponseStatusCode(response.getStatus());
        ins.setResponseMessage(requestData.getResponse());
        ins.setRemark(remark);
        ins.setExecutionTime(executionTime);
        ins.setDeviceInformation(request.getHeader("User-Agent"));
        ins.setCreateTime(LocalDateTime.now());

        return ins;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Comment("操作用户id")
    private Integer userId;

    @Comment("备注")
    private String remark;

    @Comment("操作 ip")
    private String clientIpAddress;

    @Comment("请求时间")
    private LocalDateTime requestTime;

    @Comment("请求路径")
    private String requestPath;

    @Comment("请求方法")
    private String requestMethod;

    @Comment("请求参数")
    private String requestParameters;

    @Comment("响应状态码")
    private Integer responseStatusCode;

    @Comment("响应信息")
    private String responseMessage;

    @Comment("执行时间")
    private Long executionTime;

    @Comment("设备信息，User Agent")
    private String deviceInformation;

    @Comment("操作时间")
    private LocalDateTime createTime;
}
