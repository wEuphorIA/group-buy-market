package cn.bugstack.domain.trade.adapter.port;

import cn.bugstack.domain.trade.model.entity.NotifyTaskEntity;

/**
 @author Euphoria
 @version 1.0
 @description: 交易接口服务接口
 @date 2025/10/22 下午3:27 */
public interface ITradePort {
    String groupBuyNotify(NotifyTaskEntity notifyTaskEntity) throws Exception;
}
