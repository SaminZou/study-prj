@echo off
setlocal enabledelayedexpansion

:: ==============================
:: Port to Process Killer Script
:: ==============================

:: 提示用户输入端口号
set /p "appport=Input port to check: "
if "%appport%"=="" (
    echo [ERROR] Port cannot be empty.
    exit /b 1
)

:: 打印表头
echo Protocol    Local Address          Foreign Address        State           PID
echo ============================================================================

:: 初始化变量
set "pidToKill="

:: 使用 netstat 查找端口对应的进程
for /f "tokens=1,2,3,4,5" %%a in ('netstat -ano ^| findstr /r /c:":%appport%[ ]"') do (
    set "protocol=%%a"
    set "local=%%b"
    set "remote=%%c"
    set "status=%%d"
    set "pid=%%e"
    echo !protocol!  !local!  !remote!  !status!  !pid!
    set "pidToKill=!pid!"
)

:: 如果没有找到进程，退出
if not defined pidToKill (
    echo [INFO] No process found using port %appport%.
    exit /b 0
)

:: 询问是否结束进程
set /p "choice=Do you want to kill process with PID %pidToKill%? (Y/N): "
if /i not "%choice%"=="Y" (
    echo [INFO] Operation cancelled by user.
    exit /b 0
)

:: 结束进程
taskkill /f /pid %pidToKill% >nul 2>&1
if errorlevel 1 (
    echo [ERROR] Failed to kill process with PID %pidToKill%.
    exit /b 1
) else (
    echo [SUCCESS] Successfully killed process with PID %pidToKill%.
)

endlocal
exit /b 0
