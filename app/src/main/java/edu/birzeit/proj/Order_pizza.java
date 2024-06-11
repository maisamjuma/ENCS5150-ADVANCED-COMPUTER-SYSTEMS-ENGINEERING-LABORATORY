package edu.birzeit.proj;

public class Order_pizza {
    private String Email;
    private String Ord;

    public Order_pizza() {
    }

    public Order_pizza(String Email,String Ord) {
        this.Email = Email;
        this.Ord = Ord;

    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String Email) {
        this.Email = Email;
    }


    public String getOrderr() {
        return Ord;
    }

    public void setOrderr(String Ord) {
        this.Ord = Ord;
    }


    @Override
    public String toString() {
        return "User{" +
                "Email=" + Email +
                ", Order='" + Ord +
                '\'' +
                '}';
    }
}