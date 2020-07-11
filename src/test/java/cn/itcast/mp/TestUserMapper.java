package cn.itcast.mp;

import cn.itcast.mp.emuns.SexEnum;
import cn.itcast.mp.mapper.UserMapper;
import cn.itcast.mp.pojo.User;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestUserMapper {

    @Autowired
    private UserMapper userMapper;

    @Test
    public void testInsert() {
        User user = new User();
        user.setMail("8410111@qq.com");
        user.setAge(123);
        user.setName("貂蝉");
        user.setPassword("8888888");
        user.setUserName("diaochan");
        user.setAddress("唐人街");
        user.setVersion(1);
        user.setSex(SexEnum.WOMAN);
        // result 数据库受影响的行数
        int result = this.userMapper.insert(user);
        System.out.println("result = " + result);
        // 获取自增长后的Id
        System.out.println("Id = " + user.getId());
    }

    @Test
    public void selectById() {
        User user = this.userMapper.selectById(18L);
        System.out.println("user = " + user);
    }

    @Test
    public void updateById() {
        User user = new User();
        user.setId(1L);
        user.setAge(24);
        user.setPassword("0000");
        int i = this.userMapper.updateById(user);
        System.out.println("i = " + i);
    }

    @Test
    public void Update() {
        User user = new User();
        user.setAge(20);
        user.setPassword("5678888");
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        // 匹配 username = 张三
        wrapper.eq("user_name", "zhangsan");
        int update = this.userMapper.update(user, wrapper);
        System.out.println("update = " + update);
    }

    @Test
    public void Update2() {
        UpdateWrapper<User> wrapper = new UpdateWrapper<>();
        wrapper.set("age", 21).set("password", "999999999") // 更新的字段
                .eq("user_name", "zhangsan"); // 更新的条件
        int update = this.userMapper.update(null, wrapper);
        System.out.println("update = " + update);
    }

    @Test
    public void deleteById() {
        int i = this.userMapper.deleteById(2L);
        System.out.println("i = " + i);
    }

    @Test
    public void deleteByMap() {
        HashMap<String, Object> map = new HashMap<>();
        map.put("user_name", "zhangsan");
        map.put("password", "999999999");
        // 根据map删除数据，多条件之间是 and
        int i = this.userMapper.deleteByMap(map);
        System.out.println("i = " + i);
    }

    @Test
    public void delete() {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("user_name", "caocao")
                .eq("age", 23);
        int delete = this.userMapper.delete(wrapper);
        System.out.println("delete = " + delete);
    }

    @Test
    public void delete2() {
        User user = new User();
        user.setPassword("123456");
        user.setUserName("caocao2");
        QueryWrapper<User> wrapper = new QueryWrapper<>(user);
        int delete = this.userMapper.delete(wrapper);
        System.out.println("delete = " + delete);
    }

    @Test
    public void deleteBatchIds() {
        int ids = this.userMapper.deleteBatchIds(Arrays.asList(9L, 10L));
        System.out.println("ids = " + ids);
    }

    @Test
    public void selectBatchIds() {
        // 根据Id批量查询
        List<User> users = this.userMapper.selectBatchIds(Arrays.asList(2L, 3L, 1L));
        for (User user : users) {
            System.out.println("user = " + user);
        }
    }

    @Test
    public void selectOne() {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("password", "123456");
        // 查询超出一条时，会抛出异常
        User user = this.userMapper.selectOne(wrapper);
        System.out.println("user = " + user);
    }

    @Test
    public void selectCount() {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.gt("age", 20);
        Integer integer = this.userMapper.selectCount(wrapper);
        System.out.println("integer = " + integer);
    }

    @Test
    public void selectList() {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.like("email", "test");
        List<User> users = this.userMapper.selectList(wrapper);
        for (User user : users) {
            System.out.println("user = " + user);
        }
    }

    @Test
    public void selectPage() {
        // 查询第一页，查询2条数据
        Page<User> page = new Page<>(2, 2);

        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.like("email", "test");

        IPage<User> userIPage = this.userMapper.selectPage(page, wrapper);
        System.out.println("数据总条数=" + userIPage.getTotal());
        System.out.println("数据总页数=" + userIPage.getPages());
        System.out.println("当前页数=" + userIPage.getCurrent());

        List<User> records = userIPage.getRecords();
        for (User record : records) {
            System.out.println("User = " + record);
        }
    }

    @Test
    public void testFindById() {
        // 测试自定义的方法
        User byId = this.userMapper.findById(2L);
        System.out.println("byId = " + byId);
    }

    @Test
    public void testAllEq() {
        Map<String, Object> params = new HashMap<>();
        params.put("name", "李四");
        params.put("age", 20);
        params.put("password", null);
        QueryWrapper<User> wrapper = new QueryWrapper<>();

        //  SELECT id,user_name,password,name,age,email AS mail FROM tb_user WHERE password IS NULL AND name = ? AND age = ?
//        wrapper.allEq(params);

        // SELECT id,user_name,password,name,age,email AS mail FROM tb_user WHERE name = ? AND age = ?
        // 对于null是否加入判断
//        wrapper.allEq(params, false);

        // SELECT id,user_name,password,name,age,email AS mail FROM tb_user WHERE age = ?
        // 满足条件才会加入where
//        wrapper.allEq((k, v) -> (k.equals("age") || k.equals("id")), params);
        // SELECT id,user_name,password,name,age,email AS mail FROM tb_user WHERE name = ? AND age = ?
        wrapper.allEq((k, v) -> (k.equals("age") || k.equals("id") || k.equals("name")), params);

        List<User> users = this.userMapper.selectList(wrapper);
        for (User user : users) {
            System.out.println("user = " + user);
        }
    }

    @Test
    public void testEq() {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        // SELECT id,user_name,password,name,age,email FROM tb_user WHERE password = ?
        // AND age >= ? AND name IN (?,?,?)
        wrapper.eq("password", "123456")
                .ge("age", 20)
                .in("name", "李四", "王五", "赵六");
        List<User> users = this.userMapper.selectList(wrapper);
        for (User user : users) {
            System.out.println(user);
        }
    }

    @Test
    public void testLike() {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        // SELECT id,user_name,password,name,age,email FROM tb_user WHERE name LIKE ?
        // Parameters: 曹%(String)
        wrapper.likeRight("name", "曹");
        List<User> users = this.userMapper.selectList(wrapper);
        for (User user : users) {
            System.out.println(user);
        }
    }

    @Test
    public void testDesc() {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.orderByAsc("age");
        List<User> users = this.userMapper.selectList(wrapper);
        for (User user : users) {
            System.out.println(user);
        }
    }

    @Test
    public void testOr() {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("name", "王五").or().eq("age", 20);
        // SELECT id,user_name,password,name,age,email AS mail FROM tb_user WHERE name = ? OR age = ?
        List<User> users = this.userMapper.selectList(wrapper);
        for (User user : users) {
            System.out.println(user);
        }
    }

    @Test
    public void testSelect() {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("name", "王五")
                .or()
                .eq("age", 20)
                .select("id", "name", "age");

        // SELECT id,name,age FROM tb_user WHERE name = ? OR age = ?
        List<User> users = this.userMapper.selectList(wrapper);
        // User(id=2, userName=null, password=null, name=李四, age=20, mail=null, address=null)
        // User(id=3, userName=null, password=null, name=王五, age=28, mail=null, address=null)
        for (User user : users) {
            System.out.println(user);
        }
    }

    @Test
    public void TestFindAll() {
        List<User> all = this.userMapper.findAll();
        for (User user : all) {
            System.out.println("user = " + user);
        }
    }
}

