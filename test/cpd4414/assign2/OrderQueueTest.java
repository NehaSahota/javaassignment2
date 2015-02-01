
/*
 * Copyright 2015 Len Payne <len.payne@lambtoncollege.ca>.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package cpd4414.assign2;

import cpd4414.assign2.OrderQueue;
import cpd4414.assign2.Purchase;
import cpd4414.assign2.Order;
import java.util.Date;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author <Neha>
 */
public class OrderQueueTest {

    public OrderQueueTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    @Test
    public void testWhenCustomerExistsAndPurchasesExistThenTimeReceivedIsNow() throws Exception {
        OrderQueue orderQueue = new OrderQueue();
        Order order = new Order("CUST00001", "ABC Construction");
        order.addPurchase(new Purchase(11, 450));
        order.addPurchase(new Purchase(12, 250));
        orderQueue.add(order);

        long expResult = new Date().getTime();
        long result = order.getTimeReceived().getTime();
        assertTrue(Math.abs(result - expResult) < 1000);
    }

    @Test
    public void testWhenCustomerDoesNotExistsThenThrowException() throws Exception {
        OrderQueue orderQueue = new OrderQueue();
        boolean thrown = false;
        Order order = new Order("", "");
        try {
            orderQueue.add(order);
        } catch (custDoesNotExistsException ex) {

            thrown = true;
        }
        assertTrue(thrown);

    }

    @Test
    public void testWhenListofPurchaseDoesNotExistsThenThrowException() throws Exception {
        OrderQueue orderQueue = new OrderQueue();
        boolean thrown = false;
        Order order = new Order("CUST00001", "ABC Construction");

        try {
            orderQueue.add(order);
        } catch (purchaseDoesNotExistsException ex) {

            thrown = true;
        }
        assertTrue(thrown);

    }

    @Test
    public void testReturnOrderWithEarliestTimeReceivedThatDoesNotHaveTimeProcessed() throws Exception {
        OrderQueue orderQueue = new OrderQueue();
        Order order = new Order("CUST00001", "ABC Construction");
        order.addPurchase(new Purchase(11, 450));
        order.addPurchase(new Purchase(12, 250));
        orderQueue.add(order);

        Order newOrder = new Order("CUST00002", "Sally's Construction");
        newOrder.addPurchase(new Purchase(13, 50));
        newOrder.addPurchase(new Purchase(14, 50));
        orderQueue.add(newOrder);

        Order expResult = order;
        Order result = orderQueue.nextOrder();

        assertEquals(expResult, order);

    }

    @Test
    public void testReturnNullWhenNoOrderInTheSystem() throws Exception {
        OrderQueue orderQueue = new OrderQueue();

        String result = "";

        try {
            orderQueue.nextOrder();
        } catch (Exception e) {
            result = null;
        }
        Order expResult = null;
        assertEquals(expResult, result);

    }

    @Test
    public void testWhenOrderHasTimeReceivedAndPurchaseInStockSetTimeProcessedToNow() throws Exception {
        OrderQueue orderQueue = new OrderQueue();
        Order order = new Order("CUST00001", "ABC Construction");
        order.addPurchase(new Purchase(11, 4));
        order.addPurchase(new Purchase(12, 2));
        orderQueue.add(order);
        orderQueue.processOrder(order);

        Date expResult = new Date();
        Date result = order.getTimeProcessed();
        assertEquals(expResult, result);

    }
    
    @Test
    public void testWhenOrderDoesNotHaveTimeProcessed() throws Exception {
        OrderQueue orderQueue = new OrderQueue();
        Boolean thrown = false;
        Order order = new Order("CUST00001", "ABC Construction");
        order.addPurchase(new Purchase(11, 4));
        order.addPurchase(new Purchase(12, 2));
        orderQueue.add(order);
//        orderQueue.processOrder(order);
        try{
            orderQueue.fulfill(order);
        }
        
        catch(getTimeProcessedNullException ex){
            thrown = true;
        }
       
        assertTrue(thrown);

    }
    
    @Test
    public void testWhenOrderDoesNotHaveTimeReceived() throws Exception {
        OrderQueue orderQueue = new OrderQueue();
        Boolean thrown = false;
        Order order = new Order("CUST00001", "ABC Construction");
        order.addPurchase(new Purchase(11, 4));
        order.addPurchase(new Purchase(12, 2));

        try{
            orderQueue.fulfill(order);
        }
        
        catch(getTimeReceivedNullException ex){
            thrown = true;
        }
       
        assertTrue(thrown);

    }
        @Test
    public void testWhenOrderHasTimeFulfilledAndTimeProcessed() throws Exception {
        OrderQueue orderQueue = new OrderQueue();
        Boolean thrown = false;
        Order order = new Order("CUST00001", "ABC Construction");
        order.addPurchase(new Purchase(11, 4));
        order.addPurchase(new Purchase(12, 2));
        orderQueue.add(order);
        orderQueue.processOrder(order);
         orderQueue.fulfill(order);
      
       Date expResult=new Date();
       Date result=order.getTimeFulfilled();
        assertEquals(expResult, result);
    }

}
