<?php
error_reporting(0);
require "config.php";
 
$username = $_GET["username"];
$password = $_GET["password"];
 
// $username = "patient3";
// $password = "345";

$sql = "SELECT * FROM `Patient` WHERE `username`='$username' AND `password`='$password';";
 
$result = mysqli_query($con, $sql);

$response = array();
 
while($row = mysqli_fetch_array($result)){
    $response = array("id"=>$row[0],"username"=>$row[1],"password"=>$row[2],"name"=>$row[3],"gender"=>$row[4]);
}
echo json_encode(array("user_data"=>$response));
 
?>