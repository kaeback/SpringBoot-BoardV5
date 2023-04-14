package com.example.board.service;

import com.example.board.model.board.Reply;
import com.example.board.repository.ReplyMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class ReplyService {

    private final ReplyMapper replyMapper;

    // 리플 등록
    public void saveReply(Reply reply) {
        replyMapper.saveReply(reply);
    }

    // 리플 목록
    public List<Reply> findReplies(Long board_id) {
        return replyMapper.findReplies(board_id);
    }

    // 리플 읽기
    public Reply findReply(Long reply_id) {
        return replyMapper.findReply(reply_id);
    }

    // 리플 수정
    public void updateReply(Reply reply) {
        replyMapper.updateReply(reply);
    }

    // 리플 삭제
    public void removeReply(Long reply_id) {
        replyMapper.removeReply(reply_id);
    }
}
