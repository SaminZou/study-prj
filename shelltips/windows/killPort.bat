@echo off

rem 输入一个需要检查的接口号
set /p appport="input port to check:"

rem 打印标题
echo  Protocol    Local   Remote    Status     PID

rem 检查端口号对应的进程号
netstat -aon | findstr %appport%

rem 关闭对应的进程
set /p killport="input port to kill:"

taskkill /f /t /im %killport%