# DodoOpenJava
Dodo开放平台的JavaSDK

尚在开发中，我们目前已经支持全部的接口和事件

目前还新增了权限系统以及命令系统

JavaDoc：https://mcchampions.github.io/

Dodo开放平台：https://open.imdodo.com/

### 添加依赖
#### Maven：
```xml
<repositories>
  <repository>
    <id>jitpack.io</id>
    <url>https://jitpack.io</url>
  </repository>
</repositories>

  <dependency>
    <groupId>com.github.minecraftchampions</groupId>
    <artifactId>DodoOpenJava</artifactId>
    <version>v9.4</version>
  </dependency>
```
#### Gradle
```xml
	allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}

	dependencies {
	        implementation 'com.github.minecraftchampions:DodoOpenJava:v9.4'
	}
```
