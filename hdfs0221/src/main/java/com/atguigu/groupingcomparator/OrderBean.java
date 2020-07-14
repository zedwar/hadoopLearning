package com.atguigu.groupingcomparator;

import org.apache.hadoop.io.WritableComparable;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

public class OrderBean implements WritableComparable<OrderBean> {
    private String orderID;
    private String productID;
    private double price;

    @Override
    public String toString() {
        return  orderID + "\t" +
                  productID + "\t" +
                 price ;
    }

    public String getOrderID() {
        return orderID;
    }

    public void setOrderID(String orderID) {
        this.orderID = orderID;
    }

    public String getProductID() {
        return productID;
    }

    public void setProductID(String productID) {
        this.productID = productID;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public int compareTo(OrderBean o) {
        int compare = this.orderID.compareTo(o.orderID);
        if (compare==0){
            return Double.compare(o.price,this.price);
        }else {
            return compare;
        }
    }

    @Override
    public void write(DataOutput dataOutput) throws IOException {
        dataOutput.writeUTF(orderID);
        dataOutput.writeUTF(productID);
        dataOutput.writeDouble(price);
    }

    @Override
    public void readFields(DataInput dataInput) throws IOException {
        this.orderID = dataInput.readUTF();
        this.productID = dataInput.readUTF();
        this.price = dataInput.readDouble();
    }
}
