package service;

public class TopCustomerEntity {
    String userName;

    public TopCustomerEntity(String userName) {
        this.userName = userName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Override
    public String toString() {
        return "TopCustomerEntity{" +
                "userName='" + userName + '\'' +
                '}';
    }
}
