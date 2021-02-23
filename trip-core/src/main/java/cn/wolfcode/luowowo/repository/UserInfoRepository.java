package cn.wolfcode.luowowo.repository;

import cn.wolfcode.luowowo.domain.UserInfo;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
/*
* 用户持久层接口
* */
@Repository
public interface UserInfoRepository extends MongoRepository<UserInfo,String> {
    //用户注册验证手机是否存在
    UserInfo findByPhone(String phone);
}
