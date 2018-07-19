# Курс BaseJava (обновленный и переработанный)

## Разработка Web приложения "База данных резюме"
  -  используем: Java 8, <a href="https://zeroturnaround.com/rebellabs/java-tools-and-technologies-landscape-2016-trends/#java-ides-adoption">IntelliJ IDEA</a>,
    <a href="https://zeroturnaround.com/rebellabs/java-tools-and-technologies-landscape-2016-trends/#java-vcs-adoption">GitHib/Git</a>, Сервлеты, JSP, JSTL, Tomcat, JUnit, PostgreSQL, GSON, JAXB
  - хранение резюме
     -  в памяти на основе массива, отсортированного массива, списка и ассоциированного массива (Map)
     -  в файловой системе (File API и <a href="http://www.quizful.net/post/java-nio-tutorial">Java 7 NIO File API</a>)
        - в стандартной и кастомной сериализации Java
        - в формате JSON (<a href="https://github.com/google/gson">Google Gson</a>)
        - в формате XML (<a href="https://ru.wikipedia.org/wiki/Java_Architecture_for_XML_Binding">JAXB</a>)
     -  в реляционной базе <a href="https://ru.wikipedia.org/wiki/PostgreSQL">PostgreSQL</a>
  -  деплой веб приложения
     - в контейнер сервлетов <a href="http://tomcat.apache.org/">Tomcat</a>
     - в облачный сервис <a href="https://www.heroku.com/">Heroku</a>

Приложение будет разрабатываться начиная со первого занятия, основываясь на базовых темах курса:
**объектная модель, коллекции, система ввода-вывода, работа с файлами, сериализация, работа с XML, JSON, SQL, персистентность в базу данных (PostgreSQL), сервлеты, HTML/JSP/JSTL, веб-контейнер Tomcat, модульные тесты JUnit, java.util.Logging, система контроля версий Git.**

> Любое знание стоит воспринимать как подобие семантического дерева: убедитесь в том, что понимаете фундаментальные принципы, то есть ствол и крупные ветки, прежде чем лезть в мелкие листья-детали. Иначе последним не на чем будет держаться.

