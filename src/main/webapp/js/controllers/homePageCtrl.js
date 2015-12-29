/**
 * Created by borisbondarenko on 21.12.15.
 */
(function () {

    angular.module('pinmap')
        .controller('homeCtrl', ['$scope', 'authService', 'pinService', 'userService', 'subService', homeCtrl]);

    function homeCtrl($scope, authService, pinService, userService, subService) {

        var thisCtrl = this;

        // user firstName
        thisCtrl.username = '';
        // adds GoogleMap
        thisCtrl.map = {};
        // pin info window
        thisCtrl.pinInfo = {};
        // add pin window
        thisCtrl.addPin = {};
        // my pins
        thisCtrl.myPins = [];
        // my subscriptions
        thisCtrl.mySubs = [];
        // pin events (like click) handler
        thisCtrl.markerEvents = {};

        // logout method
        thisCtrl.logout = logout;
        // add new pin method
        thisCtrl.addNewPin = addNewPin;
        // subscribe method
        thisCtrl.subscribeOnUser = subscribeOnUser;
        // unsubscribe method
        thisCtrl.unsubscribeFromUser = unsubscribeFromUser;

        initModel();
        function initModel() {

            // instance of gmap
            thisCtrl.map = {
                center: {latitude: 0, longitude: 0},
                zoom: 3,
                pan: 1,
                control: {},
                options: {
                    disableDoubleClickZoom: true
                },
                mapEvents: {
                    dblclick: function (mapModel, eventName, args) {

                        var latLng = args[0].latLng;
                        thisCtrl.addPin.model = {
                            latitude: latLng.lat(),
                            longitude: latLng.lng()
                        };
                        thisCtrl.addPin.pinTitle = 'Azaza title!';
                        thisCtrl.addPin.pinDescription = '';
                        thisCtrl.addPin.show = true;
                        // from documentation: scope apply required because
                        // this event handler is outside of the angular domain
                        $scope.$evalAsync();
                    }
                }
            };

            // instance of pin info window
            thisCtrl.pinInfo = {
                show: false,
                closeClick: function () {
                    this.show = false;
                },
                options: {},
                title: ''
            };

            // instance of add pin window
            thisCtrl.addPin = {
                show: false,
                closeClick: function () {
                    this.show = false
                },
                options: {},
                title: 'Add new pin!',
                pinTitle: '',
                pinDescription: ''
            };

            // instance of pin event handler
            thisCtrl.markerEvents = {
                click: function (marker, eventName, model) {
                    thisCtrl.pinInfo.model = model;
                    thisCtrl.pinInfo.title = model.name;
                    thisCtrl.pinInfo.description = model.description;
                    thisCtrl.pinInfo.userName = model.userName;
                    thisCtrl.pinInfo.created = model.created;
                    thisCtrl.pinInfo.show = true;
                }
            };

            // gets current user for userService
            userService.getCurrentUser()
                .then(function (result) {

                    thisCtrl.username = result.username;
                });

            // gets pins for current user
            pinService.getMyPins()
                .then(function (response) {

                    response.items.forEach(function (pin) {
                        thisCtrl.myPins.push(pinDtoToPin(pin));
                    });
                });

            // gets subscriptions for current user
            subService.getMySubs()
                .then(function (response) {

                    response.items.forEach(function (sub) {
                        thisCtrl.mySubs.push({
                            id: sub.id,
                            author: sub.author,
                            subscriber: sub.subscriber,
                            since: dateFormat(new Date(sub.since), 'default')
                        });
                    });
                })
        }

        function logout() {

            authService.logout();
        }

        function addNewPin() {

            var pin = {
                description: thisCtrl.addPin.pinDescription,
                name: thisCtrl.addPin.pinTitle,
                location: {
                    "x": thisCtrl.addPin.model.longitude,
                    "y": thisCtrl.addPin.model.latitude
                }
            };

            pinService.addPin(pin)
                .then(function (result) {

                    thisCtrl.myPins.push(pinDtoToPin(result));
                });
            thisCtrl.addPin.show = false;
        }

        function subscribeOnUser(user) {
            subService.subscribe(user)
                .then(function (result) {

                });
        }

        function unsubscribeFromUser(user) {

            subService.unsubscribe(user)
                .then(function (result) {

                    thisCtrl.mySubs = thisCtrl.mySubs
                        .filter(function (item) {

                            return item.author !== user;
                        });
                });
        }

        /**
         * function for conversion pin from server to stored pin
         * @param pin
         */
        function pinDtoToPin(pin) {

            return {
                id: pin.id,
                latitude: pin.location.y,
                longitude: pin.location.x,
                name: pin.name,
                description: pin.description,
                userName: pin.username,
                created: dateFormat(new Date(pin.created), 'default'),
                options: {
                    labelContent: pin.name,
                    labelAnchor: '0 0',
                    labelVisible: true,
                    labelClass: 'marker-label',
                    icon: pin.username === thisCtrl.username ? 'img/greendot.png' : 'img/bluedot.png'
                }
            };
        }
    }

})();