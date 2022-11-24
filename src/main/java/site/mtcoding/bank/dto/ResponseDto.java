package site.mtcoding.bank.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

// http code = 200(get, put, delete), 201(post, 로그인할 때는 post 요청이어도 200)
@RequiredArgsConstructor
@Getter
public class ResponseDto<T> {
    // 각 변수들은 변경될 일이 있는가 없는가를 생각해가면서 만들어야 한다.
    // Http상태코드가 들어갈 것, 근데 우리만의 것을 안 쓸거면 그냥 필요없다.
    // private final Integer code;
    private final String msg;
    private final T data;
}
