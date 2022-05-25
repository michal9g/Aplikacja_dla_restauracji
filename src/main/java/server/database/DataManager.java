package server.database;

import messages.*;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;

public class DataManager {

    public static ResultSet executeQuery(String sql){
        try {
            DatabaseConnection connectNow = new DatabaseConnection();
            Connection connectDB = connectNow.getConnection();
            Statement statement = connectDB.createStatement();
            return statement.executeQuery(sql);
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Boolean execute(String sql){
        try {
            DatabaseConnection connectNow = new DatabaseConnection();
            Connection connectDB = connectNow.getConnection();
            Statement statement = connectDB.createStatement();
            statement.executeUpdate(sql);
            statement.close();
        }catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public static LoginMessage verifyLogin(Object object){
        LoginMessage messout = (LoginMessage) object;
        LoginMessage message = null;
        String query = "SELECT count(1), account_id FROM user_account WHERE username = '"+messout.getLogin()+
                "' AND password = '"+messout.getPassword()+"'";
        ResultSet rs = executeQuery(query);
        try {
            while(rs.next()){
                message = new LoginMessage(rs.getInt(1), rs.getInt(2));
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return message;
    }

    public static void logoutInsertWT(Object object){
        LogoutMessage mess = (LogoutMessage) object;
        String query = "INSERT INTO  work_time(start_of_work, stop_of_work, user_id) " +
                "VALUES ('"+mess.getDataStart()+"','"+mess.getDataStop()+"',"+mess.getUserId()+");";
         execute(query);
    }

    public static LinkedList<WorkTimeMessage> getWorkTimeList(Object object){
        WorkTimeMessage mess = (WorkTimeMessage) object;
        LinkedList<WorkTimeMessage> inList = new LinkedList<>();
        String query ="SELECT user_account.account_id,user_account.firstname,user_account.lastname, " +
                "work_time.start_of_work,work_time.stop_of_work FROM user_account " +
                "INNER JOIN work_time ON user_account.account_id = work_time.user_id " +
                "WHERE user_account.account_id = " +mess.getId()+
                " AND work_time.start_of_work BETWEEN '"+mess.getStart()+"' AND '"+mess.getStop()+"'";
        ResultSet rs = executeQuery(query);
        try {
            while(rs.next()){
                inList.add(new WorkTimeMessage(rs.getInt(1),rs.getString(2),rs.getString(3),
                        rs.getString(4),rs.getString(5)));
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return inList;
    }

    public static WorkTimeMessage getWorkTime(Object object){
        WorkTimeMessage mess = (WorkTimeMessage) object;
        WorkTimeMessage wt = null;
        String query = "SELECT ROUND((SUM(TIME_TO_SEC(stop_of_work)-TIME_TO_SEC(start_of_work))/3600),2) " +
                "FROM work_time WHERE user_id = " + mess.getId()+
                " AND start_of_work BETWEEN '"+mess.getStart()+"' AND '"+mess.getStop()+"';";
        ResultSet rs = executeQuery(query);
        try {
            while(rs.next()){
                wt = new WorkTimeMessage(rs.getString(1));
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return wt;
    }

    public static LinkedList<ReceiptMessage>  getReceipt(Object object){
        ReceiptMessage mess = (ReceiptMessage) object;
        LinkedList<ReceiptMessage> inList = new LinkedList<>();
        String query = "SELECT id,ROUND(value_n,2),value_b, " +
                "type_of_payment, DATE_FORMAT(date_rec, '%Y.%m.%d') AS 'date_rec' FROM receipt " +
                "WHERE date_rec BETWEEN '"+mess.getStart()+"' AND '"+mess.getStop()+"';";
        ResultSet rs = executeQuery(query);
        try {
            while(rs.next()){
                inList.add(new ReceiptMessage(rs.getInt(1),rs.getString(2),rs.getString(3),
                        rs.getString(4),rs.getString(5)));
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return inList;
    }

    public static ReceiptReportMessage getReceiptReport(Object object){
        ReceiptReportMessage mess = (ReceiptReportMessage) object;
        ReceiptReportMessage receiptReport = null;
        String query = "SELECT SUM(value_b), ROUND(SUM(value_n),2)," +
                "SUM(discount) FROM receipt " + "WHERE date_rec BETWEEN '"+mess.getStart()+"' AND '"+mess.getStop()+"';";
        ResultSet rs = executeQuery(query);
        try {
            while(rs.next()){
                receiptReport = new ReceiptReportMessage(rs.getString(1),rs.getString(2),rs.getString(3));
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return receiptReport;
    }

    public static LinkedList<CustomersMessage>  getCustomers(){
        LinkedList<CustomersMessage> inList = new LinkedList<>();
        String query = "SELECT id,firstname,lastname, " +
                "phone_number FROM customers ";
        ResultSet rs = executeQuery(query);
        try {
            while(rs.next()){
                inList.add(new CustomersMessage(rs.getInt(1),rs.getString(2),rs.getString(3),
                        rs.getString(4)));
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return inList;
    }

    public static LinkedList<ReservationMessage>  getReservation(){
        LinkedList<ReservationMessage> inList = new LinkedList<>();
        String query = "SELECT customers_id,table_num,num_of_people, " +
                "date FROM reservations WHERE date > SYSDATE()";
        ResultSet rs = executeQuery(query);
        try {
            while(rs.next()){
                inList.add(new ReservationMessage(rs.getInt(1),rs.getString(4),rs.getString(2),
                        rs.getString(3)));
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return inList;
    }

    public static Boolean addReservation(Object object){
        ReservationMessage mess = (ReservationMessage) object;
        String query = "INSERT INTO  reservations (table_num, date, num_of_people, user_id,customers_id) " +
                "VALUES ("+mess.getTable_num()+",'"+mess.getDate()+"',"+mess.getNum_of_people()+","
                +mess.getUserId()+","+mess.getId()+");";
        return execute(query);
    }

    public static LinkedList<EmployeeMessage> getEmployee(){
        LinkedList<EmployeeMessage> inList = new LinkedList<>();
        String query = "SELECT account_id FROM user_account";
        ResultSet rs = executeQuery(query);
        try {
            while(rs.next()){
                inList.add(new EmployeeMessage(rs.getInt(1)));
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return inList;
    }

    public static Boolean addSchedule(Object object){
        ScheduleMessage mess = (ScheduleMessage) object;
        String query = "INSERT INTO  schedule (date, shift, user_id) " +
                "VALUES ('"+mess.getDate()+"','"+mess.getShift()+"',"+mess.getId()+");";
        return execute(query);
    }

    public static LinkedList<ScheduleMessage> getSchedule(){
        LinkedList<ScheduleMessage> inList = new LinkedList<>();
        String query = "SELECT schedule.user_id,user_account.firstname,user_account.lastname, " +
                "schedule.shift,schedule.date FROM schedule, user_account "+
                "WHERE schedule.user_id = user_account.account_id"+
                " AND schedule.date between DATE_SUB(SYSDATE(),INTERVAL 1 DAY) AND DATE_ADD(SYSDATE(),INTERVAL 7 DAY)";
        ResultSet rs = executeQuery(query);
        try {
            while(rs.next()){
                inList.add(new ScheduleMessage(rs.getInt(1), rs.getString(2), rs.getString(3),
                        rs.getInt(4), rs.getString(5)));
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return inList;
    }

    public static LinkedList<MenuMessage> getMenu(){
        LinkedList<MenuMessage> inList = new LinkedList<>();
        String query = "SELECT id, name, type, price FROM menu";
        ResultSet rs = executeQuery(query);
        try {
            while(rs.next()){
                inList.add(new MenuMessage(rs.getInt(1), rs.getString(2), rs.getString(3),
                        rs.getFloat(4)));
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return inList;
    }

    public static Boolean deleteMenu(Object object){
        MenuMessage mess = (MenuMessage) object;
        String query = "DELETE FROM menu WHERE id = "+mess.getId();
        return execute(query);
    }

    public static Boolean addMenu(Object object){
        MenuMessage mess = (MenuMessage) object;
        String query = "INSERT INTO  menu (name, type, price) " +
                "VALUES ('"+mess.getName()+"','"+mess.getType()+"',"+mess.getPrice()+");";
        return execute(query);
    }

    public static Boolean addCustomer(Object object){
        CustomersMessage message = (CustomersMessage) object;
        String query = "INSERT INTO  customers (firstname, lastname, phone_number) " +
                "VALUES ('"+message.getFirstname()+"','"+message.getLastname()+"',"+message.getPhonenumber()+");";
        return execute(query);
    }

    public static Boolean editMenu(Object object){
        MenuMessage message = (MenuMessage) object;
        String query = "UPDATE menu SET price = " +message.getPrice() +
                "Where id = "+message.getId()+";";
        return execute(query);
    }

    public static LinkedList<OrderMenuMessage> getOrdersId(){
        LinkedList<OrderMenuMessage> inList = new LinkedList<>();
        String query = "SELECT id FROM orders";
        ResultSet rs = executeQuery(query);
        try {
            while(rs.next()){
                inList.add(new OrderMenuMessage(rs.getInt(1)));
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return inList;
    }

    public static LinkedList<OrderMenuMessage> getOrderMenu(Object object){
        LinkedList<OrderMenuMessage> inList = new LinkedList<>();
        OrderMenuMessage message = (OrderMenuMessage) object;
        String query = "SELECT menu.name, orders_menu.number_pos, menu.price FROM orders_menu "+
                "lEFT JOIN menu ON  orders_menu.menu_id = menu.id WHERE orders_id = "+message.getId();
        ResultSet rs = executeQuery(query);
        try {
            while(rs.next()){
                inList.add(new OrderMenuMessage(rs.getString(1), rs.getInt(2), rs.getFloat(3)));
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return inList;
    }

    public static OrderMenuMessage getOrderDetails(Object object){
        OrderMenuMessage message = (OrderMenuMessage) object;
        OrderMenuMessage wt = null;
        String query = "SELECT orders.table_num, orders.at_location, SUM(menu.price*orders_menu.number_pos) FROM orders " +
                "LEFT JOIN orders_menu ON orders.id = orders_menu.orders_id  " +
                "LEFT JOIN menu ON  orders_menu.menu_id = menu.id "+
                "WHERE orders.id = "+message.getId();
        ResultSet rs = executeQuery(query);
        try {
            while(rs.next()){
                wt = new OrderMenuMessage(rs.getInt(1), rs.getFloat(3), rs.getString(2));
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return wt;
    }

    public static Boolean editOrder(Object object){
        EditOrderMessage message = (EditOrderMessage) object;
        String query = "INSERT INTO orders_menu (menu_id, orders_id, number_pos) " +
                "VALUES ("+message.getIdMenu()+","+message.getIdOrder()+","+message.getNumber()+");";
        return execute(query);
    }

    public static Boolean generateReceipt(Object object){
        OrderReceiptMessage message = (OrderReceiptMessage) object;
        String query = "INSERT INTO receipt (value_n, value_b, discount, type_of_payment,date_rec,user_id) "+
                "VALUES ("+message.getValue_n()+","+message.getValue_b()+","+message.getDiscount()+",'"+
                message.getType_of_payment()+"','"+message.getDate()+"',"+message.getUserId()+");";
        String query2 = "DELETE FROM orders_menu WHERE orders_id = "+message.getOrderId();
        String query3 = "DELETE FROM orders WHERE id = "+message.getOrderId();
        Boolean bool = execute(query);
        execute(query2);
        execute(query3);
        return bool;
    }

    public static Boolean createOrder(Object object){
        LinkedList<OrderMenuMessage> inList = (LinkedList<OrderMenuMessage>) object;
        OrderMenuMessage message = inList.getLast();
        inList.removeLast();
        int id = 0;
        Boolean bool = null;
        String query = "INSERT INTO orders (table_num,at_location,user_id) "+
                "VALUES ("+message.getTable_num()+",'"+message.getAt_location()+"',"+message.getId()+");";
        execute(query);
        String query1 = "SELECT id FROM orders ORDER BY id DESC LIMIT 1";
        ResultSet rs = executeQuery(query1);
        try {
            while(rs.next()){
                id = rs.getInt(1);
            }
        } catch (Exception e){
            e.printStackTrace();
            e.getCause();
        }
        while(!inList.isEmpty()) {
            OrderMenuMessage messageOrder = inList.pop();
            String query2 = "INSERT INTO orders_menu (menu_id, orders_id ,number_pos) "+
                    "VALUES ("+messageOrder.getId()+",'"+id+"',"+messageOrder.getNumber()+");";
            bool =  execute(query2);
        }
        return  bool;
    }
}
