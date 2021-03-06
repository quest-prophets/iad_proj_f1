package com.rogo.inv.iadprojf1.service.impl.race;
import com.rogo.inv.iadprojf1.entity.race.RaceResult;
import com.rogo.inv.iadprojf1.repository.raceRepository.RaceResultRepository;
import com.rogo.inv.iadprojf1.service.RaceResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service("raceResultService")
public class RaceResultServiceImpl implements RaceResultService {
    @Autowired
    RaceResultRepository repository;

    @Override
    public List<RaceResult> findAll() {
        return repository.findAll();
    }

    @Override
    public RaceResult save(RaceResult entity) {
        return repository.save(entity);
    }

    @Override
    public void delete(RaceResult entity) {
        repository.delete(entity);
    }

    @Override
    public RaceResult findById(int car, int race) {
        return repository.findById(car, race);
    }

    @Override
    public List<Object[]> getResultTable(int season, String champ) { return repository.getResultTable(season, champ);}

    @Override
    public Object[] getRaceNews() {return repository.getRaceNews();}

    @Override
    public List<Object[]> getRacerProfileTable(int user_id) { return repository.getRacerProfileTable(user_id);}
}