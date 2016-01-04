/**
 * Created by borisbondarenko on 23.12.15.
 *
 * @description
 * Core functionality service for manage the pins/
 *
 * @author brzzbr
 */
(function () {

    angular.module('pinmap')
        .service('pinService', ['$resource', 'notifyService',pinService]);

    function pinService($resource, notifyService) {

        var pinResource = $resource('/api/pins');
        var pollerCallback;
        var poll;

        var lastPolling = new Date().getTime();

        return {
            getMyPins: getMyPins,
            addPin: addPin,
            pinsPoller: pinsPoller,
            stopPolling: stopPolling
        };

        /**
         * retrieves all the pins accessible for user
         * @returns {*}
         */
        function getMyPins() {
            return pinResource.get().$promise
                .then(function (pins) {

                    lastPolling = new Date().getTime();
                    return pins;
                }, function(error){

                    console.log(error);
                    notifyService.error('We have got an error while getting your pins');
                });
        }

        /**
         * adds a pin for a user
         * @param pin
         * @returns {*}
         */
        function addPin(pin) {
            return pinResource.save(pin).$promise
                .then(function (result) {
                    return result;
                }, function(error){

                    console.log(error);
                    notifyService.error('We have got an error while adding a new pin');
                });
        }

        /**
         * Due to some technical issues with web-sockets and XAuth
         * I forced to use a dirty hack named "short polling" to retrieve
         * new pins placed by authors of user's subscriptions.
         * @param callback
         */
        function pinsPoller(callback) {

            pollerCallback = callback;
            var poller = function () {
                pinResource.get({fromdate: lastPolling}).$promise
                    .then(function (pins) {

                        lastPolling = new Date().getTime();
                        pollerCallback(pins);
                    }, function(error){

                        console.log(error);
                        notifyService.error('We have got an error while polling new pins. Poller is stopped!');
                        stopPolling();
                    });
            };
            poller();

            poll = setInterval(poller, 5000);
        }

        /**
         * stops this miserable polling stuff
         */
        function stopPolling() {
            clearInterval(poll);
        }
    }
})();
