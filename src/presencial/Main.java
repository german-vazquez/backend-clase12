package presencial;

import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Main {
    private static final String SQL_CREATE_TABLE = "drop table if exists usuarios; create table usuarios(ID int primary key, PRIMER_NOMBRE varchar (100) not null, APELLIDO varchar (100) not null, EDAD int not null)";
    private static final String SQL_INSERT1 = "insert into usuarios (ID, PRIMER_NOMBRE, APELLIDO, EDAD)  values (1, 'Rodolfo', 'Baspineiro', 29)";
    private static final String SQL_INSERT2 = "insert into usuarios (ID, PRIMER_NOMBRE, APELLIDO, EDAD)  values (2, 'Pedro', 'Lopez', 45)";
    private static final String SQL_INSERT3 = "insert into usuarios (ID, PRIMER_NOMBRE, APELLIDO, EDAD)  values (3, 'Pablo', 'Martini', 80)";
    private static final String SQL_DELETE = "delete from usuarios where ID=3";
    private static final String SQL_SELECT = "select * from usuarios";

    private static final Logger logger = Logger.getLogger(Main.class);

    public static void main(String[] args) {
        // dentro del main esta to do lo que se ejecuta
        Connection connection = null;
        try {
            connection = getConnection();
            Statement statement = connection.createStatement();
            //crear la tabla
            statement.execute(SQL_CREATE_TABLE);
            //inserto los tres elementos
            statement.execute(SQL_INSERT1);
            statement.execute(SQL_INSERT2);
            statement.execute(SQL_INSERT3);
            //eliminamos el tercero como pedia la consigna y loggeamos
            statement.execute(SQL_DELETE);
            logger.warn("Se eliminó al usuario con id=3");
            // mostrar los datos de la BD
            ResultSet rs = statement.executeQuery(SQL_SELECT);
            while (rs.next()) {
                System.out.println("ID:" + rs.getInt(1) + "primer nombre: " + rs.getString(2) + "apellido: " + rs.getString(3) + "edad: " + rs.getInt(4));
            }

        } catch (Exception exception) {
            logger.error(exception.getMessage());
            exception.printStackTrace();
        }


    }

    public static Connection getConnection() throws Exception {
        //indicar el driver y retornar el getconnection del DriverManager
        Class.forName("org.h2.Driver");
        return DriverManager.getConnection("jdbc:h2:~/clase12", "sa", "sa");
    }
}
