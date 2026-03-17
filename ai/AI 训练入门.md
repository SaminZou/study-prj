```yaml
title: AI 训练入门
author: samin
date: 2026-03-13
```

# 模型训练框架

| 算法          | 使用框架                 | 适用场景   |
| ----------- | -------------------- | ------ |
| 随机森林        | scikit-learn         | 小数据预测  |
| XGBoost     | XGBoost              | 工业预测常用 |
| LSTM        | TensorFlow / PyTorch | 时间序列预测 |
| Prophet     | Prophet              | 周期趋势预测 |
| Transformer | PyTorch              | 大规模预测  |

工业能源公司通常使用 XGBoost 或 LSTM

# 模型文件

| 模型文件  | 框架         |
| ----- | ---------- |
| .pkl  | Python ML  |
| .pt   | PyTorch    |
| .pb   | TensorFlow |
| .onnx | 通用模型       |

# 模型运行

模型推理服务

# 拟合

- 过拟合
- 欠拟合