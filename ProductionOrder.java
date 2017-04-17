/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mes;

import java.util.*;
   
    
/**
 *
 * @author Utilizador
 */
public class ProductionOrder 
{
     private String orderID;
     private Date orderDate;
     private String orderStatus;
     
     /**
      * 
      * @return 
      */
     public String getOrderID()
     {
         return orderID;
     }
     
     /**
      * 
      * @return 
      */
     public Date getOrderDate()
     {
         return orderDate;
     }
     
     /**
      * 
      * @return 
      */
     public String getOrderStatus()
     {
         return orderStatus;
     }
     
     /**
      * 
      * @return 
      */
     public boolean setOrderStatus()
     {
         return true;
     }
     
    
}
