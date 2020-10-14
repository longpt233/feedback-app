package controler;


import model.UserModel;
import service.ShowUserService;

public class MainApp  {



    public static void main(String[] args) {

        System.out.println("hello project");
        UserModel admin=new UserModel("admin");
        ShowUserService tmp =new ShowUserService(admin);
        System.out.println(tmp.CheckExist());
    }
}
