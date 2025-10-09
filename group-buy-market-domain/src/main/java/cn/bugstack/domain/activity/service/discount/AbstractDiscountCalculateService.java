package cn.bugstack.domain.activity.service.discount;

import cn.bugstack.domain.activity.model.valobj.DiscountTypeEnum;
import cn.bugstack.domain.activity.model.valobj.GroupBuyActivityDiscountVO;

import java.math.BigDecimal;

/**
 @author Euphoria
 @version 1.0
 @description: TODO
 @date 2025/10/9 上午10:36 */
public abstract class AbstractDiscountCalculateService implements IDiscountCalculateService {

    // 默认最小价格
    private static final BigDecimal DEFAULT_MIN_PRICE = new BigDecimal("0.01");

    @Override
    public BigDecimal calculate(String userId, BigDecimal originalPrice, GroupBuyActivityDiscountVO.GroupBuyDiscount groupBuyDiscount) {
        // 过滤人群标签
        if (DiscountTypeEnum.TAG.equals(groupBuyDiscount.getDiscountType())){
            boolean isCrowdRange = filterTagId(userId,groupBuyDiscount.getTagId());
            if (!isCrowdRange) return originalPrice;

        }
        BigDecimal discountedPrice = doCalculate(originalPrice, groupBuyDiscount);

        return ensureMinimumPrice(discountedPrice);
    }

    private boolean filterTagId(String userId, String tagId) {
        return true;
    }

    protected abstract BigDecimal doCalculate(BigDecimal originalPrice, GroupBuyActivityDiscountVO.GroupBuyDiscount groupBuyDiscount);

    private BigDecimal ensureMinimumPrice(BigDecimal price) {
        if (price == null) {
            return DEFAULT_MIN_PRICE;
        }
        return price.compareTo(BigDecimal.ZERO) <= 0 ? DEFAULT_MIN_PRICE : price;
    }
}
