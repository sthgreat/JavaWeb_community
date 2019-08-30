##Cloud
1. yum update 升级所有包同时也升级软件和系统内核 // yum upgrade 只升级所有包，不升级软件和系统内核<br>
2. yum install git; yum install maven 安装git,maven <br>
3. mkdir App
4. cd App
5. git clone https://github.com/sthgreat/JavaWeb_community.git
6. mvn -v 查版本号
7. mvn clean compile package
8. cp src/main/resources/application.properties src/main/resources/application-production.properties
9. vim src/main/resources/application-production.properties

java -jar -Dspring.profiles.active=production target/web_community-0.0.1-SNAPSHOT.jar 
ps -aux | grep java
##命令
pwd 查看当前位置 <br>
mkdir 创建文件夹 <br>
shift + z + z vim保存 
