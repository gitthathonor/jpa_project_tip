package site.mtcoding.bank.web;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import site.mtcoding.bank.config.auth.LoginUser;
import site.mtcoding.bank.dto.ResponseDto;
import site.mtcoding.bank.dto.UserReqDto.JoinReqDto;
import site.mtcoding.bank.dto.UserRespDto.JoinRespDto;
import site.mtcoding.bank.service.UserService;

@RequestMapping("/api")
@RequiredArgsConstructor
@RestController
public class UserApiController {

    private final UserService userService;

    @GetMapping("/user/session")
    public String userSession(@AuthenticationPrincipal LoginUser loginUser) {
        return "username : " + loginUser.getUsername();
    }

    @PostMapping("/join")
    public ResponseEntity<?> join(@RequestBody JoinReqDto joinReqDto) {
        JoinRespDto joinRespDto = userService.회원가입(joinReqDto);
        return new ResponseEntity<>(new ResponseDto<>("회원가입성공", joinRespDto), HttpStatus.CREATED);
    }
}
