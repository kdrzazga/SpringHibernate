var dictionary = {
        'en' : {
            'bank' : 'Bank',
            'account' : 'Account',
            'transfer-input' : 'Transfer Input',
            'from-account' : 'From Account',
            'to-account' : 'To Account',
            'amount' : 'Amount:',
            'source' : 'Source',
            'destination' : 'Destination',
            'accounts-load-error' : 'Accounts not loaded. Is server running?',
            'bank-name' : 'Bank Name:',
            'bank-short-name' : "Short Name:",
            'book' : 'Book',
            'from' : 'from',
            'to' : 'to',
            'units' : 'units',
            'table-internal' : 'internal',
            'book-error' : 'Couldn\'t book',
            'accounts' : "Accounts",
            'accounts:' : 'Accounts:',
            'show' : 'Show',
            'save-changes' : 'Save Changes',
            'delete-bank' : 'Delete Bank',
            'create-bank' : 'Create New Bank',
            'account-info' : "Account Info",
            'account-id' : 'Account Id:',
            'account-details' : 'Account Details',
            'account-name' : 'Account Name:',
            'cash-balance' : 'Cash Balance:',
            'save-account' : 'Save Account',
            'delete-account' : 'Delete Account',
            'create-account' : 'Create New Account',
            'developer-panel' : 'Developer\'s Panel',
			'stop' : 'Stop',
			'english' : 'English',
			'german' : 'Deutsch',
			'debit-cards' : 'Debit Cards',
			'credit-cards' : 'Credit Cards',
			'credits' : 'Credits',
			'balance' : 'balance',
			'currency' : 'Currency',
			'active' : 'Active',
			'debit' : 'debit',
			'cards' : 'cards',
			'not' : 'not',
			'loaded' : 'loaded',
			'ERROR' : 'ERROR',
			'credit' : 'credit',
			'credit-limit' : 'Credit Limit'
        },

        'de' : {
            'bank' : 'Bank',
            'account' : 'Konto',
            'transfer-input' : 'Transfer Eingabe',
            'from-account' : 'Von Konto',
            'to-account' : 'Zu Konto',
            'amount' : 'Betrag:',
            'source' : 'Quelle',
            'destination' : 'Ziel',
            'accounts-load-error' : 'Die Konten wurde nicht geladen. Lauft das Server?',
            'bank-name' : 'Name der Bank:',
            'short-name' : "Kurz Name:",
            'book' : 'Buchen',
            'from' : 'von',
            'to' : 'zu',
            'units' : 'Betrag',
            'table-internal' : 'intern',
            'book-error' : 'Buchen nicht erfolgreich',
            'accounts' : "Konten",
            'accounts:' : 'Konten:',
            'show' : 'Zeigen',
			'save-changes' : 'Anderungen speichern',
			'delete-bank' : 'Bank Loschen',
			'create-bank' : 'Neue Bank Erzeugen',
			'account-info' : "Konto Info",
			'account-id' : 'Konto Id:',
			'account-details' : 'Konto Details',
			'account-name' : 'Konto Name:',
			'cash-balance' : 'Geld',
			'save-account' : 'Konto spreichern',
			'delete-account' : 'Konto loeschen',
			'create-account' : 'Neue Konto erstellen',
			'developer-panel' : 'Entwickler Panel',
			'stop' : 'Halt',
			'english' : 'English',
			'german' : 'Deutsch',
			'debit-cards' : 'E.C. Karten',
			'credit-cards' : 'Kreditkarten',
			'Credits' : 'Krediten',
			'balance' : 'Kontostand',
			'currency' : 'Waehrung',
			'active' : 'Aktiv',
			'debit' : 'E.C',
			'cards' : 'Karten',
			'not' : 'nicht',
			'loaded' : 'geladen',
			'ERROR' : 'FEHLER',
			'credit' : 'Kredit',
			'credit-limit' : 'Kreditlimit'
        }
    };

function readEnglishProperties() {

}

function translateNodes(lang) {
    $('.lang').each(function (index, element) {
        $(this).text(dictionary[lang][$(this).attr('key')])
    });
}

$(function(){
        $('.translate').click(function(){
            var lang = $(this).attr('id');
            localStorage.setItem("language", lang);
            translateNodes(lang);
        });
    });
