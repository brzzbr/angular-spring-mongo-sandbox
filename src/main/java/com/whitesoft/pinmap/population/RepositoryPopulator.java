package com.whitesoft.pinmap.population;

import com.whitesoft.pinmap.domain.Pin;
import com.whitesoft.pinmap.domain.User;
import com.whitesoft.pinmap.repositories.PinsRepository;
import com.whitesoft.pinmap.repositories.UsersRepository;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.geo.GeoJsonPoint;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * Created by borisbondarenko on 23.12.15.
 *
 * Populator for mongo repo. Mongo-import works incorrect,
 * so I forced to do such a hack.
 *
 * @author brzzbr
 */
@Component
public class RepositoryPopulator implements InitializingBean{

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private PinsRepository pinsRepository;

    @Override
    public void afterPropertiesSet() throws Exception {

        // if there are already populated users we don't need to proceed
        if(usersRepository.count() > 0) return;

        // test user tzarivan
        User user_1 = new User();
        user_1.setLogin("tzarivan");
        user_1.setFirstName("Tzar");
        user_1.setLastName("Ivan");
        user_1.setEmail("tzar@kremlin.ru");
        user_1.setPassword("827ccb0eea8a706c4c34a16891f84e7b");
        user_1.setLastLogin(new Date());
        user_1 = usersRepository.insert(user_1);

        Pin pin_1_1 = new Pin();
        pin_1_1.setDescription("testPin-1");
        pin_1_1.setLocation(new GeoJsonPoint(-163.341718368527, 58.979056611115));
        pin_1_1.setCreated(new Date());
        pin_1_1.setUser(user_1);
        pinsRepository.insert(pin_1_1);

        Pin pin_1_2 = new Pin();
        pin_1_2.setDescription("testPin-2");
        pin_1_2.setLocation(new GeoJsonPoint(-139.71654875834193 , -1.284906816104069));
        pin_1_2.setCreated(new Date());
        pin_1_2.setUser(user_1);
        pinsRepository.insert(pin_1_2);

        Pin pin_1_3 = new Pin();
        pin_1_3.setDescription("testPin-3");
        pin_1_3.setLocation(new GeoJsonPoint(33.65962669212195 , -87.27739778850307));
        pin_1_3.setCreated(new Date());
        pin_1_3.setUser(user_1);
        pinsRepository.insert(pin_1_3);

        Pin pin_1_4 = new Pin();
        pin_1_4.setDescription("testPin-4");
        pin_1_4.setLocation(new GeoJsonPoint(8.776460088647042 , 34.05230739727284));
        pin_1_4.setCreated(new Date());
        pin_1_4.setUser(user_1);
        pinsRepository.insert(pin_1_4);

        Pin pin_1_5 = new Pin();
        pin_1_5.setDescription("testPin-5");
        pin_1_5.setLocation(new GeoJsonPoint(-55.37909116752343 , 28.046531409558185));
        pin_1_5.setCreated(new Date());
        pin_1_5.setUser(user_1);
        pinsRepository.insert(pin_1_5);

        // test user johndoe
        User user_2 = new User();
        user_2.setLogin("johndoe");
        user_2.setFirstName("John");
        user_2.setLastName("Doe");
        user_2.setEmail("john@gmail.com");
        user_2.setPassword("827ccb0eea8a706c4c34a16891f84e7b");
        user_2.setLastLogin(new Date());
        user_2 = usersRepository.insert(user_2);

        Pin pin_2_1 = new Pin();
        pin_2_1.setDescription("testPin-6");
        pin_2_1.setLocation(new GeoJsonPoint(133.98993214620953 , -17.612796956109392));
        pin_2_1.setCreated(new Date());
        pin_2_1.setUser(user_2);
        pinsRepository.insert(pin_2_1);

        Pin pin_2_2 = new Pin();
        pin_2_2.setDescription("testPin-7");
        pin_2_2.setLocation(new GeoJsonPoint(-171.0439395038345 , -17.049799811339597));
        pin_2_2.setCreated(new Date());
        pin_2_2.setUser(user_2);
        pinsRepository.insert(pin_2_2);

        Pin pin_2_3 = new Pin();
        pin_2_3.setDescription("testPin-8");
        pin_2_3.setLocation(new GeoJsonPoint(-108.56803032130665 , 36.883067827456614));
        pin_2_3.setCreated(new Date());
        pin_2_3.setUser(user_2);
        pinsRepository.insert(pin_2_3);

        // test user kermitthefrog
        User user_3 = new User();
        user_3.setLogin("kermitthefrog");
        user_3.setFirstName("Kermit");
        user_3.setLastName("Frog");
        user_3.setEmail("kermitthefrog@muppet.com");
        user_3.setPassword("827ccb0eea8a706c4c34a16891f84e7b");
        user_3.setLastLogin(new Date());
        user_3 = usersRepository.insert(user_3);

        Pin pin_3_1 = new Pin();
        pin_3_1.setDescription("testPin-9");
        pin_3_1.setLocation(new GeoJsonPoint(171.38492559118833 , -32.869736261757886));
        pin_3_1.setCreated(new Date());
        pin_3_1.setUser(user_3);
        pinsRepository.insert(pin_3_1);
    }
}
