package cn.bugstack.domain.activity.service.discount.impl;

import cn.bugstack.domain.activity.model.valobj.GroupBuyActivityDiscountVO;
import cn.bugstack.domain.activity.service.discount.AbstractDiscountCalculateService;
import cn.bugstack.types.common.Constants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

/**
 @author Euphoria
 @version 1.0
 @description: TODO
 @date 2025/10/9 上午10:44 */
@Slf4j
@Service("MJ")
public class MJCalculateService extends AbstractDiscountCalculateService {
    @Override
    protected BigDecimal doCalculate(BigDecimal originalPrice, GroupBuyActivityDiscountVO.GroupBuyDiscount groupBuyDiscount) {
        log.info("优惠策略折扣计算:{}", groupBuyDiscount.getDiscountType().getCode());
        //获取公式
        String marketExpr = groupBuyDiscount.getMarketExpr();

        String[] split = marketExpr.split(Constants.SPLIT);

        BigDecimal x = new BigDecimal(split[0]);

        BigDecimal y = new BigDecimal(split[1]);

        if (originalPrice.compareTo(x) < 0){
            return originalPrice;
        }

        return originalPrice.subtract(y);
    }
}
