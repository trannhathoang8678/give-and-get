package fpt.se1601.giveandget.service;

import fpt.se1601.giveandget.controller.request.DonationRequest;
import fpt.se1601.giveandget.controller.response.CommentResponse;
import fpt.se1601.giveandget.reponsitory.*;
import fpt.se1601.giveandget.reponsitory.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DonationService {
    @Autowired
    DonationRepository donationRepository;
    @Autowired
    DonationTypeRepository donationTypeRepository;
    @Autowired
    AreaRepository areaRepository;
    @Autowired
    RelationshipRepository relationshipRepository;
    @Autowired
    CommentRepository commentRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    ReportRepository reportRepository;

    public DonationTypeEntity addDonationType(String name) {
        try {

            return donationTypeRepository.save(new DonationTypeEntity(name));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public DonationTypeEntity updateDonationType(int id, String name) {
        try {
            return donationTypeRepository.save(new DonationTypeEntity(id, name));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public boolean isDonationTypeHasNameExists(String name) {
        try {
            return donationTypeRepository.existsByName(name);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public String deleteDonationType(int id) {
        try {
            donationTypeRepository.deleteById(id);
            return "Delete donation type success";
        } catch (Exception e) {
            e.printStackTrace();
            return "Delete donation type failed: " + e.getMessage();
        }
    }

    public List<DonationTypeEntity> getDonationType() {
        try {
            return donationTypeRepository.findAll();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

    public List<AreaEntity> getArea() {
        try {
            return areaRepository.findAll();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public AreaEntity addArea(String name) {
        try {
            AreaEntity areaEntity = new AreaEntity(name);
            return areaRepository.save(areaEntity);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public boolean isAreaHasNameExists(String name) {
        try {
            return areaRepository.existsByName(name);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public AreaEntity updateArea(int id, String name) {
        try {

            return areaRepository.save(new AreaEntity(id, name));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public String deleteArea(int id) {
        try {
            areaRepository.deleteById(id);
            return "Delete area success";
        } catch (Exception e) {
            return "Delete area failed" + e.getMessage();
        }
    }

    public DonationEntity addDonation(DonationRequest donationRequest) {
        try {
            DonationEntity donationEntity = new DonationEntity(donationRequest);
            return donationRepository.save(donationEntity);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


    public DonationEntity updateDonation(DonationRequest donationRequest) {
        try {
            DonationEntity donationEntity = new DonationEntity(donationRequest);
            return donationRepository.save(donationEntity);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public String deleteDonation(int id) {
        try {

            List<RelationshipEntity> relationships = relationshipRepository.findByDonationId(id);
            if (relationships != null)
                for (RelationshipEntity relationship : relationships)
                    relationshipRepository.deleteById(relationship.getId());
            List<CommentEntity> comments = commentRepository.findByDonationId(id);
            if (comments != null) {
                for (CommentEntity comment : comments)
                    commentRepository.deleteById(comment.getId());
            }
            List<ReportEntity> reportEntities = reportRepository.findByDonation(new DonationEntity(id));
            if (reportEntities != null)
            {
                for(ReportEntity reportEntity : reportEntities)
                    reportRepository.deleteById(reportEntity.getId());
            }
                donationRepository.deleteById(id);
            return "Delete success";
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<DonationEntity> getDonationsByOrder(Pageable pageable) {
        try {
            return donationRepository.findAll(pageable).getContent();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public RelationshipEntity addRelationship(int userId, int donationId, short isDonor) {
        try {
            if (relationshipRepository.existsRelationship(userId, donationId, isDonor) == 1)
                return null;
            RelationshipEntity relationshipEntity = new RelationshipEntity(new UserEntity(userId), new DonationEntity(donationId), isDonor);
            return relationshipRepository.save(relationshipEntity);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public String deleteSaveRelationship(int userId, int donationId) {
        try {
            relationshipRepository.deleteById(relationshipRepository.findIdOfSaveRelationship(userId, donationId));
            return "Unsave donation success";
        } catch (Exception e) {
            return "Unsave donation faile. Error:" + e.getMessage();
        }
    }


    public List<DonationEntity> getDonationsByTypeInOrder(int typeId, Pageable pageable) {
        try {
            return donationRepository.findByTypeId(typeId, pageable);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<DonationEntity> getDonationsByAreaInOrder(int areaId, Pageable pageable) {
        try {
            return donationRepository.findByAreaId(areaId, pageable);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<DonationEntity> getDonationsRelatedToUser(int userId) {
        try {
            List<DonationEntity> donationEntities = new ArrayList<>();
            for (RelationshipEntity relationshipEntity : relationshipRepository.findByUserId(userId))
                donationEntities.add(relationshipEntity.getDonation());
            return donationEntities;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<DonationEntity> getDonationsHaveName(String name) {
        try {
            return donationRepository.findByNameLike(name);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<CommentResponse> getCommentOfDonation(int donationId) {
        try {
            List<CommentEntity> commentEntities = commentRepository.findByDonationId(donationId);
            List<CommentResponse> commentResponses = new ArrayList<>();
            for (CommentEntity commentEntity : commentEntities) {
                commentResponses.add(new CommentResponse(commentEntity, userRepository.findNameById(commentEntity.getId())));
            }
            return commentResponses;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public int getNumberDonations() {
        try {
            return (int) donationRepository.count();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    public String setReceiveStatusOfDonation(int id) {
        try {
            DonationEntity donation = donationRepository.findOneById(id);
            donation.setReceived(true);
            donationRepository.save(donation);
            return "Set status receive success";
        } catch (Exception e) {
            return "Set status receive failed " + e.getMessage();
        }
    }

    public int getNumberReceivedDonation() {
        try {
            return donationRepository.getNumberReceiveDonation();
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    public List<DonationEntity> getReceivedDonation() {
        try {
            return donationRepository.findByIsReceived(true);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
