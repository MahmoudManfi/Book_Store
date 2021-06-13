package service;

import com.library.Controller.DatabaseConnector;
import com.library.Model.databaseTables.Book;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

import java.io.File;
import java.sql.*;
import java.util.*;


public class ReportGenerator {

    public void generateBookSalesReport() {
        File file = new File("src/service/book_sales.jrxml");
        try {
            List<BookSalesEntity> books = getBookSales();
            JasperReport jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());
            JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(books);
            Map<String, Object> redundant_map = new HashMap<>();
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, redundant_map, dataSource);
            JasperExportManager.exportReportToPdfFile(jasperPrint, "book_sales_results.pdf");
        } catch (JRException e) {
            System.err.println("Error during instantiating the JasperReport");
            e.printStackTrace();
        }
    }

    public void generateTopCustomersReport() {
        File file = new File("src/service/top_customers.jrxml");
        try {
            List<TopCustomerEntity> books = getTopCustomers();
            JasperReport jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());
            JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(books);
            Map<String, Object> redundant_map = new HashMap<>();
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, redundant_map, dataSource);
            JasperExportManager.exportReportToPdfFile(jasperPrint, "top_customers_results.pdf");
        } catch (JRException e) {
            System.err.println("Error during instantiating the JasperReport");
            e.printStackTrace();
        }
    }

    private List<BookSalesEntity> getBookSales() {
        List<BookSalesEntity> results = new ArrayList<>();
        DatabaseConnector dbc = DatabaseConnector.getInstance();
        String query = "select book.title, sum(sold_book.number_copies)\n" +
                "from book join sold_book on book.ISBN_number = sold_book.ISBN_number\n" +
                "where date >= ( now() - interval  1 month )\n" +
                "group by book.title;";

        List<List<String>> queryResults = dbc.implementAdvancedSelect(query, 2);
        for (List<String> itr : queryResults) {
            try {
                BookSalesEntity bookSalesEntity = new BookSalesEntity(itr.get(0), Integer.valueOf(itr.get(1)));
                results.add(bookSalesEntity);
            } catch (Exception e) {
                System.err.println("Exception while trying to read the 2d array in getBookSales() in ReportGenerator Class ");
                System.err.println("Most likely this is because we couldn't cast some string to an integer ");
            }
        }
        return results;
    }

    private List<TopCustomerEntity> getTopCustomers() {
        List<TopCustomerEntity> results = new ArrayList<>();
        DatabaseConnector dbc = DatabaseConnector.getInstance();

        String query = "select sold_book.user_name, SUM(sold_book.number_copies)\n" +
                "from sold_book\n" +
                "where date >= (now() - interval 3 month )\n" +
                "group by sold_book.user_name\n" +
                "order by SUM(sold_book.number_copies) desc\n" +
                "LIMIT 5;";

        List<List<String>> queryResults = dbc.implementAdvancedSelect(query, 1);
        for (List<String> itr : queryResults) {
            TopCustomerEntity topCustomerEntity = new TopCustomerEntity(itr.get(0));
            results.add(topCustomerEntity);
        }
        return results;
    }

    public static void main(String[] args) {
        ReportGenerator reportGenerator = new ReportGenerator();
//        List<BookSalesEntity> res = reportGenerator.getBookSales();
//        for (int i = 0; i < res.size(); i++) {
//            System.out.println(res.get(i).toString());
//        }
//        reportGenerator.generateBookSalesReport();
        List<TopCustomerEntity> res = reportGenerator.getTopCustomers();
        for (int i = 0; i < res.size(); i++) {
            System.out.println(res.get(i).toString());
        }
        reportGenerator.generateTopCustomersReport();


    }

}
