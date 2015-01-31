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
class custDoesNotExistsException extends Exception {

    public custDoesNotExistsException(String the_customer_does_not_exixts) {
        System.err.println(the_customer_does_not_exixts);
    }
    
}
