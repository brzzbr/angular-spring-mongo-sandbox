/**
 * Created by borisbondarenko on 31.12.15.
 *
 * @description
 * Notify service. Primitive and simple. Just bubbles the messages.
 *
 * @author brzzbr
 */
(function () {

    angular.module('pinmap')
        .factory('notifyService', ['notify', notifyService]);

    function notifyService(notify) {

        /**
         * simply shows the notif-bubble
         * @param message
         * @param classes
         */
        function showNotify(message, classes) {
            notify({
                messageTemplate: message,
                classes: classes,
                position: 'right',
                templateUrl: '../templates/notify.html'
            });
        }

        var Service = {};

        /**
         * green-way
         * @param message
         */
        Service.success = function (message) {
            message = '<span>' + message + '</span>';
            showNotify(message, 'alert-info');
        };

        /**
         * red-way
         * @param message
         */
        Service.error = function (message) {
            message = '<span>' + message + '</span>';
            showNotify(message, 'alert-danger');
        };

        return Service;
    }

})();