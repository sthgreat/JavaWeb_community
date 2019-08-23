# 社区

##此文件记录开发中的坑（心得笔记） <br>
###开发组件：SpringBoot、Mybatis、Mysql、BootStrap、thymeleaf、jquery(异步) <br>
###1、驼峰匹配 <br>
Mybatis中的驼峰匹配需要注意 <br>
未开启且在未设置resultMap时，数据库字段需要与model中属性相同 <br>
驼峰匹配指的是字段xxx_xxx与xxxXxx匹配 <br>
开启指令：mybatis.configuration.map-underscore-to-camel-case=true
###2、热部署 <br>
不需要重启服务器即可让修改的文件生效 <br>
###3、有关错误处理 <br>
springboot自身提供了错误处理方法，具体的需要实现@ControllerAdvice、@ExceptionHandler等注解方法<br>
###4、@transactional
###10086、快捷键 <br>
ctrl+F12 查看类中方法 <br>
alt+F7 查看方法的引用 <br>
ctrl+F6 （光标放在函数的参数括号内按）修改全局参数顺序 <br>
ctrl+alt+b 查询当前interface中方法的所有实现类 <br>
ctrl+alt+l 代码格式化 <br>
###10087、还存在问题 <br>
访问时的数据库并发操作<br>