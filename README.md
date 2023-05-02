# DodoOpenJava <img src="https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=java&logoColor=white" alt="Java">
Dodo开放平台的JavaSDK

尚在开发中，我们目前已经支持全部的接口和事件

目前还新增了权限系统以及命令系统

JavaDoc：https://mcchampions.github.io/

Dodo开放平台：https://open.imdodo.com/

### 添加依赖
#### 依赖关系:
all-in-one: 全合一，只需添加这个依赖项

command: core & event-core & permissions

configuration: core

core: null

event-core: core

event-webhook: core & event-core

event-websocket: core & event-core

permissions: core & configuration

#### Maven：
```xml
<repositories>
  <repository>
    <id>jitpack.io</id>
    <url>https://jitpack.io</url>
  </repository>
</repositories>
```
```xml
<dependencies>
    <!--根据需求添加-->
  <dependency>
    <groupId>top.qscraft.dodoopenjava</groupId>
    <artifactId>core</artifactId>
    <version>2.0.1</version>
  </dependency>
  <dependency>
    <groupId>top.qscraft.dodoopenjava</groupId>
    <artifactId>command</artifactId>
    <version>2.0.1</version>
  </dependency>
  <dependency>
    <groupId>top.qscraft.dodoopenjava</groupId>
    <artifactId>configuration</artifactId>
    <version>2.0.1</version>
  </dependency>
  <dependency>
    <groupId>top.qscraft.dodoopenjava</groupId>
    <artifactId>event-core</artifactId>
    <version>2.0.1</version>
  </dependency>
  <dependency>
    <groupId>top.qscraft.dodoopenjava</groupId>
    <artifactId>event-websocket</artifactId>
    <version>2.0.1</version>
  </dependency>
  <dependency>
    <groupId>top.qscraft.dodoopenjava</groupId>
    <artifactId>event-webhook</artifactId>
    <version>2.0.1</version>
  </dependency>
  <dependency>
    <groupId>top.qscraft.dodoopenjava</groupId>
    <artifactId>permissions</artifactId>
    <version>2.0.1</version>
  </dependency>
  <!--也可以全部添加-->
  <dependency>
    <groupId>top.qscraft.dodoopenjava</groupId>
    <artifactId>all-in-one</artifactId>
    <version>2.0.1</version>
  </dependency>
</dependencies>
```
#### Gradle
```groovy
	allprojects {
		repositories {
			maven { url 'https://jitpack.io' }
		}
	}

	dependencies {
	        implementation 'top.qscraft.dodoopenjava:core:2.0.1'
	        implementation 'top.qscraft.dodoopenjava:command:2.0.1'
	        implementation 'top.qscraft.dodoopenjava:configuration:2.0.1'
	        implementation 'top.qscraft.dodoopenjava:event-core:2.0.1'
	        implementation 'top.qscraft.dodoopenjava:event-websocket:2.0.1'
	        implementation 'top.qscraft.dodoopenjava:event-webhook:2.0.1'
	        implementation 'top.qscraft.dodoopenjava:permissions:2.0.1'
	        //或者是全部添加
            implementation 'top.qscraft.dodoopenjava:all-in-one:2.0.1'
	}
```

### 其他
别问我以前版本号为什么那么大，问就是非正式版(你就说信不信吧)
### 教程(编写中)
[直达链接](https://www.showdoc.com.cn/DodoOpenJava/)
