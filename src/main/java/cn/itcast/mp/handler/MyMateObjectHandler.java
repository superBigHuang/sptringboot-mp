package cn.itcast.mp.handler;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

@Component
public class MyMateObjectHandler implements MetaObjectHandler {

    // 在插入数据时填充
    @Override
    public void insertFill(MetaObject metaObject) {
        // 先获取到password的值，在进行判断
        // 如果为空，就进行填充
        // 如果不为空，就不处理
        Object password = getFieldValByName("password", metaObject);
        if (password == null) {
            setFieldValByName("password", "123456", metaObject);
        }

    }

    // 在更新数据时填充
    @Override
    public void updateFill(MetaObject metaObject) {

    }
}
