<html>
<body>
<?php

$servername = "localhost";
$username = "root";
$password = "";
$database = "testdb";

// Create connection
$conn = new mysqli($servername, $username, $password, $database);

// Check connection
if ($conn->connect_error) { // -> ist wie der . bei java (z.B.: this.name)
    die("Connection failed: " . $conn->connect_error);
} 
echo "Connected successfully to database: " . $database . "<br/><br/>";
	
$user=$_POST["name"];
$passwd=$_POST["passwd"];
    
//execute a DB query
$sql = "SELECT username, password FROM logins WHERE username = '$user'";
$result = $conn->query($sql);

if ($result->num_rows > 0) {
    // output data of each row
    while($row = $result->fetch_assoc()) {
        echo "<b>username: </b>" . $row["username"] . "<br/>" . " <b>password: </b>" . $row["password"] . "<br>";
    }
} else {

	$sql = "INSERT INTO logins (username, password) VALUES ('$user', '$passwd')";

if ($conn->query($sql) === TRUE) {
    echo "New record created successfully";
} else {
    echo "Error: " . $sql . "<br>" . $conn->error;
}
	
	
}

$conn->close();

?>
</body>
</html>