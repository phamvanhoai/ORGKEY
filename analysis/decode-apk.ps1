$ErrorActionPreference = 'Stop'
$workspace = Split-Path $PSScriptRoot -Parent
$androidTools = Join-Path $env:LOCALAPPDATA 'rea/android'
$jdk = Get-ChildItem (Join-Path $androidTools 'jdk21') -Directory | Select-Object -First 1
$jar = Join-Path $androidTools 'jadx-headless-mcp-0.7.1-all.jar'
$output = Join-Path $PSScriptRoot 'decoded-original'
$classes = Join-Path $output 'driver'
New-Item -ItemType Directory -Force $classes | Out-Null
& (Join-Path $jdk.FullName 'bin/javac.exe') -cp $jar -d $classes (Join-Path $PSScriptRoot 'DecodeApk.java')
if ($LASTEXITCODE -ne 0) { throw 'Decoder compilation failed' }
$ErrorActionPreference = 'Continue' # JADX writes normal progress to stderr on Windows PowerShell.
& (Join-Path $jdk.FullName 'bin/java.exe') -Xmx6g -cp "$classes;$jar" DecodeApk (Join-Path $workspace 'ORG+24_+Your+Music_2026.10.01_APKPure.apk') $output
if ($LASTEXITCODE -ne 0) { throw 'Decoder failed; inspect log' }
