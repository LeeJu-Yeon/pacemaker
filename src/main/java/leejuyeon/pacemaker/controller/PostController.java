package leejuyeon.pacemaker.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/post")
public class PostController {

    // 운동인증게시물 작성 = 헬스장 도착인증
    @PostMapping
    public ResponseEntity<?> addPost() {
        return ResponseEntity.ok("");
    }

    // 운동인증게시물 수정 -> 완료인증 클릭시 자동으로 당일 게시물 조회 & 수정
    @PutMapping("/post/{postId}???")
    public ResponseEntity<?> updatePost() {
        return ResponseEntity.ok("");
    }

}
