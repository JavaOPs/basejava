
# Четырнадцатое занятие

### Разбор HW13

### JOIN
- <a href="http://www.skillz.ru/dev/php/article-Obyasnenie_SQL_obedinenii_JOIN_INNER_OUTER.html">LEFT, RIGHT, INNER JOIN</a>
- Добавляем в `SqlStorage` контакты

### Транзакции. 
- <a href="http://ru.wikipedia.org/wiki/Транзакция_(информатика)">Транзакция. ACID.</a> <a href="https://ru.wikipedia.org/wiki/Уровень_изолированности_транзакций">Уровни изоляции транзакций.</a>
- <a href="http://www.osp.ru/pcworld/2009/07/9708191/">Уровни изоляции транзакций в SQL</a>
- Добавляем в `SqlStorage` транзакции
- Batch execute.

### Установка/запуск Tomcat
- Скачать и установить <a href="http://tomcat.apache.org/download-80.cgi">Tomcat 8</a>. Устанавливать лучше простым копированием из архива в каталог (в том числе и для unix). Следите чтобы в пути не было пробелов и национальных букв.
- Для доступа к <a href="http://localhost:8080/manager">Tomсat Manager</a> добавьте в конфигурацию Tomcat `TOMCAT_HOME\conf\tomcat-users.xml` права:
```
<user username="tomcat" password="tomcat" roles="tomcat,manager-gui,admin-gui"/>
```
- Запуск из `TOMCAT_HOME\bin\`: `catalina.bat start`
 
## Домашнее задание HW14
- Закончить реализацию `SqlStorage` с контактами
- Добавить `TextSection` в базу данных (`init_db.sql`) и `SqlStorage`.
