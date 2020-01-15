function stopApplication() {
    var xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (this.readyState === 4) {
            if (this.status === 200) {
                console.log("App stopped")
            }
            if (this.status === 404) {
                const message = "Couldn't stop the app";
                console.error(message);
                alert(message);
            }
        }
    };
    xhttp.open("POST", "http://localhost:8080/stop", true);
    xhttp.send();
}

function fillTransferSelectedSourceAccount(accountId) {
    document.getElementById("transfer-selected-source-account").innerText = accountId;
}

function fillTransferSelectedDestAccount(accountId) {
    document.getElementById("transfer-selected-dest-account").innerText = accountId;
}

function fillAccountSelectedSourceAccount(accountId) {
    document.getElementById("account-selected-account").innerText = accountId;
    sessionStorage.setItem('accountId', accountId);
}

function fillBankSelectedBank(bankId) {
    document.getElementById("bank-selected-bank").innerText = bankId;
}

function createCmbBox(id, options, onSelectFunction) {
    var accountIdsComboBox = "<div id=\"" + id + "\" class=\"dropdown-content\">";

    for (var i = 0; i < options.length; i++) {
        var _id = options[i].id;

        accountIdsComboBox += "<a href=\"" + "#\"" + " onclick='" + onSelectFunction + "(" + _id + ");'>" + _id + "</a>";
    }
    accountIdsComboBox += "</div>";
    return accountIdsComboBox;
}

function readAccounts() {
    var xhttp = new XMLHttpRequest();

    xhttp.onreadystatechange = function () {
        if (this.readyState === 4) {
            if (this.status === 200) {
            }
            var accountList = JSON.parse(this.responseText);

            document.getElementById("accounts-list").innerHTML = createCmbBox("accounts-list", accountList, "fillAccountSelectedSourceAccount");
            document.getElementById("src-accounts-list").innerHTML = createCmbBox("accounts-list", accountList, "fillTransferSelectedSourceAccount");
            document.getElementById("dest-accounts-list").innerHTML = createCmbBox("accounts-list", accountList, "fillTransferSelectedDestAccount");
        }
        if (this.status === 404) {
            const message = "Couldn't read accounts";
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
        if (this.readyState === 4) {
            if (this.status === 200) {
                var banksList = JSON.parse(this.responseText);

                document.getElementById("banks-list").innerHTML = createCmbBox("banks-list", banksList, "fillBankSelectedBank");
            }
            if (this.status === 404) {
                var message = "Couldn't read banks";
                console.error(message);
                alert(message);
            }
        }
    };
    xhttp.open("GET", "http://localhost:8080/banks", true);
    xhttp.send();
}

function createTransfersTable(transferList) {

    var table = "";

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
    return table;
}

function readTransfers() {
    var xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (this.readyState === 4) {
            if (this.status === 200) {
                console.log("Read transfers " + this.responseText);

                var transferList = JSON.parse(this.responseText);
                document.getElementById("transfers-table").innerHTML = createTransfersTable(transferList);

            }
            if (this.status === 404) {
                const message = "Couldn't read transfers";
                console.error(message);
                alert(message);
            }
        }
    };
    xhttp.open("GET", "http://localhost:8080/transfers", true);
    xhttp.send();
}
