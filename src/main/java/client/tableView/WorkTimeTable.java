package client.tableView;

public class WorkTimeTable {
    String firstname,lastname,start,stop;
    int account_id;

    public WorkTimeTable(int account_id, String firstname, String lastname, String start, String stop) {
        this.account_id = account_id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.start = start;
        this.stop = stop;
    }
    public int getAccount_id(){
        return account_id;
    }
    public void setAccount_id(int account_id){
        this.account_id = account_id;
    }
    public String getFirstname(){
        return firstname;
    }
    public void setFirstname(String firstname){
        this.firstname = firstname;
    }
    public String getLastname(){
        return lastname;
    }
    public void setLastname(String lastname){
        this.lastname = lastname;
    }
    public String getStart() {
        return start;
    }
    public void setStart(String start) {
        this.start = start;
    }
    public String getStop() {
        return stop;
    }
    public void setStop(String stop) {
        this.stop = stop;
    }
}
