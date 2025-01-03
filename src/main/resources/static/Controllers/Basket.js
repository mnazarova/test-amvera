'use strict';

angular.module("Basket",[])
    .controller("BasketController", function($scope, $http) {
        getDataUser();
        getDataClient();
        getCurrentOrder();

        function getDataUser() {
            $http({
                method: 'GET',
                url: '/getDataUser'
            }).then(
                function (res) { // success
                    $scope.user = res.data;
                    $scope.ur = res.data.roles[0];
                },
                function (res) { // error
                    console.log("Error: " + res.status + " : " + res.data);
                }
            );
        }

        function getDataClient() {
            $http({
                method: 'GET',
                url: '/getDataClient'
            }).then(
                function (res) { // success
                    $scope.client = res.data;
                    // console.log($scope.client);
                },
                function (res) { // error
                    console.log("Error: " + res.status + " : " + res.data);
                }
            );
        }


        /*function getMedicines () {
            $http({
                method: 'GET',
                url: '/getMedicines'
            }).then(
                function(res) { // success
                    $scope.medicines = res.data;
                    $scope.total = 0;
                    /!*$scope.medicines.forEach(function(item, index, array) {
                        console.log(item, index);
                    });*!/
                    for(var i=0, n = $scope.medicines.length ; i<n ; i++) {
                        $scope.total += $scope.medicines[i].quantity * $scope.medicines[i].price;
                        console.log($scope.total);
                    }
                    // console.log($scope.medicines);
                },
                function(res) { // error
                    console.log("Error: " + res.status + " : " + res.data);
                }
            );
        }*/


        function getCurrentOrder () {
            $http({
                method: 'GET',
                url: '/getCurrentOrder'
            }).then(
                function(res) { // success
                    $scope.medicines = res.data;
                    console.log($scope.medicines);

                    $scope.total = 0;
                    /*$scope.medicines.forEach(function(item, index, array) {
                        console.log(item, index);
                    });*/
                    for(var i=0, n = $scope.medicines.length ; i<n ; i++) {
                        $scope.total += $scope.medicines[i].quantity * $scope.medicines[i].price;
                        console.log($scope.total);
                    }
                },
                function(res) { // error
                    console.log("Error: " + res.status + " : " + res.data);
                }
            );
        }

        $scope.minus = function (medicineId) {
            $http({
                method: 'PUT',
                url: '/minus',
                data: angular.toJson(medicineId),
                headers: {
                    'Content-Type': 'application/json'
                }
            }).then(
                function(res) { // success
                    $scope.medicines = res.data;
                    $scope.total = 0;
                    for(var i=0, n = $scope.medicines.length ; i<n ; i++) {
                        $scope.total += $scope.medicines[i].quantity * $scope.medicines[i].price;
                    }
                    // console.log($scope.medicines);
                },
                function(res) { // error
                    console.log("Error: " + res.status + " : " + res.data);
                }
            );
        };



        $scope.plus = function (medicineId) {
            $http({
                method: 'PUT',
                url: '/plus',
                data: angular.toJson(medicineId),
                headers: {
                    'Content-Type': 'application/json'
                }
            }).then(
                function (res) { // success
                    $scope.medicines = res.data;
                    /*$scope.medicines.find(function (i) {
                        if(+i.id === +medicineId)
                            $scope.total +=
                        console.log(i);
                    });*/
                    $scope.total = 0;
                    for (var i=0, n = $scope.medicines.length ; i<n ; i++) {
                        $scope.total += $scope.medicines[i].quantity * $scope.medicines[i].price;
                    }
                    // console.log($scope.medicines);
                },
                function(res) { // error
                    console.log("Error: " + res.status + " : " + res.data);
                }
            );
        }



        $scope.deletePosition = function (medicineId) {
            $http({
                method: 'PUT',
                url: '/deletePosition',
                data: angular.toJson(medicineId),
                headers: {
                    'Content-Type': 'application/json'
                }
            }).then(
                function (res) { // success
                    $scope.medicines = res.data;
                    $scope.total = 0;
                    for (var i=0, n = $scope.medicines.length ; i<n ; i++) {
                        $scope.total += $scope.medicines[i].quantity * $scope.medicines[i].price;
                    }
                    // console.log($scope.medicines);
                },
                function(res) { // error
                    console.log("Error: " + res.status + " : " + res.data);
                }
            );
        };


        $scope.makeOrder = function () {
            $http({
                method: 'POST',
                url: '/makeOrder'
            }).then(
                function (res) { // success
                    getCurrentOrder();
                    /*$scope.medicines = res.data;
                    $scope.total = 0;
                    for (var i=0, n = $scope.medicines.length ; i<n ; i++) {
                        $scope.total += $scope.medicines[i].quantity * $scope.medicines[i].price;
                    }
                    // console.log($scope.medicines);*/
                },
                function(res) { // error
                    console.log("Error: " + res.status + " : " + res.data);
                }
            );
        }


    });