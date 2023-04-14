package com.example.board.model.board;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ReplyDto {
    private Long reply_id;
    private Long board_id;
    private String member_id;
    private String content;
    private LocalDateTime created_time;
    private boolean isWriter;
}
