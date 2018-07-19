
# Тринадцатое занятие

### Базы данных. Реляционные СУБД. PostgreSQL</a>
- <a href="http://db-engines.com/en/ranking">DB-Engines Ranking</a>
- <a href="https://ru.wikipedia.org/wiki/Реляционная_СУБД">Реляционная СУБД</a>
- <a href="http://www.codenet.ru/progr/vbasic/vb_db/1.php">Введение в базы данных</a>
- <a href="http://habrahabr.ru/post/103021/">Реляционные базы vs NoSQL</a>. SQL. Денормализация. PK, FK, Cascade
- <a href="https://ru.wikipedia.org/wiki/PostgreSQL">PostgreSQL. Надёжность</a>
- Создание базы резюме. <a href="https://habrahabr.ru/company/JetBrains/blog/204064/">Работа с базами данных из IDEA</a>
- <a href="https://www.jetbrains.com/datagrip/features/">IDEA Database tools</a>.
  
### Конфигурирование данных в Java проекте
- <a href="https://www.mkyong.com/java/java-properties-file-examples/">Properties sample</a>
- Конфигурирование DB и каталога хранения  

### Подключение DB в проект
- <a href="http://ru.wikipedia.org/wiki/Java_Database_Connectivity">JDBC</a>. <a href="http://www.developersbook.com/jdbc/interview-questions/jdbc-interview-questions-faqs.php">JDBC Architecture</a>.
- ConnectionFactory. Реализация SqlStorage.
- Ресурсы:
    - <a href="http://www.ozon.ru/context/detail/id/19383907/">Книга: Семь баз данных за семь недель. Введение в современные базы данных и идеологию NoSQL</a>
    - <a href="http://devcolibri.com/477">Работа с базами данных с помощью JDBC драйвера</a>
    - <a href="https://www.youtube.com/playlist?list=PLIU76b8Cjem5qdMQLXiIwGLTLyUHkTqi2">Уроки по JDBC</a>

### ![hw](https://cloud.githubusercontent.com/assets/13649199/13672719/09593080-e6e7-11e5-81d1-5cb629c438ca.png) Домашнее задание HW13
- Доделать `SqlStorage` без контактов и секций.
  - Для работы с DB надо в lib и проект добавить <a href="http://repo1.maven.org/maven2/org/postgresql/postgresql/9.4.1211/">драйвер базы данных</a>
  - Запустить `SqlStorageTest`  (в `AbstractStorageTest` контакты и секции закоменченны), креденшелы к базе взять из `Config`
- Вынести общий код (`getConnection(), prepareStatement, catch SQLException`) в класс `SqlHelper`.
