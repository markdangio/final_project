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
    
    private String name, subject, professor, description;
    private int number, section;
    
    public Classes (String name, String subject, int number, int section, String professor, String description)
    {
        this.name = name;
        this.subject = subject;
        this.professor = professor;
        this.description = description;
        this.number = number;
        this.section = section;
    }

    public String getName() {
        return name;
    }

    public String getSubject() {
        return subject;
    }

    public String getProfessor() {
        return professor;
    }

    public String getDescription() {
        return description;
    }

    public int getNumber() {
        return number;
    }

    public int getSection() {
        return section;
    }
    
}
