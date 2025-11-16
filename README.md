# Furniture Management System

Консольное приложение для управления каталогом мебели с использованием Spring Boot и PostgreSQL.

## Требования

- Java 17 или выше
- PostgreSQL 12 или выше
- Maven

## Настройка базы данных

1. Установите и запустите PostgreSQL
2. Создайте базу данных:
```sql
CREATE DATABASE furniture_db;
```

## Настройка приложения

1. Откройте файл `src/main/resources/application.properties`
2. Настройте подключение к базе данных:
```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/furniture_db
spring.datasource.username=postgres
spring.datasource.password=postgres
```

## Запуск приложения

1. Склонируйте репозиторий
2. Перейдите в папку проекта
3. Выполните команду:
```bash
mvn spring-boot:run
```
### После запуска перейдите на localhost:8080
(если вы не изменяли настройки)