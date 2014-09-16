package com.dsideal.shm.service.exception;

/**
 * 
 * @author feilm220
 *
 */
public class UserAlreadyExistsException extends RuntimeException {

	private static final long serialVersionUID = 8213408532004186935L;

	public UserAlreadyExistsException(final String message) {
        super(message);
    }

}
