# DodoOpenJava
Dodo开放平台的JavaSDK

尚在开发中，我们目前已经支持全部的接口以及事件

JavaDoc：https://mcchampions.github.io/

Dodo开放平台：https://open.imdodo.com/

### 添加依赖
### Maven：
```xml
<repositories>
  <repository>
    <id>jitpack.io</id>
    <url>https://jitpack.io</url>
  </repository>
</repositories>

  <dependency>
    <groupId>com.github.mcchampions</groupId>
    <artifactId>DodoOpenJava</artifactId>
    <version>v4.1</version>
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
	        implementation 'com.github.mcchampions:DodoOpenJava:v4.1'
	}
```
