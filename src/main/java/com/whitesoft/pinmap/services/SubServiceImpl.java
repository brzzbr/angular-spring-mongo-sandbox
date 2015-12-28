package com.whitesoft.pinmap.services;

import com.whitesoft.pinmap.domain.Sub;
import com.whitesoft.pinmap.domain.User;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by borisbondarenko on 28.12.15.
 *
 * Implementation oа subscription service. Allows to get subscriptions,
 * subscribe or unsubscribe on users' pins.
 *
 * @author brzzbr
 */
@Service
public class SubServiceImpl implements SubService {

    @Override
    public List<Sub> getSubs(User user) {
        return null;
    }
}
