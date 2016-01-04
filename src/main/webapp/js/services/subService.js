/**
 * Created by borisbondarenko on 28.12.15.
 *
 * @description
 * Service which allows a user to manage his subscribtions
 *
 * @author brzzbr
 */
(function () {

    angular.module('pinmap')
        .service('subService', ['$resource', 'notifyService',subService]);

    function subService($resource, notifyService) {

        var subResource = $resource('/api/subs/:user',
            {
                user: '@user'
            },
            {
                subscribe: {
                    method: 'POST'
                },
                unsubscribe: {
                    method: 'DELETE'
                }
            });

        return {
            getMySubs: getMySubs,
            subscribe: subscribe,
            unsubscribe: unsubscribe
        };

        /**
         * gets all the subscriptions for current user
         * @returns {*}
         */
        function getMySubs() {
            return subResource.get().$promise
                .then(function (subs) {
                    return subs;
                }, function(error){

                    console.log(error);
                    notifyService.error('We have got an error while getting your subscriptions');
                });
        }

        /**
         * allows to subscribe on user
         * @param user
         * @returns {*}
         */
        function subscribe(user) {
            return subResource.subscribe({user: user}).$promise
                .then(function (result) {
                    return result;
                }, function(error){

                    console.log(error);
                    notifyService.error('We have got an error while subscribing on a new author');
                });
        }

        /**
         * allows to unsubscribe from user
         * @param user
         * @returns {*}
         */
        function unsubscribe(user) {
            return subResource.unsubscribe({user: user}).$promise
                .then(function (result) {

                }, function(error){

                    console.log(error);
                    notifyService.error('We have got an error while unsubscribing');
                });
        }
    }
})();
