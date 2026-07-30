# nnbot-chat

Android WebView-приложение для чата с Hermes Agent.

## 🚀 Сборка через GitHub Actions

Сборка APK полностью автоматизирована через GitHub Actions. Никаких локальных/ручных сборок не требуется.

### Как собрать

#### Debug APK (каждый коммит в main)

Сборка запускается автоматически при каждом пуше в ветку `main`:

1. Перейдите на вкладку **Actions** в репозитории GitHub
2. Выберите workflow **Android CI**
3. Откройте последний запуск
4. Скачайте артефакт `nnbot-chat-debug`

Или через GitHub CLI:

```bash
gh run download --repo Dev-Apk/nnbot-chat --name nnbot-chat-debug --dir ./apk
```

#### Release APK (по тегу)

Для создания релизной сборки:

```bash
git tag v1.2.0
git push origin v1.2.0
```

GitHub Actions автоматически:
- Соберёт **release APK**
- Загрузит его как артефакт `nnbot-chat-release`
- Создаст **GitHub Release** с APK во вложениях

### Структура workflow

Файл: `.github/workflows/android.yml`

| Событие | Действие |
|---------|----------|
| Push в `main` | Сборка debug APK |
| PR в `main` | Проверка сборки |
| Push тега `v*` | Сборка debug + release APK + создание Release |

### Требования

- JDK 17 (устанавливается автоматически)
- Android SDK platform 34, build-tools 34.0.0 (устанавливаются автоматически)
- Gradle 8.10.2 (через wrapper)

## 🛠 Локальная разработка (опционально)

Если хотите собрать локально:

```bash
./gradlew assembleDebug
```

Убедитесь, что Android SDK установлен и указан в `local.properties`:

```properties
sdk.dir=/path/to/Android/Sdk
```

## 📦 Структура проекта

```
nnbot-chat/
├── app/
│   ├── build.gradle          # Модуль приложения
│   └── src/main/
│       ├── AndroidManifest.xml
│       ├── java/com/nnbot/chat/MainActivity.kt
│       └── res/
├── build.gradle              # Корневой Gradle-файл
├── settings.gradle
├── gradle.properties
├── gradlew / gradlew.bat     # Gradle wrapper
└── .github/workflows/        # GitHub Actions CI/CD
```