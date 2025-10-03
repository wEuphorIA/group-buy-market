package cn.bugstack.types.design.framework.tree;

import lombok.Getter;
import lombok.Setter;

import java.util.concurrent.ExecutionException;

/**
 @author Euphoria
 @version 1.0
 @description: TODO
 @date 2025/10/3 上午11:28 */
public abstract class AbstractStrategyRouter<T, D, R> implements StrategyMapper<T, D, R> ,StrategyHandler<T, D, R>  {

    @Getter
    @Setter
    protected StrategyHandler<T, D, R> defaultStrategyHandler = StrategyHandler.DEFAULT;

    public R router(T requestParameter, D dynamicContext) throws Exception{
        StrategyHandler<T, D, R> strategyHandler = get(requestParameter, dynamicContext);
        if (strategyHandler != null)  return  strategyHandler.apply(requestParameter, dynamicContext);
        return defaultStrategyHandler.apply(requestParameter, dynamicContext);
    }
}
