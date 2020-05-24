package cn.Lee.ssm.user.service;


import cn.Lee.ssm.user.mapper.UserMapper;
import cn.Lee.ssm.user.pojo.User;
import cn.Lee.ssm.user.pojo.UserExample;
import cn.Lee.ssm.utils.MailUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class UserService {


    @Autowired
    private UserMapper userMapper;


    public User findByUsername(String username) {


        UserExample userExample = new UserExample();
        UserExample.Criteria criteria = userExample.createCriteria();
        criteria.andUsernameEqualTo(username);
        List<User> userList = userMapper.selectByExample(userExample);
        if (userList != null && userList.size() > 0) {

            return userList.get(0);

        }

        return null;

    }

    public User findByEmail(String email) {

        UserExample userExample = new UserExample();
        UserExample.Criteria criteria = userExample.createCriteria();
        criteria.andEmailEqualTo(email);
        List<User> userList = userMapper.selectByExample(userExample);
        if (userList != null && userList.size() > 0) {

            return userList.get(0);

        }

        return null;
    }

    public void regist(User user) throws Exception {

        String code = UUID.randomUUID().toString().replace("-", "")
                + UUID.randomUUID().toString().replace("-", "");
        user.setState(0);
        user.setCode(code);
        userMapper.insert(user);
        new MailUtil().sendMail(user.getEmail(), code);

    }

    public User findByCode(String code) {


        UserExample userExample = new UserExample();
        UserExample.Criteria criteria = userExample.createCriteria();
        criteria.andCodeEqualTo(code);
        List<User> userList = userMapper.selectByExample(userExample);
        if (userList != null && userList.size() > 0) {

            return userList.get(0);

        }

        return null;
    }

    public void updateUser(User user) {

        user.setState(1);
        user.setCode(null);
        userMapper.updateByPrimaryKey(user);
    }
}
