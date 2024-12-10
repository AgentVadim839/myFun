package com.myvote.vote4me.dao;

import com.myvote.vote4me.entity.Voter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VoterRepository extends JpaRepository<Voter, Integer> {
}
