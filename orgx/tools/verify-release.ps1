param([switch]$Build,[switch]$Install)
$ErrorActionPreference='Stop'
$project=Resolve-Path (Join-Path $PSScriptRoot '..')
$apk=Join-Path $project 'app/build/outputs/apk/debug/app-debug.apk'
if($Build){$env:JAVA_HOME='C:\Program Files\Microsoft\jdk-17.0.20.101-hotspot';Push-Location $project; .\gradlew.bat :app:testDebugUnitTest :app:assembleDebug --console=plain; Pop-Location}
if(-not(Test-Path $apk)){throw "APK not found: $apk"}
$gradle=Get-Content (Join-Path $project 'app/build.gradle.kts') -Raw
$versionName=[regex]::Match($gradle,'versionName\s*=\s*"([^"]+)"').Groups[1].Value
$versionCode=[regex]::Match($gradle,'versionCode\s*=\s*(\d+)').Groups[1].Value
if(-not $versionName -or -not $versionCode){throw 'Could not read version from build.gradle.kts'}
$hash=(Get-FileHash $apk -Algorithm SHA256).Hash
Write-Host "APK: $apk"
Write-Host "Version: $versionName ($versionCode)"
Write-Host "SHA256: $hash"
if($Install){$adb=Join-Path $project '..\..\tools\android-sdk\platform-tools\adb.exe';&$adb devices;&$adb install -r $apk;&$adb shell am start -n com.hovait.orgx/.MainActivity}
