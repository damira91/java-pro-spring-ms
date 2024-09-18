# Java-pro-srpring-ms
## Общее описание 
Этот проект включает в себя сервисы WireMock и PostgreSQL, развернутые с использованием Docker. WireMock используется для создания мок-сервисов, а PostgreSQL для хранения данных.
## Требования
- Docker (версия 20.10 или выше)
- Docker Compose (версия 1.27 или выше)
## Развертывание Docker

1. **Клонируйте репозиторий**

   ```sh
   git clone https://github.com/damira91/java-pro-spring-ms.git
   cd java-pro-spring-ms

2. **Запустите контейнеры**
   Перейдите в директорию модуля dev/docker:
   ```
   cd dev/docker
   ```
   далее выполните следующую команду, чтобы запустить все контейнеры:
   ```
    docker-compose up
   ```
   Вы должны увидеть, что контейнеры wiremock-service и postgres находятся в статусе Up
   
## Работа с проектом

**WireMock**

WireMock будет доступен на порту 8822 (указан в docker-compose.yml).

Примеры запросов к WireMock:

GET /api/v1/limits/1

Этот запрос должен вернуть 200 OK с содержимым файла 1-sufficient-limit.json.

POST /api/v1/limits/debit

Этот запрос должен вернуть 200 OK с содержимым файла 3-limit-remained.json при соответствующих заголовках.

**PostgreSQL**

PostgreSQL будет доступен на порту 5433 (указан в docker-compose.yml). Доступ к базе данных можно получить с помощью клиента PostgreSQL, используя следующие параметры:

Хост: localhost
Порт: 5433
Имя пользователя: postgres
Пароль: postgres
База данных: postgres

**Запуск модулей**
После запуска контейнеров необходимо запустить модули backend и client-service
