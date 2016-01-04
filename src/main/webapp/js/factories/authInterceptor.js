/**
 * Created by borisbondarenko on 22.12.15.
 *
 * @description
 * A couple of interceptors to handle with token-based security.
 *
 * @author brzzbr
 */
(function () {

    angular.module('pinmap')
        // simply adds an "x-auth-token" header to each request sent from the client
        .factory('authInterceptor', function ($q, localStorageService) {
            return {
                // Add authorization token to headers
                request: function (config) {
                    config.headers = config.headers || {};
                    var token = localStorageService.get('token');

                    if (token && token.expires && token.expires > new Date().getTime()) {
                        config.headers['x-auth-token'] = token.token;
                    }

                    return config;
                }
            };
        })
        // handles the situation of token expiration
        .factory('authExpiredInterceptor', function ($q, $injector, localStorageService) {
            return {
                responseError: function (response) {
                    // token has expired
                    if (response.status === 401 && (response.data.error == 'Alarm! Access Denied!')) {
                        localStorageService.remove('token');

                        var navigationService = $injector.get('navigationService');
                        navigationService.goToLoginState();

                        return $q.reject(response);
                    }

                    return response;
                }
            };
        });
})();
