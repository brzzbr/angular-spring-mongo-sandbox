/**
 * Created by borisbondarenko on 21.12.15.
 */
(function () {

    angular.module('pinmap')
        .service('authService', ['$http', '$window', '$state', authService]);

    function authService($http, $state) {

        var credentials = {};

        return {
            authenticate: authenticate,
            login: login,
            logout: logout,
            redirectToLogin: redirectToLogin
        };

        function authenticate(callback) {
            var headers = credentials ? {
                authorization: "Basic "
                + btoa(credentials.username + ":"
                    + credentials.password)
            } : {};

            $http.get('user', {
                headers: headers
            }).success(function (data) {
                callback && callback(!!data.name);
            }).error(function () {
                callback && callback(false);
            });
        }

        function login(username, password, callback) {
            credentials = {username: username, password: password};
            authenticate(callback);
        }

        function logout() {
            $http.post('logout').success(function (data) {
                redirectToLogin();
            }).error(function () {
                console.log('Logout failed');
            });
        }

        function redirectToLogin() {
            $state.go('login');
        }
    }
})();
