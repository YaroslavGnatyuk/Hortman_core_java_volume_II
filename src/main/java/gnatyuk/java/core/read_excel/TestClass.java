package gnatyuk.java.core.read_excel;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.atomic.AtomicInteger;

public class TestClass {
    public static void main(String[] args) {
        //todo Fix bug with wrong categories for product!!!
        String pathAtWindows = "D:\\mt_all13.11.2017.xls";
        String pathToNewExcelWindows = "D:\\new_mt_all13.11.2017.xls";

        String pathAtLinux = "/win_media/mt_all13.11.2017.xls";

        ReadExcel readExcel = new ReadExcel(pathAtWindows);
        List<Product> products = readExcel.generateProducts();
        AtomicInteger counter = new AtomicInteger(0);

        int cores = Runtime.getRuntime().availableProcessors();
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        List<Future<AtomicInteger>> futures = new ArrayList<>();
        for (int i = 0; i < cores; i++) {
            futures.add(executorService.submit(new CategoriesGenerator(products, counter)));
        }

        for (Future f : futures) {
            try {
                f.get();
                System.out.println("complete");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }

        executorService.shutdown();
        /*products = products.stream()
                .filter(e -> !e.getCategories().isEmpty() || e.getCategories() != null)
                .collect(Collectors.toList());*/
        if (executorService.isShutdown()) {
            CreateExcel excel = new CreateExcel(pathToNewExcelWindows, products);
            excel.createExcelFile();
        }
    }
}
