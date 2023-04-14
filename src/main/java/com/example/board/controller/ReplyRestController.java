package com.example.board.controller;

import com.example.board.model.board.Reply;
import com.example.board.model.board.ReplyDto;
import com.example.board.model.member.Member;
import com.example.board.service.ReplyService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("reply")
@RestController
public class ReplyRestController {

    private final ReplyService replyService;

    // 리플 등록
    @PostMapping("{board_id}")
    public ResponseEntity<String> writeReply(@PathVariable Long board_id,
                                             @ModelAttribute Reply reply,
                                             @SessionAttribute("loginMember")Member loginMember) {
        log.info("reply: {}", reply);
        reply.setMember_id(loginMember.getMember_id());

        replyService.saveReply(reply);

        return ResponseEntity.ok("등록 성공");
    }

    // 리플 읽기
    @GetMapping("{board_id}/{reply_id}")
    public ResponseEntity<Reply> findReply(@PathVariable Long board_id,
                                           @PathVariable Long reply_id) {

        Reply reply = replyService.findReply(reply_id);

        return ResponseEntity.ok(reply);
    }

    // 리플 목록
    @GetMapping("{board_id}")
    public ResponseEntity<List<ReplyDto>> findReplies(@SessionAttribute("loginMember") Member loginMember,
                                                   @PathVariable Long board_id) {
        List<Reply> replies = replyService.findReplies(board_id);
        List<ReplyDto> replyDtos = new ArrayList<>();
        if (replies != null && replies.size() > 0) {
            for (Reply reply : replies) {
                ReplyDto replyDto = Reply.toDto(reply);
                if (reply.getMember_id().equals(loginMember.getMember_id())) {
                    replyDto.setWriter(true);
                }
                replyDtos.add(replyDto);
            }
        }
        return ResponseEntity.ok(replyDtos);
    }

    // 리플 수정
    @PutMapping("{board_id}/{reply_id}")
    public ResponseEntity<Reply> updateReply(@SessionAttribute("loginMember") Member loginMember,
                                             @PathVariable Long board_id,
                                             @PathVariable Long reply_id,
                                             @ModelAttribute Reply reply) {
        log.info("reply: {}", reply);

        // 수정 권한 확인
        Reply findReply = replyService.findReply(reply_id);
        if (findReply.getMember_id().equals(loginMember.getMember_id())) {
            replyService.updateReply(reply);
        }

        return ResponseEntity.ok(reply);
    }

    // 리플 삭제
    @DeleteMapping("{board_id}/{reply_id}")
    public ResponseEntity<String> removeReply(@SessionAttribute("loginMember") Member loginMember,
                                              @PathVariable Long board_id,
                                              @PathVariable Long reply_id) {

        // 삭제 권한 확인
        Reply findReply = replyService.findReply(reply_id);
        if (findReply.getMember_id().equals(loginMember.getMember_id())) {
            replyService.removeReply(reply_id);
        }

        return ResponseEntity.ok("삭제 성공");
    }

}
