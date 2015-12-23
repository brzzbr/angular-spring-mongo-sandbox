/**
 * Created by borisbondarenko on 21.12.15.
 */
(function () {

    angular.module('pinmap')
        .controller('homeCtrl', ['$scope', 'authService', 'pinService', homeCtrl]);

    function homeCtrl($scope, authService, pinService) {

        var thisCtrl = this;

        // user firstName
        thisCtrl.firstName = 'stab stab stab';
        // collection of current user's pins
        thisCtrl.myPins = [];
        // logout method
        thisCtrl.logout = logout;

        // adds GoogleMap
        $scope.map = {center: {latitude: 0, longitude: -0}, zoom: 2};

        initModel();
        function initModel() {

            pinService.getMyPins()
                .success(function (response) {

                    console.log(response);
                    response.items.forEach(function (pin) {
                        thisCtrl.myPins.push({
                            id: pin.id,
                            coords: {
                                latitude: pin.location.y,
                                longitude: pin.location.x
                            },
                            description: pin.description,
                            created: pin.created
                        });
                    });
                })
                .error(function (error) {

                });
        }

        function logout() {

            authService.logout();
        }
    }

})();