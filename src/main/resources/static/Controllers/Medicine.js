angular.module("Medicine",[])
    .controller("MedicineController", function($scope, $http) {
        // getDataUser();
        getAllGroups();

        $scope.initId = function (id) {
            if(id === 'null')
                $scope.medicineId = 0;
            else
               $scope.medicineId = id;
            /*console.log(1);
            else
            console.log(id);*/
            // console.log($scope.medicineId);
        };

        function getAllGroups() {
            $http({
                method: 'GET',
                url: '/getAllGroups'
            }).then(
                function(res) { // success
                    $scope.groups = res.data;
                    // console.log($scope.groups);
                },
                function(res) { // error
                    console.log("Error: " + res.status + " : " + res.data);
                }
            );
        }

        $scope.getDataUser = function () {
            $http({
                method: 'GET',
                url: '/getDataUser'
            }).then(
                function (res) { // success
                    $scope.ur = res.data.roles[0];
                    if($scope.medicineId)
                        $scope.getMedicine($scope.medicineId);
                    // else
                    //     getMedicine(0);
                },
                function (res) { // error
                    console.log("Error: " + res.status + " : " + res.data);
                }
            );
        };

        $scope.getMedicine = function(medicineId) {
            // console.log(medicineId)
            $http({
                method: 'PUT',
                url: '/getMedicine',
                data: angular.toJson(medicineId),
                headers: {
                    'Content-Type': 'application/json'
                }
            }).then(
                function (res) { // success
                    $scope.medicine = res.data;
                    console.log($scope.medicine);
                },
                function (res) { // error
                    console.log("Error: " + res.status + " : " + res.data);
                }
            );
        }


        $scope.deleteMedicine = function () {
            console.log($scope.medicineId)
            $http({
                method: 'POST',
                url: '/deleteMedicine',
                data: angular.toJson($scope.medicineId),
                headers: {
                    'Content-Type': 'application/json'
                }
            }).then(
                function (res) { // success
                    $scope.medicine = res.data;
                    console.log($scope.medicine);
                },
                function (res) { // error
                    console.log("Error: " + res.status + " : " + res.data);
                }
            );
        };



        $scope.saveMedicine = function () {
            $scope.success = false;
            $scope.errorNumberQuantity = false;
            $scope.errorNumberPrice = false;
            $scope.saveMedicineForm = true;

            if($scope.editMedicine.name.$invalid || $scope.editMedicine.img.$invalid || $scope.editMedicine.quantity.$invalid
                || $scope.editMedicine.price.$invalid || !$scope.editMedicine.group.$modelValue) {
                console.log(1);
                return;
            }

            if(+$scope.editMedicine.quantity.$modelValue<1) {
                $scope.errorNumberQuantity = true;
                return;
            }
            if(+$scope.editMedicine.price.$modelValue<1) {
                $scope.errorNumberPrice = true;
                return;
            }

            $scope.medicineDTO = {
                id: $scope.medicineId,
                name: $scope.medicine.name,
                groupId: $scope.medicine.groupId,
                img: $scope.medicine.img,
                quantity: $scope.medicine.quantity,
                price: $scope.medicine.price
            };
            console.log($scope.medicineDTO);

            $http({
                method: 'PUT',
                url: '/saveMedicine',
                data: angular.toJson($scope.medicineDTO),
                headers: {
                    'Content-Type': 'application/json'
                }
            }).then(
                function(res) { // success
                    $scope.medicine = res.data;
                    $scope.success = true;
                    $scope.medicineId = $scope.medicine.id;
                    console.log($scope.medicineId);
                },
                function(res) { // error
                    console.log("Error: " + res.status + " : " + res.data);
                }
            );
        }
    });