package server;

import messages.*;
import server.database.DataManager;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.util.LinkedList;

public  class ClientHandler implements Runnable {

    private final Socket clientSocket;
    ObjectInputStream in;
    ObjectOutputStream out;

    public ClientHandler(Socket socket) {
        this.clientSocket = socket;
    }

    public void run() {
        try {
            in = new ObjectInputStream(clientSocket.getInputStream());
            out = new ObjectOutputStream(clientSocket.getOutputStream());
        } catch (Exception e) {
            e.printStackTrace();
        }
        while (true) {
            try {
                String read = in.readObject().toString();
                ServerOperation serverOperation = ServerOperation.valueOf(read);
                if (serverOperation == ServerOperation.disconnect) {
                    out.writeObject(null);
                    System.out.println("Wylogowano klienta: "+ InetAddress.getLocalHost());
                    clientSocket.close();
                    return;
                } else {
                    Object object = in.readObject();
                    executeOperation(serverOperation, object);
                }
            } catch (IOException e) {
                e.printStackTrace();
                return;
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    private synchronized void executeOperation(ServerOperation serverOperation, Object object) {
        switch (serverOperation) {
            case verifyLogin:
                LoginMessage message = DataManager.verifyLogin(object);
                try {
                    out.writeObject(message);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            case logout:
                DataManager.logoutInsertWT(object);
                try{
                    out.writeObject(null);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            case getWorkTime:
                WorkTimeMessage wt = DataManager.getWorkTime(object);
                try{
                    out.writeObject(wt);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            case getWorkTimeList:
                LinkedList<WorkTimeMessage> inList = DataManager.getWorkTimeList(object);
                try{
                    out.writeObject(inList);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            case getReceipt:
                LinkedList<ReceiptMessage> inListRec = DataManager.getReceipt(object);
                try{
                    out.writeObject(inListRec);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            case getReceiptReport:
                ReceiptReportMessage receiptReport = DataManager.getReceiptReport(object);
                try{
                    out.writeObject(receiptReport);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            case getCustomers:
                LinkedList<CustomersMessage> inListCus = DataManager.getCustomers();
                try{
                    out.writeObject(inListCus);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            case getReservation:
                LinkedList<ReservationMessage> inListRes = DataManager.getReservation();
                try{
                    out.writeObject(inListRes);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            case addReservation:
                try{
                    out.writeObject(DataManager.addReservation(object));
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            case getEmployee:
                LinkedList<EmployeeMessage> employee = DataManager.getEmployee();
                try {
                    out.writeObject(employee);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            case addSchedule:
                try{
                    out.writeObject(DataManager.addSchedule(object));
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            case getSchedule:
                LinkedList<ScheduleMessage> schedule = DataManager.getSchedule();
                try {
                    out.writeObject(schedule);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            case getMenu:
                LinkedList<MenuMessage> menu = DataManager.getMenu();
                try {
                    out.writeObject(menu);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            case deleteMenu:
                try{
                    out.writeObject(DataManager.deleteMenu(object));
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            case addMenu:
                try{
                    out.writeObject(DataManager.addMenu(object));
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            case addCustomer:
                try{
                    out.writeObject(DataManager.addCustomer(object));
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            case editMenu:
                try{
                    out.writeObject(DataManager.editMenu(object));
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            case getOrdersId:
                LinkedList<OrderMenuMessage> ordersId = DataManager.getOrdersId();
                try {
                    out.writeObject(ordersId);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            case getOrder:
                LinkedList<OrderMenuMessage> orderMenu = DataManager.getOrderMenu(object);
                try {
                    out.writeObject(orderMenu);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            case getOrderDetails:
                OrderMenuMessage orderDetails = DataManager.getOrderDetails(object);
                try {
                    out.writeObject(orderDetails);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            case editOrder:
                try{
                    out.writeObject(DataManager.editOrder(object));
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            case generateReceipt:
                try{
                    out.writeObject(DataManager.generateReceipt(object));
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            case createOrder:
                try{
                    out.writeObject(DataManager.createOrder(object));
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
        }

    }
}
