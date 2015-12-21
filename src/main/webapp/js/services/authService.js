/**
 * Created by borisbondarenko on 21.12.15.
 */
(function () {

    angular.module('pinmap')
        .service('authService', ['$q', 'xAuthProvider', authService]);

    function authService($q, xAuthProvider) {

        return {
            login: login,
            logout: logout,
            authorize: authorize
        };

        function login(credentials, callback){
            var cb = callback || angular.noop;
            var deferred = $q.defer();

            xAuthProvider.login(credentials).then(function (data) {
                return cb();
            }).catch(function (err) {
                this.logout();
                deferred.reject(err);
                return cb(err);
            }.bind(this));

            return deferred.promise;
        }

        function logout(){
            xAuthProvider.logout();
            $rootScope.previousStateName = undefined;
            $rootScope.previousStateNameParams = undefined;
        }

        function authorize(callback) {

            $http.get('api/user', {
                headers: headers
            }).success(function (data) {
                if(!!data.name)
                    callback(true);
                else
                    callback(false);
            }).error(function () {

                callback(false);
            });

        }
    }
})();
