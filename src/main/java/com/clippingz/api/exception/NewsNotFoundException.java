/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.clippingz.api.exception;

/**
 *
 * @author marcelo
 */
public class NewsNotFoundException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public NewsNotFoundException(Object id) {
        super(id != null ? id.toString() : null);
    }

    public NewsNotFoundException(Long id) {
    }

}
