/**
 * 
 */
package com.url.shortening.base.factory;

import com.url.shortening.base.dto.BaseRequest;

/**
 * @author ajay
 *
 */
public interface IProcessorFactory {

	IProcessor getProcessor(BaseRequest request);
}
