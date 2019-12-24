function stopApplication() {
    var xhttp = new XMLHttpRequest();
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
    };
    xhttp.open("POST", "http://localhost:8080/stop", true);
    xhttp.send();
}

function readAccounts() {
    var xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (this.readyState == 4) {
            if (this.status == 200) {
                console.log("Reading accounts not finished");
            }

            var accountIds = "";
            var accountList = JSON.parse(this.responseText);
            for (var i = 0; i < accountList.length; i++) {
                var id = accountList[i].id;

                accountIds += id + "<br/>";
            }
            document.getElementById("transfer-banks-list").innerHTML = accountIds;
            document.getElementById("transfer-accounts-list").innerHTML = accountIds;
            document.getElementById("accounts-list").innerHTML = accountIds;
        }
        if (this.status == 404) {
            var message = "Couldn't read accounts";
            console.error(message);
            alert(message);
        }
    };
    xhttp.open("GET", "http://localhost:8080/corporate-accounts", true);
    xhttp.send();

}

function readBanks() {
    var xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (this.readyState == 4) {
            if (this.status == 200) {
                console.log("Reading banks not finished");
                document.getElementById("banks-list").innerHTML = this.responseText;
            }
            if (this.status == 404) {
                var message = "Couldn't read banks";
                console.error(message);
                alert(message);
            }
        }
    };
    xhttp.open("GET", "http://localhost:8080/banks", true);
    xhttp.send();

}

function readTransfers() {
    var xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (this.readyState == 4) {
            if (this.status == 200) {
                console.log("Reading transfers")
                console.log(this.responseText);

                var table = "<thead><td class='table-td'>id</td><td class='table-td'>from</td><td class='table-td'>to</td>" +
                    "<td class='table-td'>units</td><td class='table-td'>internal</td></thead>";

                var transferList = JSON.parse(this.responseText);

                for (var i = 0; i < transferList.length; i++) {
                    var id = transferList[i].id;
                    var units = transferList[i].units;
                    var internal = transferList[i].internal;
                    var from = transferList[i].srcAccount.shortname;
                    var to = transferList[i].destAccount.shortname;

                    table += "<tr><td class='table-td'>" + id + "</td><td class='table-td'>" + from
                        + "</td><td class='table-td'>" + to + "</td>" + "</td><td class='table-td'>" + units
                        + "</td><td class='table-td'>" + internal + "</td></tr>";
                }

                document.getElementById("transfers-table").innerHTML = table;

            }
            if (this.status == 404) {
                var message = "Couldn't read transfers";
                console.error(message);
                alert(message);
            }
        }
    };
    xhttp.open("GET", "http://localhost:8080/transfers", true);
    xhttp.send();
}
