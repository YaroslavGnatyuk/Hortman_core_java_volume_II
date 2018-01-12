package gnatyuk.java.core.read_excel;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.Collectors;

public class CategoriesGenerator implements Callable<AtomicInteger> {
    private AtomicInteger counter;
    private List<Product> products;

    public CategoriesGenerator(List<Product> products, AtomicInteger counter) {
        this.counter = counter;
        this.products = products;
    }

    private void getCategories(){
        WebDriver driver = null;
        try {
            driver = WebDriverUtils.getChromeWebDriver();
            driver.manage().window().setSize(new Dimension(0, 0));
            for (; counter.get() < products.size(); ) {
                Product product = products.get(counter.get());

                driver.get(product.getLinkToMicrotron().toString());
                System.out.println(counter.get() + Thread.currentThread().getName());
                Thread.sleep(10);
                if (driver.findElement(By.id("content_product")).isDisplayed()) {
                    Document page = Jsoup.parse(driver.getPageSource());
                    product.setCategories(getCategories(page));
                } else {
                    driver.navigate().refresh();
                    Thread.sleep(500);
                    Document page = Jsoup.parse(driver.getPageSource());
                    product.setCategories(getCategories(page));
                }
                counter.getAndIncrement();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            if (driver != null) {
                driver.close();
                driver.quit();
            }
        }
    }

    @Override
    public AtomicInteger call() {
        getCategories();
        return counter;
    }

    private static List<String> getCategories(Document page) {
        AtomicInteger count = new AtomicInteger(0);
        List<String> categories = page.body()
                .getElementById("content_path")
                .getElementsByClass("path")
                .stream()
                .filter(e -> count.incrementAndGet() > 2 && count.get() < 6)
                .map(Element::text)
                .collect(Collectors.toList());
        Collections.reverse(categories);
        return categories;
    }
}
