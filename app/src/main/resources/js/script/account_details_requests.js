function initAccountDetails() {
    var id = sessionStorage.getItem("accountId");
    document.getElementById("account-id").innerText = "" + id + "";

    console.log("Loading debit-, credit-cards, and credits for account " + id);

    loadDebitCards(id);
    loadCreditCards(id);
    loadCredits(id);
}

function createDebitCardsTable() {
    var cardsTableBody = "<tbody>";
    var debitCardList = JSON.parse(this.responseText);
    console.log(debitCardList.length + " debit cards loaded");

    for (var i = 0; i < debitCardList.length; i++) {
        var id = debitCardList[i].id;
        var account = debitCardList[i].account.shortname;
        var bank = debitCardList[i].bank.shortname;
        var currency = debitCardList[i].currency.shortname;
        var balance = debitCardList[i].balance;
        var active = debitCardList[i].active;

        cardsTableBody += "<tr><td class=\"table-td\">" + id
            + "</td><td class=\"table-td\">" + account
            + "</td><td class=\"table-td\">" + bank
            + "</td><td class=\"table-td\">" + balance
            + "</td><td class=\"table-td\">" + currency
            + "</td><td class=\"table-td\">" + active + "</td></tr>"
    }

    cardsTableBody += "</tbody>";
    return cardsTableBody;
}

function loadDebitCards(id) {
    var xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (this.readyState === 4) {
            if (this.status === 200) {
                console.log("Debit cards read " + this.responseText);
                var cardsTableBody = createDebitCardsTable.call(this);
                document.getElementById("debit-cards-content").innerHTML = cardsTableBody;
            }

            if (this.status === 404) {
                var message = "Couldn't read debit cards";
                console.error(message);
                alert(message);
            }
        }
    };
    xhttp.open("GET", "http://localhost:8080/debitcards/" + id, true);
    xhttp.send();
}

function createCreditsCardTable() {
    var cardsTableBody = "<tbody>";
    var creditCardList = JSON.parse(this.responseText);
    console.log(creditCardList.length + " credit cards loaded");

    for (var i = 0; i < creditCardList.length; i++) {
        var id = creditCardList[i].id;
        var account = creditCardList[i].account.shortname;
        var bank = creditCardList[i].bank.shortname;
        var currency = creditCardList[i].currency.shortname;
        var balance = creditCardList[i].balance;
        var creditLimit = creditCardList[i].credit_limit;
        var active = creditCardList[i].active;

        cardsTableBody += "<tr><td class=\"table-td\">" + id
            + "</td><td class=\"table-td\">" + account
            + "</td><td class=\"table-td\">" + bank
            + "</td><td class=\"table-td\">" + balance
            + "</td><td class=\"table-td\">" + currency
            + "</td><td class=\"table-td\">" + creditLimit
            + "</td><td class=\"table-td\">" + active + "</td></tr>"
    }

    cardsTableBody += "</tbody>";
    return cardsTableBody;
}

function loadCreditCards(id) {

    var xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (this.readyState === 4) {
            if (this.status === 200) {
                console.log("Credit cards read " + this.responseText);
                var cardsTableBody = createCreditsCardTable.call(this);
                document.getElementById("credit-cards-content").innerHTML = cardsTableBody;
            }

            if (this.status === 404) {
                var message = "Couldn't read credit cards";
                console.error(message);
                alert(message);
            }
        }
    };
    xhttp.open("GET", "http://localhost:8080/creditcards/" + id, true);
    xhttp.send();
}

function createCreditsTable() {
    var creditsTableBody = "<tbody>";
    var creditList = JSON.parse(this.responseText);
    console.log(creditList.length + " credits loaded");

    for (var i = 0; i < creditList.length; i++) {
        var id = creditList[i].id;
        var account = creditList[i].account.shortname;
        var bank = creditList[i].bank.shortname;
        var amount = creditList[i].amount;
        var currency = creditList[i].currency;

        creditsTableBody += "<tr><td class=\"table-td\">" + id
            + "</td><td class=\"table-td\">" + account
            + "</td><td class=\"table-td\">" + bank
            + "</td><td class=\"table-td\">" + amount
            + "</td><td class=\"table-td\">" + currency + "</td></tr>"
    }

    creditsTableBody += "</tbody>";
    return creditsTableBody;
}

function loadCredits(id) {
    var xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (this.readyState === 4) {
            if (this.status === 200) {
                console.log("Credits read " + this.responseText);
                var creditsTableBody = createCreditsTable.call(this);
                document.getElementById("credits-content").innerHTML = creditsTableBody;
            }

            if (this.status === 404) {
                var message = "Couldn't read credits";
                console.error(message);
                alert(message);
            }
        }
    };
    xhttp.open("GET", "http://localhost:8080/credits/" + id, true);
    xhttp.send();
}
