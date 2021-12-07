package test;

import back_end.bo.BOFactory;
import back_end.bo.custom.UserBO;
import back_end.dto.UserDTO;
import java.sql.SQLIntegrityConstraintViolationException;

public class AddUser {
    public static void main(String[] args) {
        UserBO bo = (UserBO) BOFactory.getInstance().getBO(BOFactory.BOTypes.USER);
        try {
            bo.addUser(new UserDTO("admin", "admin", "admin"));
            System.out.println("user adding success");
        }catch (SQLIntegrityConstraintViolationException e){
            System.out.println("username already exists");
        } catch (Exception e) {
            System.out.println("unknown error, contact someone");
        }
    }
}
