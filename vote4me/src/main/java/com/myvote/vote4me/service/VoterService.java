package com.myvote.vote4me.service;

import com.myvote.vote4me.entity.Voter;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface VoterService {
    List<Voter> findAll();

    Voter findById(int theId);

    Voter save(Voter voter);

    void deleteById(int theId);
}
