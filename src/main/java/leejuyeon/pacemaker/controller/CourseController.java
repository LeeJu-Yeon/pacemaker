package leejuyeon.pacemaker.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/course")
public class CourseController {

  // 코스 생성
  @PostMapping
  public ResponseEntity<?> addCourse() {
    return ResponseEntity.ok("");
  }

  // 코스 조회
  @GetMapping
  public ResponseEntity<?> getCourses() {
    return ResponseEntity.ok("");
  }

  // 코스 검색 - ES
  @GetMapping("/search")
  public ResponseEntity<?> searchCourses() {
    return ResponseEntity.ok("");
  }

  // 코스 상세조회
  @GetMapping("/{courseId}")
  public ResponseEntity<?> getCourseDetails() {
    return ResponseEntity.ok("");
  }

  // 코스 도전
  @PostMapping("/{courseId}/challenge")
  public ResponseEntity<?> challengeCourse() {
    return ResponseEntity.ok("");
  }

  // 코스 좋아요
  @PostMapping("/{courseId}/like")
  public ResponseEntity<?> likeCourse() {
    return ResponseEntity.ok("");
  }

}
