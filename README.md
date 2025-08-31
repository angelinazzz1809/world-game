# 世界迷宫游戏 🎮

这是一个用 **Java** 编写的文字冒险小游戏。玩家可以输入任意数字作为随机种子（seed），游戏会根据不同的 seed 生成不同的迷宫地图。  
在地图中，玩家需要操控角色 `@` 寻找到目标点 `△`。当到达目标后，会进入一个内嵌的小游戏（类似贪吃蛇），需要吃掉所有花 🌸 才能返回主地图继续探索

---

## 🚀 运行方式

1. 下载本仓库（或通过 `git clone` 克隆）。  
2. 使用 IntelliJ IDEA 打开，运行 `core.Main`。  
   或者命令行下：  
   ```bash
   javac -d out src/core/Main.java
   java -cp out core.Main

## 🎥 演示视频 demo gameplay

👉 [点击这里查看游戏演示](https://drive.google.com/file/d/1MCrxlnxZtuQAAgZXc8sL-VQJfJ2vf5Gk/view?usp=sharing)

🎮 游戏玩法

主地图

输入 seed → 随机生成迷宫地图

@ ：玩家角色

△ ：目标点（三角形）

操作方式：

W = 向上

A = 向左

S = 向下

D = 向右

按 I 查看操作说明（information）

按 R 重新开始游戏（输入新的 seed 即可生成新地图）

内嵌小游戏（贪吃蛇模式）
当玩家 @ 碰到小三角 △ 时，会进入一个类似 贪吃蛇 的小游戏：

玩家需要吃掉所有花 🌸 才能过关

完成后会返回主地图，继续探索
