package com.bezkoder.spring.datajpa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.bezkoder.spring.datajpa.model.Tutorial;

public interface TutorialRepository extends JpaRepository<Tutorial, Long> {
  List<Tutorial> findByPublished(boolean published);
  
  /*@Query("SELECT t FROM Tutorial t WHERE t.title LIKE %:title%")*/
  List<Tutorial> findByTitleContaining(String title);
  
  @Query("from Tutorial order by title")
  List<Tutorial> findAllOrderByTitle();
}
