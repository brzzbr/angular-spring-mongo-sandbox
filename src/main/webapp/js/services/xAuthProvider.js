/**
 * Created by borisbondarenko on 22.12.15.
 */
(function () {

    angular.module('pinmap')
        .service('xAuthProvider', ['$http', 'localStorageService', xAuthProvider]);

    function xAuthProvider($http, localStorageService) {

        return {
            login: login,
            logout: logout,
            getToken: getToken,
            hasValidToken: hasValidToken,
            isAuthorized: isAuthorized
        };

        function login(credentials) {
            var data = "username=" + encodeURIComponent(credentials.username) + "&password="
                + encodeURIComponent(credentials.password);
            return $http.post('/api/authenticate', data, {
                headers: {
                    "Content-Type": "application/x-www-form-urlencoded",
                    "Accept": "application/json"
                }
            }).success(function (response) {
                localStorageService.set('token', response);
                localStorageService.set('currentUser', response.token.split(':')[0]);
                localStorageService.set('isAuthorized', true);
                return response;
            });
        }

        function logout() {
            localStorageService.clearAll();
        }

        function getToken() {
            return localStorageService.get('token');
        }

        function isAuthorized() {
            return localStorageService.get('isAuthorized');
        }

        function hasValidToken() {
            var token = this.getToken();
            return token && token.expires && token.expires > new Date().getTime();
        }
    }
})();