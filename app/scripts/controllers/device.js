'use strict';

/**
 * @ngdoc function
 * @name tpangularApp.controller:DeviceCtrl
 * @description
 * # DeviceCtrl
 * Controller of the tpangularApp
 */
angular.module('tpangularApp')
    //Factory device to use $resource instead $http for the rest request
    .factory('Device', function($resource) {
        return $resource('http://localhost\:8080/devices/:id', { id: '@id' });
    })
    //Controller to get all devices in db
    .controller('DeviceCtrl', function($scope, Device) {
        this.awesomeThings = [
            'HTML5 Boilerplate',
            'AngularJS',
            'Karma'
        ];
        $scope.devices = Device.query();
        console.log($scope.devices)
    })
    //Controller to delete a device from its id
    .controller('DeviceDeleteCtrl', function($scope, $routeParams, $location, Device) {
        $scope.device = Device.get({ id: $routeParams.deviceId }, function() {
            $scope.device.$delete();
        });
        $location.path('/device');
    })
    //Controller to add a device
    .controller('DeviceAddCtrl', function($scope, $routeParams, $location, Device) {
        $scope.add = function(device) {
            var newDevice = new Device;
            newDevice.name = device.name;
            newDevice.avgcons = device.avgcons;
            newDevice.deviceType = device.deviceType;
            if (device.deviceType == 'electronic') {
                newDevice.type = device.type;
            } else {
                newDevice.temperatureMin = device.temperatureMin;
                newDevice.temperatureMax = device.temperatureMax;
            }
            newDevice.$save();
            $location.path('/device');
        };

        $scope.reset = function() {
            $scope.device = angular.copy({});
        };
    });
