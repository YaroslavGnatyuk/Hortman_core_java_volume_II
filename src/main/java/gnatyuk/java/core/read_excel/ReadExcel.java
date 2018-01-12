package gnatyuk.java.core.read_excel;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ReadExcel {
    List<Map<String, Object>> rowProducts = new ArrayList<>();
    private String path;

    public ReadExcel(String path) {
        this.path = path;
    }

    public List<Product> generateProducts() {
        try {

            FileInputStream excelFile = new FileInputStream(new File(path));
            Workbook workbook = new HSSFWorkbook(excelFile);
            Sheet dataTypeSheet = workbook.getSheetAt(0);
            dataTypeSheet.getMergedRegions();
            Iterator<Row> iterator = dataTypeSheet.iterator();
            while (iterator.hasNext()) {
                boolean isContainedDigitField = false;
                List<Object> raw = new ArrayList<>();
                Row currentRow = iterator.next();
                Iterator<Cell> cellIterator = currentRow.iterator();
                while (cellIterator.hasNext()) {
                    Cell currentCell = cellIterator.next();

                    if (currentCell.getCellTypeEnum() == CellType.STRING) {
                        raw.add(currentCell.getStringCellValue());
                    } else if (currentCell.getCellTypeEnum() == CellType.NUMERIC) {
                        isContainedDigitField = true;
                        raw.add(currentCell.getNumericCellValue());
                    }
                }
                if (raw.size() > 4 && isContainedDigitField) {
                    Map<String, Object> rowProduct = getMapFromList(raw);
                    rowProducts.add(rowProduct);
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return Product.generateListOfProduct(rowProducts);
    }

    private Map<String, Object> getMapFromList(List<Object> raw) {
        String stub = "http://***.com";
        Map<String, Object> rowProduct = new HashMap<>();
        rowProduct.put("id", raw.get(0));
        rowProduct.put("productName", raw.get(1));
        rowProduct.put("price", raw.get(2));
        rowProduct.put("wholesalePrice", raw.get(3));
        rowProduct.put("linkToMicrotron", raw.get(4));

        if (raw.size() > 5 && isValidURL((String)raw.get(5))) {
            rowProduct.put("linkToDescription", raw.get(5));
        } else {
            rowProduct.put("linkToDescription", stub);
        }

        return rowProduct;
    }

    private boolean isValidURL(String url){
        Pattern pattern = Pattern.compile("^http://www.microtron.ua/goods");
        Matcher matcher = pattern.matcher(url);
        return matcher.find();
    }
}
