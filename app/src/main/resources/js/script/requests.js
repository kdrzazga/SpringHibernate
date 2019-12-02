function stopApplication() {
    xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (this.readyState == 4) {
            if (this.status == 200) {
                console.log("App stopped")
            }
            if (this.status == 404) {
                var message = "Couldn't stop the app";
                console.error(message);
                alert(message);
            }
        }
    }
    xhttp.open("POST", "http://localhost:8081/stop", true);
    xhttp.send();
}