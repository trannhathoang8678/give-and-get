package fpt.se1601.giveandget.service;

import fpt.se1601.giveandget.reponsitory.CommentRepository;
import fpt.se1601.giveandget.reponsitory.ReportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReactionService {
    @Autowired
    UserService userService;
    @Autowired
    CommentRepository commentRepository;
    @Autowired
    ReportRepository reportRepository;

}
