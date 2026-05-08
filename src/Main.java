import java.util.*;

public class Main {
    public static void main(String[] args) {

        new GardenItem("Секатор", 800.0, "Инструмент для обрезки веток");
        new GardenItem("Лопата", 1200.0, "Садовая лопата для копки");
        new Electronic("Кухонная лампа", 6000.0, "Кухонная светодиодная лампа");
        new Electronic("Настольная лампа", 5000.0, "Лампа для рабочего стола");
        new MobileDevays("VIVO X300", 95000.0, "Смартфон с отличной камерой");
        new MobileDevays("iPhone 17", 110000.0, "Флагманский смартфон");

        System.out.println("   ПРОВЕРКА СТАТУСОВ КЛИЕНТОВ   ");

        ClientStatusChecker vipChecker = status -> status == ClientStatus.VIP || status == ClientStatus.GOLD;

        Product.checkClientStatus(ClientStatus.NEW, vipChecker, "Иван Петров");
        Product.checkClientStatus(ClientStatus.REGULAR, vipChecker, "Мария Сидорова");
        Product.checkClientStatus(ClientStatus.VIP, vipChecker, "Алексей Козлов");
        Product.checkClientStatus(ClientStatus.GOLD, vipChecker, "Хакан Эмрах Аган");

        ClientStatusChecker newClientChecker = status -> status == ClientStatus.NEW;
        System.out.println("\n   Поиск новых клиентов   ");
        Product.checkClientStatus(ClientStatus.NEW, newClientChecker, "Дмитрий Новиков");
        Product.checkClientStatus(ClientStatus.REGULAR, newClientChecker, "Ольга Смирнова");

        System.out.println("\n   СТАТУСЫ   ");

        OrderStatus[] statuses = OrderStatus.values();
        System.out.println("Доступные статусы заказов:");
        Arrays.stream(statuses).forEach(s -> System.out.println("  - " + s));

        Product.showProductsByPriceRange(5000, 100000);

        Product.showProductsByType(MobileDevays.class);

        Product.showStatistics();

        List<Product> expensiveProducts = Product.productList.stream()
                .filter(p -> p.get_Price() > 10000)
                .sorted(Comparator.comparing(Product::get_Price).reversed())
                .toList();

        System.out.println("\nДорогие товары (>10000 руб.), отсортированные по убыванию:");
        expensiveProducts.forEach(p -> System.out.println("  - " + p.get_Title() + ": " + p.get_Price() + " руб."));

        Scanner scanner = new Scanner(System.in);
        boolean isRunning = true;

        while (isRunning) {
            System.out.println("\n   ГЛАВНОЕ МЕНЮ   ");
            System.out.println("1. Список товаров (сортировка по названию)");
            System.out.println("2. Список товаров (сортировка по цене)");
            System.out.println("3. Сравнить товары и выбрать лучший");
            System.out.println("4. Поиск товаров по названию");
            System.out.println("5. Обновить статус заказа");
            System.out.println("6. Фильтр товаров по цене");
            System.out.println("7. Показать статистику");
            System.out.println("0. Завершить работу");
            System.out.print("Выберите действие: ");

            if (!scanner.hasNextInt()) {
                System.out.println("Ошибка: Введите цифру из меню!");
                scanner.next();
                continue;
            }

            int choice = scanner.nextInt();
            scanner.nextLine();

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
                    System.out.print("\nВведите номер первого товара: ");
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

                case 4:
                    System.out.print("Введите название товара для поиска: ");
                    String searchTerm = scanner.nextLine();
                    List<Product> found = Product.productList.stream()
                            .filter(p -> p.get_Title().toLowerCase().contains(searchTerm.toLowerCase()))
                            .toList();

                    System.out.println("\nРезультаты поиска:");
                    if (found.isEmpty()) {
                        System.out.println("Товары не найдены");
                    } else {
                        found.forEach(p -> System.out.println("  - " + p.get_Title() + " (" + p.get_Price() + " руб.)"));
                    }
                    break;

                case 5:
                    Product.showAllProducts();
                    System.out.print("Введите номер товара: ");
                    int prodId = scanner.nextInt();
                    scanner.nextLine();
                    Product product = Product.getProductByIndex(prodId);
                    if (product != null) {
                        System.out.println("Текущий статус: " + product.getOrderStatus());
                        System.out.println("Выберите новый статус:");
                        OrderStatus[] ordStatuses = OrderStatus.values();
                        for (int i = 0; i < ordStatuses.length; i++) {
                            System.out.println(i + ". " + ordStatuses[i]);
                        }
                        System.out.print("Ваш выбор: ");
                        int statusChoice = scanner.nextInt();
                        if (statusChoice >= 0 && statusChoice < ordStatuses.length) {
                            product.updateOrderStatus(ordStatuses[statusChoice]);
                        } else {
                            System.out.println("Неверный выбор!");
                        }
                    }
                    break;

                case 6:
                    System.out.print("Введите минимальную цену: ");
                    double minPrice = scanner.nextDouble();
                    System.out.print("Введите максимальную цену: ");
                    double maxPrice = scanner.nextDouble();
                    Product.showProductsByPriceRange(minPrice, maxPrice);
                    break;

                case 7:
                    Product.showStatistics();
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
// Этап 7 +
// 7.1) Добавляем enum для создания статуса клиента, ожидание заказа и т.д.
// 7.2) Добавленные enum применяем в коде.
// 7.3) Создаем минимум один функциональный интерфейс (реализовать в main(обязательно через lambda)) (Проверка статуса клиента).
// 7.4) Реализовать пользовательский интерфейс, работаем через StreamAPI, работаем с коллекциями и масивом.