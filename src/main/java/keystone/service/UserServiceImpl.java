package keystone.service;


import keystone.dao.UserDao;
import keystone.dto.User;
import keystone.mappers.UserMapper;
import lombok.extern.slf4j.Slf4j;
import org.dozer.Mapper;

import javax.annotation.Resource;

@Slf4j
public class UserServiceImpl implements UserService {
    @Resource
    private UserDao userDao;

    @Resource
    private UserMapper userMapper;

    @Resource
    private Mapper mapper;

    public void createUser(User user) {
        keystone.domain.User userDomain = mapper.map(user, keystone.domain.User.class);

        userDao.createUser(userDomain);
    }
}
