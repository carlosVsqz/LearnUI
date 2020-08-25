package com.starterkit.springboot.brs;

import com.starterkit.springboot.brs.config.auth.FirebaseService;
import com.starterkit.springboot.brs.config.auth.fake.FakeFirebaseService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("fake-token-granter")
public class BackendApplicationFakeGranterTests {

    @Autowired
    private FirebaseService firebaseService;

    @Test
    public void contextLoads() {
    }

    @Test
    public void firebaseServiceIsNotFake() {
        assertThat(firebaseService).isInstanceOf(FakeFirebaseService.class);
    }

}

