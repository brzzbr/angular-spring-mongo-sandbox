/**
 * Created by borisbondarenko on 22.12.15.
 */
(function () {

    angular.module('pinmap')
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
