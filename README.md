# Modern Warfare Chess Project

中文页面 [点击这里](#现代战争棋mwchess项目)

This project presents a chess variant inspired by modern warfare, ***Modern Warfare Chess (MWChess)***. You can find the complete game rules here, along with instructions on how to play this variant on digital devices via ***Ludii Player***. In addition, the project includes an analysis of its gameplay and discussions on the development of AI agents for this variant. 

---

## Table of Contents
- [1. About Chess and Chess Variants](#1-about-chess-and-chess-variants)
- [2. Complete Rules](#2-complete-rules)
- [3. Play via Ludii Player](#3-play-via-ludii-player)
- [4. AI Agent](#4-ai-agent)
- [5. Game Analysis](#5-game-analysis)
- [6. Deep Learning](#6-deep-learning)
- [7. Relevant Links](#7-relevant-links)
- [8. License](#8-license)

---

## 1. About Chess and Chess Variants
Chess originated in 6th-century India, where its earliest known form was called Chaturanga. From there, it spread to Persia and the Arab world, eventually reaching Europe during the Middle Ages. Over time, it evolved into the standardized rule system recognized today, with the core mechanics of modern chess becoming firmly established by the late 15th century. As a game of deep intellectual engagement with a history spanning more than a millennium, chess has played a significant role in the development of human culture, embodying the essence of strategic planning, logical reasoning, and psychological competition.

Today, chess stands as one of the most influential abstract strategy games worldwide and is formally recognized as a competitive sport. Hundreds of international tournaments are held annually, including the World Chess Championship, the Chess Olympiad, and the FIDE Grand Prix, drawing elite players from across the globe and advancing the global reach of the game.

Beyond its competitive significance, chess holds rich symbolic meaning. It represents the warfare and hierarchical power structures of middle age—not only through the combat between pawns, knights, and other battlefield roles, but also through the political maneuvering among nobles and courtiers. During play, each move demands careful deliberation, as players must manage their forces, weigh local tactics against global strategy, and make decisions under pressure. Once the opening phase transitions into the middlegame and endgame, the dynamics of piece exchanges and positional sacrifices become a true test of skill and psychological resilience.

A chess variant refers to any game that modifies or reinterprets the core rules, pieces, or board structure of standard chess while maintaining a recognizable connection to the original framework. These variants include historically significant precursors to modern chess—such as Chaturanga and Shatranj—as well as contemporary creations continuously emerging from the global chess community.

Thanks to the creativity and dedication of chess enthusiasts and independent developers around the world, the foundational concepts of chess have been constantly extended and reimagined. Many variants introduce new piece types, asymmetrical or non-standard boards, dynamic rule systems, and even thematic overlays, breathing fresh life into this ancient game and ensuring its ongoing relevance and innovation.

---

## 2. Complete Rules

This chess variant, MWChess, is designed to replace the medieval warfare symbolism of traditional chess with a modern warfare theme through thoughtful abstraction and design. While presenting contemporary military concepts, it retains the classic elements of chess, making the game both easy to learn and rich in strategic depth.

You can find the complete rules of MWChess here — [English version](rule/MWChessRule_EN.md) and [Chinese version](rule/MWChessRule_CN.md).

---

## 3. Play via ***Ludii Player***

Ludii is a general game system project designed to provide a unified platform for modeling and running a wide range of board, card, and other strategy games. The Ludii Player is the project’s official game execution software, capable of loading and playing games described in the Ludii Game Description Language (.lud files).

A complete .lud game description file for MWChess has been prepared for the Ludii platform. You can find this file in the project repository and load it into the Ludii Player to play this chess variant.

- ***[.lud](lud/MWChessV1.0.lud)*** file for MWChess
- ***[Ludii Portal](https://ludii.games)***, the official page of Luddi
- ***[Ludii Player](https://ludii.games/download.php)***, the download page of Ludii Player
- ***[User guide](https://ludii.games/downloads/LudiiUserGuide.pdf)*** of Ludii Player 

---
## 4. AI Agent

Ludii Player comes with a variety of built-in AI Agent, including those based on search algorithms (such as Alpha-Beta Search) and simulation-based algorithms (Monte Carlo Tree Search). Among them, UBFM and Alpha-Beta perform particularly well in deterministic, chess-like games with clearly defined rules. When combined with the piece-value-based heuristic functions provided in the AI meta section of the .lud file, these two AIs can reach a level comparable to that of a beginner human player (such as myself…) when the thinking time per move is set to around 3 seconds.

Therefore, when playing MWChess in single-player mode via Ludii Player, it is recommended to choose ***UBFM*** or ***Alpha-Beta*** as the AI Agent and set the AI thinking time to at least ***3*** seconds to achieve a not-bad gameplay experience.

- About the AI Agents in Ludii Player: ***[User Guide](https://ludii.games/downloads/LudiiUserGuide.pdf)***
- Ludii Language Reference: ***[Ludii Language Reference](https://ludii.games/downloads/LudiiLanguageReference.pdf)***

---
## 5. Game Analysis

Using the UBFM algorithm, a preliminary playability analysis of MWChess was conducted by running 100 AI vs AI matches, each limited to a maximum of 70 turns, and comparing the results with those from classical chess. Based on the comparison below, we can draw the following preliminary conclusion: MWChess demonstrates good fairness, and the number of turns required for a draw is likely shorter than in classical chess, indicating a faster overall pace of play.

MWChess ：
- Player 1 Wins: 54%
- Player 2 Wins: 43%
- Draws: 3%
- Non-Draw Avg Turns: 19.01
- Non-Draw Turns StdDev: 14.88

Chess ：
- Player 1 Wins: 18%
- Player 2 Wins: 25%
- Draws: 57%
- Non-Draw Avg Turns: 44.93
- Non-Draw Turns StdDev: 13.98

Further analysis regarding the game’s playability would be highly appreciated.

You can find tutorials on testing games using the Ludii API here: ***[Ludii’s Tutorials](https://ludiitutorials.readthedocs.io/en/latest/)***, as well as the ***[RunningTrials.java](analysis/RunningTrials.java)*** file used for testing MWChess, along with the test result files: ***[results_MWChess.txt](analysis/results_MWChess.txt)*** and ***[results_Chess.txt](analysis/results_Chess.txt)***.

---
## 6. Deep Learning

Pursuing higher-level AI bots is both an interesting and challenging endeavor. High-level AI bots help humans study game fairness (e.g., whether the first player has a clear advantage or disadvantage) and playability (e.g., existence of fixed patterns), while also assisting players in improving their own skills. For example, AlphaZero has driven a major revolution in the chess community through such advances.

The open-source project ***Polygames*** is a promising starting point. Polygames, developed by Meta AI (formerly Facebook AI), is a research framework designed for training strategy game AIs via self-play. Currently, Polygames supports the universal game language format (.lud) proposed by Ludii as input for autonomous AI training.

However, training high-level AI requires expertise in deep learning. Therefore, I warmly encourage all developers interested in the MWChess project to try training their own AI bots and keep me updated on your progress.

- Official GitHub of the Polygames project: ***[Polygames](https://github.com/facebookarchive/Polygames)***
- GitHub extension for Polygames + Ludii: ***[Polygames&Ludii](https://github.com/facebookarchive/Polygames/tree/main/src/games/ludii)***, along with the research paper: ***[arxiv](https://arxiv.org/pdf/2101.09562)***
---

## 7. Relevant Links

- ***Ludii*** game submission post : [Ludii Forum](https://ludii.games/forum/showthread.php?tid=2473)
- ***chessvariants.org*** submission : Coming soon...
  
---

## 8. License

- 🔓 The **code** in this repository (e.g., `analysis/RunningTrials.java`) - is licensed under the [MIT License](./LICENSE).
- 📄 The **game rules**, documentation and Ludii `.lud` files (e.g., `rule/MWChessRule_EN.md`, `rule/MWChessRule_CN.md` and `lud/MWChessV1.0.lud`) are licensed under the **Creative Commons Attribution-NonCommercial 4.0 International License**.

[![Creative Commons License](https://licensebuttons.net/l/by-nc/4.0/88x31.png)](https://creativecommons.org/licenses/by-nc/4.0/)
  
View license: [CC BY-NC 4.0](https://creativecommons.org/licenses/by-nc/4.0/)

---

# 现代战争棋（MWChess）项目

For the English version, [click here](#modern-warfare-chess-project)

该项目介绍了一款受现代战争主题启发的国际象棋变体，***现代战争棋（MWChess）***。您可以在此查阅完整的游戏规则，并了解如何通过 ***Ludii Player*** 在电子设备上游玩该变体。此外，项目还包含对其可玩性的分析，以及针对该变体的 AI Agent 开发探讨。

---

## 目录
- [1. 关于国际象棋和国际象棋变体](#1-关于国际象棋和国际象棋变体)
- [2. 完整规则](#2-完整规则)
- [3. 在 Ludii Player 上游玩](#3-在-ludii-player-上游玩)
- [4. AI 机器人](#4-ai机器人)
- [5. 游戏分析](#5-游戏分析)
- [6. 深度学习](#6-深度学习)
- [7. 相关连接](#7-相关连接)
- [8. 许可协议](#8-许可协议)

---

## 1. 关于国际象棋和国际象棋变体

国际象棋（Chess）起源于公元6世纪的印度，其早期形式为“恰图兰卡（Chaturanga）”，随后传播至波斯、阿拉伯世界，并在中世纪时期传入欧洲。在欧洲的发展过程中，国际象棋逐步演化为如今广为人知的标准规则体系，并在15世纪末基本定型。作为一项拥有逾千年历史的智力游戏，国际象棋在人类文明史上始终扮演着重要角色，是策略性、逻辑思维与心理博弈的集大成者。

如今，国际象棋不仅是全球范围内最具影响力的抽象策略游戏之一，还被广泛认定为一项竞技体育项目。每年有数百场国际大型赛事举行，包括世界国际象棋锦标赛（World Chess Championship）、国际棋联奥林匹克（Chess Olympiad）、国际棋联大奖赛（FIDE Grand Prix）等，吸引了来自世界各地的顶尖选手参与，也持续推动着这项古老游戏的全球化发展。

国际象棋不仅在竞技层面具有深远影响，其深层抽象意涵也引人入胜。它象征着中世纪的战争与权力结构：不仅有步兵与骑士等战场角色的厮杀，也体现了贵族阶层、宫廷谋臣之间的政治较量。对弈过程中，玩家需要步步为营，调动子力，在局部战术与整体战略之间权衡决策。一旦开局布局完成，进入中盘与残局的子力交换，更是对博弈技巧与心理承受力的深度考验。

所谓“国际象棋变体”，是指在使用与标准国际象棋相似的棋盘、棋子或基础规则的前提下，进行一定幅度修改或创新的玩法。这些变体既包括在标准国际象棋定型之前广泛流行的历史版本，如“恰图兰卡”、“香巴拉棋（Shatranj）”，也涵盖当代棋类社区中不断涌现的各种原创变体。

得益于全球棋迷与开发者社区的积极探索，国际象棋的核心理念被不断延展与再诠释。许多变体引入了新的棋子类型、异构棋盘，甚至是动态规则与主题设定，使得这项古老游戏获得了持续的生命力与创新的活力。

## 2. 完整规则

本变体-MWChess-意在 通过合理的抽象和设计 将国际象棋原本的象征意义- 中世纪战争 替换为现代战争背景。在展示现代战争概念的同时，又保留国际象棋中的经典元素，使得该变体易于学习 同时又具有足够的博弈深度。

你可以在这里找到MWChess的完整规则，[中文版](rule/MWChessRule_CN.md), [英文版](rule/MWChessRule_EN.md)。

---

## 3. 在 ***Ludii Player*** 上游玩

Ludii 是一个通用游戏系统项目，旨在为各种棋类、牌类及其他博弈类游戏提供统一的建模与运行平台。Ludii Player 是该项目的官方游戏运行程序，支持加载和游玩基于 Ludii 游戏描述语言（.lud 文件）编写的游戏规则。

MWChess 已为 Ludii 平台编写了完整的游戏描述文件，您可以在本项目中找到该 .lud 文件，并通过在 Ludii Player 中加载它来游玩本变体。

- MWChess 的 ***[.lud](lud/MWChessV1.0.lud)*** 文件
- Ludii的官方主页：***[Ludii Portal](https://ludii.games)***
- Ludii Player 的下载界面：***[Ludii Player](https://ludii.games/download.php)***
- Ludii Player 的使用说明：***[User Guide](https://ludii.games/downloads/LudiiUserGuide.pdf)***

---
## 4. AI 机器人

Ludii Player 内置了多种AI机器人，涵盖基于搜索算法（如 Alpha-Beta Search）和基于模拟算法（Monte Carlo Tree Search）的实现。其中，UBFM 和 Alpha-Beta 在规则明确、无随机性因素的国际象棋类游戏中表现尤为出色。当配合 .lud 文件中 AI meta 部分提供的基于棋子价值的启发式函数时，这两类AI机器人在单步思考时间设置为约3秒的情况下，已基本达到可以与初学者（例如我……）对弈的水平。

因此，当玩家在 Ludii Player 中以单人模式体验 MWChess 时，建议选择 ***UBFM*** 或 ***Alpha-Beta*** 作为 AI 对手，并将 AI 思考时间设置为 ***3*** 秒以上，以获得较好的游戏体验。

- Ludii Player 中 AI 机器人的使用说明：***[User Guide](https://ludii.games/downloads/LudiiUserGuide.pdf)***
- Ludii 语言 指南：***[Ludii Language Reference](https://ludii.games/downloads/LudiiLanguageReference.pdf)***
 
---
## 5. 游戏分析

基于UBFM算法，我对MWChess的游戏性进行了初步的测试。方法是运行100局 AI vs AI 对局（每局不超过70回合），并与经典国际象棋进行对比。根据下方的对比结果，可以得出以下初步结论：MWChess 在公平性方面表现良好，其对局达到平局所需的回合数可能比经典国际象棋更少，也就是说游戏节奏更快。

MWChess ：
- Player 1 Wins: 54%
- Player 2 Wins: 43%
- Draws: 3%
- Non-Draw Avg Turns: 19.01
- Non-Draw Turns StdDev: 14.88

Chess ：
- Player 1 Wins: 18%
- Player 2 Wins: 25%
- Draws: 57%
- Non-Draw Avg Turns: 44.93
- Non-Draw Turns StdDev: 13.98

我非常欢迎对于游戏性方面的进一步分析和研究。

你可以在这里找到通过Ludii API测试游戏的教程：***[Ludii’s Tutorials](https://ludiitutorials.readthedocs.io/en/latest/)***, 以及测试MWChess所使用的 ***[RunningTrials.java](analysis/RunningTrials.java)*** 以及测试结果文件：***[results_MWChess.txt](analysis/results_MWChess.txt)*** 和 ***[results_Chess.txt](analysis/results_Chess.txt)***。

---
## 6. 深度学习

追求更高水平的 AI 机器人是一项既有趣又充满挑战的工作。高水平的 AI 机器人不仅有助于人类深入研究游戏的公平性（例如先手是否具备明显优势或劣势）和可玩性（是否存在固定套路），还能帮助玩家提升自身水平。正如 AlphaZero 对国际象棋界带来的巨大变革一样，强大的 AI 能推动游戏策略的创新。

开源项目 ***Polygames*** 是一个不错的起点。Polygames 由 Meta AI（原 Facebook AI）开发，是一个专为通过自我对弈训练策略游戏 AI 而设计的研究框架。目前，Polygames 支持 Ludii 提出的通用游戏语言（.lud 格式）作为游戏规则输入，用于 AI 机器人的自主训练。

训练高水平 AI 机器人需要深度学习方面的专业知识。因此，对于所有对 MWChess 项目感兴趣的开发者，我非常欢迎你们尝试训练自己的 AI 机器人，并随时告诉我你们的进展。

- Polygames项目官方GitHub：***[Polygames](https://github.com/facebookarchive/Polygames)***
- Polygames + Ludii 的GitHub拓展：***[Polygames&Ludii](https://github.com/facebookarchive/Polygames/tree/main/src/games/ludii)***, 以及研究论文：***[arxiv](https://arxiv.org/pdf/2101.09562)***

---

## 7. 相关连接

- Ludii 游戏提交贴 : [Ludii Forum](https://ludii.games/forum/showthread.php?tid=2473)
- chessvariants.org 国际象棋变体提交 : Coming soon...
  
---

## 8. 许可协议

- 🔓 本仓库中的**代码**（例如 `analysis/RunningTrials.java`）采用 [MIT 许可协议](./LICENSE) 进行授权。  
- 📄 **游戏规则**、文档及 Ludii `.lud` 文件（例如 `rule/MWChessRule_EN.md`、`rule/MWChessRule_CN.md` 和 `lud/MWChessV1.0.lud`）采用**知识共享署名-非商业性使用 4.0 国际许可协议**授权。

[![知识共享许可协议](https://licensebuttons.net/l/by-nc/4.0/88x31.png)](https://creativecommons.org/licenses/by-nc/4.0/)

查看许可详情：[CC BY-NC 4.0](https://creativecommons.org/licenses/by-nc/4.0/)
