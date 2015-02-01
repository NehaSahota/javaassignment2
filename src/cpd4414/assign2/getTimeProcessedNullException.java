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
class getTimeProcessedNullException extends Exception {

    public getTimeProcessedNullException(String the_time_processed_is_null) {
        System.err.println(the_time_processed_is_null);
    }
    
}
