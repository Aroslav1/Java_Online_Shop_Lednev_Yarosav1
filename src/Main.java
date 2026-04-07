public class Main {
    public static void main(String[] args) {

        GardenItem shovel = new GardenItem("Крабы", "Камчатский", 1500.0);
        Electronic lamp = new Electronic("Зарядное устройство", "Настольная", 2100.0);
        MobileDevays phone = new MobileDevays("VIVO X300", "Смартфон", 95000.0);

        System.out.println("ID - " + shovel.getId() + ". Название - " + shovel.getTitle() + ". Описание - " + shovel.getDescription());
        System.out.println("ID - " + lamp.getId() + ". Название - " + lamp.getTitle() + ". Описание - " + lamp.getDescription());
        System.out.println("ID - " + phone.getId() + ". Название - " + phone.getTitle() + ". Описание - " + phone.getDescription());

        Category cat = new Category("Электроника", "Все гаджеты", 0);
        System.out.println("Категория: " + cat.getTitle() + " (ID: " + cat.getId() + ")\n");

        cat.ADD_Category();
        phone.SHOP_Category();

        System.out.println("\n--- ИТОГО ---");
        System.out.println("Количество категорий: " + Category.getCategoryCount());
        System.out.println("Количество sub категории: " + Category.getSubCategoryCount());
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


//TODO Задача ОнлайнШопинг,
// -----------------------
// Этап 3
// 3.1) Нужно создать класс Category (Он уже создан), добавить ADD_Category и SHOP_Category.
// 3.2) Потом нужно создать счетчик в Category: 1) сколько категорий, 2) сколько sub категории


