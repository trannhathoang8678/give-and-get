package fpt.se1601.giveandget.service;

import fpt.se1601.giveandget.controller.request.DonationRequest;
import fpt.se1601.giveandget.reponsitory.*;
import fpt.se1601.giveandget.reponsitory.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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
    UserRepository userRepository;

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

    public int deleteDonationType(String name) {
        try {
            return donationTypeRepository.deleteByName(name);
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
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

    public int deleteArea(String name) {
        try {
            return areaRepository.deleteByName(name);
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    public List<AreaEntity> findAreas() {
        try {
            return areaRepository.findAll();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
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
            donationRepository.deleteById(id);
            return "Delete success";
        } catch (Exception e) {
            e.printStackTrace();
            return "Delete failed";
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

            RelationshipEntity relationshipEntity = new RelationshipEntity(new UserEntity(userId), new DonationEntity(donationId), isDonor);
            return relationshipRepository.save(relationshipEntity);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public RelationshipEntity addRelationship(int userId, int donationId) {
        try {

            RelationshipEntity relationshipEntity = new RelationshipEntity(new UserEntity(userId), new DonationEntity(donationId));
            return relationshipRepository.save(relationshipEntity);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public RelationshipEntity updateRelationship(int id, int userId, int donationId, short isDonor) {
        try {
            RelationshipEntity relationshipEntity = new RelationshipEntity(id, new UserEntity(userId), new DonationEntity(donationId), isDonor);
            return relationshipRepository.save(relationshipEntity);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<DonationEntity> getDonationsByTypeInOrder(int typeId, Pageable pageable) {
        try {
            return donationRepository.findByDonationType(new DonationTypeEntity(typeId), pageable);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<DonationEntity> getDonationsByAreaInOrder(int areaId, Pageable pageable) {
        try {
            return donationRepository.findByArea(new AreaEntity(areaId), pageable);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<DonationEntity> getDonationsRelatedToUser(int userId) {
        try {
            List<DonationEntity> donationEntities = new ArrayList<>();
            for (RelationshipEntity relationshipEntity : relationshipRepository.findByUser(new UserEntity(userId)))
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

    public int getNumberDonations() {
        try {
            return (int) donationRepository.count();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }
}
