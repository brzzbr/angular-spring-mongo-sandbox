/**
 * Created by borisbondarenko on 23.12.15.
 */
(function () {

    angular.module('pinmap')
        .service('pinService', ['$http', '$resource', pinService]);

    function pinService($http, $resource) {

        var pinResource = $resource('/api/mypins');

        return {
            getMyPins: getMyPins,
            addPin: addPin
        };

        function getMyPins() {
            return pinResource.get().$promise
                .then(function (pins) {
                    return pins;
                });
        }

        function addPin(pin) {
            return pinResource.save(pin).$promise
                .then(function (result) {
                    return result;
                });
        }
    }
})();
