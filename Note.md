#此文件记录开发中的坑（心得笔记）
##开发组件：SpringBoot、Mybatis、Mysql、BootStrap、thymeleaf
##1、驼峰匹配
Mybatis中的驼峰匹配需要注意 <br>
未开启且在未设置resultMap时，数据库字段需要与model中属性相同 <br>
驼峰匹配指的是字段xxx_xxx与xxxXxx匹配 <br>
开启指令：mybatis.configuration.map-underscore-to-camel-case=true
##2、热部署
不需要重启服务器即可让修改的文件生效 <br>
##3、有关错误处理
springboot自身提供了错误处理方法，具体的需要实现@ControllerAdvice、@ExceptionHandler等注解方法<br>
##10086、快捷键
ctrl+F12 查看类中方法 <br>
