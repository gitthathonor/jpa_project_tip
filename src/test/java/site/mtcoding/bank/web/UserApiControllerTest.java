package site.mtcoding.bank.web;

import static org.mockito.Answers.values;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import com.fasterxml.jackson.databind.ObjectMapper;

import site.mtcoding.bank.dto.UserReqDto.JoinReqDto;

@ActiveProfiles("test")
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.MOCK) // RANDOM_PORT로 하면 실제환경 테스트라 격리가 안 됨
public class UserApiControllerTest { // Transactional대신에 sql-truncate를 사용할 것. auto_increment전략 때문에 pk 초기화가 안 돼서

    // 통합 테스트 시에는 무조건 설정해놓고 사용하자
    private static final String APPLICATION_JSON_UTF8 = "application/json; charset=utf-8";
    private static final String APPLICATION_FORM_URLENCODED = "application/x-www-form-urlencoded; charset=utf-8";

    @Autowired
    private MockMvc mvc;

    @Autowired
    private ObjectMapper om;

    @Test
    public void join_test() throws Exception {

        // given
        JoinReqDto joinReqDto = new JoinReqDto();
        joinReqDto.setUsername("ssar");
        joinReqDto.setPassword("1234");
        joinReqDto.setEmail("ssar@nate.com");
        String requestBody = om.writeValueAsString(joinReqDto);

        // when
        ResultActions resultActions = mvc.perform(
                post("/api/join").content(requestBody).contentType(APPLICATION_JSON_UTF8));
        String responseBody = resultActions.andReturn().getResponse().getContentAsString();
        System.out.println("디버그 : " + responseBody);

        // then
        resultActions.andExpect(status().isCreated());
        resultActions.andExpect(jsonPath("$.data.username").value("ssar"));
    }
}
