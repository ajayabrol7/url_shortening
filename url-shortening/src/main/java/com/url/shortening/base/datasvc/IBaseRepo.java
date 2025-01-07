package com.url.shortening.base.datasvc;

import org.springframework.data.jpa.repository.JpaRepository;

public interface IBaseRepo<TDto, TLong> extends JpaRepository<TDto, TLong> {

}
