/**
 * Created by borisbondarenko on 21.12.15.
 */
(function() {

    angular.module('pinmap')
        .controller('homeCtrl', ['authService', homeCtrl]);

    function homeCtrl(authService){

        var thisCtrl = this;

        thisCtrl.logout = logout;

        function logout(){

            authService.logout();
        }
    }

})();