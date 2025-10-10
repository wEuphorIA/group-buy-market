package cn.bugstack.domain.activity.service.trial;

import cn.bugstack.domain.activity.adapter.repository.IActivityRepository;
import cn.bugstack.types.design.framework.tree.AbstractMultiThreadStrategyRouter;
import cn.bugstack.types.design.framework.tree.AbstractStrategyRouter;

import javax.annotation.Resource;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;

/**
 @author Euphoria
 @version 1.0
 @description: 抽象的拼团营销支撑类
 @date 2025/10/3 上午11:45 */
public abstract class AbstractGroupBuyMarketSupport<MarketProductEntity,DynamicContext,TrialBalanceEntity> extends AbstractMultiThreadStrategyRouter<MarketProductEntity,DynamicContext,TrialBalanceEntity> {

    protected long timeout = 500;

    @Resource
    protected IActivityRepository repository;

    @Override
    protected void multiThread(MarketProductEntity requestParameter, DynamicContext dynamicContext) throws ExecutionException, InterruptedException, TimeoutException {

    }
}
