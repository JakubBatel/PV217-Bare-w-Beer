package cz.muni.fi.pv217.barewbeer;

import java.io.IOException;
import java.net.URL;
import com.radcortez.flyway.test.junit.DataSourceInfo;
import com.radcortez.flyway.test.junit.DataSourceProvider;
import org.junit.jupiter.api.extension.ExtensionContext;
import io.smallrye.config.PropertiesConfigSource;
import io.smallrye.config.SmallRyeConfig;
import io.smallrye.config.SmallRyeConfigBuilder;

public class QuarkusDataSourceProvider implements DataSourceProvider {
    @Override
    public DataSourceInfo getDatasourceInfo(final ExtensionContext extensionContext) {
        // We don't have access to the Quarkus CL here, so we cannot use ConfigProvider.getConfig() to retrieve the same configuration.

        URL properties = Thread.currentThread().getContextClassLoader().getResource("application.properties");
        assert properties != null;

        try {
            SmallRyeConfig config = new SmallRyeConfigBuilder()
                .withSources(new PropertiesConfigSource(properties))
                .withProfile("test")
                .build();

            return DataSourceInfo.config(config.getRawValue("quarkus.datasource.jdbc.url"));
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }
}