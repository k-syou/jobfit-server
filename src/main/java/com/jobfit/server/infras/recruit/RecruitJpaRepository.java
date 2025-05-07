package com.jobfit.server.infras.recruit;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.jobfit.server.domain.recruit.Recruit;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;

public interface RecruitJpaRepository extends JpaRepository<Recruit, Long> {
  // JPQL 쿼리 작성
  @Query("SELECT r FROM Recruit r WHERE " +
      "(:companyName IS NULL OR :companyName = '' OR r.companyName LIKE %:companyName%) AND " +
      "(:region IS NULL OR :region = '' OR r.workPlace LIKE %:region%) AND " +
      "(:job IS NULL OR :job = '' OR r.title LIKE %:job%) AND " +
      "(:careerType IS NULL OR :careerType = '' OR r.careerType = :careerType)")
  Page<Recruit> findRecruits(
      @Param("companyName") String companyName,
      @Param("region") String region,
      @Param("job") String job,
      @Param("careerType") String careerType, // 파라미터 이름 명시
      Pageable pageable // Pageable 객체로 페이징 및 정렬 정보 받기
  );



  @Query("""
      SELECT new com.jobfit.server.infras.recruit.RecruitWithFavoriteDto(
          r.id, r.title, r.companyName, r.wage, r.workType, r.workPlace, r.careerType, r.registerDate, r.endDate,
          CASE 
              WHEN :userId IS NOT NULL AND f.id IS NOT NULL THEN true
              ELSE false
          END
      )
      FROM Recruit r
      LEFT JOIN Favorite f ON f.recruitId = r.id AND f.userId = :userId
      WHERE (:companyName IS NULL OR :companyName = '' OR r.companyName LIKE %:companyName%)
        AND (:region IS NULL OR :region = '' OR r.workPlace LIKE %:region%)
        AND (:job IS NULL OR :job = '' OR r.title LIKE %:job%)
        AND (:careerType IS NULL OR :careerType = '' OR r.careerType = :careerType)
  """)
  Page<RecruitWithFavoriteDto> findRecruitsWithFavoriteStatus(
      @Param("userId") Long userId,
      @Param("companyName") String companyName,
      @Param("region") String region,
      @Param("job") String job,
      @Param("careerType") String careerType,
      Pageable pageable
  );
}
