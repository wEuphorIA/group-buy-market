package cn.bugstack.domain.activity.service.trial.factory;

import cn.bugstack.domain.activity.model.entity.MarketProductEntity;
import cn.bugstack.domain.activity.model.entity.TrialBalanceEntity;
import cn.bugstack.domain.activity.model.valobj.GroupBuyActivityDiscountVO;
import cn.bugstack.domain.activity.model.valobj.SkuVO;
import cn.bugstack.domain.activity.service.trial.node.RootNode;
import cn.bugstack.types.design.framework.tree.StrategyHandler;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

/**
 @author Euphoria
 @version 1.0
 @description: TODO
 @date 2025/10/3 上午11:49 */
//活动策略工厂
@Service
public class DefaultActivityStrategyFactory {

    private final RootNode rootNode;

    public DefaultActivityStrategyFactory(RootNode rootNode) {
        this.rootNode = rootNode;
    }

    public StrategyHandler<MarketProductEntity, DynamicContext, TrialBalanceEntity> strategyHandler(){
        return rootNode;
    }

    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class DynamicContext{

        private GroupBuyActivityDiscountVO groupBuyActivityDiscountVO;

        private SkuVO skuVO;

        private BigDecimal deductionPrice;
    }
}
