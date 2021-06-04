package me.lv.handler;

import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @author plume
 * @date 2021/6/2 14:42
 */
@Slf4j
public class LogHandler implements InvocationHandler {

    private final Object target;

    public LogHandler(Object target) {
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        before();
        Object invoke = method.invoke(target, args);
        after();
        return invoke;
    }

    private void before() {
        log.info("before");
    }

    private void after() {
        log.info("after");
    }
}
