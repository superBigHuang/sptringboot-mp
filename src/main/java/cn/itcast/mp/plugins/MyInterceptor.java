package cn.itcast.mp.plugins;

import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.*;

import java.util.Properties;
import java.util.concurrent.Executor;


//MyBatis 允许你在已映射语句执行过程中的某一点进行拦截调用。默认情况下，MyBatis 允许使用插件来拦截的方法
//调用包括：
//1. Executor (update, query, flushStatements, commit, rollback, getTransaction, close, isClosed)
//2. ParameterHandler (getParameterObject, setParameters)
//3. ResultSetHandler (handleResultSets, handleOutputParameters)
//4. StatementHandler (prepare, parameterize, batch, update, query)
//我们看到了可以拦截Executor接口的部分方法，比如update，query，commit，rollback等方法，还有其他接口的
//一些方法等。
//总体概括为：
//1. 拦截执行器的方法
//2. 拦截参数的处理
//3. 拦截结果集的处理
//4. 拦截Sql语法构建的处理

@Intercepts({@Signature(
        type = Executor.class, // 拦截的执行器
        method = "update",  // 拦截update执行方法
        args = {MappedStatement.class, Object.class}
)})
public class MyInterceptor implements Interceptor {
    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        // 拦截方法，具体业务逻辑编写的位置
        return invocation.proceed();
    }

    @Override
    public Object plugin(Object o) {
        // 创建target对象的代理对象，目的是将当前拦截器加入到该对象中
        return Plugin.wrap(o, this);
    }

    @Override
    public void setProperties(Properties properties) {
        //属性设置

    }
}
