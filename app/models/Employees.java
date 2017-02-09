package models;

import javax.persistence.*;

@Entity
public class Employees
{
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "employeeId")
    public Long employeeId;

    @Column (name = "firstName")
    public String employeeFirstName;

    @Column(name = "lastName")
    public String employeeLastName;

    @Column (name = "photo")
    public byte[] photo;

    @Column (name = "title")
    public String employeeTitle;


}

