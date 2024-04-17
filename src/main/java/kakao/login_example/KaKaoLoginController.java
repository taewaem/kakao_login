package kakao.login_example;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.HashMap;

@Slf4j
@RestController
@RequestMapping
@RequiredArgsConstructor
public class KaKaoLoginController {


    @Value("${kakao.client_id}")
    private String client_id;

    private final KaKaoService kakaoService;
    @GetMapping("/oauth/kakao")
    public String KaKaoCallBack(@RequestParam("code") String code) throws IOException {
        String accessToken = kakaoService.getAccessTokenFromKaKao(client_id, code);
        HashMap<String, Object> userInfo = kakaoService.getUserInfo(accessToken);
        log.info("id : " + userInfo.get("id"));
        // User 로그인, 또는 회원가입 로직 추가

        return userInfo.toString();
    }
}
