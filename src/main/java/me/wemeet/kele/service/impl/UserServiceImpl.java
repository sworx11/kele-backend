package me.wemeet.kele.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import me.wemeet.kele.common.helper.RedisHelper;
import me.wemeet.kele.common.util.KeleUtils;
import me.wemeet.kele.entity.User;
import me.wemeet.kele.mapper.UserMapper;
import me.wemeet.kele.service.CommonService;
import me.wemeet.kele.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Quino Wu
 * @since 2021-12-04
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public long countUserByEmail(String email) {
        QueryWrapper<User> wrapper = new QueryWrapper<User>().eq("email", email);
        return count(wrapper);
    }

    @Override
    public long countUserByPhone(String phone) {
        QueryWrapper<User> wrapper = new QueryWrapper<User>().eq("phone", phone);
        return count(wrapper);
    }

    @Override
    public void createUser(String email, String name, String password, String lang) {
        email = new String(Base64.getDecoder().decode(email), StandardCharsets.UTF_8);
        User user = new User();
        user.setNickName(KeleUtils.nextNickName(lang));
        user.setEmail(email);
        user.setName(name);
        user.setPassword(password);
        userMapper.insert(user);
    }

    @Override
    public User login(User user) {
        QueryWrapper<User> wrapper = new QueryWrapper<User>().eq("password", user.getPassword());
        if (user.getName() != null && !user.getName().isBlank()) {
            wrapper.eq("name", user.getName());
            return getOne(wrapper);
        }

        if (user.getEmail() != null && !user.getEmail().isBlank()) {
            wrapper.eq("email", user.getEmail());
            return getOne(wrapper);
        }

        if (user.getPhone() != null && !user.getPhone().isBlank()) {
            wrapper.eq("phone", user.getPhone());
            return getOne(wrapper);
        }

        return null;
    }
}