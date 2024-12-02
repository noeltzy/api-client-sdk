# API-Client-SDK

## 开发进度
1. 基础SDK功能开发
2. 支持Springboot自动配置和装配
3. 三个基本的测试调用接口，只有使用json传递的post方法支持添加用于校验的Header

## TODOs
1. 补充其他方法添加Header头
2. 支持根据元数据构建调用API接口这样就不用每次都写接口函数的调用了，不用反复更新SDK的版本
3. 其他待定


## spring-boot-start的开发流程
1. 编写好需要打包的工具类
2. 编写一个配置类,使用如下注解，暴露配置项给调用端
    ```java
    @ComponentScan
    @Data
    @Configuration
    @ConfigurationProperties("api.client")
    ```
3. 编写META-INFO/spring.factories
    ```text
   org.springframework.boot.autoconfigure.EnableAutoConfiguration=your-config-class-reference
    ```
4. 更改pom.xml文件
```diff
- <build>
-     <plugins>
-         <plugin>
-             <groupId>org.springframework.boot</groupId>
-             <artifactId>spring-boot-maven-plugin</artifactId>
-             <configuration>
-                 <excludes>
-                     <exclude>
-                         <groupId>org.projectlombok</groupId>
-                         <artifactId>lombok</artifactId>
-                     </exclude>
-                 </excludes>
-             </configuration>
-         </plugin>
-     </plugins>
- </build>

```