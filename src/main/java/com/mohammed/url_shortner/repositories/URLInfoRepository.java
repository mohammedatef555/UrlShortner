package com.mohammed.url_shortner.repositories;

import com.mohammed.url_shortner.models.URLInfo;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface URLInfoRepository extends JpaRepository<URLInfo, Long> {
    public List<URLInfo> findByShortenUrl(String shortenUrl);
    public List<URLInfo> findAllByOrderByIdAsc();
}
