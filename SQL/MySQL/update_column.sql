-- 背景：假设一个需要双向绑定的需求
-- project 表有字段 id, detail_id，id 有数据
-- project_detail 表有字段 id, project_id，均已经有数据
-- project 表和 project_detail 表通过 id 和 project_id 关联，更新 project 表中的 detail_id 为 project_detail 的 detail_id

-- 知识点：UPDATE JOIN
UPDATE project p
JOIN project_detail pd ON p.id = pd.project_id
SET p.detail_id = pd.detail_id;
