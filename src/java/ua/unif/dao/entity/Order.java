package ua.unif.dao.entity;

import java.util.List;

public class Order {
    private int id;
    private Client client;
    private boolean paid;
    private boolean confirmed;
    private boolean closed;
    private List<OrderedGoood> orderedGooods;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public boolean isPaid() {
        return paid;
    }

    public void setPaid(boolean paid) {
        this.paid = paid;
    }

    public boolean isConfirmed() {
        return confirmed;
    }

    public void setConfirmed(boolean confirmed) {
        this.confirmed = confirmed;
    }

    public boolean isClosed() {
        return closed;
    }

    public void setClosed(boolean closed) {
        this.closed = closed;
    }

    public List<OrderedGoood> getOrderedGooods() {
        return orderedGooods;
    }

    public void setOrderedGooods(List<OrderedGoood> orderedGooods) {
        this.orderedGooods = orderedGooods;
    }
}
