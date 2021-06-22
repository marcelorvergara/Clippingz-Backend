/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.clippingz.auth.service;

import com.clippingz.auth.model.User;

/**
 *
 * @author marcelo
 */
public interface UserService {

    void save(User user);

    User findByUsername(String username);
}
