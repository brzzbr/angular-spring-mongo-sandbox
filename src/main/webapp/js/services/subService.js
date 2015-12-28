/**
 * Created by borisbondarenko on 28.12.15.
 */
(function(){

    angular.module('pinmap')
        .service('subService', ['$resource', subService]);

    function subService($resource) {

        var subResource = $resource('/api/subs');

        return {
            getMySubs: getMySubs,
            subscribe: subscribe,
            unsubscribe: unsubscribe
        };

        function getMySubs() {
            return subResource.get().$promise
                .then(function (subs) {
                    return subs;
                });
        }

        function subscribe(user) {

        }

        function unsubscribe(user) {

        }
    }
})();
