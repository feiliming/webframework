
1. 引入Spring Boot依赖
	(1)使用parent POM 
	<parent>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-parent</artifactId>
        <version>1.2.0.BUILD-SNAPSHOT</version>
    </parent>
    (2)使用dependencyManagement
	<dependencyManagement>
	     <dependencies>
	        <dependency>
	            <groupId>org.springframework.boot</groupId>
	            <artifactId>spring-boot-dependencies</artifactId>
	            <version>1.2.0.BUILD-SNAPSHOT</version>
	            <type>pom</type>
	            <scope>import</scope>
	        </dependency>
	    </dependencies>
	</dependencyManagement>
	
2. dynamic web module 3.0 requires java 1.6 or newer
   Maven -> Update Project...Java Compiler变了, 在pom.xml中的properties加如下设置
         使用maven-compiler-plugin 指定JDK版本和编码
    <plugin>
        <groupId>org.apache.maven.plugins</groupId>
        <artifactId>maven-compiler-plugin</artifactId>
        <version>3.1</version>
        <configuration>
            <source>1.7</source>
            <target>1.7</target>
            <encoding>UTF-8</encoding>
        </configuration>
    </plugin>

3. Maven -> Update Project...项目编码、Java Compiler变了, 在pom.xml中的properties加如下设置
    <properties>
        <java.version>1.7</java.version>
        <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
        <project.reporting.outputEncoding>UTF-8</project.reporting.outputEncoding>
    </properties>
    
4. 去除内嵌tomcat

5. spring mvc

6.     