package com.jobfit.server.infras.skill;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.jobfit.server.domain.skill.Certification;

public interface CertificationJpaRepository extends JpaRepository <Certification, Long> {
	@Query("SELECT c FROM Certification c WHERE :name IS NULL OR c.name LIKE %:name%")
	List<Certification> findAllByNameLikeOrAll(@Param("name") String name);
}
