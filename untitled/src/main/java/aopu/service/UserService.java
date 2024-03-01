package aopu.service;

import aopu.pojo.User;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
    public User login(User user);

    public User findUserByUsername(String username);

    public boolean isExistByUsername(String username);

    void register(String username, String password,String nickname,String corporateName,String storeName,String telephone);

    void update(User user);

    void updatePwd(User user, String newPwd);
}
