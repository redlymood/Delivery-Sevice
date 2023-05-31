package ru.redlymood.DeliveryService.model;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.util.Date;
import javax.persistence.*;


@Entity
@Table(name = "Delivery")
public class Delivery {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @ManyToOne
    @JoinColumn(name = "order_id", referencedColumnName = "id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Order order;
    @ManyToOne
    @JoinColumn(name = "courier_id", referencedColumnName = "id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Courier courier;
    @Column(name = "status")
    private String status;
    @Column(name = "payment_method")
    private String paymentMethod;

    @Column(name = "date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date date;

    public Delivery() {}

    public Delivery(Order order, Courier courier, String status, String paymentMethod) {
        this.order = order;
        this.courier = courier;
        this.status = status;
        this.paymentMethod = paymentMethod;
        date = new Date();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Courier getCourier() {
        return courier;
    }

    public void setCourier(Courier courier) {
        this.courier = courier;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
