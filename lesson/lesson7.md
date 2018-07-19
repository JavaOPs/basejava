
# Седьмое занятие

## [Разбор Домашнего Задания-6](lesson6.md#Домашнее-задание)

## Параметризация. Стирание типов.
- <a href="http://www.quizful.net/post/java-generics-tutorial">Дженерики (Java, обучающая статья)</a>
- <a href="http://developer.alexanderklimov.ru/android/java/generic.php">Обобщения (Generic)</a>
- <a href="http://docs.oracle.com/javase/tutorial/java/generics/restrictions.html">Ограничения.</a>

## Логирование
-  <a href="https://logging.apache.org/">Log4J (Apache logging)</a>
-  <a href="http://www.vogella.com/tutorials/Logging/article.html">Java Logging API - Tutorial</a>
- <a href="https://habrahabr.ru/post/130195/">Логирование в Java / quick start</a>
- <a href="http://skipy.ru/useful/logging.html">Ведение лога приложения</a>
- <a href="http://habrahabr.ru/post/113145/">Java Logging: история кошмара</a>

## Синглетон, Enum
- <a href="https://ru.wikipedia.org/wiki/Одиночка_(шаблон_проектирования)">Одиночка (шаблон проектирования)</a>
- <a href="http://easy-code.ru/lesson/enum-types-java">Перечисляемые типы (enum) в Java</a>

## Домашнее задание: 
<a href="https://ru.wikipedia.org/wiki/Доменный_объект">Доменный объект</a>

Cделать объектную модель резюме (диаграмма и классы). <a href="http://u-rise.com/teacherofjava.pdf">Образец резюме</a> (делаем упрощенно)
  - Делать только классы, включаемые в Resume. Resume - главный класс. В него все включается (композиция - строгий вид агрегации).
  - Схожие по структуре и функциональности сущности делаем одним классом.
  - Модель упрощаем для хранения только необходимой информации для вывода/ редактирования резюме.
  - В модели резюме должны быть представлены контакты и следующие разделы:
    - PERSONAL("Личные качества")
    - OBJECTIVE("Позиция")
    - ACHIEVEMENT("Достижения")
    - QUALIFICATIONS("Квалификация")
    - EXPERIENCE("Опыт работы")
    - EDUCATION("Образование")
  - В секциях Достижения и Квалификация хранить список строк
  - Учесть в классах модели, что обработка резюме (вывод в html, сохранение, чтение) будет сделано следующим образом:
обработка `fullName`, цикл обработки по контактам, цикл обработки по секциям (для секций использовать полиморфизм, как для фигур: круг, квадрат..).
При добавлении/удалении новых видов контактов (например домашний телефон) или разделов изменения в коде (и БД) должны быть минимальны.

#### Инструменты для рисования:

- <a href="http://stackoverflow.com/questions/8942751/use-intellij-to-generate-class-diagram#26926334">Generate class diagram in IntelliJ IDEA</a> (<a href="https://www.jetbrains.com/help/idea/2016.1/working-with-diagrams.html?origin=old_help">Help: working with Diagrams</a>)
- Нарисовать и сфотографировать
- <a href="http://www.draw.io">Online: www.draw.io</a>
- <a href="https://www.yworks.com/">yEd - Graph Editor</a>
