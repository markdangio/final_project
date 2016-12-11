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
public class Classes {
    
    private String classId, name, subject, professor, description;
    private int number, section;
    
    /**
     * Initialize a Classes object.
     *
     * @param classId The class's id
     * @param name The class's name
     * @param subject The class's subject
     * @param number The class's number
     * @param section The class's section
     * @param professor The class's professor
     * @param description The class's description
     */
    public Classes (String classId, String name, String subject, int number, int section, String professor, String description)
    {
        this.classId = classId;
        this.name = name;
        this.subject = subject;
        this.professor = professor;
        this.description = description;
        this.number = number;
        this.section = section;
    }
    
    /**
     * Get the class's id.
     *
     * @return The class's id
     */
    public String getClassId() {
        return classId;
    }
    
    /**
     * Get the class's name.
     *
     * @return The class's name
     */
    public String getName() {
        return name;
    }

    /**
     * Get the class's subject.
     *
     * @return The class's subject
     */
    public String getSubject() {
        return subject;
    }

    /**
     * Get the class's professor.
     *
     * @return The class's professor
     */
    public String getProfessor() {
        return professor;
    }

    /**
     * Get the class's description.
     *
     * @return The class's description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Get the class's number.
     *
     * @return The class's number
     */
    public int getNumber() {
        return number;
    }

    /**
     * Get the class's section.
     *
     * @return The class's section
     */
    public int getSection() {
        return section;
    }
    
}
