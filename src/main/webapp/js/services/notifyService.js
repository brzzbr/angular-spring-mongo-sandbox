/**
 * Created by borisbondarenko on 31.12.15.
 */
(function () {

    angular.module('pinmap')
        .factory('notifyService', ['notify', notifyService]);

    function notifyService(notify) {

        function showNotify(message, classes) {
            notify({
                messageTemplate: message,
                classes: classes,
                position: 'right',
                templateUrl: '../templates/notify.html'
            });
        }

        var Service = {};

        Service.success = function (message) {
            message = '<span>' + message + '</span>';
            showNotify(message, 'alert-info');
        };

        Service.error = function (message) {
            message = '<span>' + message + '</span>';
            showNotify(message, 'alert-danger');
        };

        return Service;
    }

})();