package com.myvote.vote4me.controller;

import com.myvote.vote4me.entity.Voter;
import com.myvote.vote4me.service.VoterService;
import jakarta.annotation.PostConstruct;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.LinkedList;
import java.util.List;

@Controller
public class VoterController {
    private VoterService voterService;
    private List choiceList;
    enum Choices {
        Бондаренко_Максим,
        Дєб,
        Якийсь_Мужик
    }

    @PostConstruct
    private void initLists(){
        choiceList = new LinkedList();
        for(Choices choices : Choices.values()) {
            choiceList.add(choices.name());
        }
    }

    @Autowired
    public VoterController(VoterService voterService) {
        this.voterService = voterService;
    }

    @GetMapping("/vote")
    public String startVote(HttpSession httpSession, Model model) {
        if (httpSession.getAttribute("hasVoted") != null && (boolean) httpSession.getAttribute("hasVoted")) {
            return "alreadyVoted";
        }
        Voter voter = new Voter();
        model.addAttribute("voter", voter);
        model.addAttribute("choices", choiceList);
        return "voting";
    }

    @PostMapping("/processVote")
    public String processVote(@ModelAttribute("voter")Voter voter, HttpSession httpSession) {
        try {
            voterService.save(voter);
            httpSession.setAttribute("hasVoted", true);
            return "redirect:/success";
        } catch (Exception e) {
            System.err.println("Ошибка сохранения голосующего: " + e.getMessage());
            return "error";
        }
    }

    @GetMapping("/success")
    public String showSuccessPage() {
        return "success";
    }

    @GetMapping("/showVoters")
    public String showVoters(Model model) {
        List<Voter> voters = voters = voterService.findAll();
        model.addAttribute("voters", voters);
        return "show-voters";
    }
}
