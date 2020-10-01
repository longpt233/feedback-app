package controler;


import model.User;
import service.ShowUser;

public class MainApp  {



    public static void main(String[] args) {

        System.out.println("hello project");
        User admin=new User("admin");
        ShowUser tmp =new ShowUser(admin);
        System.out.println(tmp.CheckExist());
    }
}
