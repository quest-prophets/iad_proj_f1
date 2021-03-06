package com.rogo.inv.iadprojf1.service.impl;
import com.rogo.inv.iadprojf1.entity.AcceptStatus;
import com.rogo.inv.iadprojf1.entity.Car;
import com.rogo.inv.iadprojf1.entity.Team;
import com.rogo.inv.iadprojf1.entity.User;
import com.rogo.inv.iadprojf1.entity.message.Chat;
import com.rogo.inv.iadprojf1.entity.storage.CarcaseStorage;
import com.rogo.inv.iadprojf1.entity.storage.ChassisStorage;
import com.rogo.inv.iadprojf1.entity.storage.ElectronicsStorage;
import com.rogo.inv.iadprojf1.entity.storage.EngineStorage;
import com.rogo.inv.iadprojf1.repository.CarRepository;
import com.rogo.inv.iadprojf1.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service("carService")
public class CarServiceImpl implements CarService {
    @Autowired
    CarRepository repository;

    @Override
    public List<Car> findAll() {
        return repository.findAll();
    }

    @Override
    public Car save(Car entity) {
        return repository.save(entity);
    }

    @Override
    public void delete(Car entity) {
        repository.delete(entity);
    }

    @Override
    public Car findById(int id) {
        return repository.findById(id);
    }

    @Override
    public List<Car> findAllByTeam(Team team) { return repository.findAllByTeam(team); }

    @Override
    public List<Object[]> getConditionTable(int car) { return repository.getConditionTable(car);}

    @Override @Transactional
    public int updCarcase(@Param("carc") CarcaseStorage carc, @Param("car") Integer car) { return repository.updCarcase(carc, car);}

    @Override @Transactional
    public int updChassis(@Param("chass") ChassisStorage chass, @Param("car") Integer car) { return repository.updChassis(chass, car);}

    @Override @Transactional
    public int updEngine(@Param("eng") EngineStorage eng, @Param("car") Integer car) { return repository.updEngine(eng, car);}

    @Override @Transactional
    public int updElectronics(@Param("elec") ElectronicsStorage elec, @Param("car") Integer car) { return repository.updElectronics(elec, car);}

    @Override @Transactional
   public int refuseCar( @Param("car") Integer car) { return  repository.refuseCar(car); }

   @Override
    public List<Car> findAllByTeamAndStatus(Team team, AcceptStatus status) { return repository.findAllByTeamAndStatus(team, status); }

    @Override
    public List<Car> findAllBySender(User sender) { return repository.findAllBySender(sender); }
}