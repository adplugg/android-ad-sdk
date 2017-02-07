package com.adplugg;

/**
 * AdRequestException
 *
 * Exception class specific to AdPlugg SDK
 *
 * @author justin.fiedler
 * @date 2/6/17
 */

public class AdRequestException extends Exception {

    public AdRequestException(String message) {
        super(message);
    }
}
