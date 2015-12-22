package integration.com.whitesoft.pinmap;

import com.whitesoft.pinmap.config.AppConfiguration;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

/**
 * Created by borisbondarenko on 21.12.15.
 *
 * Class of base integration test. Injects spring context.
 *
 * @author brzzbr
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = {AppConfiguration.class})
public abstract class BaseIntegrationTest {
}
