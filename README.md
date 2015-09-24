# 安徽新华社财务记账系统

版权所有 © 新华社集团 By 安徽钰诚集团


## 主要功能
1. **新建账套**
2. **记账**
3. **查账**
4. **结转损益**
5. **报表**

## 分支开发过程Git操作指导
```
1.开发场景：
    远程master分支上是一个可用的版本，现在有新需求“新建账套功能”，
    为了不影响master分支，采用本地新建分支开发模式
2.操作步骤：
    - git checkout -b accountbook; 新建并切换到accountbook分支(或者 git branch accountbook; git checkout accountbook;)
    - 现在工作区间切换到了accountbook上，你可以在找个分支上完成业务功能开发，这个分支与master是独立的，开发完成以后完成下面操作
    - git commit -a -m "提交日志" ; 提交修改到本地accountbook分支，不要push
    - git checkout master; 切回到主分支master
    - git merge accountbook; 合并accountbook到主分支，如果合并的时候出现冲突，需要先解决冲突
    - git commit -a -m "日志";  提交主分支变化到本地仓库
    - git push; 推送本地主分支到远程master分支

3.解决冲突常见场景：
    - 1.本地主分支落后于远程主分支版本，这个时候，push请求不了，需要先pull远程分支
    方法：
        git stash ; 暂存本地修改
        git pull ; 拉取远程分支
        git pop; 拿回暂存内容

    - 2.比场景1里面更复杂的就是，你和其他人修改了同一个文件的同一个部分，这样pull回来的时候，文件是有冲突的，需要手动处理
    方法：
        打开那些有冲突的文件，手动删除冲突的部分，保存；
        冲突解决完了之后执行: git commit -a -m "日志";
        再推送到远程分支: git push;
```


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


## UrlRewrite说明
##### 1.UrlRewrite官网： <http://cdn.rawgit.com/paultuckey/urlrewritefilter/master/src/doc/manual/4.0/index.html>

##### 2.对业务路径进行重写，模拟静态路径，配置文件路径：/WEB-INF/urlrewrite.xml








