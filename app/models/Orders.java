package models;

import javax.persistence.*;

@Entity
public class Orders
{

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "orderId")
    public Long orderId;

    @Column (name = "employeeId")
    public Long employeeId;

    @Column(name = "customerId")
    public String customerId;

    @Column (name = "freight")
    public double freight;

    @Column (name = "shipCity")
    public String shipCity;

    @Column (name = "shipCountry")
    public String shipCountry;
}
