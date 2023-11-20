# Проект по автоматизации тестирования сайта alfabank.ru

<br>
<p align="center">
<img width="110%" title="Alfabank" src="images/logo/alfaBank.jpg">
</p>
<br>

## :pushpin: Содержание:

- [Использованный стек технологий](#computer-использованный-стек-технологий)
- [Реализованные проверки](#computer-реализованные-проверки)
- [Запуск тестов](#running_woman-запуск-тестов)
- [Сборка в Jenkins](#-сборка-в-jenkins)
- [Пример Allure-отчета](#-пример-allure-отчета)
- [Интеграция с Allure TestOps](#-интеграция-с-allure-testops)
- [Уведомления в Telegram с использованием бота](#-уведомления-в-telegram-с-использованием-бота)
- [Видео примера запуска теста в Selenoid](#-видео-примера-запуска-теста-в-selenoid)

## :computer: Использованный стек технологий

<p align="center">
<img width="6%" title="IntelliJ IDEA" src="images/logo/Intelij_IDEA.svg">
<img width="6%" title="Java" src="images/logo/Java.svg">
<img width="6%" title="Selenide" src="images/logo/Selenide.svg">
<img width="6%" title="Selenoid" src="images/logo/Selenoid.svg">
<img width="6%" title="Allure Report" src="images/logo/Allure_Report.svg">
<img width="5%" title="Allure TestOps" src="images/logo/allureTestOps.svg">
<img width="6%" title="Gradle" src="images/logo/Gradle.svg">
<img width="6%" title="JUnit5" src="images/logo/JUnit5.svg">
<img width="6%" title="GitHub" src="images/logo/GitHub.svg">
<img width="6%" title="Jenkins" src="images/logo/Jenkins.svg">
<img width="6%" title="Jira" src="images/logo/Jira.svg">
<img width="6%" title="Telegram" src="images/logo/Telegram.svg">
</p>

Автотесты написаны на <code>Java</code> с использованием <code>JUnit 5</code> и <code>Gradle</code>.
Для UI-тестов использован фреймворк [Selenide](https://selenide.org/).
Запуск тестов можно осуществлять локально или с помощью [Selenoid](https://aerokube.com/selenoid/).
Также реализована сборка в <code>Jenkins</code> с формированием Allure-отчета и отправкой уведомления с результатами в <code>Telegram</code> после завершения прогона.

Allure-отчет включает в себя:
* шаги выполнения тестов;
* скриншот страницы в браузере в момент окончания автотеста;
* Page Source;
* логи браузерной консоли;
* видео выполнения автотеста.

## :computer: Реализованные проверки

- [x] *Проверка рассчёта кредитного калькулятора на главной странице*
- [x] *Проверка секции карты: кредитные, дебетовые*
    - форма для оформления заявки на выбранную карту
- [x] *Проверка секции вклады: доступные для оформления*
- [x] *Проверка секции кредиты: информация по кредитам*
- [x] *Проверка секции ипотека: предлагаемые варианты, условия оформления*
- [x] *Проверка секции Инвестиции*
- [x] *Проверка секции Премиум: оформление карты 'Премиум'*
- [x] *Проверка модального окна, при наведении на секцию 'Ещё'*

## :running_woman: Запуск тестов

### Локальный запуск тестов
```
gradle clean test -Denv=local
```

При необходимости можно переопределить параметры запуска
```
test - запуск всех тестов
main_test - запуск тестов на главной странице (рассчёт ежемесячного платежа по кредиту)
creditCard_test - запуск тестов в блоке Карты: секция кредитные карты
debitCard_test - запуск тестов в блоке Карты: секция дебетовые карты
deposits_test - запуск тестов в блоке Вклады
credits_test - запуск тестов в блоке Кредиты
mortgage_test - запуск тестов в блоке Ипотека
investment_test - запуск тестов в блоке Инвестиции
premiumCard_test - запуск тестов в блоке Премиум
modalWindow_test - кнопка Ещё

```

### Запуск тестов на удаленном браузере
```
gradle clean test -Denv=remote
```
При необходимости также можно переопределить параметры запуска

```
test - запуск всех тестов
main_test - запуск тестов на главной странице (рассчёт ежемесячного платежа по кредиту)
creditCard_test - запуск тестов в блоке Карты: секция кредитные карты
debitCard_test - запуск тестов в блоке Карты: секция дебетовые карты
deposits_test - запуск тестов в блоке Вклады
credits_test - запуск тестов в блоке Кредиты
mortgage_test - запуск тестов в блоке Ипотека
investment_test - запуск тестов в блоке Инвестиции
premiumCard_test - запуск тестов в блоке Премиум
modalWindow_test - кнопка Ещё
```

## <img width="4%" style="vertical-align:middle" title="Gradle" src="images/logo/Gradle.svg"> <a href=""> Локальный запуск</a>
<p align="center">
<img title="Gradle Build" src="images/screenshots/Прогон gradle first.png">
<img title="Gradle Build" src="images/screenshots/Прогон gradle two.png">
<img title="Gradle Build" src="images/screenshots/Прогон gradle three.png">
</p>

## <img width="4%" style="vertical-align:middle" title="Jenkins" src="images/logo/Jenkins.svg"> <a href="https://jenkins.autotests.cloud/job/Alfa_Test/15/"> Сборка в Jenkins</a>
<p align="center">
<img title="Jenkins Build" src="images/screenshots/Build.png">
</p>

## <img width="4%" style="vertical-align:middle" title="Allure Report" src="images/logo/Allure_Report.svg"> <a href="https://jenkins.autotests.cloud/job/Alfa_Test/15/allure/">Пример Allure-отчета</a>
### Overview

<p align="center">
<img title="Allure Overview" src="images/screenshots/Jenkins1g.png">
</p>

### Результат выполнения теста

<p align="center">
<img title="Allure Overview" src="images/screenshots/jenkins2g.png">
</p>

## <img width="4%" title="Allure TestOPS" src="images/logo/allureTestOps.svg"> Интеграция с [Allure TestOps](https://qameta.io/)

### Основной дашборд

<p align="center">
  <img src="images/screenshots/testOps1.png" alt="dashboard" width="900">
</p>

### Тест-кейсы

<p align="center">
  <img src="images/screenshots/testOps2.png" alt="testcase" width="900">
</p>



### <img width="4%" style="vertical-align:middle" title="Telegram" src="images/logo/Telegram.svg"> Уведомления в Telegram с использованием бота

После завершения сборки специальный бот, созданный в <code>Telegram</code>, автоматически обрабатывает и отправляет сообщение с отчетом о прогоне.

<p align="center">
<img width="70%" title="Telegram Notifications" src="images/screenshots/Телеграмм.png">
</p>

# Интеграция с Jira
<p align="center">
  <img src="images/screenshots/jira_.png" alt="JiraIntegration" width="950">
</p>

### <img width="4%" style="vertical-align:middle" title="Selenoid" src="images/logo/Selenoid.svg"> Видео примера запуска теста в Selenoid

К каждому тесту в отчете прилагается видео. Одно из таких видео представлено ниже section1 > CreditCardTest > Отправка не заполненной формы заявки на кредитную Альфа-Карту.
<p align="center">
  <img title="Selenoid Video" src="images/video/1a1224827da92c27b21c9e564de8738a.gif">
</p>