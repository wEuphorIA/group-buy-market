package cn.bugstack.domain.activity.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 @author Euphoria
 @version 1.0
 @description: 营销商品实体信息，通过这样一个信息获取商品优惠信息
 @date 2025/10/3 上午11:39 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MarketProductEntity {

    /**
     用户ID
     */
    private String userId;
    /**
     商品ID
     */
    private String goodsId;
    /**
     渠道
     */
    private String source;
    /**
     来源
     */
    private String channel;

    /**
      活动id
     */
    private Long activityId;

}
