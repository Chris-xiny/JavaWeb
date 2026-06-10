# Tlias Web Management System

基于 Spring Boot 的企业级 Web 后台管理系统，跟随黑马程序员 JavaWeb 课程实战学习项目。项目实现了员工管理、班级管理、学员管理、数据统计报表等核心功能，涵盖了 JavaWeb 后端开发的主流技术栈。

## 技术栈总览

| 技术领域 | 技术选型 | 版本 | 说明 |
|:--------|:---------|:----:|:-----|
| 编程语言 | Java | 17 | 采用 LTS 长期支持版本 |
| 应用框架 | Spring Boot | 3.5.14 | 企业级快速开发框架 |
| 持久层框架 | MyBatis | 3.0.3 | ORM 框架，支持 XML 映射文件 |
| 数据库 | MySQL | - | 关系型数据库（端口 3307） |
| 分页插件 | PageHelper | 2.1.1 | MyBatis 分页增强 |
| 身份认证 | JWT (jjwt) | 0.12.6 | Token 无状态认证方案 |
| 切面编程 | Spring AOP | - | 操作日志自动记录 |
| 文件存储 | 阿里云 OSS | 3.18.4 | 云端对象存储服务 |
| 代码简化 | Lombok | 1.18.20 | 注解驱动，减少样板代码 |
| 日志框架 | Logback | - | 日志输出与管理 |
| 单元测试 | JUnit Jupiter | 5.9.1 | 单元测试框架 |
| 构建工具 | Maven | - | 多模块项目构建与管理 |

## 项目结构

项目采用 **Maven 多模块** 架构，父工程统一管理依赖版本，子模块各司其职：

```
Web-Project
├── tlias-parent              # 父工程：统一依赖版本管理
│   └── pom.xml
├── tlias-entity              # 实体模块：POJO / 数据模型
│   └── src/main/java/com/ChrisXin/entity/
│       ├── Emp.java              # 员工实体
│       ├── Dept.java             # 部门实体
│       ├── Clazz.java            # 班级实体
│       ├── Student.java          # 学员实体
│       ├── Result.java           # 统一响应结果封装
│       ├── PageResult.java       # 分页结果封装
│       ├── LoginInfo.java        # 登录信息
│       ├── OperateLog.java       # 操作日志
│       └── ...QueryParam.java    # 各类查询参数对象
├── tlias-utils               # 工具模块：通用工具类
│   └── src/main/java/com/ChrisXin/utils/
│       ├── JwtUtils.java             # JWT 令牌工具（生成 & 解析）
│       ├── AliyunOSSOperator.java    # 阿里云 OSS 上传操作
│       ├── AliyunOSSProperties.java  # OSS 配置属性封装
│       └── CurrentHolder.java        # 线程局部变量（ThreadLocal）
└── tlias-web-management      # Web 主模块：核心业务逻辑
    └── src/main/java/com/ChrisXin/
        ├── controller/           # 控制层（RESTful API）
        │   ├── LoginController.java
        │   ├── EmpController.java
        │   ├── DeptController.java
        │   ├── ClazzController.java
        │   ├── StudentController.java
        │   ├── ReportController.java
        │   └── UploadController.java
        ├── service/              # 业务逻辑层
        │   ├── impl/             # 接口实现
        │   └── ...Service.java
        ├── mapper/               # 数据访问层（MyBatis Mapper）
        ├── filter/               # 过滤器
        │   └── TokenFilter.java
        ├── interceptor/          # 拦截器
        │   └── TokenInterceptor.java
        ├── aop/                  # 切面
        │   ├── OperationLogAspect.java   # 操作日志切面
        │   └── RecordTimeAspect.java     # 耗时统计切面
        ├── anno/                 # 自定义注解
        │   └── Log.java
        ├── configuration/        # 配置类
        │   └── WebConfig.java
        └── exception/            # 全局异常处理
            └── GlobalExceptionHandler.java
```

## 核心技术实践

### RESTful API 设计

项目遵循 RESTful 风格设计接口，使用标准 HTTP 方法映射 CRUD 操作：`@GetMapping` 查询、`@PostMapping` 新增、`@PutMapping` 修改、`@DeleteMapping` 删除。通过 `Result` 类统一封装响应数据格式，保证前后端交互的一致性。

### JWT 令牌认证

采用 JWT（JSON Web Token）实现无状态身份认证。用户登录成功后签发包含用户信息的 Token，后续请求通过 `TokenInterceptor` 拦截器校验 Token 合法性。拦截器基于 `HandlerInterceptor` 实现，配合 `WebMvcConfigurer` 注册拦截规则并排除登录接口。

### Spring AOP 操作日志

通过自定义注解 `@Log` 标记需要记录日志的 Controller 方法，利用 AOP 环绕通知（`@Around`）自动采集操作人、操作时间、请求参数、返回值和耗时等信息并持久化到数据库，实现了业务逻辑与日志记录的解耦。

### MyBatis 持久层

使用 MyBatis + XML 映射文件进行数据库操作，开启了驼峰命名自动映射（`map-underscore-to-camel-case`）。复杂查询（如多表关联、动态条件）通过 XML 编写 SQL，简单操作可使用注解方式。集成 PageHelper 插件实现物理分页。

### 阿里云 OSS 文件上传

集成阿里云 OSS SDK 实现文件云端存储，通过 `@ConfigurationProperties` 将 yml 配置批量注入到 `AliyunOSSProperties` 实体类中，支持按日期目录归档上传、UUID 重命名防止文件名冲突。

### 全局异常处理

使用 `@RestControllerAdvice` + `@ExceptionHandler` 实现全局异常统一处理，针对性捕获 `DuplicateKeyException`（唯一键冲突）、`HttpRequestMethodNotSupportedException`（请求方法不支持）等常见异常，返回友好的错误信息。

### 数据统计报表

提供员工职位分布、性别比例、班级学员数量、学生学历分布等维度的统计数据，为前端图表展示（如 ECharts）提供数据接口支持。

## 环境要求

- **JDK**: 17+
- **Maven**: 3.6+
- **MySQL**: 8.0+
- **IDE**: IntelliJ IDEA（推荐）

## 快速开始

```bash
# 1. 克隆项目
git clone <your-repo-url>

# 2. 配置数据库
#    修改 application.yml 中的数据库连接信息

# 3. 配置阿里云 OSS（可选）
#    设置环境变量 OSS_ACCESS_KEY_ID 和 OSS_ACCESS_KEY_SECRET

# 4. Maven 编译
mvn clean install

# 5. 启动项目
#    运行 TliasWebManagementApplication 主类
```

## 学习收获

通过本项目的实战练习，系统掌握了以下 JavaWeb 核心知识：

- Spring Boot 自动配置与起步依赖原理
- Maven 多模块工程的聚合与继承机制
- RESTful API 设计规范与统一响应封装
- MyBatis 持久层开发（XML 映射 + 注解方式）
- JWT 令牌认证与拦截器机制
- Spring AOP 切面编程的实际应用
- 全局异常处理与统一错误响应
- 文件上传与第三方云服务集成
- ThreadLocal 在请求上下文中的应用
- 数据统计接口的设计与实现
