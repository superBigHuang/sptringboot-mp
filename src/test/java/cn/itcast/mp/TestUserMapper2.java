package cn.itcast.mp;

import cn.itcast.mp.pojo.User;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestUserMapper2 {

    @Test
    public void testSelectById() {
        User user = new User();
        user.setId(2L);
        User user1 = user.selectById();
        System.out.println("user1 = " + user1);
    }

    @Test
    public void testInsert() {
        User user = new User();
        user.setUserName("刘备");
        user.setPassword("444455566");
        user.setAge(1234);
        user.setName("刘备");
        user.setMail("dwad@qq.com");
        boolean insert = user.insert();
        System.out.println("insert = " + insert);
        System.out.println(user);
    }

    @Test
    public void testUpdate() {
        User user = new User();
        user.setId(13L); // 查询条件
        user.setUserName("liubei");
        user.setAge(567);
        boolean b = user.updateById();
        System.out.println(user);
    }

    @Test
    public void testDelete() {
        User user = new User();
        user.setId(15L);
        boolean b = user.deleteById();
        System.out.println("b = " + b);
    }
}
