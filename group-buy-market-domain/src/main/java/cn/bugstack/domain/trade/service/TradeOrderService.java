package cn.bugstack.domain.trade.service;

import cn.bugstack.domain.trade.adapter.repository.ITradeRepository;
import cn.bugstack.domain.trade.model.aggregate.GroupBuyOrderAggregate;
import cn.bugstack.domain.trade.model.entity.MarketPayOrderEntity;
import cn.bugstack.domain.trade.model.entity.PayActivityEntity;
import cn.bugstack.domain.trade.model.entity.PayDiscountEntity;
import cn.bugstack.domain.trade.model.entity.UserEntity;
import cn.bugstack.domain.trade.model.valobj.GroupBuyProgressVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 @author Euphoria
 @version 1.0
 @description: 交易订单服务
 @date 2025/10/12 下午4:01 */
@Slf4j
@Service
public class TradeOrderService implements ITradeOrderService{

    @Resource
    private ITradeRepository repository;


    /**
     查询，未被支付消费完成的营销优惠订单

     @param userId     用户ID
     @param outTradeNo 外部唯一单号
     @return 拼团，预购订单营销实体对象
     */
    @Override
    public MarketPayOrderEntity queryNoPayMarketPayOrderByOutTradeNo(String userId, String outTradeNo) {
        log.info("拼团交易-查询未支付营销订单:{} outTradeNo:{}", userId, outTradeNo);
        return repository.queryNoPayMarketPayOrderByOutTradeNo(userId,outTradeNo);
    }

    /**
     查询拼团进度

     @param teamId 拼团ID
     @return 进度
     */
    @Override
    public GroupBuyProgressVO queryGroupBuyProgress(String teamId) {
        log.info("拼团交易-查询拼单进度:{}", teamId);

        return repository.queryGroupBuyProgress(teamId);
    }

    /**
     锁定，营销预支付订单；商品下单前，预购锁定。

     @param userEntity        用户根实体对象
     @param payActivityEntity 拼团，支付活动实体对象
     @param payDiscountEntity 拼团，支付优惠实体对象
     @return 拼团，预购订单营销实体对象
     */
    @Override
    public MarketPayOrderEntity lockMarketPayOrder(UserEntity userEntity, PayActivityEntity payActivityEntity, PayDiscountEntity payDiscountEntity) {
        log.info("拼团交易-锁定营销优惠支付订单:{} activityId:{} goodsId:{}", userEntity.getUserId(), payActivityEntity.getActivityId(), payDiscountEntity.getGoodsId());
        GroupBuyOrderAggregate groupBuyOrderAggregate = GroupBuyOrderAggregate.builder()
                .userEntity(userEntity)
                .payActivityEntity(payActivityEntity)
                .payDiscountEntity(payDiscountEntity)
                .build();
        return repository.lockMarketPayOrder(groupBuyOrderAggregate);
    }
}
