/*
* PRIVATE
*/

function readAccount(accountId) {
    var accountName = document.getElementById("account-name");
    var accountShortName = document.getElementById("account-shortname");
    var accountBalance = document.getElementById("account-cash-balance");

    var xhttp = new XMLHttpRequest();

    xhttp.onreadystatechange = function () {
        if (this.readyState === 4) {
            if (this.status === 200) {
                var account = JSON.parse(this.responseText);
                console.log("Show account read accountId=" + account.name);
                accountName.value = account.name;
                accountShortName.value = account.shortname;
                accountBalance.value = account.balance;
            }

            if (this.status === 404) {
                var message = "Couldn't read banks";
                console.error(message);
                alert(message);
            }
        }
    };

    var url = "http://localhost:8080/account/" + accountId;
    console.log("request " + url);
    xhttp.open("GET", url, true);
    xhttp.send();
}

function readBank(bankId) {
    var bankName = document.getElementById("bank-name");
    var bankShortName = document.getElementById("bank-short-name");

    var xhttp = new XMLHttpRequest();

    xhttp.onreadystatechange = function () {
        if (this.readyState === 4) {
            if (this.status === 200) {
                var bank = JSON.parse(this.responseText);
                console.log("Show bank read bankId=" + bank.name);
                bankName.value = bank.name;
                bankShortName.value = bank.shortname;
            }

            if (this.status === 404) {
                var message = "Couldn't read banks";
                console.error(message);
                alert(message);
            }
        }
    };

    var url = "http://localhost:8080/bank/" + bankId;
    console.log("request " + url);
    xhttp.open("GET", url, true);
    xhttp.send();
}

function readBankAccounts(bankId) {
    var accounts = document.getElementById("bank-accounts");

    var xhttp = new XMLHttpRequest();

    xhttp.onreadystatechange = function () {
        if (this.readyState === 4) {
            if (this.status === 200) {
                var associatedAccounts = JSON.parse(this.responseText);
                console.log("Number of accounts read: " + associatedAccounts.length);

                var accountIds = "";
                for (var i = 0; i < associatedAccounts.length; i++) {
                    accountIds += associatedAccounts[i].id + " ";
                }

                accounts.innerText = accountIds;
            }

            if (this.status === 404) {
                var message = "Couldn't read banks";
                console.error(message);
                alert(message);
                return "Error";
            }
        }
    };

    var url = "http://localhost:8080/associatedAccounts/" + bankId;
    console.log("request " + url);
    xhttp.open("GET", url, true);
    xhttp.send();
}

function deleteAccount() {
    var id = document.getElementById("account-selected-account").innerText;
    var xhttp = new XMLHttpRequest();

    xhttp.onreadystatechange = function () {
        if (this.readyState === 4) {
            if (this.status === 200) {
                console.log(this.responseText);
                alert("Account " + id + " deleted");
            }

            if (this.status === 404) {
                var message = "Couldn't delete";
                console.error(message);
                alert(message);
            }
        }
    };

    var url = "http://localhost:8080/account/" + id;
    console.log("request " + url);
    xhttp.open("DELETE", url, true);
    xhttp.send();
}

function bookInternalTransfer(srcAccountId, destAccountId, units) {
    console.log("Booking internal transfer of " + units + " from " + srcAccountId + " to " + destAccountId);
    var xhttp = new XMLHttpRequest();

    var newTransfer = {"srcAccountId": srcAccountId};//TODO

    xhttp.onreadystatechange = function () {
        if (this.readyState === 4) {
            if (this.status === 200) {
                console.log(this.responseText);
                alert("Transfer " + " booked");
            }

            if (this.status === 404) {
                var message = "Couldn't book";
                console.error(message);
                alert(message);
            }
        }
        else alert("Couldn't book");
    };

    var url = "http://localhost:8080/transfer";
    //TODO
    console.log("request " + url);
    xhttp.open("POST", url);
    xhttp.setRequestHeader("Content-Type", "application/json");
    xhttp.send(JSON.stringify(newTransfer));
}

function isTransferInternal(account1, account2) {
    return true;//TODO
}

function saveBank(id, newBankName, newBankShortName) {
    /*TODO*/
    console.log("Updating bank " + id + ", " + newBankName + ", " + newBankShortName)
    var requestUrl = "http://localhost:8080/bank";

    $.ajax({
        url: requestUrl,    //Your api requestUrl
        type: 'PUT',   //type is any HTTP method
        data: {
            "id": id,
            "name": newBankName,
            "shortname": newBankShortName
        },      //Data as js object
        success: function () {
            console.log("Bank " + id + " updated successfully");
        },
        error: function(XMLHttpRequest, textStatus, errorThrown) {
            alert("Status: " + textStatus); alert("Error: " + errorThrown);
        }
    });
}

function saveAccount(id, newAccountName, newAccountShortName, newAccountBalance) {
    console.log("Saving account " + id + ", " + newAccountName + ", " + newAccountShortName + ", " + newAccountBalance);
    //TODO
}

function deleteAccount(id) {
    console.log("Deleting account " + id);
    //TODO: jQuery required
}

/*
* PUBLIC
*/

function onBookTransferClock() {
    var srcAccountId = document.getElementById("transfer-selected-source-account").innerText;
    var destAccountId = document.getElementById("transfer-selected-dest-account").innerText;
    var units = document.getElementById("transfer-amount").value;

    if (isTransferInternal(srcAccountId, destAccountId)) {
        bookInternalTransfer(srcAccountId, destAccountId, units);
    }
}

function onShowAccountClick() {
    var id = document.getElementById("account-selected-account").innerText;
    readAccount(id);
}

function onSaveAccountClick() {
    var id = document.getElementById("account-selected-account").innerText;

    var newAccountName = document.getElementById("account-name").value;
    var newAccountShortName = document.getElementById("account-shortname").value;
    var newAccountBalance = document.getElementById("account-cash-balance").value;

    saveAccount(id, newAccountName, newAccountShortName, newAccountBalance);
}

function onDeleteAccountClick() {
    var id = document.getElementById("account-selected-account").innerText;

    deleteAccount(id);
}

function onCreateAccountClick() {
    console.log("Not implemented yet");//TODO
}

function onShowBankClick() {

    var id = document.getElementById("bank-selected-bank").innerText;
    readBank(id);
    readBankAccounts(id);
}

function onSaveBankClick() {
    var id = document.getElementById("bank-selected-bank").innerText;
    var newBankName = document.getElementById("bank-name").value;
    var newBankShortName = document.getElementById("bank-short-name").value;

    saveBank(id, newBankName, newBankShortName);
}

function onDeleteBankClick() {
    console.log("Not implemented yet");//TODO
}

function onCreateBankClick() {
    console.log("Not implemented yet");//TODO
}

