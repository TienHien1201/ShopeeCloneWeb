package TransactionApp.src;

import MauCuoiKi.src.persistence.*;
import MauCuoiKi.src.domain.*;
import MauCuoiKi.src.presentation.*;

public class App {
    public static void main(String[] args) {
        StudentPersistenceService studentPersistenceService = new StudentPersistenceServiceImpl("dataStudent.db");
        StudentService studentService = new StudentServiceImpl(studentPersistenceService);
        StudentManagementUI ui = new StudentManagementUI(studentService);
    }
}
