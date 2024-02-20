import time

import jenkins

# 背景：先调用执行构建镜像任务，待任务执行成功后执行镜像推送到远程仓库任务
# 使用 Jenkins 团队提供的 python sdk，而不是直接调用 rest api，会使脚本创建更加便捷

def execute_jobs():
    # Jenkins 服务器的 URL、用户名和密码
    jenkins_url = 'http://jenkins.samin.com'
    username = 'samin'
    password = 'smain'

    # 创建 Jenkins 对象
    server = jenkins.Jenkins(jenkins_url, username=username, password=password)

    # 触发构建镜像
    job_name1 = 'build/biz-images'
    parameters1 = {
        'parameter1': 'master',
        'parameter2': 'latest'
    }
    server.build_job(job_name1, parameters1)

    # API 调用后，Jenkins 任务并没有立即生效，设置缓冲时间
    time.sleep(10)

    # 轮询检测任务1是否完成
    job_info1 = server.get_job_info(job_name1)
    while job_info1['lastBuild']["number"] != job_info1['lastCompletedBuild']["number"]:
        print(f'Job 【{job_name1}】 is being processed...')
        time.sleep(3)  # 每 3 秒检测一次
        job_info1 = server.get_job_info(job_name1)

    print(f'Job 【{job_name1}】 completed.')

    # 触发推送镜像
    job_name2 = 'deploy/biz-images'
    parameters2 = {
        'parameter1': 'latest'
    }
    server.build_job(job_name2, parameters2)

    print(f'Job 【{job_name2}】 completed.')


execute_jobs()
