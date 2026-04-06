public class Main {
    public static void main(String[] args) {

        GardenItem shovel = new GardenItem("VIVO X300", "Телефон", 1500.0);
        Electronic lamp = new Electronic("Лампа", "Настольная", 2100.0);
        MobileDevays phone = new MobileDevays("Лопата", "Садовый инвентарь", 95000.0);

        System.out.println("ID - " + shovel.getId() + ". Название - " + shovel.getTitle() + ". Описание - " + shovel.getDescription());
        System.out.println("ID - " + lamp.getId() + ". Название - " + lamp.getTitle() + ". Описание - " + lamp.getDescription());
        System.out.println("ID - " + phone.getId() + ". Название - " + phone.getTitle() + ". Описание - " + phone.getDescription());

        Category cat = new Category("Электроника", "Все гаджеты");
        System.out.println("Категория: " + cat.getTitle() + " (ID: " + cat.getId() + ")");
    }
}


//TODO Задача ОнлайнШопинг,
// -----------------------
// Этап 2
// 2-1) Класс Product - "ДОБАВИТЬ" должно быть поле description - описание.
// Product class должна быть обстракцией.
// 2-2) Category - "ДОБАВИТЬ" 1 - id, 2 - title, 3 - description.
// 2-3) автогенерация id, каждый новый шаг - новое id которое генерируется самостоятельно.
// 2-4) наследники Electronic, GardenItem.
// 2-5) создаем новый класс - "MobileDevays" он должен быть наследником наследником электроники - "Electronic".
// 2-6) создаем несколько объектов из новых классов в main добавляем.