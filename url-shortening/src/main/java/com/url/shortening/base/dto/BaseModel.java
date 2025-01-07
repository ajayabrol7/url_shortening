/**
 * 
 */
package com.url.shortening.base.dto;

import java.io.Serializable;

/**
 * @author anthony
 *
 */
public abstract class BaseModel<TId> implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2764490233434061181L;

	public abstract TId getId();

}
