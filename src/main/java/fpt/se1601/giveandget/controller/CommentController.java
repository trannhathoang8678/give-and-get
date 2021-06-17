package fpt.se1601.giveandget.controller;

import fpt.se1601.giveandget.reponsitory.CommentRepository;
import fpt.se1601.giveandget.reponsitory.entity.CommentEntity;
import fpt.se1601.giveandget.service.CommentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.swing.text.html.parser.Entity;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/comments")
public class CommentController {
    @Autowired
    private CommentService commentService;

    Logger logger = LoggerFactory.getLogger(CommentController.class);

    @GetMapping("/")
    public List<CommentEntity> getAll() {
        List<CommentEntity> comments = commentService.getAll();
        if (comments != null) {
            logger.info("All comments are fetched successfully");
            return comments;
        } else {
            logger.error("Failed to fetch all comments");
            return null;
        }
    }

    @PostMapping("/")
    public void saveComment(@RequestBody CommentEntity commentEntity) {
        if (commentEntity != null) {
            commentService.save(commentEntity);
            logger.info("Comment has been saved successfully");
        } else {
            logger.error("Failed to save comment");
        }
    }

    @PutMapping("/{id}")
    public void editComment(@RequestBody CommentEntity newCommentEntity) {
        if (newCommentEntity != null) {
            commentService.update(newCommentEntity);
            logger.info("Comment has been update successfully");
        } else {
            logger.error("Failed to update comment");
        }
    }

    @GetMapping("/{id}")
    public CommentEntity getOne(@PathVariable("id") int id) {
        return commentService.get(id);
    }
}
