/**
 * Copyright (c) 2005-2012 https://github.com/zhangkaitao
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 */
package com.jmhz.device.sys.service.impl;

import com.jmhz.device.sys.exception.UserPasswordRetryLimitExceedException;
import com.jmhz.device.sys.entity.User;
import com.jmhz.device.sys.exception.UserPasswordNotMatchException;
import com.jmhz.device.sys.utils.UserLogUtils;
import com.jmhz.device.util.Md5Utils;
import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;

import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

/**
 * <p>User: Zhang Kaitao
 * <p>Date: 13-3-12 上午7:18
 * <p>Version: 1.0
 */
@Service
public class PasswordService {

    @Autowired
    private CacheManager cacheManager;

    private Cache passwordRetryCache;

    private int maxRetryCount = 10;

    public void setMaxRetryCount(int maxRetryCount) {
        this.maxRetryCount = maxRetryCount;
    }

    @PostConstruct
    public void init() {
        passwordRetryCache = cacheManager.getCache("passwordRetryCache");
    }

    public void validate(User user, String password) {
        String username = user.getUsername();

        int retryCount = 0;

        Element cacheElement = passwordRetryCache.get(username);
        if (cacheElement != null) {
            retryCount = (Integer) cacheElement.getObjectValue();
            if (retryCount >= maxRetryCount) {
                UserLogUtils.log(
                        username,
                        "passwordError",
                        "password error, retry limit exceed! password: {},max retry count {}",
                        password, maxRetryCount);
                throw new UserPasswordRetryLimitExceedException(maxRetryCount);
            }
        }

        if (!matches(user, password)) {
            passwordRetryCache.put(new Element(username, ++retryCount));
            UserLogUtils.log(
                    username,
                    "passwordError",
                    "password error! password: {} retry count: {}",
                    password, retryCount);
            throw new UserPasswordNotMatchException();
        } else {
            clearPasswordRetryCache(username);
        }
    }

    public boolean matches(User user, String newPassword) {
        return user.getPassword().equals(encryptPassword(user.getUsername(), newPassword, user.getSalt()));
    }

    public boolean matchePassword(User user, String newPassword) {
        return user.getPassword().equals(encryptPasswords(user.getUsername(), newPassword, user.getSalt()));
    }
    public void clearPasswordRetryCache(String username) {
        passwordRetryCache.remove(username);
    }


    public String encryptPassword(String username, String password, String salt) {
        return Md5Utils.hash(username + password + salt);
    }

    public String encryptPasswords(String username, String password, String salt) {
        String newPassword = new SimpleHash(
                "md5",
                password,
                ByteSource.Util.bytes(username + salt),
                2).toHex();
        return newPassword;
    }


    public static void main(String[] args) {
        System.out.println(new PasswordService().encryptPassword("monitor", "123456", "iY71e4d123"));
    }
}
