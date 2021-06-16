package fpt.se1601.giveandget.service;

import fpt.se1601.giveandget.controller.request.DonationRequest;
import fpt.se1601.giveandget.reponsitory.AreaRepository;
import fpt.se1601.giveandget.reponsitory.DonationRepository;
import fpt.se1601.giveandget.reponsitory.DonationTypeRepository;
import fpt.se1601.giveandget.reponsitory.entity.AreaEntity;
import fpt.se1601.giveandget.reponsitory.entity.DonationEntity;
import fpt.se1601.giveandget.reponsitory.entity.DonationTypeEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DonationService {
    @Autowired
    DonationRepository donationRepository;
    @Autowired
    DonationTypeRepository donationTypeRepository;
    @Autowired
    AreaRepository areaRepository;
    public DonationTypeEntity addDonationType(String name)
    {
        try{
            return donationTypeRepository.save(new DonationTypeEntity(name));
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return null;
        }
    }
    public DonationTypeEntity updateDonationType(String name)
    {
        try{
            DonationTypeEntity donationTypeEntity = donationTypeRepository.findByName(name);
            return donationTypeRepository.save(new DonationTypeEntity(name));
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return null;
        }
    }
    public int deleteDonationType(String name)
    {
        try{
            return donationTypeRepository.deleteByName(name);
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return 0;
        }
    }
    public List<DonationTypeEntity>  getDonationType()
    {
        try{
            return donationTypeRepository.findAll();
        }
        catch (Exception e){
            e.printStackTrace();
            return null;
        }

    }
    public AreaEntity addArea(String name)
    {
        try{
            AreaEntity areaEntity = new AreaEntity(name);
            return areaRepository.save(areaEntity);
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return null;
        }
    }
    public AreaEntity updateArea(String name)
    {
        try{
            AreaEntity areaEntity = areaRepository.findByName(name);
            return areaRepository.save(areaEntity);
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return null;
        }
    }
    public int deleteArea(String name)
    {
        try{
            return areaRepository.deleteByName(name);
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return 0;
        }
    }
    public List<AreaEntity> findAreas()
    {
        try{
            return areaRepository.findAll();
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return null;
        }
    }
    public DonationEntity addDonation(DonationRequest donationRequest)
    {
        try{
            DonationEntity donationEntity = new DonationEntity(donationRequest);
            return donationRepository.save(donationEntity);
        }
        catch (Exception e)
        {

            e.printStackTrace();
            return null;
        }
    }
    public DonationEntity updateDonation(DonationRequest donationRequest)
    {
        try{
            DonationEntity donationEntity = new DonationEntity(donationRequest);
            return donationRepository.save(donationEntity);
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return null;
        }
    }
    public String deleteDonation(int id)
    {
        try{
            donationRepository.deleteById(id);
            return "Delete success";
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return "Delete failed";
        }
    }
    public List<DonationEntity> getDonationsByOrder(Sort sort,Pageable pageable)
    {
        try{
            return donationRepository.findAll(sort,pageable);
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return null;
        }
    }


}
