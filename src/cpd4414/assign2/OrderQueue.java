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

import java.util.ArrayDeque;
import java.util.Date;
import java.util.Queue;

/**
 *
 * @author <Neha>
 */
public class OrderQueue {

    Queue<Order> orderQueue = new ArrayDeque<>();
    Queue<Order> processQueue = new ArrayDeque<>();

    public void add(Order order) throws Exception {
        if (order.getCustomerId().isEmpty() || order.getCustomerName().isEmpty()) {
            throw new custDoesNotExistsException("The customer does not exists");
        }
        if (order.getListOfPurchases().isEmpty()) {
            throw new purchaseDoesNotExistsException("The list or purchase does not exists");
        }

        orderQueue.add(order);
        order.setTimeReceived(new Date());
    }

    public Order nextOrder() {
        return orderQueue.element();

    }

    public void processOrder(Order order) throws Exception {

        if (order.getTimeReceived() == null) {
            throw new getTimeReceivedNullException("The order does not have time recieved ");

        }
        for (Purchase product : order.getListOfPurchases()) {
            int inventoryProdIDQuantity = Inventory.getQuantityForId(product.getProductId());
            int orderProdIDQuantity = product.getQuantity();
            if (orderProdIDQuantity > inventoryProdIDQuantity) {
                throw new outOfStockException("The product is out of stock");

            }

        }

        order.setTimeProcessed(new Date());
        orderQueue.remove(order);
        processQueue.add(order);

    }

    public void fulfill(Order order) throws Exception {

        if (order.getTimeReceived() == null) {
            throw new getTimeReceivedNullException("The time received  is null");

        }
        if (order.getTimeProcessed() == null) {
            throw new getTimeProcessedNullException("The time processed is null");
        }
        for (Purchase product : order.getListOfPurchases()) {
            int inventoryProdIDQuantity = Inventory.getQuantityForId(product.getProductId());
            int orderProdIDQuantity = product.getQuantity();
            if (orderProdIDQuantity > inventoryProdIDQuantity) {
                throw new outOfStockException("The product is out of stock");

            }

        }

        order.setTimeFulfilled(new Date());
    }

}
