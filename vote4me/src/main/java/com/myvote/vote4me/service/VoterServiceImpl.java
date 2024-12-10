package com.myvote.vote4me.service;

import com.myvote.vote4me.dao.VoterRepository;
import com.myvote.vote4me.entity.Voter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VoterServiceImpl implements VoterService {
    private VoterRepository voterRepository;

    @Autowired
    public VoterServiceImpl(VoterRepository voterRepository) {
        this.voterRepository = voterRepository;
    }

    @Override
    public List<Voter> findAll() {
        return voterRepository.findAll();
    }

    @Override
    public Voter findById(int theId) {
        Optional<Voter> result = voterRepository.findById(theId);
        Voter voter = null;
        if(result.isPresent()) {
            voter = result.get();
        } else {
            throw new RuntimeException("Did not find voter id - " + theId);
        }
        return voter;
    }

    @Override
    public Voter save(Voter voter) {
        return voterRepository.save(voter);
    }

    @Override
    public void deleteById(int theId) {
        voterRepository.deleteById(theId);
    }
}
