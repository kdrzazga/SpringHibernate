var dictionary = {
        'en' : {
            'account' : 'Account',
            'transfer-input' : 'Transfer Input',
            'from-account' : 'From Account',
            'to-account' : 'To Account',
            'amount' : 'Amount:',
            'source' : 'Source',
            'destination' : 'Destination',
            'load-error' : 'Accounts not loaded. Is server running?',
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
            'developer-panel' : 'Developer\'s Panel'
        },

        'de' : {
            'account' : 'Konto',
            'transfer-input' : 'Transfer Eingabe',
            'from-account' : 'Von Konto',
            'to-account' : 'Zu Konto',
            'amount' : 'Betrag:',
            'source' : 'Quelle',
            'destination' : 'Ziel',
            'load-error' : 'Die Konten wurde nicht geladen. Lauft das Server?',
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
			'cash-balance' : 'Geld:',
			'save-account' : 'Konto spreichern',
			'delete-account' : 'Konto loeschen',
			'create-account' : 'Neue Konto erstellen',
			'developer-panel' : 'Entwickler Panel'
        }
    };

    $(function(){
        $('.translate').click(function(){
            var lang = $(this).attr('id');

            $('.lang').each(function(index, element){
                $(this).text(dictionary[lang][$(this).attr('key')])
                });
        });
    });
