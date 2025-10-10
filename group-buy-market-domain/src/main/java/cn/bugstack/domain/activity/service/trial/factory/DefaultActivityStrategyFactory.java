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
 @description: 活动策略工厂
 @date 2025/10/3 上午11:49 */
@Service
public class DefaultActivityStrategyFactory {

    private final RootNode rootNode;

    public DefaultActivityStrategyFactory(RootNode rootNode) {
        this.rootNode = rootNode;
    }

    public StrategyHandler<MarketProductEntity, DynamicContext, TrialBalanceEntity> strategyHandler() {
        return rootNode;
    }

    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class DynamicContext {

        // 活动折扣信息
        private GroupBuyActivityDiscountVO groupBuyActivityDiscountVO;

        // 商品SKU信息
        private SkuVO skuVO;

        // 折扣价格
        private BigDecimal deductionPrice;

        // 活动可见性限制
        private boolean visible;

        // 活动
        private boolean enable;

    }
}
