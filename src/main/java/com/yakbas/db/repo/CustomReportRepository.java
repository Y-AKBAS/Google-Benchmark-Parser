package com.yakbas.db.repo;

import com.yakbas.db.domain.CustomReportEntity;
import org.springframework.data.annotation.Id;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomReportRepository extends JpaRepository<CustomReportEntity, Id> {

    @Query(value = "select E from CustomReportEntity E where E.a_requestName LIKE %:param% ORDER BY b_iteration,c_realTime")
    List<CustomReportEntity> findByRequestNameLike(@Param("param") String like);
}
