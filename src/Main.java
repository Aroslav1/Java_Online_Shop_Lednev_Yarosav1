public class Main {
    public static void main(String[] args) {
        Product shop = new Product();

        GardenItem shovel = new GardenItem("Лопата", "Садовая", 1500.0);
        Electronic lamp = new Electronic("Лампа", "Настольная", 2100.0);
        MobileDevays phone = new MobileDevays("VIVO X300", "Смартфон", 95000.0);

        shovel.ADD_Category();
        lamp.ADD_Category();
        phone.ADD_Category();

        System.out.println();

        shop.addProduct(shovel);
        shop.addProduct(lamp);
        shop.addProduct(phone);

        shop.showAllProducts();

        System.out.println("\nИТОГО");
        System.out.println("Общее количество созданных подкатегорий: " + Category.getSubCategoryCount());
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
// 3.2) Потом нужно создать счетчик в Category: 1) сколько категорий, 2) сколько sub категории.


//TODO Задача ОнлайнШопинг,
// -----------------------
// Этап 4
// 4.1) Надо с Category добавить абстрактный метод Show info, который мы будем переобразовывать.
// 4.2) Создать несколько разных товаров в Product в списке в виде Array list.
// 4.3) Надо в Show info чтобы вести список показать на экране.


