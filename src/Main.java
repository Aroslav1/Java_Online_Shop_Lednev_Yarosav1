import java.util.*;

public class Main {
    public static void main(String[] args) {

        new GardenItem("Секатор", 800.0, "Инструмент");
        new GardenItem("Лопата", 1200.0, "Садовая");
        new Electronic("Кухонная лампа", 6000.0, "Кухонная");
        new Electronic("Настольная лампа", 5000.0, "Лампа");
        new MobileDevays("VIVO X300", 95000.0, "Смартфон");
        new MobileDevays("iPhone 17", 110000.0, "Смартфон");

        Scanner scanner = new Scanner(System.in);
        boolean isRunning = true;

        while (isRunning) {
            System.out.println("\nГЛАВНОЕ МЕНЮ");
            System.out.println("1. Список товаров (сортировка по названию)");
            System.out.println("2. Список товаров (сортировка по цене)");
            System.out.println("3. Сравнить товары и выбрать лучший");
            System.out.println("0. Завершить работу");
            System.out.print("Выберите действие: ");

            if (!scanner.hasNextInt()) {
                System.out.println("Ошибка: Введите цифру из меню!");
                scanner.next();
                continue;
            }

            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    Collections.sort(Product.productList);
                    Product.showAllProducts();
                    break;

                case 2:
                    Product.productList.sort(Product.PriceComparator);
                    Product.showAllProducts();
                    break;

                case 3:
                    Product.showAllProducts();
                    System.out.println("\nСравнение товаров одного типа");
                    System.out.print("Введите номер первого товара: ");
                    int id1 = scanner.nextInt();
                    System.out.print("Введите номер второго товара: ");
                    int id2 = scanner.nextInt();

                    Product p1 = Product.getProductByIndex(id1);
                    Product p2 = Product.getProductByIndex(id2);

                    if (p1 != null && p2 != null) {
                        Product.safeCompare(p1, p2);
                    } else {
                        System.out.println("Ошибка: Товар с таким номером не существует!");
                    }
                    break;

                case 0:
                    isRunning = false;
                    break;

                default:
                    System.out.println("Ошибка: Пункта " + choice + " не существует.");
                    break;
            }
        }
        scanner.close();
    }
}



//TODO Задача ОнлайнШопинг,
// -----------------------
// Этап 2 +
// 2-1) Класс Product - "ДОБАВИТЬ" должно быть поле description - описание.
// Product class должна быть обстракцией.
// 2-2) Category - "ДОБАВИТЬ" 1 - id, 2 - title, 3 - description.
// 2-3) автогенерация id, каждый новый шаг - новое id которое генерируется самостоятельно.
// 2-4) наследники Electronic, GardenItem.
// 2-5) создаем новый класс - "MobileDevays" он должен быть наследником наследником электроники - "Electronic".
// 2-6) создаем несколько объектов из новых классов в main добавляем.


//TODO Задача ОнлайнШопинг,
// -----------------------
// Этап 3 +
// 3.1) Нужно создать класс Category (Он уже создан), добавить ADD_Category и SHOP_Category.
// 3.2) Потом нужно создать счетчик в Category: 1) сколько категорий, 2) сколько sub категории.


//TODO Задача ОнлайнШопинг,
// -----------------------
// Этап 4 +
// 4.1) Надо с Category добавить абстрактный метод Show info, который мы будем переобразовывать.
// 4.2) Создать несколько разных товаров в Product в списке в виде Array list.
// 4.3) Надо в Show info чтобы вести список показать на экране.


//TODO Задача ОнлайнШопинг,
// -----------------------
// Этап 5 +
// 5.1)
// Payble - помогает объектам товаров (3 бастрактных, возвращает double - GetFinalPrice, void - pay с параметром double(amount), bool - isPaid)
// Financible - помогает объектам клиентам
// 1 - double - ChekBalance (Без параметра)
// 2 - bool - HasEnouthMoney (double amount)
// 3 - String - GetFinalsesStatus (без параметра)
// 5.2) необходимо все классы расширять при помощи Hashcode, equals, instanceof и подобного.
// 5.3) создаем сравнение объектам по цвету по названию и т.д.


//TODO Задача ОнлайнШопинг,
// -----------------------
// Этап 6 +
// 6.1) Добавить в проект сортировку категорий товаров Comparable для категорий.
// 6.2) Нужно реализовать Comparator чтобы пользователи могли сортировать по собственному желанию.
// 6.3) Создаем пользовательское меню, критерии сортировки.
// 6.4) Сравнение товаров выбирают товар и нужно сравнить, нельзя сравнивать телефон с цветком поэтому нужна безопастность.

//TODO Задача ОнлайнШопинг,
// -----------------------
// Этап 7 (Следующая пара)