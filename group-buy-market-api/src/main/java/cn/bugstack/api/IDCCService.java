package cn.bugstack.api;

import cn.bugstack.api.response.Response;

/**
 @author Euphoria
 @version 1.0
 @description: DCC 动态配置中心
 @date 2025/10/12 上午12:01 */
public interface IDCCService {

    Response<Boolean> updateConfig(String key, String value);

}

