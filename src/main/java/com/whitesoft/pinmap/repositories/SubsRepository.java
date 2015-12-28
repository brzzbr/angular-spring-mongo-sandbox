package com.whitesoft.pinmap.repositories;

import com.whitesoft.pinmap.domain.Sub;
import com.whitesoft.pinmap.domain.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

/**
 * Created by borisbondarenko on 28.12.15.
 *
 * Mongo repository for users' subscriptions
 *
 * @author brzzbr
 */
public interface SubsRepository extends MongoRepository<Sub, String>{

    List<Sub> findBySubscriber(User subscriber);
}
