package console;

import model.Furniture;
import com.example.service.FurnitureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class ConsoleApp implements CommandLineRunner {

    @Autowired
    private FurnitureService service;

    @Override
    public void run(String... args) throws Exception {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Меню");
            System.out.println("1. Добавить мебель");
            System.out.println("2. Показать все записи");
            System.out.println("3. Обновить запись");
            System.out.println("4. Удалить запись");
            System.out.println("5. Поиск по цене (дороже введённой)");
            System.out.println("6. Поиск по типу");
            System.out.println("0. Выход");
            System.out.print("Выберите действие: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // очистка буфера

            switch (choice) {
                case 1 -> {
                    System.out.print("Введите название: ");
                    String name = scanner.nextLine();
                    System.out.print("Введите тип: ");
                    String type = scanner.nextLine();
                    System.out.print("Введите цену: ");
                    double price = scanner.nextDouble();
                    Furniture furniture = new Furniture(null, name, type, price);
                    service.addFurniture(furniture);
                }
                case 2 -> service.getAllFurniture().forEach(System.out::println);
                case 3 -> {
                    System.out.print("Введите ID: ");
                    int id = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Новое название: ");
                    String name = scanner.nextLine();
                    System.out.print("Новый тип: ");
                    String type = scanner.nextLine();
                    System.out.print("Новая цена: ");
                    double price = scanner.nextDouble();
                    service.updateFurniture(id, name, type, price);
                }
                case 4 -> {
                    System.out.print("Введите ID: ");
                    int id = scanner.nextInt();
                    service.deleteFurnitureById(id);
                }
                case 5 -> {
                    System.out.print("Введите минимальную цену: ");
                    double minPrice = scanner.nextDouble();
                    service.searchByPrice(minPrice).forEach(System.out::println);
                }
                case 6 -> {
                    System.out.print("Введите тип: ");
                    String type = scanner.nextLine();
                    service.searchByType(type).forEach(System.out::println);
                }
                case 0 -> {
                    System.out.println("Выход...");
                    return;
                }
                default -> System.out.println("Неверный выбор");
            }
        }
    }
}
