package cn.bugstack.types.design.framework.tree;

/**
 @author Euphoria
 @version 1.0
 @description: 策略处理器
 作为策略的执行者，负责封装和执行具体的业务逻辑单元。
 @date 2025/10/2 下午11:22 */
public interface StrategyHandler<T,D,R> {

    StrategyHandler DEFAULT  = (T,D) -> null;

    R apply(T requestParameter, D dynamicContext) throws Exception;
}
