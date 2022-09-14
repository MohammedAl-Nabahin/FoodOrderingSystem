/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

/**
 *
 * @author mohdo
 */
public class Order {
    int orderId;
    int userId;
    int cartId;
    String timeStamp;
    String delivred;

    public Order() {
    }

    public Order(int orderId, int userId, int cartId, String timeStamp , String delivred) {
        this.orderId = orderId;
        this.userId = userId;
        this.cartId = cartId;
        this.timeStamp = timeStamp;
        this.delivred = delivred;
    }

    public String getDelivred() {
        return delivred;
    }

    public void setDelivred(String delivred) {
        this.delivred = delivred;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getCartId() {
        return cartId;
    }

    public void setCartId(int itemId) {
        this.cartId = itemId;
    }

    public String getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp;
    }

    @Override
    public String toString() {
        return "Order{" + "orderId=" + orderId + ", userId=" + userId + ", itemId=" + cartId + ", timeStamp=" + timeStamp + '}';
    }
    
    
    
}


//DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");  
 //  LocalDateTime now = LocalDateTime.now();  
  // System.out.println(dtf.format(now));  