package com.bezkoder.spring.datajpa.serviceImpl;

import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.bezkoder.spring.datajpa.service.TutorialService;
import com.bezkoder.spring.datajpa.model.Tutorial;
import com.bezkoder.spring.datajpa.repository.TutorialRepository;

import jakarta.transaction.Transactional;

@Service
public class TutorialServiceImpl implements TutorialService{
	
	private final TutorialRepository tutorialRepository;

	public TutorialServiceImpl(final TutorialRepository tutorialRepository) {
		this.tutorialRepository = tutorialRepository;
	}
	@Override
	public Tutorial postTutorial(Tutorial params) {
		long idtut = tutorialRepository.save(params).getId();
		Tutorial tutorial = new Tutorial();
		tutorial.setId(idtut);
		tutorial.setDescription(params.getDescription());
		tutorial.setPublished(params.isPublished());
		tutorial.setTitle(params.getTitle());
		return tutorial;
	}
	@Override
	@Transactional
	public void updateTutorial(Tutorial params, long id) {
		final Tutorial tutorial = tutorialRepository.findById(id).orElseThrow(()->new ResponseStatusException(HttpStatus.BAD_REQUEST));
		
		tutorial.setDescription(params.getDescription());
		tutorial.setPublished(params.isPublished());
		tutorial.setTitle(params.getTitle());
			
		tutorialRepository.save(tutorial);
		
	}

	@Override
	public Tutorial getTutorial(long id) {
		final Tutorial tutorial = tutorialRepository.findById(id).orElseThrow(()->new ResponseStatusException(HttpStatus.BAD_REQUEST));
		return (tutorial);
	}

	@Override
	public List<Tutorial> getAll() {
		return tutorialRepository.findAllOrderByTitle();
	}

	@Override
	public void deleteTutorial(long id) {
		final Tutorial tutorial = tutorialRepository.findById(id).orElseThrow(()->new ResponseStatusException(HttpStatus.BAD_REQUEST));
		tutorialRepository.delete(tutorial);
	}
	
	@Override
	public void deleteAll()
	{
		tutorialRepository.deleteAll();
	}
	
	@Override
	public List<Tutorial> findByPublished(boolean published)
	{
		List <Tutorial> tutorials = tutorialRepository.findByPublished(published);
		return tutorials;
	}
	
	@Override
	public List<Tutorial> findByTitleContainingNew(String title)
	{   
		if (title == null)
		{
			title = "";
		}
        List <Tutorial> tutorials = tutorialRepository.findByTitleContaining(title);
        return tutorials;
	}
}
