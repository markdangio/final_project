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

    /**
     * Add a classes record.
     *
     * @param classId The class's id
     * @param name The class's name
     * @param subject The class's subject
     * @param number The class's number
     * @param section The class's section
     * @param professor The class's professor
     * @param description The class's description
     * @return true iff the class was added successfully
     */
    public static boolean addClass(String classId, String name, String subject, int number, int section, String professor,
            String description) {

        Classes newClass = new Classes(classId, name, subject, number, section, professor, description);
        return ClassesPersistence.addClass(newClass);
    }
    /**
     * Checks to see if a class record exists.
     *
     * @param name The class's name
     * @param subject The class's subject
     * @param number The class's name
     * @param section The class's section
     * @param professor The class's professor
     * @return boolean that says if the class exists
     */
    public static boolean checkClass(String name, String subject, int number, int section, String professor) {

        return ClassesPersistence.checkClass(name, subject, number, section, professor);
    }
    
    /**
     * Gets the id of a class
     *
     * @param name The class's name
     * @param subject The class's subject
     * @param number The class's name
     * @param section The class's section
     * @param professor The class's professor
     * @return string of the class's id
     */
    public static String getClassId(String name, String subject, int number, int section, String professor) {

        return ClassesPersistence.getClassId(name, subject, number, section, professor);
    }

}
