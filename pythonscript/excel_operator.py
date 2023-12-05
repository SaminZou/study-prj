import pandas as pd

srcDir = './excel_operator_data.xlsx'
# 读取 Excel 文件
df = pd.read_excel(srcDir)

# 数据行数，自动过滤标题
print("有效数据行数：" + str(len(df)))

# 遍历数据
for index, row in df.iterrows():
    print(str(index + 1) + ":" + row['名称'])
