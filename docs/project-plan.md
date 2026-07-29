# AI Assistant Android App

## Цель
Сделать Telegram-замену: Android-приложение для общения с Hermes через `chat.nnbot.fun`. Дальше расширить до продукта для клиентов с управлением агентами.

## Этап 1. Верификация Hermes API
- Найти работающий Hermes backend endpoint на VPS.
- Определить способ отправки сообщений: webhook/proxy/JSON API.
- Подготовить простой БЕ для чата на `/usr/share/html/chat-app` или через `hermes serve --port 9119`.

## Этап 2. Поддомен chat.nnbot.fun
- Добавить DNS A запись на VPS.
- Создать Traefik router/Traefik/n8n compose сервис или отдельный docker compose в `/usr/share/html/`.
- Поднять HTTPS через ACME.

## Этап 3. Веб-чат
- Сделать фронт: `index.html` + JS/WebSocket.
- Подключить к Hermes API с токеном/без.
- Подготовить отдельный репо с веб-чатом.

## Этап 4. Android WebView
- Создать/доработать проект `nnbot-chat-android` (Kotlin/Java).
- Открыть `https://chat.nnbot.fun` в WebView.
- Обработать прогресс, ошибки, back navigation.

## Этап 5. GitHub Actions
- Настроить сборку APK через Actions.
- Сделать релизы в GitHub Releases.

## Безопасность
- Токен/ключ для API хранить только в `.env` на сервере.
- Для мобильных клиентов позже добавить простую аутентификацию.

## Тестирование
- Отправка сообщения с телефона → Hermes.
- Проверка SSL/TLS.
- Проверка на Android 8+.
