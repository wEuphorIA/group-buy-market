package cn.bugstack.infrastructure.adapter.repository;

import cn.bugstack.domain.activity.adapter.repository.IActivityRepository;
import cn.bugstack.domain.activity.model.valobj.DiscountTypeEnum;
import cn.bugstack.domain.activity.model.valobj.GroupBuyActivityDiscountVO;
import cn.bugstack.domain.activity.model.valobj.SCSkuActivityVO;
import cn.bugstack.domain.activity.model.valobj.SkuVO;
import cn.bugstack.infrastructure.dao.IGroupBuyActivityDao;
import cn.bugstack.infrastructure.dao.IGroupBuyDiscountDao;
import cn.bugstack.infrastructure.dao.ISCSkuActivityDao;
import cn.bugstack.infrastructure.dao.ISkuDao;
import cn.bugstack.infrastructure.dao.po.GroupBuyActivity;
import cn.bugstack.infrastructure.dao.po.GroupBuyDiscount;
import cn.bugstack.infrastructure.dao.po.SCSkuActivity;
import cn.bugstack.infrastructure.dao.po.Sku;
import cn.bugstack.infrastructure.dcc.DCCService;
import cn.bugstack.infrastructure.redis.IRedisService;
import org.redisson.api.RBitSet;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

/**
 @author Euphoria
 @version 1.0
 @description: 活动仓储
 @date 2025/10/8 下午9:01 */
@Repository
public class ActivityRepository implements IActivityRepository {

    @Resource
    private IGroupBuyActivityDao groupBuyActivityDao;

    @Resource
    private IGroupBuyDiscountDao groupBuyDiscountDao;

    @Resource
    private ISkuDao skuDao;

    @Resource
    private ISCSkuActivityDao iscSkuActivityDao;

    @Resource
    private IRedisService redisService;

    @Resource
    private DCCService dccService;

    @Override
    public GroupBuyActivityDiscountVO queryGroupBuyActivityDiscountVO(Long activityId) {

        GroupBuyActivity groupBuyActivityRes = groupBuyActivityDao.queryValidGroupBuyActivityId(activityId);
        if (groupBuyActivityRes == null) return null;

        String discountId = groupBuyActivityRes.getDiscountId();

        GroupBuyDiscount groupBuyDiscountRes = groupBuyDiscountDao.queryGroupBuyActivityDiscountByDiscountId(discountId);

        if (groupBuyDiscountRes == null) return null;

        GroupBuyActivityDiscountVO.GroupBuyDiscount groupBuyDiscount = GroupBuyActivityDiscountVO.GroupBuyDiscount.builder()
                .discountName(groupBuyDiscountRes.getDiscountName())
                .discountDesc(groupBuyDiscountRes.getDiscountDesc())
                .discountType(DiscountTypeEnum.get(groupBuyDiscountRes.getDiscountType()))
                .marketPlan(groupBuyDiscountRes.getMarketPlan())
                .marketExpr(groupBuyDiscountRes.getMarketExpr())
                .tagId(groupBuyDiscountRes.getTagId())
                .build();

        return GroupBuyActivityDiscountVO.builder()
                .activityId(groupBuyActivityRes.getActivityId())
                .activityName(groupBuyActivityRes.getActivityName())
                .groupBuyDiscount(groupBuyDiscount)
                .groupType(groupBuyActivityRes.getGroupType())
                .takeLimitCount(groupBuyActivityRes.getTakeLimitCount())
                .target(groupBuyActivityRes.getTarget())
                .validTime(groupBuyActivityRes.getValidTime())
                .status(groupBuyActivityRes.getStatus())
                .startTime(groupBuyActivityRes.getStartTime())
                .endTime(groupBuyActivityRes.getEndTime())
                .tagId(groupBuyActivityRes.getTagId())
                .tagScope(groupBuyActivityRes.getTagScope())
                .build();

    }

    @Override
    public SkuVO querySkuByGoodsId(String goodsId) {
        Sku sku = skuDao.querySkuByGoodsId(goodsId);
        if (sku == null) return null;
        return SkuVO.builder()
                .goodsId(sku.getGoodsId())
                .goodsName(sku.getGoodsName())
                .originalPrice(sku.getOriginalPrice())
                .build();

    }

    @Override
    public SCSkuActivityVO querySCSkuActivityBySCGoodsId(String source, String channel, String goodsId) {
        SCSkuActivity scSkuActivityReq = new SCSkuActivity();
        scSkuActivityReq.setSource(source);
        scSkuActivityReq.setChannel(channel);
        scSkuActivityReq.setGoodsId(goodsId);

        SCSkuActivity scSkuActivity = iscSkuActivityDao.querySCSkuActivityBySCGoodsId(scSkuActivityReq);
        if (scSkuActivity == null) return null;

        return SCSkuActivityVO.builder()
                .activityId(scSkuActivity.getActivityId())
                .source(scSkuActivity.getSource())
                .chanel(scSkuActivity.getChannel())
                .goodsId(scSkuActivity.getGoodsId())
                .build();
    }

    @Override
    public boolean isTagCrowRange(String tagId, String userId) {
        RBitSet bitSet = redisService.getBitSet(tagId);
        if (!bitSet.isExists()) return true;

        return bitSet.get(redisService.getIndexFromUserId(userId));
    }

    @Override
    public boolean downgradeSwitch() {
        return dccService.isDowngradeSwitch();
    }

    @Override
    public boolean cutRange(String userId) {
        return dccService.isCutRange(userId);
    }

    @Override
    public boolean isWhite(String userId) {
        return dccService.isWhiteLsit(userId);
    }
}
