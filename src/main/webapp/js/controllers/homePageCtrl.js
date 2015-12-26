/**
 * Created by borisbondarenko on 21.12.15.
 */
(function () {

    angular.module('pinmap')
        .controller('homeCtrl', ['authService', 'pinService', 'userService', homeCtrl]);

    function homeCtrl(authService, pinService, userService) {

        var thisCtrl = this;

        // user firstName
        thisCtrl.firstName = '';
        // adds GoogleMap
        thisCtrl.map = {};
        // pin info window
        thisCtrl.window = {};
        // my pins
        thisCtrl.myPins = [];
        // pin events (like click) handler
        thisCtrl.markerEvents = {};

        // logout method
        thisCtrl.logout = logout;

        initModel();
        function initModel() {

            thisCtrl.map = {
                center: {latitude: 0, longitude: 0},
                zoom: 3,
                pan: 1
            };

            thisCtrl.window = {
                marker: {},
                show: false,
                closeClick: function () {
                    this.show = false;
                },
                options: {},
                title: ''
            };

            thisCtrl.markerEvents = {
                click: function (marker, eventName, model) {
                    thisCtrl.window.model = model;
                    thisCtrl.window.title = model.name;
                    thisCtrl.window.description = model.description;
                    thisCtrl.window.userName = model.userName;
                    thisCtrl.window.created = model.created;
                    thisCtrl.window.show = true;
                }
            };

            userService.getCurrentUser()
                .then(function (result) {

                    thisCtrl.firstName = result.fullName;
                });


            pinService.getMyPins()
                .then(function (response) {

                    response.data.items.forEach(function (pin) {
                        thisCtrl.myPins.push({
                            id: pin.id,
                            latitude: pin.location.y,
                            longitude: pin.location.x,
                            name: pin.name,
                            description: pin.description,
                            userName: pin.userName,
                            created: dateFormat(new Date(pin.created), "default"),
                            options: {
                                labelContent: pin.name,
                                labelAnchor: '0 0',
                                labelVisible: true,
                                labelClass: 'marker-label'
                            }
                        });
                    });
                });
        }

        function logout() {

            authService.logout();
        }
    }

})();