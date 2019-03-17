package by.epam.halavin.maintask.dao.dataconverter.impl;

import by.epam.halavin.maintask.bean.user.Passenger;
import by.epam.halavin.maintask.bean.user.User;
import by.epam.halavin.maintask.dao.connection.ConnectionPool;
import by.epam.halavin.maintask.dao.exception.DAOException;
import org.apache.commons.codec.digest.DigestUtils;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Objects;

public class PassengerConverterTest {
    private static Passenger expected;

    @BeforeClass
    public static void initUser() {
        expected = new Passenger();
        expected.setId(1);
        expected.setName("Федор");
        expected.setSurname("Федоров");
        expected.setEmail("wowka@gmail.com");
        expected.setTel("+375 29 444 77 99");
        expected.setStatus("unban");
        expected.setPassword(DigestUtils.sha256Hex("1111"));
        expected.setBonus(9.65);
        expected.setDiscount(1.1);
    }

    @Test
    public void convert() throws SQLException, DAOException {
        Connection connection = ConnectionPool.getInstance().getConnection();
        String email = "wowka@gmail.com";

        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("select * from users where email = '" + email + "'");
        PassengerConverter converter = new PassengerConverter();
        User result = converter.convertFirstRow(resultSet);

        Assert.assertEquals(expected, result);
    }

    @Test
    public void convertRows() throws SQLException, DAOException {
        String expected = "[{id=1, name='Федор', surname='Федоров', email='wowka@gmail.com', tel='+375 29 444 77 99', password='0ffe1abd1a08215353c233d6e009613e95eec4253832a761af28ff37ac5a150c'}\n" +
                "{status='unban', discount=1.1, bonus='9.65'} \n" +
                ", {id=2, name='Никита', surname='Алин', email='wer@gmail.com', tel='+375 29 666 84 12', password='edee29f882543b956620b26d0ee0e7e950399b1c4222f5de05e06425b4c995e9'}\n" +
                "{status='ban', discount=1.0, bonus='15.0'} \n" +
                ", {id=3, name='Антон', surname='Филин', email='antonfil@gmail.com', tel='+375 44 333 22 11', password='79f06f8fde333461739f220090a23cb2a79f6d714bee100d0e4b4af249294619'}\n" +
                "{status='ban', discount=1.0, bonus='12.0'} \n" +
                ", {id=4, name='ндрей ', surname='Макаренко', email='adnrmak@gmail.com', tel='+375 44 212 12 13', password='d7697570462f7562b83e81258de0f1e41832e98072e44c36ec8efec46786e24e'}\n" +
                "{status='unban', discount=5.0, bonus='15.0'} \n" +
                ", {id=5, name='Павел', surname='Савчик', email='tut@gmail.com', tel='+375 29 666 34 11', password='318aee3fed8c9d040d35a7fc1fa776fb31303833aa2de885354ddf3d44d8fb69'}\n" +
                "{status='unban', discount=0.0, bonus='0.0'} \n" +
                ", {id=6, name='Антон', surname='Сусел', email='babil@gmail.com', tel='+375 29 234 54 29', password='9800a8677d99e5f6968d7357e44006388b09d3b6a8676d0f930fbaa63d02330d'}\n" +
                "{status='ban', discount=0.0, bonus='0.0'} \n" +
                ", {id=7, name='Купер', surname='Антохин', email='vavilo@gmail.com', tel='+375 29 456 39 71', password='03ac674216f3e15c761ee1a5e255f067953623c8b388b4459e13f978d7c846f4'}\n" +
                "{status='unban', discount=0.0, bonus='0.0'} \n" +
                ", {id=8, name='Валера', surname='Шаткий', email='retroil@gmail.com', tel='+375 44 342 22 11', password='9af15b336e6a9619928537df30b2e6a2376569fcf9d7e773eccede65606529a0'}\n" +
                "{status='ban', discount=1.0, bonus='1.0'} \n" +
                ", {id=9, name='Сергей', surname='Деров', email='derov@gmail.com', tel='+375 17 899 01 01', password='0ffe1abd1a08215353c233d6e009613e95eec4253832a761af28ff37ac5a150c'}\n" +
                "{status='unban', discount=0.0, bonus='0.0'} \n" +
                ", {id=10, name='Слава', surname='Норвин', email='norvin@gmail.com', tel='+375 29 565 34 12', password='0ffe1abd1a08215353c233d6e009613e95eec4253832a761af28ff37ac5a150c'}\n" +
                "{status='unban', discount=0.0, bonus='0.0'} \n" +
                ", {id=12, name='Артем', surname='Грушев', email='grushev@mail.ru', tel='+375 29 456 21 23', password='bcb15f821479b4d5772bd0ca866c00ad5f926e3580720659cc80d39c9d09802a'}\n" +
                "{status='ban', discount=1.2, bonus='11.0'} \n" +
                ", {id=13, name='Кирилл', surname='Фролов', email='frolov@mail.ru', tel='+375 17 989 23 14', password='bcb15f821479b4d5772bd0ca866c00ad5f926e3580720659cc80d39c9d09802a'}\n" +
                "{status='unban', discount=1.2, bonus='12.1'} \n" +
                ", {id=28, name='Павел', surname='Савчик', email='pav_sav@mail.com', tel='+375 11 222 33 23', password='bcb15f821479b4d5772bd0ca866c00ad5f926e3580720659cc80d39c9d09802a'}\n" +
                "{status='unban', discount=0.9, bonus='1.9'} \n" +
                ", {id=29, name='Гриша', surname='Савин', email='savin@mail.ru', tel='+375 29 324 25 26', password='bcb15f821479b4d5772bd0ca866c00ad5f926e3580720659cc80d39c9d09802a'}\n" +
                "{status='ban', discount=1.3, bonus='1.1'} \n" +
                ", {id=30, name='Павел', surname='Савчик', email='retail@mail.ru', tel='+375 22 290 29 29', password='bcb15f821479b4d5772bd0ca866c00ad5f926e3580720659cc80d39c9d09802a'}\n" +
                "{status='unban', discount=0.0, bonus='0.0'} \n" +
                ", {id=31, name='Кирилл', surname='Фромев', email='fromev@gmail.com', tel='+375 29 684 39 61', password='bcb15f821479b4d5772bd0ca866c00ad5f926e3580720659cc80d39c9d09802a'}\n" +
                "{status='ban', discount=5.2, bonus='21.2'} \n" +
                ", {id=32, name='Неон', surname='Аферов', email='aferov@gmail.com', tel='+375 29 666 34 11', password='bcb15f821479b4d5772bd0ca866c00ad5f926e3580720659cc80d39c9d09802a'}\n" +
                "{status='unban', discount=0.0, bonus='0.0'} \n" +
                "]";

        Connection connection = ConnectionPool.getInstance().getConnection();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("select * from users ");
        PassengerConverter converter = new PassengerConverter();
        List<User> result = converter.convertRows(resultSet);

        Assert.assertEquals(Objects.toString(result), expected);
    }

}