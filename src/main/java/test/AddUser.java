package test;

import back_end.bo.BOFacory;
import back_end.bo.custom.UserBO;
import back_end.dto.UserDTO;

import java.sql.SQLIntegrityConstraintViolationException;

public class AddUser {
    public static void main(String[] args) {
        UserBO bo = (UserBO) BOFacory.getInstance().getBO(BOFacory.BOTypes.USER);
        try {
            bo.addUser(new UserDTO("charaka", ""));
            System.out.println("user adding success");
        }catch (SQLIntegrityConstraintViolationException e){
            System.out.println("username already exists");
        } catch (Exception e) {
            System.out.println("unknown error, contact someone");
        }
    }
}
