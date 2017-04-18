'use strict';

/**
 * @ngdoc function
 * @name tpangularApp.controller:MainCtrl
 * @description
 * # MainCtrl
 * Controller of the tpangularApp
 */
angular.module('tpangularApp')
    .controller('MainCtrl', function($scope, $location, $http) {
        // $scope.persons = {};
        // $http.get({
        //         method: 'GET',
        //         url: 'rest/personne/personnes'
        //     })
        //     .success(successCallback)
        //     .error(function(data, status, headers, config) {
        //         // erreur de récupération :(
        //     });

        // var successCallback = function(data, status, headers, config) {
        //     $scope.variableTest = data;
        //     $scope.persons = data;
        // };

        /* ** Menu items' higlight management ** */
        $scope.menuItems = ['Home', 'DeviceList', 'PersonList', 'HomeList'];
        if ($scope.activeMenu == null)
            $scope.activeMenu = $scope.menuItems[0];

        $scope.isActive = function(menu) {
            var active = menu === $scope.activeMenu;
            return menu === $scope.activeMenu;
        };

        $scope.setActive = function(menu) {
            $scope.activeMenu = menu;
        };
        /* ************************************* */
    });


// 'use strict';

// /**
//  * @ngdoc function
//  * @name jeremyApp.controller:AboutCtrl
//  * @description
//  * # AboutCtrl
//  * Controller of the jeremyApp
//  */
// angular.module('jeremyApp')
//     .controller('ListeCtrl', ['$scope', 'LoadBase', function($scope, LoadBase) {
//         $scope.h1 = "Liste";
//         $scope.p = "Allez dans la section List pour voir les différentes listes. Allez dans la section Ajout pour pouvoir ajouter des choses";

//         $scope.personnes = [];

//         var lb = LoadBase.query(function() {
//             for (var i = 0; i < lb.length; i++) {
//                 $scope.personnes.push({ "nom": lb[i]["nom"], "prenom": lb[i]["prenom"], "mail": lb[i]["mail"] });
//             }
//         });
//     }]);

// /**
//  * The factory to get everything
//  */
// angular.module('jeremyApp').factory('LoadBase', ['$resource', function($resource) {
//     return $resource("rest/person/");
// }]);
