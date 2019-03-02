/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TestPackage;

import General.User;
import java.sql.SQLException;

/**
 *
 * @author irshed
 */
public class UserClassTest {
    public static void main(String[] args) throws SQLException {
        User user = new User();
        user.setName("Guha");
        user.setPhno("12345678");
        user.setMailid("123@gmail.com");
        user.setCity("chennai");
        User res = User.insert(user);
        System.out.println(res.getId());
    }
}
