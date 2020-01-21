function readSingleFile(e) {
    var file = e;//e.target.files[0];
    if (!file) {
        return;
    }
    var reader = new FileReader();
    reader.onload = function (e) {
        return e.target.result;
    };
    reader.readAsText(file);
}

function readPropertiesFile(filePath) {
    var data = readSingleFile(filePath);
    var formattedData = data
    // split the data by line
        .split("\n")
        // split each row into key and property
        .map(row => row.split("="))
        // use reduce to assign key-value pairs to a new object
        // using Array.prototype.reduce
        .reduce((acc, [key, value]) => (acc[key] = value, acc), {});

    return formattedData;
}
