$ErrorActionPreference = 'Stop'
$androidTools = Join-Path $env:LOCALAPPDATA 'rea\android'
$jdk = Get-ChildItem -LiteralPath (Join-Path $androidTools 'jdk21') -Directory |
    Where-Object { Test-Path -LiteralPath (Join-Path $_.FullName 'bin\java.exe') } |
    Select-Object -First 1
if (-not $jdk) { throw 'Khong tim thay JDK trong thu muc cong cu REA.' }
$env:JAVA_HOME = $jdk.FullName
$env:REA_JADX_MCP_JAR = Join-Path $androidTools 'jadx-headless-mcp-0.7.1-all.jar'
$npxCommand = Get-Command npx.cmd -ErrorAction Stop
$npxCli = Join-Path (Split-Path $npxCommand.Source) 'node_modules\npm\bin\npx-cli.js'
if (-not (Test-Path -LiteralPath $npxCli)) { throw 'Khong tim thay npx-cli.js ben can Node.js.' }
& node $npxCli --yes rea-agents@6.5.0 @args
exit $LASTEXITCODE
