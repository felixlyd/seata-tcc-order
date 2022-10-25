# 一、项目简介

# 二、项目介绍

# 三、快速开始

- 从 https://github.com/seata/seata/releases,下载服务器软件包，将其解压缩。

```shell
Usage: sh seata-server.sh(for linux and mac) or cmd seata-server.bat(for windows) [options]
  Options:
    --host, -h
      The address is expose to registration center and other service can access seata-server via this ip
      Default: 0.0.0.0
    --port, -p
      The port to listen.
      Default: 8091
    --storeMode, -m
      log store mode : file、db
      Default: file
    --help

e.g.

sh seata-server.sh -p 8091 -h 127.0.0.1 -m file
```

# 四、优化点

## 全局ID生成器

### 1.通过配置切换ID生成器

-[x] 1.通过配置切换ID生成器

### 2.优化滴滴tinyId

- [ ] 1.切换springboot为2.X版本
- [ ] 2.切换tomcat连接池为druid连接池
- [ ] 3.tinyid-client的资源配置文件从tinyid_client.properties切换为application.yml或application.properties
- [ ] 4.tinyid-server的mysql驱动版本过旧

### 3.实现不强依赖于zookeeper的雪花算法
- [ ] 1.实现不强依赖于zookeeper的雪花算法

### 4.用数据库管理seata全局事务信息而不是file配置

### 5.结合数据库解决tcc模式的幂等、悬挂、空回滚等问题

### 6.整合度考虑：使用阿里旗下的nacos？

# 四、注意事项

1. seata**支持的SQL有限**，请仔细查阅相关文档，该文档包括四部分：[SQL限制](https://seata.io/zh-cn/docs/user/sqlreference/sql-restrictions.html)、[DML语句](https://seata.io/zh-cn/docs/user/sqlreference/dml.html)、[SQL修饰](https://seata.io/zh-cn/docs/user/sqlreference/sql-decoration.html)、[函数](https://seata.io/zh-cn/docs/user/sqlreference/function.html)
2. 查阅seata文档可知，seata较依赖于配置中心，适配于Nacos、Apollo、Etcd3、Consul、Zookeeper等，没有很好的适配spring-cloud-config-server，如不借助配置中心，配置依赖于本地yml、properties等文件
3. seata可以适配应用性能监控，包含SkyWalking、Prometheus Metrics