@echo off
setlocal enabledelayedexpansion

rem 输入一个需要检查的接口号
set /p appport="Input port to check: "

rem 打印标题
echo Protocol    Local Address          Foreign Address        State           PID
echo ============================================================================

rem 初始化变量
set "pidToKill="

rem 检查端口号对应的进程号
for /f "tokens=1,2,3,4,5" %%a in ('netstat -ano ^| findstr !appport!') do (
    set "protocol=%%a"
    set "local=%%b"
    set "remote=%%c"
    set "status=%%d"
    set "pid=%%e"
    echo !protocol!  !local!  !remote!  !status!  !pid!
    set "pidToKill=!pid!"
)

rem 如果没有找到进程，退出脚本
if not defined pidToKill (
    echo No process found using port !appport!
    exit /b
)

rem 确认是否要结束进程
set /p choice="Do you want to kill process with PID !pidToKill!? (Y/N): "
if /i "!choice!" neq "Y" (
    echo Operation cancelled by user.
    exit /b
)

rem 结束对应的进程
taskkill /f /pid !pidToKill!
if errorlevel 1 (
    echo Failed to kill process with PID !pidToKill!
) else (
    echo Successfully killed process with PID !pidToKill!
)
endlocal