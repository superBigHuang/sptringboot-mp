package cn.itcast.mp;

import cn.itcast.mp.plugins.MyInterceptor;
import com.baomidou.mybatisplus.core.parser.ISqlParser;
import com.baomidou.mybatisplus.extension.parsers.BlockAttackSqlParser;
import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import com.baomidou.mybatisplus.extension.plugins.SqlExplainInterceptor;
import org.apache.ibatis.jdbc.SQL;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

// mybatis-plus 配置类
@Configuration
@MapperScan("cn.itcast.mp.mapper") //设置mapper接口的扫描包
public class MybatisPlusConfig {

    // 配置分页插件
    @Bean
    public PaginationInterceptor paginationInterceptor() {
        return new PaginationInterceptor();
    }

    // 注入自定义拦截器插件
//    @Bean
//    public MyInterceptor myInterceptor() {
//        return new MyInterceptor();
//    }

    //
    @Bean
    public SqlExplainInterceptor sqlExplainInterceptor(){
        SqlExplainInterceptor sqlExplainInterceptor = new SqlExplainInterceptor();
        List<ISqlParser> list = new ArrayList();
        list.add(new BlockAttackSqlParser()); // 全表更新或者删除的阻断器
        sqlExplainInterceptor.setSqlParserList(list);
        return sqlExplainInterceptor;
    }
}
