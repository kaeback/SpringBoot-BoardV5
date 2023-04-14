package com.example.board.repository;

import com.example.board.model.board.Reply;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ReplyMapper {
    void saveReply(Reply reply);
    List<Reply> findReplies(Long board_id);
    Reply findReply(Long reply_id);
    void updateReply(Reply reply);
    void removeReply(Long reply_id);
}
