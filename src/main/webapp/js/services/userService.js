/**
 * Created by borisbondarenko on 25.12.15.
 */
(function () {

    angular.module('pinmap')
        .service('userService', ['$resource', 'localStorageService', userService]);

    function userService($resource, localStorageService) {

        var userResource = $resource('/api/user/:id', {id: '@id'});

        return {
            getCurrentUser: getCurrentUser,
            getUser: getUser
        };

        function getCurrentUser() {

            var currentUserName = localStorageService.get('currentUser');
            return getUser(currentUserName);
        }

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
