
# Первое занятие

##  Разработка ПО.
- <a href="https://ru.wikipedia.org/wiki/Мифический_человеко-месяц">Книга: Мифический человеко-месяц</a>
- <a href="https://dou.ua/forums/topic/14015/">Методологии разработки ПО</a>
- <a href="https://habrahabr.ru/company/edison/blog/269789/">Ещё раз про семь основных методологий разработки</a>

##  Обзор инструментов и технологий.
- <a href="http://zeroturnaround.com/rebellabs/java-tools-and-technologies-landscape-for-2014/">Обзор популярности инструментов и технологий Java за 2014г.</a>
-  Дополнительно:
   - <a href="http://www.quizful.net/post/automated-builds-java">Автоматизированные сборки в Java</a>

## Обзор языка Java
- <a href="http://ru.wikipedia.org/wiki/Java">Java</a>, <a href="http://ru.wikipedia.org/wiki/Виртуальная_машина_Java">JVM</a>, <a href="http://ru.wikipedia.org/wiki/JIT">JIT-компиляция</a>

![jvm](https://cloud.githubusercontent.com/assets/18701152/15219296/e6c67e86-186b-11e6-986f-651a87deec6c.png)

- <a href="http://www.tiobe.com/index.php/content/paperinfo/tpci/index.html">Programming languages TIOBE Index</a>
- <a href="http://ru.wikipedia.org/wiki/Java_Platform,_Micro_Edition">ME</a>, <a href="http://en.wikipedia.org/wiki/Java_Platform,_Standard_Edition">SE</a> (<a href="http://ru.wikipedia.org/wiki/Java_Platform,_Standard_Edition">русский</a>), <a href="http://en.wikipedia.org/wiki/Java_Platform,_Enterprise_Edition">EE (<a href="http://ru.wikipedia.org/wiki/Java_Platform,_Enterprise_Edition">русский</a>)</a>
- <a href="http://docs.oracle.com/javase/8/docs/index.html">Oracle Java8 Home</a>
-  Дополнительно:
   - <a href="https://plumbr.eu/blog/java/java-version-and-vendor-data-analyzed-2016-edition">Java version and vendor data analyzed</a>
   - <a href="https://dzone.com/articles/most-popular-java-ee-servers-2016-edition">Most Popular Java EE Servers</a>
   - <a href="http://www.intuit.ru/studies/courses/16/16/lecture/27105">Что такое Java? История создания</a>
   - <a href="http://ggenikus.github.io/blog/2014/05/04/gc/">Понимаем основы Java garbage collection</a>

##  Системы управления версиями. Git.
- <a href="http://ru.wikipedia.org/wiki/Система_управления_версиями">Система управления версиями</a>.
- <a href="http://ru.wikipedia.org/wiki/%D0%A1%D0%B8%D1%81%D1%82%D0%B5%D0%BC%D0%B0_%D1%83%D0%BF%D1%80%D0%B0%D0%B2%D0%BB%D0%B5%D0%BD%D0%B8%D1%8F_%D0%B2%D0%B5%D1%80%D1%81%D0%B8%D1%8F%D0%BC%D0%B8#.D0.A0.D0.B0.D1.81.D0.BF.D1.80.D0.B5.D0.B4.D0.B5.D0.BB.D1.91.D0.BD.D0.BD.D1.8B.D0.B5_.D1.81.D0.B8.D1.81.D1.82.D0.B5.D0.BC.D1.8B_.D1.83.D0.BF.D1.80.D0.B0.D0.B2.D0.BB.D0.B5.D0.BD.D0.B8.D1.8F_.D0.B2.D0.B5.D1.80.D1.81.D0.B8.D1.8F.D0.BC.D0.B8">VCS/DVSC</a>.

![image](https://cloud.githubusercontent.com/assets/18701152/15219746/9295a2fe-186d-11e6-876b-c61cc9be71e4.png)

- <a href="http://www.netinstructions.com/the-case-for-git/">Популярность разный VSC</a>
- <a href="https://git-scm.com/book/ru/v2">Книга по Git</a>

## Настройка окружения
- <a href="https://github.com/JavaOPs/topjava/wiki/IDEA">Idea Wiki</a> (поставить кодировку UTF-8, поменять фонт по умолчанию на DejaVu)
- git занести в переменная окружения PATH, перезапустить cmd
- Создайте локальную копию нашего проекта: `git clone https://github.com/School-IT-Programm/resume-storage.git`
- Перейти в каталог проекта: `cd resume-storage`
- `git remote -v`
- `git remote set-url origin https://github.com/School-IT-Programm/resume-storage.git` - настройка pull
- `git remote set-url --push origin https://github.com/[YouGitHub/YourRepo].git` - настройка push
- `git push -u origin master`

## Насторойка проекта. Ветка HW1. Debug
- <a href="http://learn.javajoy.net/debug-intellij-idea">Отладчик IntelliJ IDEA</a>
- <a href="http://jeeconf.com/archive/jeeconf-2013/materials/intellij-idea/">Эффективная работа с кодом в IntelliJ IDEA</a>

## Домашнее задание
- Модифицировать класс `ArrayStorage`: хранить все резюме в начале storage (без дырок null), чтобы не перебирать каждый раз все 10000 элементов.
```
Хранеие резюме в storage (от 0 до size-1 элементов null нет):

r1, r2, r3,..., rn, null, null,..., null
<----  size ----->
<----  storage.length   --------------->
```
- Посмотреть на класс `Arrays`. Там есть полезные вещи, которые могут упростить код `ArrayStorage`.
