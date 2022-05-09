package messages;

import java.io.Serializable;

public class ReceiptReportMessage implements Serializable {
    String start, stop, utarg, profit, discounts;

    public ReceiptReportMessage(String start, String stop) {
        this.start = start;
        this.stop = stop;
    }

    public ReceiptReportMessage(String utarg, String profit, String discounts) {
        this.utarg = utarg;
        this.profit = profit;
        this.discounts = discounts;
    }

    public String getStart() {
        return start;
    }

    public String getStop() {
        return stop;
    }

    @Override
    public String toString() {
        return "Wygenerowany raport \n" +
                "Utarg = \t" + utarg + '\n' +
                "Zarobek = \t" + profit + '\n' +
                "Przyznane Rabaty = \t" + discounts + '\n';
    }
}
