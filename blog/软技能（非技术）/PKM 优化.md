``````yaml
title: PKM 优化
author: samin
date: 2025-06-30
```

# 背景

为了记录生活，也为了定期复盘一段时间的工作，我从 2015 年 10 月 6 日起开始使用印象笔记，陆续记录工作日报、技术笔记等内容。截至本文编写时，我已在印象笔记中积累了超过 500 篇技术文章（包括部分网络剪藏）和 2600 多篇日记。
2025 年 6 月 30 日，距离开始使用印象笔记已近十年，我决定正式停止使用这一工具，告别这段长期陪伴的数字记忆方式。

从文件夹 + 记事本记录生活转换到印象笔记，那时的满足感不言而喻，即不用维护海量的记事本，打开速度也慢，基本无法根据关键词查找过去的内容，经常还需要担心电脑是否在身边才能看到内容。一句话就是日记上云了。

印象笔记的缺陷：

- 搜索能力薄弱：内容搜索不够智能和精准，面对大量笔记时常常力不从心
- 功能堆叠、体验割裂：为了追逐热点，印象笔记陆续加入了 To-Do 清单、Markdown、空间、日历、AI 等模块，但这些功能集成度不高，整体体验割裂，尤以脑图为甚——不仅卡顿严重，节点受限，甚至连复制都不支持
- 原生格式封闭，迁移困难：中国版不支持导出 .enex 格式，导致想要迁移到其他平台几乎无解
- 上传流量限制苛刻：每月固定的上行流量额度在日常使用中经常不够用，成为内容记录的一种无形限制
- 设备登录数量受限：限制多端同步，打破了「随时随地记录」的初衷
- 性能持续下降：随着版本更新，应用变得越来越臃肿，打开纯文本笔记都开始变慢，尤其是批量处理日志时，频繁出现卡顿甚至崩溃

# 过程

1. 最开始是使用 word 文档记录
2. 使用印象笔记
3. 使用 Notion
4. 使用 Obsidian

# 问题

1. 选择合适的工具

2. 分类方法
  - 笔记归属分类
  - 分类编码问题（卢曼卡片盒笔记法）

3. 格式问题
  - 放在同一个文件里面记录用标题区分
  - 多个文件切割
  - 标签来处理一个文件的多个属性问题

4. 迁移笔记

> 印象笔记是传统的日志工具，只适合来分类记录（树状），笔记间难建立联系。
> 印象笔记也一直在做优化，推出脑图、markdown、工作列表、超级笔记等功能来弥补当代用户的需求，但是体验非常差，一直以来没有很好的升级优化。
> obsidian 能够很方便的建立笔记间的关联性，最后让知识形成网状结构（近似一个知识图谱）
> 慎用笔记定制化的功能，比如使用了印象笔记的标签和文件夹，在写日报的时候，喜欢建立每个月的文件夹，导致日志名只用了日来命名，迁移工作导致重名严重，无法批量处理

最终采用 python 脚本 + shell 脚本 + 印象笔记导出 html 方式，把所有原生、markdown 的印象笔记迁移到 Obsidian

# 最终目标

- 解决内容收集变成 “沉睡的记录”，让知识变成 “可调用的资产”
- 隐私数据安全
- 支持 Markdown，更方便编写日志

# 心得

- 决策是最让人厌倦的工作之一，尽量不在 obsidian 中建立层级分类（less is more）
- 不要让工具成为认知负担
- run first