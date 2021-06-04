package me.lv.service.impl;

import lombok.extern.slf4j.Slf4j;
import me.lv.service.UserService;

/**
 * @author plume
 * @date 2021/6/2 14:57
 */
@Slf4j
public class UserServiceImpl implements UserService {
    @Override
    public void users() {
        log.info("users");
    }
}
