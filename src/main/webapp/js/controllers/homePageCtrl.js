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
        // user to subscribe
        thisCtrl.userToSubscribe = '';

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
            refreshPins();

            // gets subscriptions for current user
            refreshSubs();

            // subscibe on pins poller (a part of dirty hack)
            pinService
                .pinsPoller(function (response) {

                    response.items.forEach(function (pin) {
                        thisCtrl.myPins.push(pinDtoToPin(pin));
                    });
                });
        }

        function logout() {

            pinService.stopPolling();
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

                    // add a sub to list
                    thisCtrl.mySubs.push(subDtoToSub(result));

                    // todo: get pins for sub and add them to list of pins
                    // refresh all the pins
                    refreshPins();
                });

            thisCtrl.userToSubscribe = '';
        }

        function unsubscribeFromUser(user) {

            subService.unsubscribe(user)
                .then(function (result) {

                    // get rid of unsubscribed sub
                    thisCtrl.mySubs = thisCtrl.mySubs
                        .filter(function (item) {

                            return item.author !== user;
                        });

                    // get rid of pins of unsubscribed author
                    thisCtrl.myPins = thisCtrl.myPins
                        .filter(function (item) {

                            return item.userName !== user;
                        });
                });
        }

        /**
         * function for conversion pin from server to front pin
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

        /**
         * function fot conversion from server to front sub
         * @param sub
         */
        function subDtoToSub(sub) {

            return {
                id: sub.id,
                author: sub.author,
                subscriber: sub.subscriber,
                since: dateFormat(new Date(sub.since), 'default')
            };
        }

        function refreshPins() {

            pinService.getMyPins()
                .then(function (response) {

                    thisCtrl.myPins = [];
                    response.items.forEach(function (pin) {
                        thisCtrl.myPins.push(pinDtoToPin(pin));
                    });
                });
        }

        function refreshSubs() {

            subService.getMySubs()
                .then(function (response) {

                    thisCtrl.mySubs = [];
                    response.items.forEach(function (sub) {
                        thisCtrl.mySubs.push(subDtoToSub(sub));
                    });
                });
        }
    }

})();