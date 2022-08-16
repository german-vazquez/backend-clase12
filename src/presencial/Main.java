package presencial;

import java.sql.Connection;
import java.sql.DriverManager;

public class Main {
    public static void main(String[] args) {

    }
    public static Connection getConnection()throws Exception{
        //indicar el driver y retornar el getconnection del DriverManager
        Class.forName("org.h2.Driver");
        return DriverManager.getConnection("jdbc:h2:~/clase12","sa", "sa");
    }
}
