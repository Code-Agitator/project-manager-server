### 前端代码仓库:

https://github.com/Code-Agitator/user-manager-client

### 结构说明

* advice 处理全局异常
* anno 申明注解
    * CommonIgnore 用于标注通用的忽略接口(可以忽略统一接口体包装)
* common 公用模块
    * constant 枚举变量
    * result 封装统一结构体类和工具
    * utils 通用工具
* config 项目配置
    * mybatis
        * handler 自定义扩展BaseMapper
            * RecoverLogicDeleted 扩展的BaseMapper方法，用于越过Mybaits-Plus逻辑删除的处理去恢复被逻辑删除的记录
    * security web安全配置
    * swagger swagger配置
        * CustomHandlerMethodResolver 使swagger生成的文档拥有统计响应结构体
    * web web配置
        * handler 一些处理器
            * ResponseReturnValueHandler 统一结构体包装
* controller 控制层
* domain 数据模型
* interceptor 拦截器
    * BaseHandlerInterceptor 基础拦截器 扩展只需要实现该接口并申明为spring bean
* mapper mybatis的mapper
* service 业务层
