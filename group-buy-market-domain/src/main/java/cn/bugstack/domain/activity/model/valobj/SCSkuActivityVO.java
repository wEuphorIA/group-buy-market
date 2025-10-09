package cn.bugstack.domain.activity.model.valobj;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 @author Euphoria
 @version 1.0
 @description: 渠道商品活动配置值对象
 @date 2025/10/9 下午5:53 */
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SCSkuActivityVO {

    /** 渠道 */
    private String source;
    /** 来源 */
    private String chanel;
    /** 活动ID */
    private Long activityId;
    /** 商品ID */
    private String goodsId;

}
