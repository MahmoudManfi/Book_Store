package service;

public class BookSalesEntity {
    String bookTitle;

    public BookSalesEntity(String bookTitle, Integer numberOfSales) {
        this.bookTitle = bookTitle;
        this.numberOfSales = numberOfSales;
    }

    Integer numberOfSales;



    public String getBookTitle() {
        return bookTitle;
    }

    public void setBookTitle(String bookTitle) {
        this.bookTitle = bookTitle;
    }

    public Integer getNumberOfSales() {
        return numberOfSales;
    }

    public void setNumberOfSales(Integer numberOfSales) {
        this.numberOfSales = numberOfSales;
    }

    @Override
    public String toString() {
        return "BookSalesEntity{" +
                "bookTitle='" + bookTitle + '\'' +
                ", numberOfSales=" + numberOfSales +
                '}';
    }
}
