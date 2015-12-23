/**
 * Created by borisbondarenko on 23.12.15.
 */
(function () {

    angular.module('pinmap')
        .service('pinService', ['$http', pinService]);

    function pinService($http) {

        return {
            getMyPins: getMyPins
        };

        function getMyPins() {
            return $http.get('/api/mypins');
        }
    }
})();
