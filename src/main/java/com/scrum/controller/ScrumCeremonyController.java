package com.scrum.controller;

import com.scrum.persistence.entity.FeedBack;
import com.scrum.persistence.entity.Items;
import com.scrum.persistence.entity.Retrospective;
import com.scrum.service.RetrospectiveException;
import com.scrum.service.ScrumCeremonyService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1")
public class ScrumCeremonyController {
    protected static Logger logger = LoggerFactory.getLogger(ScrumCeremonyController.class);
    @Autowired
    private ScrumCeremonyService scrumCeremonyService;

    @PostMapping(value = "/create", consumes = "application/json", produces = "application/json")
    private ResponseEntity<?> create(@RequestBody Retrospective retrospective) {
        if (StringUtils.isEmpty(retrospective.getName()) ||
                StringUtils.isEmpty(retrospective.getDate()) ||
                (retrospective.getParticipants() != null && retrospective.getParticipants().isEmpty())) {
            logger.error("Required fields missing to create a retrospective");
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        try {
            logger.info("Creating retrospective with name: " + retrospective.getName());
            return new ResponseEntity<>(scrumCeremonyService.createRetrospective(retrospective),
                    HttpStatus.CREATED);
        } catch (RetrospectiveException retrospectiveException) {
            logger.error("Couldn't create retrospective with name: " + retrospective.getName());
            return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
        }
    }

    @PostMapping(value = "/add/items", produces = "application/json;charset=UTF-8")
    private ResponseEntity<?> addItems(@RequestParam String name, @RequestBody List<Items> items) {
        Optional<Retrospective> retrospective = scrumCeremonyService.retrospectiveByName(name);
        if (!retrospective.isPresent()) {
            logger.error("Given retrospective name not found: " + name);
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        if (items.stream().anyMatch(item -> StringUtils.isEmpty(item.getName()))) {
            logger.error("Required field name missing for few items");
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(scrumCeremonyService.addItems(retrospective.get(), items), HttpStatus.OK);
    }

    @PutMapping(value = "/update/item", produces = "application/json;charset=UTF-8")
    private ResponseEntity<?> updateItem(@RequestBody Items item) {
        if (StringUtils.isEmpty(item.getId()) && StringUtils.isEmpty(item.getFeedBackId())) {
            logger.error("Required fields missing for the given item");
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        scrumCeremonyService.saveItem(item);
        return new ResponseEntity<>(item, HttpStatus.OK);
    }

    @GetMapping(value = "/data", produces = "application/json;charset=UTF-8")
    private ResponseEntity<?> data() {
        return new ResponseEntity<>(scrumCeremonyService.retrospectives(), HttpStatus.OK);
    }
}
