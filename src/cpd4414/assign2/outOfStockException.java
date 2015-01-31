/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package cpd4414.assign2;

/**
 *
 * @author c0646567
 */
class outOfStockException extends Exception {

    public outOfStockException(String the_product_is_out_of_stock) {
        System.err.println(the_product_is_out_of_stock);
    }
    
}
