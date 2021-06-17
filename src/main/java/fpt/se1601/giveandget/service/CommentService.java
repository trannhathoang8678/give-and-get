package fpt.se1601.giveandget.service;

import fpt.se1601.giveandget.reponsitory.CommentRepository;
import fpt.se1601.giveandget.reponsitory.DonationRepository;
import fpt.se1601.giveandget.reponsitory.UserRepository;
import fpt.se1601.giveandget.reponsitory.entity.CommentEntity;
import fpt.se1601.giveandget.reponsitory.entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CommentService {
    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private DonationRepository donationRepository;

    public String save(CommentEntity commentEntity) {
        UserEntity userEntity = userRepository.findOneByEmail(commentEntity.getUserEntity().getEmail());
        if (userEntity == null)
            return "No such user!";

        if (commentEntity.getUserEntity() != null && commentEntity.getDonationEntity() != null)
            commentRepository.save(commentEntity);

        return "Comment is saved successfully!";
    }

    public void update(CommentEntity newCommentEntity) {
        Optional<CommentEntity> fetchedOptional = commentRepository.findById(newCommentEntity.getId());
        if (fetchedOptional.isPresent()) {
            commentRepository.updateContent(newCommentEntity.getId(), newCommentEntity.getContent());
        }
    }

    public CommentEntity delete(int commentId) {
        return null;
    }

    public CommentEntity get(int commentId) {
        Optional<CommentEntity> fetchedOptional = commentRepository.findById(commentId);
        if (fetchedOptional.isPresent()) {
            CommentEntity fetchedComment = fetchedOptional.get();
            return fetchedComment;
        } else {
            return null;
        }
    }

    public List<CommentEntity> getAll() {
        return commentRepository.findAll();
    }
}
