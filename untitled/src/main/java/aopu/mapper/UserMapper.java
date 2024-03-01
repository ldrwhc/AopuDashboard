package aopu.mapper;

import aopu.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository //可加可不加，这个容器mybatis已经帮注入进去了
public interface UserMapper {
    public User login(User user);

    public User findUserByUsername(String username);
//
    public void add(String username, String password,String nickname,String corporateName,String storeName,String telephone);
//
    void update(User user);
}
