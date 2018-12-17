<?php
error_reporting(0);
require "config.php";
 
$id = $_POST["id"];
$department = $_POST["department"];
$a_time = $_POST["a_time"];
$doctor = $_POST["doctor"];

// $id = "test";
// $department = "test";
// $a_time = "test";
// $doctor = "test";

$sql = "INSERT INTO `Appointment` (`a_id`, `id`, `department`, `a_time`, `doctor`) VALUES (NULL, '$id', '$department', '$a_time', '$doctor');";

if(!mysqli_query($con, $sql)){
    echo '{"message":"Unable to save the data to the database."}';
} 
 
?>
