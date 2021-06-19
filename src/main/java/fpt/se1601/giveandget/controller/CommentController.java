package fpt.se1601.giveandget.controller;

import fpt.se1601.giveandget.reponsitory.CommentRepository;
import fpt.se1601.giveandget.reponsitory.entity.CommentEntity;
import fpt.se1601.giveandget.reponsitory.entity.DonationEntity;
import fpt.se1601.giveandget.reponsitory.entity.UserEntity;
import fpt.se1601.giveandget.service.CommentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.text.html.parser.Entity;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/comments")
public class CommentController {
    @Autowired
    private CommentService commentService;

    Logger logger = LoggerFactory.getLogger(CommentController.class);

    @GetMapping("")
    public List<CommentEntity> getAll() {
        List<CommentEntity> comments = commentService.getAll();
        if (comments != null) {
            logger.info("Fetched all comments");
            return comments;
        } else {
            logger.error("Failed to fetch all comments");
            return null;
        }
    }

    @PostMapping("")
    public void saveComment(@RequestBody CommentEntity commentEntity, HttpServletResponse response) throws IOException {
            commentEntity = commentService.save(commentEntity);

            if (commentEntity.getUserEntity() == null) {
                response.sendError(404, "No such user");
                return;
            }

            if (commentEntity.getDonationEntity() == null) {
                response.sendError(404, "No such donation");
                return;
            }

            response.setStatus(201);
    }

    @PutMapping("/{id}")
    public void editComment(@RequestBody CommentEntity newCommentEntity) {
        if (newCommentEntity != null) {
            commentService.update(newCommentEntity);
            logger.info("Updated comment");
        } else {
            logger.error("Failed to update comment");
        }
    }

    @GetMapping("/{id}")
    public CommentEntity getOne(@PathVariable("id") int id) {
        return commentService.get(id);
    }

    @GetMapping("/test/{id}")
    public CommentEntity getA(@PathVariable("id") int id) {
        CommentEntity commement = new CommentEntity();
        commement.setContent("Alo");
        UserEntity user = new UserEntity();
        user.setEmail("huynhhuy");
        user.setId(12);
        user.setRole("Student");
        commement.setUserEntity(user);
        DonationEntity donation = new DonationEntity();
        donation.setId(13);
        commement.setDonationEntity(donation);
        return commement;
    }
}
