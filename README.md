# dandelion

> 蒲公英，寓意者开放，自由，发散  




## 简述

该项目是一个综合类运维项目，计划是从linux系统管理，到系统集群，分布式系统中间件，应用软件版本管理，容量规划，安装部署，运维监控，以及从局域网到公有云的全网拉通，旨在实现简易，开放，可视的自动化运维中台

## 背景

有一次本地虚拟机安装kafka少配置了几个服务端参数，从而导致虚拟机的磁盘被异常日志写满了，
后面在网上去找磁盘占用信息的查找的命令，和kafka配置信息，才算把问题解决，为了避免后续还出现这种一次性的问题产生的烦恼，遂有了这个项目


## 预览

初步预览
![](https://ftp.bmp.ovh/imgs/2019/12/715801a1cf59129d.png)

## 快速开始

1. 下载项目，并确保能正确引用到 `ruoyi`（具体参考项目技术部分的链接）的依赖
2. 初始化 `ruoyi` 项目脚本
3. 初始化 `dandelion` 提供的初始化脚本
4. 通过Application类启动
5. 通过浏览器访问

## 项目技术

webscoket - js 长连接

xterm - 前端web terminal组件

JSCH  - linux ssh 连接和命令执行组件

[ruoyi][ruoyiurl] - 基于springboot的开源后台管理框架 （后续会采用[wecode][wecodeurl]重写前端）

[ruoyiurl]: http://ruoyi.vip/ "ruoyi"
[wecodeurl]: https://github.com/is-m/wecode "wecode"


