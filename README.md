#Опросник
###Архитектура
Приложение состоит из двух компонентов:
- Слой логики
- Слой данных


#####Слой логики
Слой логики предоставляет публичное REST API для использования клиентом.
Содержит бизнес логику по пользованию и управлению опросником.

#####Слой данных
Слой данных основан на базе реляционной БД.

###Используемые библиотеки и инструменты
Приложение написано на языке Java версии 7.
Сборка проекта осуществляется с помощью [Gradle](https://gradle.org/)

Используемые библиотеки:
- [Spring Framework 4.3.4](https://spring.io/) - IoC, REST, Security
- [Hibernate 5.2.4](http://hibernate.org/) - ORM
- [Jackson 2.8.4](http://wiki.fasterxml.com/Home) - JSON (REST)
- [H2 Database 1.4.193](http://www.h2database.com) - Embedded Database
- [JUnit 4.12](http://junit.org/) - Test
- [Mockito 1.10.19]() -Test(Mocking)
- [SLF4J 1.7.21](http://www.slf4j.org/) - Logging
- [Logback 1.1.7](http://logback.qos.ch/) - Logging

### Структура проекта

##### Слой API
Местоположение модулей:
> {корень проекта}/api/...

Базовые пакеты:
```
package com.maximov.quiz.service.*
package com.maximov.quiz.domain.*
package com.maximov.quiz.exception.*
```
Данная группа модулей содержит набор интерфейсов и готовых классов для реализации конкретных сервисов

##### Слой DAO

Местоположение модулей:
> {корень проекта}/dao/...

Базовые пакеты:
```
package com.maximov.quiz.dao.*
package com.maximov.quiz.entity.*
```

Данная группа модулей содержит сервисы базовых CRUD операций,
а также выборку для навигации (см. *QuizDao#getQuestions*).
Для взаимодействия с БД используется Hibernate. Маппинг на основе аннотаций.

##### Слой Service

Местоположение модулей:
> {корень проекта}/service/...

Базовые пакеты:
```
package com.maximov.quiz.service.*
```

Данная группа модулей содержит реализацию сервисов из API

##### Модуль WEB
Веб часть построена на базе *spring-mvc* с использованием *spring-security*

> *Авторизация по REST не реализована*

Модуль содержит в себе описание двух освновных REST контроллеров:

- Админстрирование ~ *AdminController* - (/api/admin)
- Опросник ~ *QuizController* - (/api/quiz)

Обработка исключений и логгирование происходит в *ExceptionController*
Основным классом-исключением бизнес логики является *GeneralException* из *common-api*

Контекст приложения описан в *root-context.xml*
Контекст сервлета описан в *context.xml*

#####База данных
По умолчанию H2 Database поднимается при старте приложения.
Для подключения к другой базе необходимо внести
изменения в файл конфигурации - *application.properties*
Подключение по умолчанию:
```
jdbc.driverClassName=org.h2.Driver
jdbc.url=jdbc:h2:mem:quiz_db;DB_CLOSE_DELAY=-1
jdbc.username=sa
jdbc.password=
hibernate.dialect=org.hibernate.dialect.H2Dialect
```
#####Тест
Модульное тестирование с помощью JUnit и Mockito
Пример unit-теста на сервис:
>*com.maximov.quiz.service.QuizServiceTest*


#####Gradle
В проекте подключен *Gradle Wrapper* - нет необходимости иметь
установленный *Gradle* для сборки.
Сборка артефактов:
```
gradlew build
```
Запуск на *Jetty* через плагин *Gradle*:
```
gradlew jettyRun
```
_________________________________
*Максимов Владимир, 2016*