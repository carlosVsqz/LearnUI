package com.starterkit.springboot.brs.config.auth.fake;

import com.starterkit.springboot.brs.config.auth.FirebaseService;
import com.starterkit.springboot.brs.config.auth.TokenInfo;
import org.springframework.context.annotation.Profile;
import org.springframework.security.oauth2.common.exceptions.InvalidTokenException;
import org.springframework.stereotype.Service;

import java.util.Map;
@Service
@Profile("fake-token-granter")
public class FakeFirebaseService implements FirebaseService {

    public static final String FAKE_PICTURES_URL = "/fake-pictures/";
    private static final String PICTURE_EXT = ".jpg";
    private final FakeTokenProperties fakeTokenProperties;

    public FakeFirebaseService(FakeTokenProperties fakeTokenProperties) {
        this.fakeTokenProperties = fakeTokenProperties;
    }

    @Override
    public TokenInfo parseToken(String idToken) {
        Map<String, FakeToken> tokens = fakeTokenProperties.getFakeTokens();

        if (!tokens.containsKey(idToken)) {
            throw new InvalidTokenException("Invalid token. See application-fake-token-granter.yml");
        }

        FakeToken fakeToken = tokens.get(idToken);

        return TokenInfo.builder()
                .email(fakeToken.getEmail())
                .name(fakeToken.getName())
                .uid(idToken)
                .pictureUrl(FAKE_PICTURES_URL + fakeToken.getName() + PICTURE_EXT)
                .build();
    }
}
