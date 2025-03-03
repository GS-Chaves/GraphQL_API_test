package org.test.api.graphql_api_test.graphql;

import org.springframework.graphql.data.method.annotation.SubscriptionMapping;
import org.springframework.stereotype.Controller;
import org.test.api.graphql_api_test.dto.UserDTO;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Sinks;

@Controller
public class UserSubscription {
    private final Sinks.Many<UserDTO> userSink = Sinks.many().multicast().onBackpressureBuffer();

    public void publish(UserDTO user) {
        userSink.tryEmitNext(user);
    }

    @SubscriptionMapping
    public Flux<UserDTO> userAdded() {
        return userSink.asFlux();
    }
}
