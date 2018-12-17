<?php
error_reporting(0);
require "config.php";
 
$username = $_POST["username"];
$password = $_POST["password"];
$name = $_POST["name"];
$gender = $_POST["gender"];
 
//$name = "sdf";
//$password = "sdf";
//$email = "sdf@r54";

$sql = "INSERT INTO `Patient` (`id`, `username`, `password`, `name`, `gender`) VALUES (NULL, '$username', '$password', '$name', '$gender');";

if(!mysqli_query($con, $sql)){
    echo '{"message":"Unable to save the data to the database."}';
}
 
?>