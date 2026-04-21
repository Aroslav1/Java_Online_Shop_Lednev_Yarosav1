public class Main {
    public static void main(String[] args) {
        GardenItem shovel1 = new GardenItem("Лопата", 1200.0, "Садовая");
        Electronic lamp = new Electronic("Лампа", 25000.0, "Настольная");
        MobileDevays phone = new MobileDevays("VIVO X300", 95000.0, "Смартфон");

        GardenItem shovel2 = new GardenItem("Лопата", 1200.0, "Садовая");

        System.out.println("Проверка сравнения объектов");
        if (shovel1.equals(shovel2)) {
            System.out.println("Результат: Лопата 1 и Лопата 2 идентичны по названию и цене.");
        } else {
            System.out.println("Результат: Товары разные.");
        }

        System.out.println("\nПроверка оплаты");
        System.out.println("Статус оплаты телефона до: " + (phone.isPaid() ? "Оплачен" : "Не оплачен"));

        double payment = 95000.0;
        phone.pay(payment);

        System.out.println("Статус оплаты телефона после: " + (phone.isPaid() ? "Оплачен" : "Не оплачен"));

        Product.showAllProducts();
        Catalog.printStats();
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


//TODO Задача ОнлайнШопинг,
// -----------------------
// Этап 5
// 5.1)
// Payble - помогает объектам товаров (3 бастрактных, возвращает double - GetFinalPrice, void - pay с параметром double(amount), bool - isPaid)
// Financible - помогает объектам клиентам
// 1 - double - ChekBalance (Без параметра)
// 2 - bool - HasEnouthMoney (double amount)
// 3 - String - GetFinalsesStatus (без параметра)
// 5.2) необходимо все классы расширять при помощи Hashcode, equals, instanceof и подобного.
// 5.3) создаем сравнение объектам по цвету по названию и т.д.