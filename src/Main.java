public class Main {
    public static void main(String[] args) {
// TODO catalog - Сделать новый каталог для магазина, 1 поле - ID будет идти в формате int, 2 Title - в формате String,
//  3 Price - в формате double. Сделать полную инкапсуляцию и конструктор, в каталоге создаем 2 объекта(каталог1/2)

        Catalog catalog = new Catalog("Арбуз", 123, 65.0);
        System.out.println("ID - " + catalog.getID() + ". Название продукта - " + catalog.getTitle() + ". Стоимость - " + catalog.getPrise());

        Catalog catalog1 = new Catalog("Дыня", 456, 50.0);
        System.out.println("ID - " + catalog1.getID() + ". Название продукта - " + catalog1.getTitle() + ". Стоимость - " + catalog1.getPrise());
    }
}
