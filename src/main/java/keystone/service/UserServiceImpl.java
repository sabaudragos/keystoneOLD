package keystone.service;


import keystone.dao.UserDao;
import keystone.dto.User;
import lombok.extern.slf4j.Slf4j;
import org.dozer.Mapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
@Slf4j
public class UserServiceImpl implements UserService {
    @Resource
    private UserDao userDao;

    @Resource
    private Mapper mapper;

    public void createUser(User user) {
        keystone.domain.User userDomain = mapper.map(user, keystone.domain.User.class);

        userDao.createUser(userDomain);
    }

    @Override
    public User getUser(Long id) {
        log.debug("User id: {}", id);
        keystone.domain.User userDomain = userDao.getUserById(id);

        return mapper.map(userDomain, User.class);
    }
}
