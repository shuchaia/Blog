package com.shuchaia.service;

import com.shuchaia.domain.ResponseResult;
import com.shuchaia.domain.entity.User;

public interface BlogLoginService {
    ResponseResult login(User user);

    ResponseResult logout();
}
