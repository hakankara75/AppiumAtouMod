package firstTest;

import org.testng.annotations.Test;

import java.sql.*;

public class Query01 {

    @Test
    public void testName() throws ClassNotFoundException, SQLException {
        //1. Adım: Driver Tanımlama
        Class.forName("org.postgresql.Driver");

        //2. Adım: Database'e bağlanma
        Connection connection= DriverManager.getConnection(
                "jdbc:postgresql://localhost:5432/Ders", //bağlanılacak database ismi girilir
                    "postgres", //username girilir
                    "123456" //password girilir
        );

        //3. Adım Statement
        Statement statement=connection.createStatement();

        //4. Adım: Result Set
        ResultSet resultSet =statement.executeQuery("select * from musteriler"); //query içine sorgu yapılacak tablo ismi yazılır

        //5. Adım: Dataları al ve yazdır
        while (resultSet.next()){
            System.out.println(resultSet.getInt(1)
                                + resultSet.getString(2)
                              + resultSet.getString(3));
        }

        //6. Adım: Bağlantıları kapatma
        connection.close();
        statement.close();
        resultSet.close();

    }
}
