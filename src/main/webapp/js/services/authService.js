/**
 * Created by borisbondarenko on 21.12.15.
 *
 * @description
 * Service for authentication (login/logout)
 *
 * @author brzzbr
 */
(function () {

    angular.module('pinmap')
        .service('authService', ['$q', 'navigationService', 'xAuthProvider', authService]);

    function authService($q, navigationService, xAuthProvider) {

        return {
            login: login,
            logout: logout,
            authorize: authorize
        };

        function login(credentials, callback) {
            var cb = callback || angular.noop;
            var deferred = $q.defer();

            xAuthProvider.login(credentials).then(function (data) {
                if(data.status == 401){
                    throw "Unauthorized!";
                }
                return cb();
            }).catch(function (err) {
                this.logout();
                deferred.reject(err);
                return cb(err);
            }.bind(this));

            return deferred.promise;
        }

        function logout() {
            xAuthProvider.logout();
            navigationService.goToLoginState();
        }

        function authorize(callback) {
            return callback(xAuthProvider.isAuthorized());
        }
    }
})();
