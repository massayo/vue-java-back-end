package com.bezkoder.spring.datajpa.controller;

import java.util.List;
//import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestParam;

import com.bezkoder.spring.datajpa.model.Tutorial;
import com.bezkoder.spring.datajpa.service.TutorialService;

import lombok.extern.slf4j.Slf4j;

@CrossOrigin(origins = "http://localhost:5173")//porte-du-vue
@RestController
@Slf4j
@RequestMapping("/api")
public class TutorialController {
    private final TutorialService tutorialService;


	public TutorialController(final TutorialService tutorialService){
        this.tutorialService = tutorialService;
    }

  /*@GetMapping("/tutorials")
  public ResponseEntity<List<Tutorial>> getAll() {
	  log.info("Retrieve all tutorials");
	  final List<Tutorial> result = tutorialService.getAll();

	  return new ResponseEntity<>(result, HttpStatus.OK);
  }*/
  @GetMapping("/tutorials")
  public ResponseEntity<List<Tutorial>> getAllTutorialsWithTitle(@RequestParam(required = false) String title) {
	  log.info("Retrieve all tutorials");
	  final List<Tutorial> result = tutorialService.findByTitleContainingNew(title);

	  return new ResponseEntity<>(result, HttpStatus.OK);
  }
  
  @GetMapping("/tutorials/{id}")
  public ResponseEntity<Tutorial> getTutorialById(@PathVariable("id") long id) {
	  Tutorial tutorial = tutorialService.getTutorial(id);
	  return new ResponseEntity<>(tutorial, HttpStatus.OK);
  }

  @PostMapping("/tutorials")
  public ResponseEntity<Tutorial> createTutorial(@RequestBody Tutorial params) {
	  Tutorial tutorial = tutorialService.postTutorial(params);
	  return new ResponseEntity<>(tutorial, HttpStatus.CREATED);
  }

  @PutMapping("/tutorials/{id}")
  public ResponseEntity<HttpStatus> updateTutorial(@PathVariable("id") long id, @RequestBody Tutorial tutorial) {
	  tutorialService.updateTutorial(tutorial, id);
	  return new ResponseEntity<>(HttpStatus.OK);
  }

  @DeleteMapping("/tutorials/{id}")
  public ResponseEntity<HttpStatus> deleteTutorial(@PathVariable("id") long id) {
	  tutorialService.deleteTutorial(id);
	  return new ResponseEntity<>(HttpStatus.OK);
  }

  @DeleteMapping("/tutorials")
  public ResponseEntity<HttpStatus> deleteAllTutorials() {
	  tutorialService.deleteAll();
	  return new ResponseEntity<>(HttpStatus.OK);
  }

  @GetMapping("/tutorials/published/{isPublished}")
  public ResponseEntity<List<Tutorial>> findByPublished(@PathVariable("isPublished") boolean isPublished) {
	  final List<Tutorial> result = tutorialService.findByPublished(isPublished);

	  return new ResponseEntity<>(result, HttpStatus.OK);

  }
}

