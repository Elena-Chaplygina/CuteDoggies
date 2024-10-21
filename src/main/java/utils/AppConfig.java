package utils;

import org.aeonbits.owner.Config;

@Config.LoadPolicy(Config.LoadType.MERGE)
@Config.Sources({
        "classpath:config.properties"
})

public interface AppConfig extends Config {

    @Key("TOKEN")
    String token();
}
