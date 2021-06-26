package fpt.se1601.giveandget.service;

import fpt.se1601.giveandget.controller.request.ReactionRequest;
import fpt.se1601.giveandget.dto.UserInfoForEmail;
import fpt.se1601.giveandget.reponsitory.CommentRepository;
import fpt.se1601.giveandget.reponsitory.RelationshipRepository;
import fpt.se1601.giveandget.reponsitory.ReportRepository;
import fpt.se1601.giveandget.reponsitory.UserRepository;
import fpt.se1601.giveandget.reponsitory.entity.CommentEntity;
import fpt.se1601.giveandget.reponsitory.entity.DonationEntity;
import fpt.se1601.giveandget.reponsitory.entity.ReportEntity;
import fpt.se1601.giveandget.reponsitory.entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReactionService {

    @Autowired
    CommentRepository commentRepository;
    @Autowired
    ReportRepository reportRepository;
    @Autowired
    SendEmailService sendEmailService;
    @Autowired
    UserRepository userRepository;
    @Autowired
    RelationshipRepository relationshipRepository;

    public String addComment(ReactionRequest reactionRequest) {
        try {
            CommentEntity commentEntity = new CommentEntity(reactionRequest.getUserId(), reactionRequest.getDonationId()
                    , reactionRequest.getContent());
            commentRepository.save(commentEntity);
            if (sendNotificationToDonationOwner(reactionRequest.getUserId(), reactionRequest.getDonationId()))
                return "Add comment and send notification success ";
            return "Add comment success but send notification failed ";
        } catch (Exception e) {
            e.printStackTrace();
            return "Add comment failed";
        }
    }

    public boolean sendNotificationToDonationOwner(int userId, int donationId) {
        try {

            sendEmailService.sendEmail("Notification of Give And Get website"
                    , userRepository.findEmailById(relationshipRepository.findUserIdDonorDonation(donationId))
                    , userRepository.findNameById(userId) + "commented in your donation");
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }


    }

    public String deleteComment(int id) {
        try {

            commentRepository.deleteById(id);
            return "Delete comment successfully";
        } catch (Exception e) {
            e.printStackTrace();
            return "Delete comment failed";
        }
    }

    public String addReport(ReactionRequest reactionRequest) {
        try {
            ReportEntity reportEntity = new ReportEntity(new UserEntity(reactionRequest.getUserId()), new DonationEntity(reactionRequest.getDonationId())
                    , reactionRequest.getContent());
            reportRepository.save(reportEntity);
            return "Add report successfully";
        } catch (Exception e) {
            e.printStackTrace();
            return "Add report failed";
        }
    }

    public String replyReport(int id, String replyContent) {
        try {
            ReportEntity report = new ReportEntity(id, replyContent);
            reportRepository.save(report);
            return "Reply to report successfully";
        } catch (Exception e) {
            e.printStackTrace();
            return "Reply to report failed";
        }
    }

    public String deleteReport(int id) {
        try {
            reportRepository.deleteById(id);
            return "Delete report successfully";
        } catch (Exception e) {
            e.printStackTrace();
            return "Delete report failed";
        }
    }

    public List<ReportEntity> getAllReport() {
        try {

            return reportRepository.findAll(Sort.by("created_timestamp").descending()
                    .and(Sort.by("donationId").descending().and(Sort.by("userId").descending())));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public boolean isCommentOfUser(int userId, int commentId) {
        try {
            return (userId == commentRepository.findUserIdById(commentId));
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean isReportOfUser(int userId, int commentId) {
        try {
            return (userId == reportRepository.findUserIdById(commentId));
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
