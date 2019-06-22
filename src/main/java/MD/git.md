IDEA 将已有项目添加到git
首先，我们解决的情况是，已经有了一个正在开发的项目，现在我们要把他分享到git@osc上面去。

1.第一步，当然是先在Git@OSC上创建仓库，拿到Git@OSC仓库的HTTP连接http://git.oschina.net/***/***.git

2.如果我们的本地项目是非git项目，那我们要先把它变成git项目
在intellij中 VCS——Import into Version Control——Create Git Repository——选择你的本地项目

3.通过git shell （可以安装git for window） 进入到项目目录 执行 以下命令

复制代码
# 给项目设置远程远程仓库 #  
git remote add origin http://git.oschina.net/***/***.git  
# 抓取远程仓库数据，并自动合并远程分支 #  
git pull origin master
当第一次拉取的时候 如果失败可以使用 
git pull origin master --allow-unrelated-histories
# 更新本地数据到Git@OSC #  
git push origin master

