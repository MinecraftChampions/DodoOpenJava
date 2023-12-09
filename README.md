# DodoOpenJava <img src="https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=java&logoColor=white" alt="Java"> [![](https://jitpack.io/v/top.qscraft/dodoopenjava.svg)](https://jitpack.io/#top.qscraft/dodoopenjava)

Dodo开放平台的JavaSDK

尚在开发中，我们目前已经支持全部的接口和事件

目前还新增了权限系统以及命令系统

JavaDoc：https://qscraft.top/javadoc

Dodo开放平台：https://open.imdodo.com/

### 计划
~~目前，计划大改SDK，可感觉似乎kotlin可好用，真在学习中~~
想了想，还是java好，准备去除多模块开始大改

### 添加依赖
#### 依赖关系:
[![p9GokoF.png](https://s1.ax1x.com/2023/05/02/p9GokoF.png)](https://imgse.com/i/p9GokoF)
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
    <version>2.5.14</version>
  </dependency>
  <dependency>
    <groupId>top.qscraft.dodoopenjava</groupId>
    <artifactId>command</artifactId>
    <version>2.5.14</version>
  </dependency>
  <dependency>
    <groupId>top.qscraft.dodoopenjava</groupId>
    <artifactId>configuration</artifactId>
    <version>2.5.14</version>
  </dependency>
  <dependency>
    <groupId>top.qscraft.dodoopenjava</groupId>
    <artifactId>event-core</artifactId>
    <version>2.5.14</version>
  </dependency>
  <dependency>
    <groupId>top.qscraft.dodoopenjava</groupId>
    <artifactId>event-websocket</artifactId>
    <version>2.5.14</version>
  </dependency>
  <dependency>
    <groupId>top.qscraft.dodoopenjava</groupId>
    <artifactId>event-webhook</artifactId>
    <version>2.5.14</version>
  </dependency>
  <dependency>
    <groupId>top.qscraft.dodoopenjava</groupId>
    <artifactId>permissions</artifactId>
    <version>2.5.14</version>
  </dependency>
  <!--又或者直接合成一个依赖项-->
  <dependency>
    <groupId>top.qscraft</groupId>
    <artifactId>dodoopenjava</artifactId>
    <version>2.5.14</version>
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
	        implementation 'top.qscraft.dodoopenjava:core:2.5.14'
	        implementation 'top.qscraft.dodoopenjava:command:2.5.14'
	        implementation 'top.qscraft.dodoopenjava:configuration:2.5.14'
	        implementation 'top.qscraft.dodoopenjava:event-core:2.5.14'
	        implementation 'top.qscraft.dodoopenjava:event-websocket:2.5.14'
	        implementation 'top.qscraft.dodoopenjava:event-webhook:2.5.14'
	        implementation 'top.qscraft.dodoopenjava:permissions:2.5.14'
		//又或者直接合成一个依赖项
	        implementation 'top.qscraft:dodoopenjava:2.5.14'
    }
```
### 教程(过于古老，无参考价值，改日重写)
[直达链接](https://www.showdoc.com.cn/DodoOpenJava/)
