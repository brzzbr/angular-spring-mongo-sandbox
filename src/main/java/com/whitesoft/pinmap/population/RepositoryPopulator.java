package com.whitesoft.pinmap.population;

import com.whitesoft.pinmap.domain.Pin;
import com.whitesoft.pinmap.domain.Sub;
import com.whitesoft.pinmap.domain.User;
import com.whitesoft.pinmap.repositories.PinsRepository;
import com.whitesoft.pinmap.repositories.SubsRepository;
import com.whitesoft.pinmap.repositories.UsersRepository;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.geo.GeoJsonPoint;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * Created by borisbondarenko on 23.12.15.
 * <p/>
 * Populator for mongo repo. Mongo-import works incorrect,
 * so I forced to do such a hack.
 *
 * @author brzzbr
 */
@Component
public class RepositoryPopulator implements InitializingBean {

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private PinsRepository pinsRepository;

    @Autowired
    private SubsRepository subsRepository;

    private String loremIpsum = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor " +
            "incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco " +
            "laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit " +
            "esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa " +
            "qui officia deserunt mollit anim id est laborum.";

    @Override
    public void afterPropertiesSet() throws Exception {

        // if there are already populated users we don't need to proceed
        if (usersRepository.count() > 0) return;

        // test user tzarivan
        User user_1 = new User();
        user_1.setUsername("tzarivan");
        user_1.setPassword("827ccb0eea8a706c4c34a16891f84e7b");
        user_1.setLastLogin(new Date());
        user_1 = usersRepository.insert(user_1);

        Pin pin_1_1 = new Pin();
        pin_1_1.setName("testPin-1");
        pin_1_1.setDescription(loremIpsum);
        pin_1_1.setLocation(new GeoJsonPoint(-163.341718368527, 58.979056611115));
        pin_1_1.setCreated(new Date());
        pin_1_1.setUser(user_1);
        pinsRepository.insert(pin_1_1);

        Pin pin_1_2 = new Pin();
        pin_1_2.setName("testPin-2");
        pin_1_2.setDescription(loremIpsum);
        pin_1_2.setLocation(new GeoJsonPoint(-139.71654875834193, -1.284906816104069));
        pin_1_2.setCreated(new Date());
        pin_1_2.setUser(user_1);
        pinsRepository.insert(pin_1_2);

        Pin pin_1_3 = new Pin();
        pin_1_3.setName("testPin-3");
        pin_1_3.setDescription(loremIpsum);
        pin_1_3.setLocation(new GeoJsonPoint(33.65962669212195, -87.27739778850307));
        pin_1_3.setCreated(new Date());
        pin_1_3.setUser(user_1);
        pinsRepository.insert(pin_1_3);

        Pin pin_1_4 = new Pin();
        pin_1_4.setName("testPin-4");
        pin_1_4.setDescription(loremIpsum);
        pin_1_4.setLocation(new GeoJsonPoint(8.776460088647042, 34.05230739727284));
        pin_1_4.setCreated(new Date());
        pin_1_4.setUser(user_1);
        pinsRepository.insert(pin_1_4);

        Pin pin_1_5 = new Pin();
        pin_1_5.setName("testPin-5");
        pin_1_5.setDescription(loremIpsum);
        pin_1_5.setLocation(new GeoJsonPoint(-55.37909116752343, 28.046531409558185));
        pin_1_5.setCreated(new Date());
        pin_1_5.setUser(user_1);
        pinsRepository.insert(pin_1_5);

        // test user johndoe
        User user_2 = new User();
        user_2.setUsername("johndoe");
        user_2.setPassword("827ccb0eea8a706c4c34a16891f84e7b");
        user_2.setLastLogin(new Date());
        user_2 = usersRepository.insert(user_2);

        Pin pin_2_1 = new Pin();
        pin_2_1.setName("testPin-6");
        pin_2_1.setDescription(loremIpsum);
        pin_2_1.setLocation(new GeoJsonPoint(133.98993214620953, -17.612796956109392));
        pin_2_1.setCreated(new Date());
        pin_2_1.setUser(user_2);
        pinsRepository.insert(pin_2_1);

        Pin pin_2_2 = new Pin();
        pin_2_2.setName("testPin-7");
        pin_2_2.setDescription(loremIpsum);
        pin_2_2.setLocation(new GeoJsonPoint(-171.0439395038345, -17.049799811339597));
        pin_2_2.setCreated(new Date());
        pin_2_2.setUser(user_2);
        pinsRepository.insert(pin_2_2);

        Pin pin_2_3 = new Pin();
        pin_2_3.setName("testPin-8");
        pin_2_3.setDescription(loremIpsum);
        pin_2_3.setLocation(new GeoJsonPoint(-108.56803032130665, 36.883067827456614));
        pin_2_3.setCreated(new Date());
        pin_2_3.setUser(user_2);
        pinsRepository.insert(pin_2_3);

        // test user kermitthefrog
        User user_3 = new User();
        user_3.setUsername("kermitthefrog");
        user_3.setPassword("827ccb0eea8a706c4c34a16891f84e7b");
        user_3.setLastLogin(new Date());
        user_3 = usersRepository.insert(user_3);

        Pin pin_3_1 = new Pin();
        pin_3_1.setName("testPin-9");
        pin_3_1.setDescription(loremIpsum);
        pin_3_1.setLocation(new GeoJsonPoint(171.38492559118833, -32.869736261757886));
        pin_3_1.setCreated(new Date());
        pin_3_1.setUser(user_3);
        pinsRepository.insert(pin_3_1);

        // user tzarivan is subscribed on johndoe
        Sub user_1_sub_2 = new Sub();
        user_1_sub_2.setAuthor(user_2);
        user_1_sub_2.setSubscriber(user_1);
        user_1_sub_2.setSince(new Date());
        user_1_sub_2.setActive(true);
        subsRepository.insert(user_1_sub_2);

        // user johndoe is subscribed on tzarivan and kermitthefrog
        Sub user_2_sub_1 = new Sub();
        user_2_sub_1.setAuthor(user_1);
        user_2_sub_1.setSubscriber(user_2);
        user_2_sub_1.setSince(new Date());
        user_2_sub_1.setActive(true);
        subsRepository.insert(user_2_sub_1);

        Sub user_2_sub_3 = new Sub();
        user_2_sub_3.setAuthor(user_3);
        user_2_sub_3.setSubscriber(user_2);
        user_2_sub_3.setSince(new Date());
        user_2_sub_3.setActive(true);
        subsRepository.insert(user_2_sub_3);
    }
}
