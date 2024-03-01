package aopu.service.impl;

import aopu.mapper.UserMapper;
import aopu.pojo.User;
import aopu.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;
    @Override
    public User login(User user) {
        User loginUser = userMapper.login(user);
        return loginUser;
    }

    @Override
    public User findUserByUsername(String username) {
        return userMapper.findUserByUsername(username);
    }

    @Override
    public boolean isExistByUsername(String username) {
        return userMapper.findUserByUsername(username) != null;
    }

    @Override
    public void register(String username, String password,String nickname,String corporateName,String storeName,String telephone) {
        userMapper.add(username,password,nickname,corporateName,storeName,telephone);
    }

    @Override
    public void update(User user) {
        System.out.println("userService" + user);
        userMapper.update(user);
    }

    @Override
    public void updatePwd(User user, String newPwd) {

    }
}
