import java.util.*;

public class Main {
    private static OrderFactory orderFactory = new OrderFactory();
    private static DiscountCalculator discountCalculator = new ClientDiscountCalculator();
    private static Scanner scanner = new Scanner(System.in);
    private static List<Receipt> receipts = new ArrayList<>();

    public static void main(String[] args) {

        CategoryManager categoryManager = CategoryManager.getInstance();

        Product product1 = new GardenItem("Секатор", 800.0, "Инструмент для обрезки веток");
        Product product2 = new GardenItem("Лопата", 1200.0, "Садовая лопата для копки");
        Product product3 = new Electronic("Кухонная лампа", 6000.0, "Кухонная светодиодная лампа");
        Product product4 = new Electronic("Настольная лампа", 5000.0, "Лампа для рабочего стола");
        Product product5 = new MobileDevays("VIVO X300", 95000.0, "Смартфон с отличной камерой");
        Product product6 = new MobileDevays("iPhone 17", 110000.0, "Флагманский смартфон");

        System.out.println("   ПРОВЕРКА СТАТУСОВ КЛИЕНТОВ   ");

        ClientOutputStrategy vipStrategy = new VipClientOutputStrategy();
        ClientOutputStrategy regularStrategy = new RegularClientOutputStrategy();

        ClientStatusChecker vipChecker = status -> status == ClientStatus.VIP || status == ClientStatus.GOLD;

        checkClientWithStrategy(ClientStatus.NEW, vipChecker, "Иван Петров", vipStrategy);
        checkClientWithStrategy(ClientStatus.REGULAR, vipChecker, "Мария Сидорова", regularStrategy);
        checkClientWithStrategy(ClientStatus.VIP, vipChecker, "Алексей Козлов", vipStrategy);
        checkClientWithStrategy(ClientStatus.GOLD, vipChecker, "Хакан Эмрах Аган", vipStrategy);

        System.out.println("\n   Создание заказов для ВСЕХ товаров   ");

        Order order1 = orderFactory.createOrder(product1, ClientStatus.VIP, discountCalculator);
        Order order2 = orderFactory.createOrder(product2, ClientStatus.REGULAR, discountCalculator);
        Order order3 = orderFactory.createOrder(product3, ClientStatus.GOLD, discountCalculator);
        Order order4 = orderFactory.createOrder(product4, ClientStatus.NEW, discountCalculator);
        Order order5 = orderFactory.createOrder(product5, ClientStatus.GOLD, discountCalculator);
        Order order6 = orderFactory.createOrder(product6, ClientStatus.VIP, discountCalculator);

        System.out.println("\n   ВСЕ СОЗДАННЫЕ ЗАКАЗЫ   ");
        order1.displayOrderInfo();
        order2.displayOrderInfo();
        order3.displayOrderInfo();
        order4.displayOrderInfo();
        order5.displayOrderInfo();
        order6.displayOrderInfo();

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
            System.out.println("10. Купить товар (создать чек)");
            System.out.println("11. Показать историю чеков");
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
                    Catalog.getInstance().printStats();
                    break;

                case 8:
                    findFirstProductDemo(scanner);
                    break;

                case 9:
                    System.out.println("\n   СОЗДАННЫЕ ЗАКАЗЫ   ");
                    OrderFactory.displayAllOrders();
                    break;

                case 10:
                    purchaseProductMenu();
                    break;

                case 11:
                    displayReceiptsHistory();
                    break;

                case 0:
                    isRunning = false;
                    System.out.println("До свидания!");
                    break;

                default:
                    System.out.println("Ошибка: Пункта " + choice + " не существует.");
                    break;
            }
        }
        scanner.close();
    }

    private static void purchaseProductMenu() {
        List<Order> orders = OrderFactory.getOrders();

        if (orders.isEmpty()) {
            System.out.println("\n Нет созданных заказов! Сначала создайте заказы.");
            return;
        }

        System.out.println("\n   ПОКУПКА ТОВАРА   ");
        System.out.println("Выберите статус клиента для расчета скидки:");

        ClientStatus[] statuses = ClientStatus.values();
        for (int i = 0; i < statuses.length; i++) {
            System.out.println(i + ". " + statuses[i].getTitle() + " (скидка: " + statuses[i].getDiscount() + "%)");
        }
        System.out.print("Ваш выбор: ");

        int statusChoice = scanner.nextInt();
        if (statusChoice < 0 || statusChoice >= statuses.length) {
            System.out.println(" Неверный выбор статуса!");
            return;
        }
        ClientStatus selectedStatus = statuses[statusChoice];

        System.out.println("\n Доступные заказы:");
        boolean hasUnpaidOrders = false;
        for (int i = 0; i < orders.size(); i++) {
            Order order = orders.get(i);
            String paidStatus = order.isPaid() ? " ОПЛАЧЕН" : " НЕ ОПЛАЧЕН";
            String discountInfo = " (скидка: " + order.getClientStatus().getDiscount() + "%)";
            System.out.println("[" + i + "] " + order.getProduct().get_Title() +
                    " - " + order.getFinalPrice() + " руб." + discountInfo +
                    " - " + paidStatus);
            if (!order.isPaid()) {
                hasUnpaidOrders = true;
            }
        }

        if (!hasUnpaidOrders) {
            System.out.println("\n Все заказы уже оплачены! Нечего покупать.");
            return;
        }

        System.out.print("\nВведите номер заказа для покупки (или -1 для выхода): ");
        int orderIndex = scanner.nextInt();

        if (orderIndex == -1) {
            return;
        }

        if (orderIndex >= 0 && orderIndex < orders.size()) {
            Order selectedOrder = orders.get(orderIndex);

            if (selectedOrder.isPaid()) {
                System.out.println(" Этот заказ уже оплачен!");
                return;
            }

            double discountAmount = discountCalculator.calculateDiscount(selectedStatus, selectedOrder.getOriginalPrice());
            double newFinalPrice = selectedOrder.getOriginalPrice() - discountAmount;

            System.out.println("\n ПОДТВЕРЖДЕНИЕ ПОКУПКИ:");
            System.out.println("  Товар: " + selectedOrder.getProduct().get_Title());
            System.out.println("  Исходная цена: " + selectedOrder.getOriginalPrice() + " руб.");
            System.out.println("  Статус клиента: " + selectedStatus.getTitle());
            System.out.println("  Скидка: " + selectedStatus.getDiscount() + "%");
            System.out.println("  Сумма скидки: " + discountAmount + " руб.");
            System.out.println("  Цена со скидкой: " + newFinalPrice + " руб.");
            System.out.print("\n  Введите сумму для оплаты: ");
            double amount = scanner.nextDouble();

            if (amount >= newFinalPrice) {
                selectedOrder.pay(amount);

                List<Order> purchasedOrders = new ArrayList<>();
                purchasedOrders.add(selectedOrder);
                double totalDiscount = discountCalculator.calculateDiscount(selectedStatus, selectedOrder.getOriginalPrice());

                Receipt newReceipt = new Receipt(
                        UUID.randomUUID().toString(),
                        new Date(),
                        purchasedOrders,
                        totalDiscount,
                        selectedStatus
                );

                receipts.add(newReceipt);
                newReceipt.displayReceipt();

                double change = amount - newFinalPrice;
                if (change > 0) {
                    System.out.printf("\n Сдача: %.2f руб.\n", change);
                }
                System.out.println("\n ПОКУПКА УСПЕШНО ЗАВЕРШЕНА!");
                System.out.println(" Номер чека: " + newReceipt.getReceiptId());
            } else {
                System.out.println(" Недостаточно средств! Нужно: " + newFinalPrice + " руб.");
                System.out.println("   Не хватает: " + (newFinalPrice - amount) + " руб.");
            }
        } else {
            System.out.println(" Неверный номер заказа!");
        }
    }

    private static void displayReceiptsHistory() {
        if (receipts.isEmpty()) {
            System.out.println("\n История покупок пуста");
            return;
        }

        System.out.println("\n   ИСТОРИЯ ПОКУПОК   ");
        System.out.println("Всего чеков: " + receipts.size());
        for (int i = 0; i < receipts.size(); i++) {
            System.out.println("\n[" + i + "]");
            receipts.get(i).displayReceipt();
        }
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
// Order.java - FactoryPattern                   - Создан класс заказа - Что делает: Создает объекты заказов без прямого вызова конструктора.
// OrderFactory.java - FactoryPattern            - Создана фабрика заказов - Что делает: Создает объекты заказов без прямого вызова конструктора.
// ClientOutputStrategy.java - StrategyPattern   - Стратегии вывода клиентов - Что делает: Позволяет выбирать алгоритм вывода информации о клиенте во время выполнения.
// DiscountCalculator.java - DependencyInjection - Интерфейс и реализация скидок - Что делает: Объекты получают свои зависимости извне, а не создают их сами.
// Receipt.java - ImmutableObject	             - Чек покупки - Что делает: Объект чека нельзя изменить после создания.
// Category.java - Singleton                     - Добавлен CategoryManager - Что делает: Гарантирует, что существует только один экземпляр класса во всей программе.
// Product.java	                                 - Добавлен clientStatus
// Main.java                                     - Интеграция всех Pattern
