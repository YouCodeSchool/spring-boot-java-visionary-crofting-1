package com.youcode.visionarycrofting.entity;




import javax.persistence.*;
import java.util.Date;
import java.util.List;


@Entity
@Table
public class Command {

 @Id
 @GeneratedValue(strategy = GenerationType.IDENTITY)
 private Long id;
 private  String ref;
 private Date  dateTime;
 private int totalPrice ;
 private String address;

 @ManyToOne
 private Client client;

 public Client getClient() {
  return client;
 }

 public void setClient(Client client) {
  this.client = client;
 }

 public Command(Long id, String ref, Date dateTime, int totalPrice, String address, Client client) {
  this.id = id;
  this.ref = ref;
  this.dateTime = dateTime;
  this.totalPrice = totalPrice;
  this.address = address;
  this.client = client;
 }

 public Command(String ref, Date dateTime, int totalPrice, String address, Client client) {
  this.ref = ref;
  this.dateTime = dateTime;
  this.totalPrice = totalPrice;
  this.address = address;
  this.client = client;
 }

 public Command() {

 }

 public Long getId() {
  return id;
 }

 public void setId(Long id) {
  this.id = id;
 }

 public String getRef() {
  return ref;
 }

 public void setRef(String ref) {
  this.ref = ref;
 }

 public Date getDateTime() {
  return dateTime;
 }

 public void setDateTime(Date dateTime) {
  this.dateTime = dateTime;
 }

 public int getTotalPrice() {
  return totalPrice;
 }

 public void setTotalPrice(int totalPrice) {
  this.totalPrice = totalPrice;
 }

 public String getAddress() {
  return address;
 }

 public void setAddress(String address) {
  this.address = address;
 }

 /*public List<Object> getListItem() {
  return listItem;
 }

 public void setListItem(List<Object> listItem) {
  this.listItem = listItem;
 }*/

 @Override
 public String toString() {
  return "Command{" +
          "id=" + id +
          ", ref='" + ref + '\'' +
          ", dateTime=" + dateTime +
          ", totalPrice=" + totalPrice +
          ", address='" + address + '\'' +
          ", client=" + client +
          '}';
 }
}
