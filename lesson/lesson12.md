
# Двенадцатое занятие

## Разбор Домашнего Задания-11: реализация dead lock.

## java.util.concurrent
-  <a href="http://habrahabr.ru/company/luxoft/blog/157273/">Обзор java.util.concurrent.*</a></li>
- <a href="https://en.wikipedia.org/wiki/Compare-and-swap">Compare-and-swap</a>
- <a href="https://habrahabr.ru/post/277669/"> Справочник по синхронизаторам java.util.concurrent.*</a>
- <a href="http://articles.javatalks.ru/articles/17">Использование ThreadLocal переменных</a>

>  Замечания по видео:

    ThreadLocal<SimpleDateFormat> DATE_FORMAT = new ThreadLocal<SimpleDateFormat>() {
       @Override
       protected SimpleDateFormat initialValue() {
            return new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
       };
    };

можно написать через лямбду: 

    ThreadLocal.withInitial(() -> new SimpleDateFormat("dd.MM.yyyy HH:mm:ss"));
    
А лучше использовать потокобезопасный `DateTimeFormatter` Java 8 Time API: 

    DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss");

## Разбор домашнего задания 10го урока

## Домашнее задание:
- <a href="http://java-course.ru/begin/postgresql">Установить PostgreSQL</a>
- Посомтреть на реляционные базы данных и SQL:
  - <a href="http://www.codenet.ru/progr/vbasic/vb_db/1.php">Введение в базы данных</a>
  - <a href="http://www.intuit.ru/studies/courses/5/5/info">Основы SQL</a>
