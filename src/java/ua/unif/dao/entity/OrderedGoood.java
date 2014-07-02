package ua.unif.dao.entity;

public class OrderedGoood {
    private int idog;
    private Good good;
    private int amount;

    public int getIdog() {
        return idog;
    }

    public void setIdog(int idog) {
        this.idog = idog;
    }

    public Good getGood() {
        return good;
    }

    public void setGood(Good good) {
        this.good = good;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }    

    @Override
    public int hashCode() {
        return idog;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final OrderedGoood other = (OrderedGoood) obj;
        if (this.idog != other.idog) {
            return false;
        }
        return true;
    }
    
    
}
