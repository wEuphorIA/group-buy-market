package cn.bugstack.infrastructure.dcc;

import cn.bugstack.types.annoations.DCCValue;
import cn.bugstack.types.common.Constants;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

/**
 @author Euphoria
 @version 1.0
 @description: 动态配置服务
 @date 2025/10/11 下午11:15 */
@Service
public class DCCService {

    @DCCValue("downgradeSwitch:0")
    private String downgradeSwitch;

    @DCCValue("cutRange:100")
    private String cutRange;

    @DCCValue("whiteList:xiaofuge,liergou")
    private String whiteList;

    public boolean isDowngradeSwitch(){
        return "1".equals(downgradeSwitch);
    }

    public boolean isCutRange(String userId){
        int hashcode = Math.abs(userId.hashCode());
        int lastTwoDigits = hashcode % 100;
        if (lastTwoDigits <= Integer.parseInt(cutRange)) {
            return true;
        }
        return false;
    }

    public boolean isWhiteLsit(String userId){
        if(StringUtils.isBlank(whiteList)){
            return false;
        }
        List<String> list = Arrays.asList(whiteList.split(Constants.SPLIT));
        return list.contains(userId);
    }
}
