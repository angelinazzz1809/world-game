# 世界迷宫游戏 🎮 World Maze Game

这是一个用 **Java** 编写的文字冒险小游戏。玩家可以输入任意数字作为随机种子（seed），游戏会根据不同的 seed 生成不同的迷宫地图。  
在地图中，玩家需要操控角色 `@` 寻找到目标点 `△`。当到达目标后，会进入一个内嵌的小游戏（类似贪吃蛇），需要吃掉所有花 🌸 才能返回主地图继续探索

This is a text-based adventure mini-game written in Java.
Players can enter any number as a random seed, and the game will generate different maze maps based on the chosen seed.
On the map, the player controls the character @ to find the target point △.
Once the player reaches the target, a built-in mini-game (similar to Snake 🐍) will start, where you must eat all the flowers 🌸 to return to the main map and continue exploring.

---

## 🚀 运行方式 How to Run

1. 下载本仓库（或通过 git clone 克隆）。
Download this repository (or clone via git clone).

2. 使用 IntelliJ IDEA 打开，运行 core.Main。
Open with IntelliJ IDEA and run core.Main.

或者在命令行下运行：
Or run in the command line:

javac -d out src/core/Main.java
java -cp out core.Main


## 🎥 演示视频 demo gameplay

👉 [点击这里查看游戏演示](https://drive.google.com/file/d/1MCrxlnxZtuQAAgZXc8sL-VQJfJ2vf5Gk/view?usp=sharing) Click here to watch the gameplay demo



## 🎮 游戏玩法 How to Play
🌍 主地图 Main Map

输入 seed → 随机生成迷宫地图
Enter a seed → randomly generate a maze map

@ ：玩家角色
@ : Player character

△ ：目标点（三角形）
△ : Target point (triangle)

操作方式：
Controls:

W = 向上
W = Move up

A = 向左
A = Move left

S = 向下
S = Move down

D = 向右
D = Move right

按 I 查看操作说明（information）
Press I to show information/help

按 R 重新开始游戏（输入新的 seed 即可生成新地图）
Press R to restart the game (enter a new seed to generate a new map)

🐍 内嵌小游戏（贪吃蛇模式） Embedded Mini-game (Snake Mode)

当玩家 @ 碰到小三角 △ 时，会进入一个类似 贪吃蛇 的小游戏
When the player @ reaches the triangle △, you will enter a Snake-like mini-game

玩家需要吃掉所有花 🌸 才能过关
The player must eat all the flowers 🌸 to win

完成后会返回主地图，继续探索
After completing, you will return to the main map and continue exploring
