angular.module("Orders",[])
    .controller("OrdersController", function($scope, $http) {

        getDataUser();
        getAllOrders();

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

        function getAllOrders() {
            $http({
                method: 'GET',
                url: '/getAllOrders'
            }).then(
                function (res) { // success
                    $scope.orders = res.data;
                    getAllClients();
                },
                function (res) { // error
                    console.log("Error: " + res.status + " : " + res.data);
                }
            );
        }

        function getAllClients() {
            $http({
                method: 'GET',
                url: '/getAllClients'
            }).then(
                function (res) { // success
                    $scope.clients = res.data;
                    for(var i=0,n=$scope.orders.length;i<n;i++) {
                        var findClient;
                        for(var j=0,k=$scope.clients.length;j<k;j++) {
                            if(+$scope.clients[j].id === +$scope.orders[i].clientId) {
                                findClient = $scope.clients[j];
                                break;
                            }
                        }
                        $scope.orders[i].surname = findClient.surname;
                        $scope.orders[i].email = findClient.email;
                        $scope.orders[i].phone = findClient.phone;
                        // console.log($scope.orders);
                    }
                },
                function (res) { // error
                    console.log("Error: " + res.status + " : " + res.data);
                }
            );
        }

        $scope.findOrders = function() {
            $scope.formOrder=true;
            var orSurname = $scope.order.surname.$modelValue;
            var orEmail = $scope.order.email.$modelValue;
            var sum = $scope.order.total.$modelValue;

            if($scope.order.total.$modelValue && +$scope.order.total.$modelValue < 1)
                return;

            $scope.findDTO = {
                surname: orSurname,
                email: orEmail,
                id: sum
            };

            $http({
                method: 'PUT',
                url: '/findOrders',
                data: angular.toJson($scope.findDTO),
                headers: {
                    'Content-Type': 'application/json'
                }
            }).then(
                function(res) { // success
                    $scope.orders = res.data;
                    // console.log($scope.orders);
                    getAllClients();
                },
                function(res) { // error
                    console.log("Error: " + res.status + " : " + res.data);
                }
            );
        };
    });