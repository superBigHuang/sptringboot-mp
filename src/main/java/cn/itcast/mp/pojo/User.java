package cn.itcast.mp.pojo;

import com.baomidou.mybatisplus.annotation.*;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
//@TableName("tb_user")
public class User extends Model<User> {
    // 设置Id生成策略
//    @TableId(type = IdType.AUTO)
    private Long id;
    private String userName;
    // 插入数据时进行填充
    @TableField(fill = FieldFill.INSERT)
    private String password;
    private String name;
    private Integer age;

    // 对象中属性名者和字段名不一致(非驼峰)
    @TableField(value = "email")
    private String mail;

    // 对象中的属性字段在表中不存在
    @TableField(exist = false)
    private String address;

    @Version // 乐观锁的版本字段
    private Integer version;
}