[*— Илон Маск](https://ru.wikipedia.org/wiki/Маск,_Илон)

# Изучайте [первое открытое занятие](lesson/lesson1.md).
### Внизу урока есть первое домашнее задание, по которому можно оценить свой уровень готовности к проекту.

## Программа
### [Регистрация](http://javaops.ru/reg/basejava)
### Открытое занятие 1
 - [Презентация проекта](lesson/lesson1.md#-Вебинар-ПРЕЗЕНТАЦИЯ-обучения)
 - [Разработка ПО](lesson/lesson1.md#-1-Разработка-ПО)
 - [Обзор языка Java](lesson/lesson1.md#-3-Обзор-языка-java)
 - [Системы управления версиями. Git](lesson/lesson1.md#-4-Системы-управления-версиями-git)
 - [ПЕРВОЕ ДОМАШНЕЕ ЗАДАНИЕ](lesson/lesson1.md#Домашнее-задание-hw1)

### Занятие 2
 - [Принципы ООП](lesson/lesson2.md#Принципы-ООП)
 - [Структура памяти: куча, стек, регистры, константы](lesson/lesson2.md#Структура-памяти-куча-стек-регистры-константы)
 - [Типы данных. Пакеты](lesson/lesson2.md#Типы-данных-Пакеты)

### Занятие 3
 - [Объектная модель в Java](lesson/lesson3.md#Объектная-модель-в-java)
 - [Сложность алгоритмов](lesson/lesson3.md#Сложность-алгоритмов)
 - [Паттерн проектирования Шаблонный метод](https://github.com/JavaOPs/JavaSE-Web/blob/master/lesson/lesson3.md#Паттерн-проектирования-Шаблонный-метод)
 
### Занятие 4
 - [Работа со строками](lesson/lesson4.md#Работа-со-строками)
 - [Исключения](lesson/lesson4.md#Исключения)
 - [Reflection. Аннотации. Модульное тестирование](lesson/lesson4.md#reflection-Аннотации-Модульное-тестирование)

### Занятие 5
 - [Контейнеры/коллекции](lesson/lesson5.md#Контейнерыколлекции)
 
### Занятие 6
 - [Iterator / Iterable. Вложенные, внутренние, локальные и анонимные классы](lesson/lesson6.md#iterator--iterable-Вложенные-внутренние-локальные-и-анонимные-классы)
 - [Новое в Java 8](lesson/lesson6.md#Новое-в-java-8)

### Занятие 7
 - [Параметризация. Стирание типов](lesson/lesson7.md#Параметризация-Стирание-типов)
 - [Логирование](lesson/lesson7.md#Логирование)
 - [Синглетон, Enum](lesson/lesson7.md#Синглетон-enum)

### Занятие 8
 - [Работа с датами и временем](lesson/lesson8.md#Работа-с-датами-и-временем)
 - [Работа с файлами и ресурсами](lesson/lesson8.md#Работа-с-файлами-и-ресурсами)

### Занятие 9
 - [Ввод/вывод](lesson/lesson9.md#Вводвывод)
 - [Сериализация](lesson/lesson9.md#Сериализация)
 - [NIO](lesson/lesson9.md#nio)
 - [Основы Java 8 Stream API](lesson/lesson9.md#Основы-java-8-stream-api) 

### Занятие 10
 - [Формат XML. Работа с XML в Java](lesson/lesson10.md#Формат-xml-Работа-с-xml-в-java)
 - [JSON](lesson/lesson10.md#json)
 - [DataInputStream / DataOutputStream](lesson/lesson10.md#datainputstream--dataoutputstream)

### Занятие 11
 - [Многопоточность. Параллельное выполнение.](lesson/lesson11.md#Многопоточность-Параллельное-выполнение)
 - [Потоки. Синхронизация](lesson/lesson11.md#Потоки-Синхронизация)
 - [Ленивая инициализация, JMM](lesson/lesson11.md#Ленивая-инициализация-jmm)
 
### Занятие 12
 - [java.util.concurrent](lesson/lesson12.md#javautilconcurrent)

### Занятие 13
 - [Базы данных. Реляционные СУБД. PostgreSQL](lesson/lesson13.md#Базы-данных-Реляционные-СУБД-postgresql)
 - [Конфигурирование данных в Java проекте](lesson/lesson13.md#Конфигурирование-данных-в-java-проекте)
 - [Подключение DB в проект](lesson/lesson13.md#Подключение-db-в-проект)

### Занятие 14
 - [JOIN](lesson/lesson14.md#join)
 - [Транзакции](lesson/lesson14.md#Транзакции)
 - [Установка/запуск Tomcat](lesson/lesson14.md#Установказапуск-tomcat)

### Занятие 15
 - [HTML, Tomcat](lesson/lesson15.md#html-tomcat)
 - [Сервлеты](lesson/lesson15.md#Сервлеты)
 
### Занятие 16
 - [JSP](lesson/lesson16.md#jsp)
 - [JSTL](lesson/lesson16.md#jstl)
 
### Занятие 17
 - [Деплой в Heroku](lesson/lesson17.md#Деплой-в-heroku)
 - [Classloader](lesson/lesson17.md#classloader)
 - [Обзор Java Enterprise](lesson/lesson17.md#Обзор-java-enterprise)

## Рекомендуемые книги
- <a href="http://myflex.org/books/java4kids/java4kids.htm">YAKOV FAIN: Программирование на Java для начинающих</a>
- <a href="https://habrahabr.ru/post/153373/">Книги по Java: от новичка до профессионала</a>
- <a href="http://scanlibs.com/java-effektivnoe-programmirovanie-2-e-izdanie">Джошуа Блох: Java. Эффективное программирование, 2-е издание</a>
- <a href="http://www.labirint.ru/books/87603/">Гамма, Хелм, Джонсон: Приемы объектно-ориентированного проектирования. Паттерны проектирования</a>
- <a href="http://www.bookvoed.ru/book?id=639284">Редмонд Э.: Семь баз данных за семь недель. Введение в современные базы данных и идеологию NoSQL.</a>

##  Ресуры в сети
- [Руководство по Java Core](http://proselyte.net/tutorials/java-core/)
- [Java. Базовый курс](https://stepik.org/course/Java-Базовый-курс-187)
- <a href="http://www.intuit.ru/studies/courses/16/16/info">intuit: Программирование на Java</a>
- <a href="http://sernam.ru/book_java.php">Основы программирования на Java: учебное пособие</a>
