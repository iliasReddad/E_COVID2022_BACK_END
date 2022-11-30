package com.bezkoder.springjwt.repository;

import com.bezkoder.springjwt.models.Content;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository

public interface ContentRepository extends JpaRepository<Content,Long> {

    @Query("SELECT r FROM Content r where r.ctn_block = :name")
    Content  findbyctn(@Param("name") String name);
}
