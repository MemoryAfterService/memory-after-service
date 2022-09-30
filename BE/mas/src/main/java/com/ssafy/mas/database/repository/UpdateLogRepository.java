package com.ssafy.mas.database.repository;

import com.ssafy.mas.database.entity.UpdateLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UpdateLogRepository extends JpaRepository<UpdateLog, Long> {
}
