package com.scrum.service;

import com.scrum.persistence.dao.*;
import com.scrum.persistence.entity.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ScrumCeremonyService {
    protected static Logger logger = LoggerFactory.getLogger(ScrumCeremonyService.class);

    @Autowired
    private RetrospectiveDao retrospectiveDao;
    @Autowired
    private ParticipantDao participantDao;
    @Autowired
    private FeedBackDao feedBackDao;
    @Autowired
    private FeedBackItemDao feedBackItemDao;
    @Autowired
    private FeedBackTypeDao feedBackTypeDao;

    public Retrospective createRetrospective(Retrospective retrospective) throws RetrospectiveException {
        logger.info("Save retrospective with name: " + retrospective.getName());
        retrospectiveDao.save(retrospective);
        addParticipants(retrospective);
        return retrospective;
    }

    public Optional<Retrospective> retrospectiveByName(String retrospectiveName) {
        logger.info("Get retrospective with name: " + retrospectiveName);
        return retrospectiveDao.findByName(retrospectiveName);
    }

    public List<Items> addItems(Retrospective retrospective, List<Items> items) {
        FeedBack feedBack;
        if (retrospective.getFeedBack() != null) {
            feedBack = retrospective.getFeedBack();
        } else {
            feedBack = new FeedBack();
            feedBack.setRetrospectiveId(retrospective.getId());
            feedBackDao.save(feedBack);
        }
        items.forEach(item -> {
            item.setFeedBackId(feedBack.getId());
            saveItem(item);
            logger.info("Created feedBack item with name: " + item.getName());
        });

        return items;
    }

    public void saveItem(Items item) {
        item.setFeedBackType(feedBackTypeDao.findByType(item.getFeedbackType()).get());
        item.setTypeId(item.getFeedBackType().getId().longValue());
        feedBackItemDao.save(item);
    }

    public List<Retrospective> retrospectives() {
        logger.info("Get all the retrospectives");
        List<Retrospective> retrospectives = retrospectiveDao.findAll();
        retrospectives.forEach(retrospective -> {
            if (retrospective.getParticipantList() != null && !retrospective.getParticipantList().isEmpty()) {
                retrospective.setParticipants(retrospective.getParticipantList().stream().map(Participant::getName)
                        .collect(Collectors.toList()));
            } else {
                retrospective.setParticipants(new ArrayList<>());
            }
            if (retrospective.getFeedBack() != null && retrospective.getFeedBack().getItems() != null &&
                    !retrospective.getFeedBack().getItems().isEmpty()) {
                retrospective.getFeedBack().getItems().forEach(item -> {
                    item.setFeedbackType(feedBackTypeDao.findById(item.getTypeId().intValue()).get().getType());
                });
            }
        });
        return retrospectiveDao.findAll();
    }

    private void addParticipants(Retrospective retrospective) {
        for (String p : retrospective.getParticipants()) {
            Participant participant = new Participant();
            participant.setName(p.trim().toLowerCase());
            participant.setRetrospectiveId(retrospective.getId());
            participantDao.save(participant);
            retrospective.getParticipantList().add(participant);
            logger.info("Adding new participant with name: " + participant.getName());
        }
    }
}
