package com.yx.admin.service;

import com.yx.admin.dao.MenuDao;
import com.yx.admin.dao.UserDao;
import com.yx.entity.domain.Menu;
import com.yx.entity.domain.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class SystemService {
    @Autowired
    private UserDao userDao;
    @Autowired
    private MenuDao menuDao;

    public List<User> findUserByUserLogin(String userLogin) {
        return userDao.findByUserLogin(userLogin);
    }

    public List<Menu> findMenuByParentId(String parentId) {
        return menuDao.findByParentId(parentId);
    }

    public Page<User> findUsers(User user, Pageable pageable) {
        return userDao.findAll(Example.of(user), pageable);
    }

    public void save(User user) {
        userDao.save(user);
    }

    public void deleteUser(String userId) {
        userDao.deleteById(userId);
    }
}
