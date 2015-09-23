# 安徽新华社财务记账系统

版权所有 © 新华社集团 By 安徽钰诚集团


## 主要功能
1. **新建账套**
2. **记账**
3. **查账**
4. **结转损益**
5. **报表**

## 架构说明
##### 1.项目使用Spring Boot作为快速开发框架，使用了Spring MVC
##### 2.ORM框架采用MyBatis
##### 3.前端使用EasyUI + FreeMarker
##### 4.项目采用Maven管理

### 开发注意事项
##### 1. 包结构严格按照MVC结构命名分层：
**`-`src/main/java:**
> - cn.ahyc.yjz.controller
> - cn.ahyc.yjz.service
> - cn.ahyc.yjz.mapper
> - cn.ahyc.yjz.model
> - cn.ahyc.yjz.config

**`-`src/main/resources**
> - config
> - logback
> - mybatis
>
>> - mappers
>
>>>> - base
>>>> - extend
>
> - static
> - templates
>
>> - common
>> - lib
>> - module

## Mybatis说明
##### 1.MyBatis配置文件分位base和extend两个路径，其中base目录是用来存放自动生成的基础code，而extend用来存放业务扩展code





