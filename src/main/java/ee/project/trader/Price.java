package ee.project.trader;

public class Price {

    String date;
    String time;
    double price;

    public Price(String date, String time, double price) {
        this.date = date;
        this.time = time;
        this.price = price;
    }
}
