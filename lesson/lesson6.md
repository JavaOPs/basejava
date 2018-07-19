
# Шестое занятие

## Разбор Домашнего Задания-5
- <a href="https://habrahabr.ru/post/104231/">Преобразования Integer и int</a>

## Iterator / Iterable. Вложенные, внутренние, локальные и анонимные классы.
- <a href="http://www.javenue.info/post/101">Iterator / Iterable</a>.
- <a href="http://easy-code.ru/lesson/java-nested-classes">Вложенные и внутренние классы</a>. Примеры в Collections API: Arrays.asList, ArrayList.iterator
- <a href="http://easy-code.ru/lesson/local-anonymous-nested-classes-java">Локальные и анонимные классы</a>. Comparator

## Новое в Java 8.
- <a href="http://devcolibri.com/4137#t2">Lambda выражения. Default методы для Interface. </a>
- <a href="http://devcolibri.com/4274#t9">Встроенные функциональные интерфейсы.</a>

## Домашнее задание
- Сделать рефакторинг тестов: `saveOverflow` должно быть только для Array реализаций.
- Рефакторинг: в конструктор Resume добавить второй параметр `fullName`
- Сделать рефакторинг всех реализаций `Storage`: заменить метод `Resume[] getAll()` на `List<Resume> getAllSorted()`
- Реализовать до конца `MapUuidStorage`. Подумать что еще может быть search key в реализации на основе Map.
