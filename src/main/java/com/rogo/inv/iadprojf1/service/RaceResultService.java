package com.rogo.inv.iadprojf1.service;

import com.rogo.inv.iadprojf1.entity.race.RaceResult;

import java.util.List;

public interface RaceResultService {
    List<RaceResult> findAll();

    RaceResult save(RaceResult entity);

    void delete(RaceResult entity);

    RaceResult findById(int car, int race);

    List<Object[]> getResultTable(int season, String champ);

    Object[] getRaceNews();

    List<Object[]> getRacerProfileTable(int user_id);
}