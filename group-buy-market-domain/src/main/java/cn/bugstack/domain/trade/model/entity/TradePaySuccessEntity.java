package cn.bugstack.domain.trade.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
/**
 * @author Fuzhengwei bugstack.cn @小傅哥
 * @description 交易支付订单实体对象
 * @create 2025-01-26 14:52
 */

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TradePaySuccessEntity {

    /** 渠道 */
    private String source;
    /** 来源 */
    private String channel;
    /** 用户ID */
    private String userId;
    /** 外部交易单号 */
    private String outTradeNo;

}
