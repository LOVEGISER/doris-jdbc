优缺点

| 操作     | mysql.jdbc | doris-jdbc    |
|--------|------------|-----|
| insert |            |     |
| update |            |     |
| delete |            |     |
| query  |            |     |

### insert操作
1.之前的jdbc(com.mysql.jdbc.Driver)即使是使用preparestatement也会存在FE性能瓶颈。
2. doris jdbc client进行数据cache,flush,transaction等操作，插入性能好。
3. 封装batchAdd(String[] cloumns,list values),方便用户入库

### update操作
  1. agg和unique支持update,直接把SQL通过Http/jdbc.mysql传给Doris

~~2.基于version是否能支持部分列更新（查询需要update数据的全字段+version->修改字段->基于版本的upset~~


### delete操作
   1. 直接把SQL通过Http/jdbc.mysql传给Doris

### query操作
   1. 把SQL通过Http/mysql.jdbc传给Doris，获取查询结果。返回为ResultSet。
   2. 支持将ResultSet封装为List<Object>返回

### 实现思路
*    jdbc.doris通过继承jdbc.mysql实现:
*    类似kop服务包实现 
*    全部实现jdbc协议。

### 考虑问题
* 除了insert，其他执行直接继承jdbc.mysql

* insert硬编码手动commit，事务基于stream load状态执行

* delete和update异步操作事务如何设置？