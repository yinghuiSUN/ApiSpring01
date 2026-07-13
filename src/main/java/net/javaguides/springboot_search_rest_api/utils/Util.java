package net.javaguides.springboot_search_rest_api.utils;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;


import java.time.LocalDateTime;
import java.util.List;

public class Util {
    // message error
    public static final String ERROR_001 = "User does not exist";
    public static final String ERROR_002 = "Password is incorrect";
    public static final String ERROR_003 = "Connexion is OK";
    public static final String ERROR_004 = "Profile does not exist";

    // cross origin url
    public static final String URL_BASE = "http://localhost:4200";

    // task message
    public static final String TASK_DELETE_MSG = "Task is deleted";
    public static final String TASK_POST_MSG = "Task is created";
    public static final String TASK_UPDATE_MSG = "Task is updated";
    public static final String TASK_MSG_001 = "The task that you want to modify does not exist";
    public static final String TASK_MSG_002 = "The task that you want to delete does not exist";

    // export message
    public static final String EXPORT_OK = "Export of task for this idUser is done";

    // profile message
    public static final String PROFILE_MSG_001 = "The profile is found";
    public static final String PROFILE_MSG_002 = "All profiles are found";
    public static final String PROFILE_UPDATE_MSG = "the profile is updated";
    public static final String PROFILE_POST_MSG = "the profile is created";

    //utilisateur message
    public static final String USER_MSG_001 = "All users are found";
    public static final String USER_POST_MSG= "the user is created";
    public static final String USER_UPDATE_MSG= "the user is updated";
    public static final String USER_MSG_002= "the user exists";
    public static final String USER_MSG_003 = "The user is found";


    // departement message
    public static final String DEPARTEMENT_MSG_001 = "All departements are found";
    public static final String DEPARTEMENT_MSG_002 = "Users of this departement are found";

    //connexion message
    public static final String LOGIN_MSG_001 = "login success";

    public static final String pattern = "yyyy-MM-dd";

    public static final String EMPLOYEE = "EMPLOYEE";
    public static final String LEAD = "LEAD";
    public static final String MANAGER = "MANAGER";
    public static final String DIRECTOR = "DIRECTOR";
    public static final String ADMIN = "ADMIN";

    public static final List<String> EMPLOYEE_LEAD_DEPT_LIST = List.of(
            "frontend", "test", "backend",
            "communication vente", "reseau social", "presse",
            "cse", "hr recrutement", "syndicat");

    public static final List<String> MANAGER_DEPT_LIST = List.of(
            "marketing service", "hr service",  "technic service");

    public static final List<String> DIRECTOR_ADMIN_DEPT_LIST = List.of("directors");





    public static Row createHeaderExcel(Row headerRow, String[] displayColum, int indexStartCell, CellStyle headerStyle){
        for (int i=0; i<displayColum.length; i++) {
            Cell cell = headerRow.createCell(indexStartCell);
            cell.setCellValue(displayColum[i]);
            cell.setCellStyle(headerStyle);
            indexStartCell++;

        }
        return  headerRow;
    }

    public static String formatDateToString(LocalDateTime localDateTime) {
        return localDateTime.toLocalDate().toString();
    }

}
