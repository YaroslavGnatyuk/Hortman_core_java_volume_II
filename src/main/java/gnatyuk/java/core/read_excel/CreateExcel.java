package gnatyuk.java.core.read_excel;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

public class CreateExcel {
    private String path;
    private List<Product> products;

    public CreateExcel() {
    }

    public CreateExcel(List<Product> products) {
        this.products = products;
    }

    public CreateExcel(String path, List<Product> products) {
        this.path = path;
        this.products = products;
    }

    public void createExcelFile() {
        Workbook workbook = new HSSFWorkbook();
        Sheet dataTypeSheet = workbook.createSheet();

        int countRow = 0;
        for (Product product : products) {
            if (product.isContainNullValue()) {
                continue;
            }

            Row row = dataTypeSheet.createRow(countRow++);

            for (int i = 0; i < 3; i++) {
                row.createCell(i).setCellValue("");
            }

            for (int i = 0 ,k = 2; i < product.getCategories().size(); i++, k--) {
                System.out.println(product.toString());
                row.getCell(k).setCellValue(product.getCategories().get(i));
            }

            Cell idCell = row.createCell(3);
            idCell.setCellValue(product.getId());

            Cell productNameCell = row.createCell(4);
            productNameCell.setCellValue(product.getProductName());

            Cell priceCell = row.createCell(5);
            priceCell.setCellValue(product.getPrice());

            Cell linkToMicrotronCell = row.createCell(6);
            linkToMicrotronCell.setCellValue(product.getLinkToMicrotron().toString());

            Cell linkToDescriptionCell = row.createCell(7);
            linkToDescriptionCell.setCellValue(product.getLinkToDescription().toString());
        }

        try {
            FileOutputStream fileOutputStream = new FileOutputStream(path);
            workbook.write(fileOutputStream);
            workbook.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
