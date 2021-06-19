package fpt.se1601.giveandget.service;

import fpt.se1601.giveandget.reponsitory.CommentRepository;
import fpt.se1601.giveandget.reponsitory.DonationRepository;
import fpt.se1601.giveandget.reponsitory.UserRepository;
import fpt.se1601.giveandget.reponsitory.entity.CommentEntity;
import fpt.se1601.giveandget.reponsitory.entity.DonationEntity;
import fpt.se1601.giveandget.reponsitory.entity.UserEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    private final Logger logger = LoggerFactory.getLogger(CommentService.class);

    public CommentEntity save(CommentEntity commentEntity) {
        Optional<UserEntity> userOptional = userRepository.findById(commentEntity.getUserEntity().getId());
        Optional<DonationEntity> donationOptional = donationRepository.findById(commentEntity.getDonationEntity().getId());

        if (userOptional.isEmpty()) {
            logger.error("No such user with 'USER_id' = " + commentEntity.getUserEntity().getId());
            commentEntity.setUserEntity(null);
            return commentEntity;
        } else {
            commentEntity.setUserEntity(userOptional.get());
        }

        if (donationOptional.isEmpty()) {
            logger.error("No such donation with 'DONATION_id' = " + commentEntity.getDonationEntity().getId());
            commentEntity.setDonationEntity(null);
            return commentEntity;
        } else {
            commentEntity.setDonationEntity(donationOptional.get());
        }

        return commentRepository.save(commentEntity);
    }

    public void update(CommentEntity newCommentEntity) {
        Optional<CommentEntity> fetchedOptional = commentRepository.findById(newCommentEntity.getId());
        if (fetchedOptional.isPresent()) {
            commentRepository.updateContent(newCommentEntity.getId(), newCommentEntity.getContent());
        }
    }

    public CommentEntity delete(int commentId) {
        CommentEntity commentEntity = get(commentId);
        if (commentEntity != null) {
            commentRepository.delete(commentEntity);
        }
        return commentEntity;
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
