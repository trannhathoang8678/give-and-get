package fpt.se1601.giveandget.service;

import fpt.se1601.giveandget.reponsitory.AreaRepository;
import fpt.se1601.giveandget.reponsitory.DonationRepository;
import fpt.se1601.giveandget.reponsitory.DonationTypeRepository;
import fpt.se1601.giveandget.reponsitory.entity.AreaEntity;
import fpt.se1601.giveandget.reponsitory.entity.DonationTypeEntity;
import org.springframework.beans.factory.annotation.Autowired;

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
            DonationTypeEntity donationTypeEntity = new DonationTypeEntity(name);
            return donationTypeRepository.save(donationTypeEntity);
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
}
