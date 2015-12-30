/**
 * Created by borisbondarenko on 28.12.15.
 */
(function () {

    angular.module('pinmap')
        .service('subService', ['$resource', subService]);

    function subService($resource) {

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

        function getMySubs() {
            return subResource.get().$promise
                .then(function (subs) {
                    return subs;
                }, function(error){

                    console.log(error);
                    notifyService.error('We have got an error while getting your subscriptions');
                });
        }

        function subscribe(user) {
            return subResource.subscribe({user: user}).$promise
                .then(function (result) {
                    return result;
                }, function(error){

                    console.log(error);
                    notifyService.error('We have got an error while subscribing on a new author');
                });
        }

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
