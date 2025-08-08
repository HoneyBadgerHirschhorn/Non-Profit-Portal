package org.example.aletheiacares;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReplyService {

    @Autowired
    private ReplyRepository replyRepository;

    public Reply saveReply(Reply reply) {
        List<Reply> matches = replyRepository.findByFirstNameAndLastName(reply.getFirstName(), reply.getLastName());

        if (!matches.isEmpty()) {
            reply.setUser(matches.get(0).getUser());
        }

        return replyRepository.save(reply);
    }
}
