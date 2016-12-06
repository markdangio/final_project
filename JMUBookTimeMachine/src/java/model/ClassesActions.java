/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author massarmh
 */
public class ClassesActions {

    public static boolean addClass(String classId, String name, String subject, int number, int section, String professor,
            String description) {

        Classes newClass = new Classes(classId, name, subject, number, section, professor, description);
        return ClassesPersistence.addClass(newClass);
    }
    /*
    public static boolean checkClasses(String name, String subject, int number, int section, String professor) {

        return ClassesPersistence.checkClasses(name, subject, number, section, professor);
    }*/

}
