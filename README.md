### 本项目为Java项目。
使用了Springboot , Maven , MybatisPlus, 数据库为PostgreSQL。

下面为使用说明。

### 1.安装Java运行环境，PostgreSQL数据库，Maven。
安装过程略。

### 2.配置数据库
使用初始管理员账户连接数据库，运行项目中的「postgre-dev.sql」SQL文件来创建本项目使用的用户、数据库和表。

连接数据库可以使用A5M2。

使用下面的账户连接本项目使用的数据库。

数据库名:   icgdevdb

用户名:      javaadmin

密码:        asd8751A52s

### 3.打开你的Eclipse或者Idea开发工具，导入Maven项目，导入本项目。

### 4.请clean并刷新Maven，来下载外部依赖需要的jar包。

### 5.运行Springboot项目。

### 6.访问localhost:8847/api/ping
如果返回了pong，代表成功启动。


### 结构目录说明

> icg/base/common                               底层的一些配置类
> * > EnumData.java                             枚举类，存放了一些用于存放/读取 数据库时用到的参数
> * > IPRequestInterceptor.java                 请求拦截器，进入控制器前会被拦截器拦截，预处理用
> * > IPRequestLimiter.java                     判断是否多次发送后台Contact(お問い合わせ)请求
> * > MsgConfig.java                            存放了返回信息id的配置类
> * > MyBatisPlusConfig.java                    MybatisPlus的配置类
> * > MyLocaleResolver.java                     国际化的配置入口
> * > ValidatedExceptionHandler.java            前台发送给后台表单数据时，用于检证数据正确性的
> * > WebMvcConfig.java                         Mvc的配置类，本项目用于解决跨域请求用的

> icg/base/model                               实体类文件夹

> icg/base/common/response/                    Reponse文件夹
> * > Comresponse.java                          返回前台response的共通类

> icg/base/utils/                              工具类文件夹
> * > DateUtils.java                            时间转换工具类
> * > I18nUtils.java                            国际化插件工具类

> icg/dao/                                    访问数据库

> icg/portal/controller/                       控制器
> icg/portal/service/                          服务层

> icg/IcgApiApplication.java                    SpringBoot入口文件

> resources/i18n/                              国际化多语言配置文件夹
> resources/mapper/                            访问数据库SQL保存文件夹

> resources/application-dev.yml                配置类
