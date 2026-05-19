import java.util.*;

public class Main {
    public static void main(String[] args) {

        CategoryManager categoryManager = CategoryManager.getInstance();

        OrderFactory orderFactory = new OrderFactory();

        Product product1 = new GardenItem("Секатор", 800.0, "Инструмент для обрезки веток");
        Product product2 = new GardenItem("Лопата", 1200.0, "Садовая лопата для копки");
        Product product3 = new Electronic("Кухонная лампа", 6000.0, "Кухонная светодиодная лампа");
        Product product4 = new Electronic("Настольная лампа", 5000.0, "Лампа для рабочего стола");
        Product product5 = new MobileDevays("VIVO X300", 95000.0, "Смартфон с отличной камерой");
        Product product6 = new MobileDevays("iPhone 17", 110000.0, "Флагманский смартфон");

        DiscountCalculator discountCalculator = new ClientDiscountCalculator();

        System.out.println("   ПРОВЕРКА СТАТУСОВ КЛИЕНТОВ   ");

        ClientOutputStrategy vipStrategy = new VipClientOutputStrategy();
        ClientOutputStrategy regularStrategy = new RegularClientOutputStrategy();

        ClientStatusChecker vipChecker = status -> status == ClientStatus.VIP || status == ClientStatus.GOLD;

        checkClientWithStrategy(ClientStatus.NEW, vipChecker, "Иван Петров", vipStrategy);
        checkClientWithStrategy(ClientStatus.REGULAR, vipChecker, "Мария Сидорова", regularStrategy);
        checkClientWithStrategy(ClientStatus.VIP, vipChecker, "Алексей Козлов", vipStrategy);
        checkClientWithStrategy(ClientStatus.GOLD, vipChecker, "Хакан Эмрах Аган", vipStrategy);

        System.out.println("\n   Создание заказов   ");

        Order order1 = orderFactory.createOrder(product1, ClientStatus.VIP, discountCalculator);
        Order order2 = orderFactory.createOrder(product5, ClientStatus.GOLD, discountCalculator);
        Order order3 = orderFactory.createOrder(product3, ClientStatus.REGULAR, discountCalculator);

        order1.displayOrderInfo();
        order2.displayOrderInfo();
        order3.displayOrderInfo();

        System.out.println("\n   ЧЕК ПОКУПКИ   ");
        Receipt receipt = new Receipt(
                UUID.randomUUID().toString(),
                new Date(),
                List.of(order1, order2),
                discountCalculator.calculateDiscount(ClientStatus.VIP, order1.getFinalPrice()) +
                        discountCalculator.calculateDiscount(ClientStatus.GOLD, order2.getFinalPrice())
        );

        receipt.displayReceipt();

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
            System.out.println("8. Найти первый товар по критерию");
            System.out.println("9. Показать созданные заказы");
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
                    Product.sortByName();
                    Product.showAllProducts();
                    break;

                case 2:
                    Product.sortByPrice();
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
                    List<Product> found = Product.findByName(searchTerm);

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

                case 8:
                    findFirstProductDemo(scanner);
                    break;

                case 9:
                    System.out.println("\n   СОЗДАННЫЕ ЗАКАЗЫ   ");
                    OrderFactory.displayAllOrders();
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

    private static void checkClientWithStrategy(ClientStatus status, ClientStatusChecker checker,
                                                String clientName, ClientOutputStrategy strategy) {
        if (checker.checkStatus(status)) {
            strategy.output(clientName, status);
        } else {
            System.out.println(clientName + " не подходит под указанный критерий");
        }
    }

    private static void findFirstProductDemo(Scanner scanner) {
        System.out.println("\n   ПОИСК ПЕРВОГО ТОВАРА   ");
        System.out.println("Выберите критерий поиска:");
        System.out.println("1. Первый товар дороже указанной цены");
        System.out.println("2. Первый товар из категории Electronic");
        System.out.println("3. Первый товар с названием, начинающимся на букву");
        System.out.print("Ваш выбор: ");

        int choice = scanner.nextInt();
        scanner.nextLine();

        switch (choice) {
            case 1:
                System.out.print("Введите минимальную цену: ");
                double minPrice = scanner.nextDouble();
                Product.findFirstMoreExpensiveThan(minPrice)
                        .ifPresentOrElse(
                                p -> System.out.println("Найден первый товар дороже " + minPrice +
                                        " руб.: " + p.get_Title() + " (" + p.get_Price() + " руб.)"),
                                () -> System.out.println("Товаров дороже " + minPrice + " руб. не найдено")
                        );
                break;

            case 2:
                Product.findFirstByType(Electronic.class)
                        .ifPresentOrElse(
                                p -> System.out.println("Найден первый электронный товар: " +
                                        p.get_Title() + " (" + p.get_Price() + " руб.)"),
                                () -> System.out.println("Электронные товары не найдены")
                        );
                break;

            case 3:
                System.out.print("Введите букву: ");
                String letter = scanner.nextLine();
                Product.findFirstStartingWith(letter)
                        .ifPresentOrElse(
                                p -> System.out.println("Найден первый товар на букву '" + letter +
                                        "': " + p.get_Title() + " (" + p.get_Price() + " руб.)"),
                                () -> System.out.println("Товаров на букву '" + letter + "' не найдено")
                        );
                break;

            default:
                System.out.println("Неверный выбор!");
        }
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


//TODO Задача ОнлайнШопинг,
// -----------------------
// Этап 8 +
// 8.1) Добавляем StreamAPI.
// 8.2) Расширяете пользовательское меню.
// 8.3) Просмотр списка заказов


//TODO Задача ОнлайнШопинг,
// -----------------------
// Этап 9 +
// 9.1) Фильтрациюя при помощи StreamAPI (first find).
// 9.2) Использовать SOLID - все принципы SOLID во всем коде.


//TODO Задача ОнлайнШопинг,
// -----------------------
// Этап 10
// 10.1) Применяете FactoryPattern используем для создания заказов товаров.
// 10.2) StrategyPattern используем, делаем переключатель для вывода клиента.
// 10.3) Singleton - фиксировать один объект во всем коде (Category).
// 10.4) DependencyInjection - на уровне интерфейса прописывает класс.
// 10.5) Immutable object - использовать для создания чека покупки (создать для этого новый объект, который будет сохранять покупки).
// ---Отметки---
// Памятка о созданных классах и измененых классах чтобы я не путался и не путал другие классы
// Order.java - FactoryPattern                   - Создан класс заказа
// OrderFactory.java - FactoryPattern            - Создана фабрика заказов
// ClientOutputStrategy.java - StrategyPattern   - Стратегии вывода клиентов
// DiscountCalculator.java - DependencyInjection - Интерфейс и реализация скидок
// Receipt.java - ImmutableObject	             - Чек покупки
// Category.java - Singleton                     - Добавлен CategoryManager
// Product.java	                                 - Добавлен clientStatus
// Main.java                                     - Интеграция всех Pattern
