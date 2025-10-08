package cn.bugstack.types.design.framework.tree;

/**
 @author Euphoria
 @version 1.0
 @description: 策略映射器
 作为策略的导航员，负责根据当前请求参数和动态上下文，决策并获取下一个应当执行的具体策略处理器。
 @date 2025/10/2 下午11:09 */
public interface StrategyMapper<T,D,R> {

    StrategyHandler<T, D, R> get(T requestParameter, D dynamicContext) throws Exception;
}
