/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    Resume[] storage = new Resume[10000];

    void clear() {
        for (int i = 0; i < storage.length; i++) {

            //Дошли до конца заполненных данных - прервем цикл
            if (storage[i] == null){
                break;
            }

            storage[i] = null;
        }
    }

    void save(Resume r) {

        //Нужно найти свободную позицию если таковой не нашлось то будет Исключение.
        for (int i = 0; i < storage.length; i++) {
            if (storage[i] == null){
                storage[i] = r;
                return;
            }
        }
        throw new ArrayIndexOutOfBoundsException("Maximum storage value reached!");
    }

    Resume get(String uuid) {

        Resume findResume = null;
        for (int i = 0; i < storage.length; i++) {
            if (storage[i] != null) {
                if (storage[i].uuid.equals(uuid)) {
                    findResume = storage[i];
                    break;
                }
            }
        }
        return findResume;
    }

    void delete(String uuid) {

        //Поиск по всем ЗАПОЛНЕННЫМ элементам - используем size
        for (int i = 0; i < size(); i++) {

            if (storage[i].uuid.equals(uuid)) {

                //Заполним пустоту последним существующим элементом
                storage[i] = storage[size()-1];

                //И затрем значение послднего - т.к. переместили его в позицию удаленного элемента
                storage[size()-1] = null;
            }

        }
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    Resume[] getAll() {
        Resume[] resumes = new Resume[size()];

        for (int i = 0; i < resumes.length; i++) {
            resumes[i] = storage[i];
        }

        return resumes;
    }

    int size() {

        for (int i = 0; i < storage.length; i++) {
            if (storage[i] == null){
                return i;
            }
        }
        return storage.length;
    }
}
