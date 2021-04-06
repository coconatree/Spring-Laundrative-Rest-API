package com.laundrative_v2.app;

import lombok.AllArgsConstructor;
import org.springframework.context.ApplicationContext;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.session.web.http.DefaultCookieSerializer;
import org.springframework.stereotype.Component;

//TODO
// What it is this doing ??

@Component
@AllArgsConstructor
public class SameSiteCookieInjector
{
    private final ApplicationContext applicationContext;

    @EventListener
    public void onApplicationEvent(ContextRefreshedEvent event)
    {
        DefaultCookieSerializer cookieSerializer = applicationContext.getBean(DefaultCookieSerializer.class);
        cookieSerializer.setSameSite("strict");
    }
}