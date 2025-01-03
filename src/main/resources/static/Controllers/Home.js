angular.module("Home",[])
    .controller("HomeController", function($scope, $http) {
        getCurrentOrder();
        getDataUser();

        function getCurrentOrder () {
            $http({
                method: 'GET',
                url: '/getCurrentOrder'
            }).then(
                function(res) { // success
                    $scope.medicines = res.data;
                },
                function(res) { // error
                    console.log("Error: " + res.status + " : " + res.data);
                }
            );
        }

        function getDataUser() {
            $http({
                method: 'GET',
                url: '/getDataUser'
            }).then(
                function (res) { // success
                    $scope.ur = res.data.roles[0];
                },
                function (res) { // error
                    console.log("Error: " + res.status + " : " + res.data);
                }
            );
        }
    });