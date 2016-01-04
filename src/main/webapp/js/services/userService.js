/**
 * Created by borisbondarenko on 25.12.15.
 *
 * @description
 * User service. Allows to get user info.
 *
 * @author brzzbr
 */
(function () {

    angular.module('pinmap')
        .service('userService', ['$resource', 'localStorageService', 'notifyService',userService]);

    function userService($resource, localStorageService, notifyService) {

        var userResource = $resource('/api/user/:id', {id: '@id'});

        return {
            getCurrentUser: getCurrentUser,
            getUser: getUser
        };

        /**
         * Simply gets currnt user
         * @returns {*}
         */
        function getCurrentUser() {

            var currentUserName = localStorageService.get('currentUser');
            return getUser(currentUserName);
        }

        /**
         * Gets some user's info
         * @param username
         * @returns {*}
         */
        function getUser(username) {

            return userResource.get({id: username}).$promise
                .then(function (user) {
                    return user;
                }, function(error){

                    console.log(error);
                    notifyService.error('We have got an error during retrieving user info');
                });
        }
    }
})();
