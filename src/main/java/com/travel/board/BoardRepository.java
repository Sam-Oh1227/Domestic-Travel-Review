package com.travel.board;

import com.travel.domain.Board;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface BoardRepository extends JpaRepository<Board, Integer> {

  @Modifying
  @Query("update Board a set a.view = a.view + 1 where a.idx = :id")
  int updateView(@Param("id") int id);

  @Modifying
  @Query("update Board a set a.recommend = a.recommend + 1 where a.idx = :id")
  int updateRecommend(@Param("id") int id);

  @Query("SELECT a FROM Board a where a.category = :category ORDER BY a.idx")
  Page<Board> findByCategory(@Param("category") String category, Pageable pageable);

}
