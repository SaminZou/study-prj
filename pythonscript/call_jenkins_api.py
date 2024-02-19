import requests

def run_jenkins_job(job_name, parameters):
    jenkins_url = 'http://your_jenkins_url'  # 替换为您的Jenkins实例URL
    username = 'your_username'  # 替换为您的Jenkins用户名
    password = 'your_password'  # 替换为您的Jenkins密码或API令牌

    # 构建API URL
    api_url = f'{jenkins_url}/job/{job_name}/buildWithParameters'

    # 构建请求参数
    params = {
        'token': 'your_build_token',  # 替换为您的构建令牌
        'parameter1': parameters['parameter1'],  # 替换为您的任务参数1
        'parameter2': parameters['parameter2']  # 替换为您的任务参数2
    }

    # 发送POST请求
    response = requests.post(api_url, auth=(username, password), params=params)

    if response.status_code == 201:
        print(f'Successfully triggered Jenkins job: {job_name}')
    else:
        print(f'Failed to trigger Jenkins job: {job_name}. Error: {response.text}')

# 调用任务
job_name = 'your_job_name'  # 替换为您的任务名称
parameters = {
    'parameter1': 'value1',  # 替换为您的任务的参数1值
    'parameter2': 'value2'  # 替换为您的任务的参数2值
}
run_jenkins_job(job_name, parameters)