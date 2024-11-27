package com.bezkoder.spring.datajpa.service;
import java.util.List;
import org.springframework.stereotype.Component;
import com.bezkoder.spring.datajpa.model.Tutorial;

@Component
public interface TutorialService {
	
	Tutorial postTutorial(Tutorial params);
	void updateTutorial(Tutorial params, long id);
	Tutorial getTutorial(long id);
	List<Tutorial> getAll();
	void deleteTutorial(long id);
	void deleteAll();
	List<Tutorial> findByPublished(boolean published);
	List<Tutorial> findByTitleContainingNew(String title);
}
