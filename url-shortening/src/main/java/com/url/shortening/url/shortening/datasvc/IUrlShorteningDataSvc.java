/**
 * 
 */
package com.url.shortening.url.shortening.datasvc;

import com.url.shortening.base.datasvc.IBaseRepoMethod;
import com.url.shortening.url.shortening.common.UrlShorteningDto;
import com.url.shortening.url.shortening.common.UrlShorteningQueryDto;
import com.url.shortening.url.shortening.model.UrlShorteningMdl;

/**
 * 
 */
public interface IUrlShorteningDataSvc
		extends IBaseRepoMethod<UrlShorteningDto, UrlShorteningQueryDto, UrlShorteningMdl, Long> {

}
